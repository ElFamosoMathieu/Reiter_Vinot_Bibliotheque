package Controleur;

import Utilitaire.Etat;
import Utilitaire.OutilsBaseSQL;
import Utilitaire.StatutEmprunt;
import Utilitaire.StatutReservation;

import java.time.LocalDate;

public class Gestion_emprunt_res {

    public Gestion_emprunt_res(){}

    public static void reserver(String nom, String titre){
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();

        // Obtenir la date actuelle
        LocalDate dateJour = LocalDate.now();

        // Convertir en format 'YYYY-MM-DD'
        String dateJourFormat = dateJour.toString();

        String query = "INSERT INTO Reservation (titre, nom, dateReservation, statutReservation)\n" +
                " VALUES ('"+ titre +"', '" + nom + "', '"+ dateJourFormat +"', '"+ StatutReservation.RESERVEE +"')";
        String erreur = "Une erreur s'est produite lors de la réservation !";
        int res = outilsBaseSQL.majSQL(query, erreur);
    }

    public static void emprunter(String nom, int id){
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();

        // Obtenir la date actuelle
        LocalDate dateJour = LocalDate.now();

        // Convertir en format 'YYYY-MM-DD'
        String dateJourFormat = dateJour.toString();

        String query = "INSERT INTO Emprunt (idExemplaire, nom, dateEmprunt, statutEmprunt)\n" +
                " VALUES ('"+ id +"', '" + nom + "', '"+ dateJourFormat +"', '"+ StatutEmprunt.EN_COURS +"')";
        String erreur = "Une erreur s'est produite lors de l'emprunt !";
        int res = outilsBaseSQL.majSQL(query, erreur);
    }

    public static void annuler(String nom, String titre){
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        String query = "UPDATE Reservation \n" +
                " SET statutReservation = '" + StatutReservation.NON_RESERVEE + "' \n" +
                " WHERE titre = '" + titre + "' && nom = '" + nom + "' && statutEmprunt = '" + StatutReservation.RESERVEE + "'";
        String erreur = "Une erreur s'est produite lors de l'annulation de la réservation !";
        int res = outilsBaseSQL.majSQL(query, erreur);
    }

    public static void rendre(String nom, int id){
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        String query = "UPDATE Emprunt \n" +
                " SET statutEmprunt = '" + StatutEmprunt.TERMINE + "' \n" +
                " WHERE idExemplaire = '" + id + "' && nom = '" + nom + "' && statutEmprunt = '" + StatutEmprunt.EN_COURS + "'";
        String erreur = "Une erreur s'est produite lors du rendu !";
        int res = outilsBaseSQL.majSQL(query, erreur);
    }

    public static void supprimerExemplaire(int idExemplaire) {
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        String query = "DELETE FROM Emprunt \n" +
                " WHERE idExemplaire = '" + idExemplaire + "'";
        String erreur = "Une erreur s'est produite lors de la suppression des emprunts !";
    }

    public static void supprimerOeuvre(String titre) {
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        String query = "DELETE FROM Reservation \n" +
                " WHERE titre = '" + titre + "'";
        String erreur = "Une erreur s'est produite lors de la suppression des réservations !";
    }

    public static void supprimerUsager(String nom){
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
