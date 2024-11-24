package Application.Controleur;

import Application.ObjetsMetier.Livre;
import Application.ObjetsMetier.Magazine;
import Application.ObjetsMetier.Oeuvre;
import Application.ObjetsMetier.Usager;
import Application.Utilitaire.OutilsBaseSQL;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static Application.ObjetsMetier.Oeuvre.e_identifier;

public class Gestion_oeuvre {
    public Gestion_oeuvre() {

    }
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
        Oeuvre oeuvre;

        oeuvre = Oeuvre.e_identifier(titre);

        if(oeuvre != null){
            Gestion_emprunt_res gestion_emprunt_res = new Gestion_emprunt_res();
            // Supprimer réservations

            gestion_emprunt_res.supprimerOeuvre(titre);

            // Retirer exemplaires

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

    public List<Oeuvre> getAllOeuvres() {
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        String query = "SELECT * FROM Livre";
        String erreur = "Une erreur s'est produite lors de la recherche de tout les Livre !";
        ResultSet res = outilsBaseSQL.rechercheSQL(query,erreur);
        List<Oeuvre> oeuvres = new ArrayList<>();
        try {
            while (res.next()) {
                String query2 = "SELECT * FROM Oeuvre WHERE titre = '" + res.getString("titre") + "'";
                String erreur2 = "Une erreur s'est produite lors de la recherche de l'oeuvre "+res.getString("titre")+" !";
                ResultSet res2 = outilsBaseSQL.rechercheSQL(query2,erreur2);
                res2.next();
                Livre livre = new Livre(res.getString("titre"), res.getString("auteur"), res2.getDate("datepublication"));
                oeuvres.add(livre);
            }
        } catch (Exception e){
            System.out.println(e);
        }

        query = "SELECT * FROM Magazine";
        erreur = "Une erreur s'est produite lors de la recherche de tout les Livre !";
        res = outilsBaseSQL.rechercheSQL(query,erreur);
        try {
            while (res.next()) {
                String query2 = "SELECT * FROM Oeuvre WHERE titre = '" + res.getString("titre") + "'";
                String erreur2 = "Une erreur s'est produite lors de la recherche de l'Oeuvre "+res.getString("titre")+" !";
                ResultSet res2 = outilsBaseSQL.rechercheSQL(query2,erreur2);
                res2.next();
                Magazine magazine = new Magazine(res.getString(2), res.getInt(3),res.getString(4), res2.getDate("datepublication"));
                oeuvres.add(magazine);
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return oeuvres;
    }
}
