package Controleur;

import ObjetsMetier.Oeuvre;
import ObjetsMetier.Usager;
import Utilitaire.Etat;
import Utilitaire.OutilsBaseSQL;

import static ObjetsMetier.Usager.e_identifier;

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
                " WHERE id  = '" + id + "'";
        String erreur = "Une erreur s'est produite lors de la suppression !";
        int res = outilsBaseSQL.majSQL(query, erreur);
    }

    public static void maj(String nom, String prenom, String mail){
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        String query = "UPDATE Usager \n" +
                " SET prenom = '" + prenom + "', mail = '" + mail + "' \n" +
                " WHERE nom = '" + nom + "'";
        String erreur = "Une erreur s'est produite lors de la suppression !";
        int res = outilsBaseSQL.majSQL(query, erreur);
    }

}
