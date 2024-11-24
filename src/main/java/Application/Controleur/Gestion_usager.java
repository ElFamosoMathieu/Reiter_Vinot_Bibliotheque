package Application.Controleur;

import Application.ObjetsMetier.Usager;
import Application.Utilitaire.OutilsBaseSQL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static Application.ObjetsMetier.Usager.e_identifier;

public class Gestion_usager {

    public Gestion_usager(){}

    public void ajouter(String nom, String prenom, String mail) {
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

    public void supprimer(String nom){
        Gestion_emprunt_res gestion_emprunt_res =new Gestion_emprunt_res();
        gestion_emprunt_res.supprimerUsager(nom);
        if (gestion_emprunt_res.verifierEmpruntVide(nom)){
            OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
            String query = "DELETE FROM Usager \n" +
                    " WHERE nom = '" + nom + "'";
            String erreur = "Une erreur s'est produite lors de la suppression !";
            int res = outilsBaseSQL.majSQL(query, erreur);
        }
    }

    public void maj(String nom, String prenom, String mail){
        Usager usager;
        usager = Usager.e_identifier(nom);
        if (usager != null){
            OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
            String query = "UPDATE Usager \n" +
                    " SET prenom = '" + prenom + "', mail = '" + mail + "' \n" +
                    " WHERE nom = '" + nom + "'";
            String erreur = "Une erreur s'est produite lors de la mise à jour !";
            int res = outilsBaseSQL.majSQL(query, erreur);
        }
    }


    public List<Usager> getAllUsagers() {
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        String query = "SELECT * FROM Usager";
        String erreur = "Une erreur s'est produite lors de la recherche de tout les usagers !";
        ResultSet res = outilsBaseSQL.rechercheSQL(query,"");
        List<Usager> usagers = new ArrayList<>();
        try {
            while (res.next()) {
                Usager usager = new Usager(res.getString("nom"), res.getString("prenom"), res.getString("mail"));
                usagers.add(usager);
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return usagers;
    }

}
