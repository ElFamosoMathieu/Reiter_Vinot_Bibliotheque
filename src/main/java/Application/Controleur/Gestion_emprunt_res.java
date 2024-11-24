package Application.Controleur;

import Application.ObjetsMetier.*;
import Application.Utilitaire.Etat;
import Application.Utilitaire.OutilsBaseSQL;
import Application.Utilitaire.StatutReservation;
import Application.Utilitaire.StatutEmprunt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Gestion_emprunt_res {

    public Gestion_emprunt_res(){}

    public void reserver(String nom, String titre){
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();

        // Obtenir la date actuelle
        LocalDate dateJour = LocalDate.now();

        // Convertir en format 'YYYY-MM-DD'
        String dateJourFormat = dateJour.toString();

        Usager usager = Usager.e_identifier(nom);
        if (usager != null){
            Oeuvre oeuvre = Oeuvre.e_identifier(titre);
            if (oeuvre != null){
                String query = "INSERT INTO Reservation (titre, nom, dateReservation, statutReservation)\n" +
                        " VALUES ('"+ titre +"', '" + nom + "', '"+ dateJourFormat +"', '"+ StatutReservation.RESERVEE +"')";
                String erreur = "Une erreur s'est produite lors de la réservation !";
                int res = outilsBaseSQL.majSQL(query, erreur);
            }
        }
    }

    public boolean verifierReservation(Usager usager,  Oeuvre oeuvre){
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();

        String query = "SELECT * from Reservation where nom = '" + usager.getNom() + "' and titre = '" + oeuvre.getTitre() + "' and statutreservation = '" + StatutReservation.RESERVEE + "'";
        String erreur = "Une erreur s'est produite lors de la verification de la reservation !";
        ResultSet res = outilsBaseSQL.rechercheSQL(query, erreur);

        try {
            while (res.next()) {
                return true;
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean verifierEmpruntVide(String nom){
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();

        String query = "SELECT count(*) as res from Emprunt where nom = '" + nom + "' and statutemprunt = '" + StatutEmprunt.EN_COURS + "';";
        String erreur = "Une erreur s'est produite lors de la verification des emprunts !";
        ResultSet res = outilsBaseSQL.rechercheSQL(query, erreur);

        boolean retour = true;

        try {
            while (res.next()) {
                int val = res.getInt("res");
                if (val > 0){
                    retour = false;
                }
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return retour;
    }

    public synchronized void emprunter(String nom, String titre){
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        // Obtenir la date actuelle
        LocalDate dateJour = LocalDate.now();

        // Convertir en format 'YYYY-MM-DD'
        String dateJourFormat = dateJour.toString();

        Usager usager = Usager.e_identifier(nom);
        if (usager != null){
            Oeuvre oeuvre = Oeuvre.e_identifier(titre);
            if (oeuvre != null){
                if (this.verifierReservation(usager, oeuvre)){
                    this.annuler(usager.getNom(), oeuvre.getTitre());
                }
                Exemplaire exemplaire = Exemplaire.e_identifier(oeuvre);
                if(exemplaire != null){
                    String query = "INSERT INTO Emprunt (idExemplaire, nom, dateEmprunt, statutEmprunt)\n" +
                            " VALUES ('"+ exemplaire.getId() +"', '" + nom + "', '"+ dateJourFormat +"', '"+ StatutEmprunt.EN_COURS +"')";
                    String erreur = "Une erreur s'est produite lors de l'emprunt !";
                    int res = outilsBaseSQL.majSQL(query, erreur);
                    System.out.println(query);
                    // exemplaire indisponible
                    Gestion_exemplaire gestion_exemplaire = new Gestion_exemplaire();
                    gestion_exemplaire.maj(exemplaire.getId(), Etat.INDISPONIBLE);
                }
            }
        }
    }

    public void annuler(String nom, String titre){
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        Usager usager = Usager.e_identifier(nom);
        if (usager != null){
            String query = "UPDATE Reservation \n" +
                    " SET statutreservation = '" + StatutReservation.NON_RESERVEE + "' \n" +
                    " WHERE titre = '" + titre + "' AND nom = '" + nom + "' AND statutreservation = '" + StatutReservation.RESERVEE + "'";
            String erreur = "Une erreur s'est produite lors de l'annulation de la réservation !";
            int res = outilsBaseSQL.majSQL(query, erreur);
        }
    }

    public void rendre(String nom, int id){
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        Usager usager = Usager.e_identifier(nom);
        if (usager != null){
            Exemplaire exemplaire = Exemplaire.e_identifier(id);
            if (exemplaire != null){
                String query = "UPDATE Emprunt \n" +
                        " SET statutemprunt = '" + StatutEmprunt.TERMINE + "' \n" +
                        " WHERE idExemplaire = '" + id + "' AND nom = '" + nom + "' AND statutemprunt = '" + StatutEmprunt.EN_COURS + "'";
                String erreur = "Une erreur s'est produite lors du rendu !";
                int res = outilsBaseSQL.majSQL(query, erreur);
                // Exemplaire disponible de nouveau
                Gestion_exemplaire gestion_exemplaire = new Gestion_exemplaire();
                gestion_exemplaire.maj(id, Etat.DISPONIBLE);
            }
        }

    }

    public void supprimerExemplaire(int idExemplaire) {
        Exemplaire exemplaire = Exemplaire.e_identifier(idExemplaire);
        if (exemplaire != null){
            OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
            String query = "DELETE FROM Emprunt \n" +
                    " WHERE idExemplaire = '" + idExemplaire + "'";
            String erreur = "Une erreur s'est produite lors de la suppression des emprunts !";
            int res = outilsBaseSQL.majSQL(query, erreur);
        }
    }

    public void supprimerOeuvre(String titre) {
        Oeuvre oeuvre = Oeuvre.e_identifier(titre);
        if (oeuvre != null) {
            OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
            String query = "DELETE FROM Reservation \n" +
                    " WHERE titre = '" + titre + "'";
            String erreur = "Une erreur s'est produite lors de la suppression des réservations !";
            int res = outilsBaseSQL.majSQL(query, erreur);

            query = "SELECT * FROM Exemplaire \n" +
                    " WHERE titre = '" + titre + "'";
            erreur = "Une erreur s'est produite lors de la liste des exemplaires !";
            ResultSet resultSet = outilsBaseSQL.rechercheSQL(query, erreur);
            try {
                while (resultSet.next()) {
                    this.supprimerExemplaire(resultSet.getInt("idExemplaire"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }

            query = "DELETE FROM Exemplaire \n" +
                    " WHERE titre = '" + titre + "'";
            erreur = "Une erreur s'est produite lors de la suppression des exemplaires !";
            res = outilsBaseSQL.majSQL(query, erreur);
        }
    }

    public void supprimerUsager(String nom){
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        Usager usager = Usager.e_identifier(nom);
        if (usager != null) {
            String query = "DELETE FROM Emprunt \n" +
                    " WHERE nom = '" + nom + "'";
            String erreur = "Une erreur s'est produite lors de la suppression des emprunts !";
            int res = outilsBaseSQL.majSQL(query, erreur);
            query = "DELETE FROM Reservation \n" +
                    " WHERE nom = '" + nom + "'";
            erreur = "Une erreur s'est produite lors de la suppression des réservations !";
            res = outilsBaseSQL.majSQL(query, erreur);
        }
    }

    public List<Emprunt> getAllEmprunts() {
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        String query = "SELECT * FROM emprunt WHERE statutemprunt != 'TERMINE'";
        String erreur = "Une erreur s'est produite lors de la recherche de tout reservation !";
        ResultSet res = outilsBaseSQL.rechercheSQL(query,erreur);
        List<Emprunt> emprunts = new ArrayList<>();
        try {
            while (res.next()) {
                String query2 = "SELECT * FROM exemplaire WHERE idexemplaire = '" + res.getString("idexemplaire") + "'";
                ResultSet res2 = outilsBaseSQL.rechercheSQL(query2,"");
                res2.next();
                String query3 = "SELECT * FROM usager WHERE nom = '" + res.getString("nom") + "'";
                ResultSet res3 = outilsBaseSQL.rechercheSQL(query3,"");
                res3.next();
                String query4 = "SELECT * FROM oeuvre WHERE titre = '" + res2.getString("titre") + "'";
                ResultSet res4 = outilsBaseSQL.rechercheSQL(query4,"");
                res4.next();
                Emprunt emprunt = new Emprunt(res.getDate("dateemprunt"), new Usager(res3.getString("nom"),res3.getString("prenom"),res3.getString("mail")),new Exemplaire(res2.getInt("idexemplaire"), Etat.valueOf(res2.getString("etat")),new Oeuvre(res4.getString("titre"),res4.getDate("datepublication"))),StatutEmprunt.valueOf(res.getString("statutemprunt")));
                emprunts.add(emprunt);
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return emprunts;
    }

    public List<Reservation> getAllReservations() {
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        String query = "SELECT * FROM reservation WHERE statutreservation = 'RESERVEE'";
        String erreur = "Une erreur s'est produite lors de la recherche de tout reservation !";
        ResultSet res = outilsBaseSQL.rechercheSQL(query,erreur);
        List<Reservation> reservations = new ArrayList<>();
        try {
            while (res.next()) {
                String query3 = "SELECT * FROM usager WHERE nom = '" + res.getString("nom") + "'";
                ResultSet res3 = outilsBaseSQL.rechercheSQL(query3,"");
                res3.next();
                String query4 = "SELECT * FROM oeuvre WHERE titre = '" + res.getString("titre") + "'";
                ResultSet res4 = outilsBaseSQL.rechercheSQL(query4,"");
                res4.next();
                Reservation reservation = new Reservation(res.getDate("datereservation"), new Oeuvre(res4.getString("titre"),res4.getDate("datepublication")),new Usager(res3.getString("nom"),res3.getString("prenom"),res3.getString("mail")),StatutReservation.valueOf(res.getString("statutreservation")));
                reservations.add(reservation);
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return reservations;
    }
}