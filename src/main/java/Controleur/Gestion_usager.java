package Controleur;

import ObjetsMetier.Usager;
import Utilitaire.OutilsBaseSQL;

import static ObjetsMetier.Usager.e_identifier;

public class Gestion_usager {
    public void creationUsager(String nom, String prenom, String mail) {
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
}
