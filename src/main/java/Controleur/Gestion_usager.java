package Controleur;

import ObjetsMetier.Usager;
import Utilitaire.OutilsBaseSQL;

import static Utilitaire.OutilsBaseSQL.makeConnexion;

public class Gestion_usager {
    public void creationUsager(String nom, String prenom, String mail) {
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();

        Usager usager = new Usager(nom, prenom, mail);
        String query = "INSERT INTO Usager (nom, prenom, mail)\n" +
                " VALUES ('"+ nom +"', '"+ prenom +"', '"+ mail +"')";
        String erreur = "Une erreur s'est produite lors de l'inscription !";
        int res = outilsBaseSQL.majSQL(query, erreur);

    }
}
