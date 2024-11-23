package Controleur;

import ObjetsMetier.Oeuvre;
import Utilitaire.OutilsBaseSQL;

import java.time.LocalDate;

import static ObjetsMetier.Livre.e_identifier;

public class Gestion_oeuvre {
    public void creationOeuvreLivre(String titre, LocalDate datePublication, String auteur) {
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        Oeuvre livre = e_identifier(titre);

        if(livre == null){
            System.out.println("insertion");
            String query = "INSERT INTO Oeuvre (titre, datePublication) VALUES (\'"+ titre +"\',\'"+datePublication.toString()+"\')";
            String erreur = "Une erreur s'est produite lors de l'inscription de l'oeuvre !";
            System.out.println(query);
            int res = outilsBaseSQL.majSQL(query,erreur);
            String query2 = "INSERT INTO Livre (titre, auteur) VALUES (\'"+ titre +"\',\'"+auteur+"\')";
            String erreur2 = "Une erreur s'est produit lors de l'inscription du Livre !";
            int res2 = outilsBaseSQL.majSQL(query2,erreur2);
        } else {
            System.out.println("Le Livre "+titre+" existe déjà !");
        }
    }

    public void creationOeuvreMagazine(String titre, LocalDate datePublication, int numero, String edition) {
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        Oeuvre magazine = null;
        magazine = e_identifier(titre);

        if(magazine == null){
            System.out.println("insertion");
            String query = "INSERT INTO Oeuvre (titre, datePublication) VALUES (\'"+ titre +"\',\'"+datePublication.toString()+"\')";
            String erreur = "Une erreur s'est produite lors de l'inscription de l'oeuvre !";
            System.out.println(query);
            int res = outilsBaseSQL.majSQL(query,erreur);
            String query2 = "INSERT INTO Magazine (titre, numéro, édition) VALUES (\'"+ titre +"\',\'"+numero+"\',\'"+edition+"\')";
            String erreur2 = "Une erreur s'est produit lors de l'inscription du Magazine !";
            int res2 = outilsBaseSQL.majSQL(query2,erreur2);
        } else {
            System.out.println("Le Magazine "+titre+" existe déjà !");
        }
    }

    public void supprimer(String titre) {
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        String erreur = "Une erreur s'est produite lors de la suppression !";
        String query = "DELETE FROM Livre \n" +
                " WHERE titre = '" + titre + "'";
        outilsBaseSQL.majSQL(query, erreur);
        query = "DELETE FROM Magazine \n" +
                " WHERE titre = '" + titre + "'";
        outilsBaseSQL.majSQL(query, erreur);
        query = "DELETE FROM oeuvre \n" +
                " WHERE titre = '" + titre + "'";
        int res = outilsBaseSQL.majSQL(query, erreur);
    }
}
