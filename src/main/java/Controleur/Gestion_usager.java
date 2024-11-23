package Controleur;

import ObjetsMetier.Usager;
import Utilitaire.OutilsBaseSQL;

import static ObjetsMetier.Usager.e_identifier;

public class Gestion_usager {
    public static void ajouter(String nom, String prenom, String mail) {
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        Usager usager;

        usager = e_identifier(nom);

        if(usager == null){
            System.out.println("insertion");
            String query = "INSERT INTO Usager (nom, prenom, mail)\n" +
                    " VALUES ('"+ nom +"', '"+ prenom +"', '"+ mail +"')";
            String erreur = "Une erreur s'est produite lors de l'inscription !";
            int res = outilsBaseSQL.majSQL(query, erreur);
        } else {
            System.out.println("L'usager " + nom + " existe déjà !");
        }
    }

    public static void supprimer(String nom){
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        String query = "DELETE FROM Usager \n" +
                " WHERE nom = '" + nom + "'";
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
