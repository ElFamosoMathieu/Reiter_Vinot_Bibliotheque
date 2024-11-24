package Application.Controleur;

import Application.ObjetsMetier.*;
import Application.Utilitaire.OutilsBaseSQL;
import Application.Utilitaire.StatutReservation;
import Application.Utilitaire.StatutEmprunt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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

        String query = "SELECT * from Reservation where nom = '" + usager.getNom() + "' and titre = " + oeuvre.getTitre() + "' and statutreservation = '" + StatutReservation.RESERVEE + "'";
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

        String query = "SELECT * from Emprunt where nom = '" + nom + "' and statutemprunt = '" + StatutEmprunt.EN_COURS + "';";
        String erreur = "Une erreur s'est produite lors de la verification des emprunts !";
        ResultSet res = outilsBaseSQL.rechercheSQL(query, erreur);

        try {
            while (res.next()) {
                return false;
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return true;
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
                }
            }
        }
    }

    public void annuler(String nom, String titre){
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        String query = "UPDATE Reservation \n" +
                " SET statutReservation = '" + StatutReservation.NON_RESERVEE + "' \n" +
                " WHERE titre = '" + titre + "' && nom = '" + nom + "' && statutEmprunt = '" + StatutReservation.RESERVEE + "'";
        String erreur = "Une erreur s'est produite lors de l'annulation de la réservation !";
        int res = outilsBaseSQL.majSQL(query, erreur);
    }

    public void rendre(String nom, int id){
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        String query = "UPDATE Emprunt \n" +
                " SET statutEmprunt = '" + StatutEmprunt.TERMINE + "' \n" +
                " WHERE idExemplaire = '" + id + "' && nom = '" + nom + "' && statutEmprunt = '" + StatutEmprunt.EN_COURS + "'";
        String erreur = "Une erreur s'est produite lors du rendu !";
        int res = outilsBaseSQL.majSQL(query, erreur);
    }

    public void supprimerExemplaire(int idExemplaire) {
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        String query = "DELETE FROM Emprunt \n" +
                " WHERE idExemplaire = '" + idExemplaire + "'";
        String erreur = "Une erreur s'est produite lors de la suppression des emprunts !";
        int res = outilsBaseSQL.majSQL(query, erreur);
    }

    public void supprimerOeuvre(String titre) {
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
        } catch (Exception e){
            System.out.println(e);
        }

        query = "DELETE FROM Exemplaire \n" +
                " WHERE titre = '" + titre + "'";
        erreur = "Une erreur s'est produite lors de la suppression des exemplaires !";
        res = outilsBaseSQL.majSQL(query, erreur);

    }

    public void supprimerUsager(String nom){
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
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