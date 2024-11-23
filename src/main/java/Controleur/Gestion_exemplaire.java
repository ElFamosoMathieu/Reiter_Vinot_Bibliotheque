package Controleur;

import Utilitaire.Etat;
import Utilitaire.OutilsBaseSQL;

public class Gestion_exemplaire {

    public Gestion_exemplaire(){}

    public static void ajouter(String oeuvre) {
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();

        String query = "INSERT INTO Exemplaire (titre, etat)\n" +
                " VALUES ('"+ oeuvre +"', '" + Etat.DISPONIBLE + "')";
        String erreur = "Une erreur s'est produite lors de la création !";
        int res = outilsBaseSQL.majSQL(query, erreur);
    }

    public static void supprimer(int id){
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        String query = "DELETE FROM Exemplaire \n" +
                " WHERE idExemplaire  = '" + id + "'";
        String erreur = "Une erreur s'est produite lors de la suppression !";
        int res = outilsBaseSQL.majSQL(query, erreur);
    }

    public static void maj(int id, Etat etat){
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        String query = "UPDATE Exemplaire \n" +
                " SET etat = '" + etat + "' \n" +
                " WHERE idExemplaire = '" + id + "'";
        String erreur = "Une erreur s'est produite lors de la suppression !";
        int res = outilsBaseSQL.majSQL(query, erreur);
    }

}
