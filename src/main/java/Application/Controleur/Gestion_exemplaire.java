package Application.Controleur;

import Application.ObjetsMetier.Exemplaire;
import Application.ObjetsMetier.Oeuvre;
import Application.Utilitaire.Etat;
import Application.Utilitaire.OutilsBaseSQL;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gestion_exemplaire {

    public Gestion_exemplaire(){}

    public void ajouter(String oeuvre) {
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();

        String query = "INSERT INTO Exemplaire (titre, etat)\n" +
                " VALUES ('"+ oeuvre +"', '" + Etat.DISPONIBLE + "')";
        String erreur = "Une erreur s'est produite lors de la création !";
        int res = outilsBaseSQL.majSQL(query, erreur);
    }

    public void supprimer(int id){
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        String query = "DELETE FROM Exemplaire \n" +
                " WHERE idExemplaire  = '" + id + "'";
        String erreur = "Une erreur s'est produite lors de la suppression !";
        int res = outilsBaseSQL.majSQL(query, erreur);
    }

    public void maj(int id, Etat etat){
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        String query = "UPDATE Exemplaire \n" +
                " SET etat = '" + etat + "' \n" +
                " WHERE idExemplaire = '" + id + "'";
        String erreur = "Une erreur s'est produite lors de la suppression !";
        int res = outilsBaseSQL.majSQL(query, erreur);
    }

    public List<Exemplaire> getAllExemplaires() {
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();
        String query = "SELECT * FROM Exemplaire";
        String erreur = "Une erreur s'est produite lors de la recherche des exemplaires !";
        ResultSet res = outilsBaseSQL.rechercheSQL(query, erreur);
        List<Exemplaire> exemplaires = new ArrayList<>();
        try {
            while (res.next()) {
                String query2 = "SELECT * FROM Oeuvre WHERE titre = '" + res.getString("titre") + "'";
                String erreur2 = "Une erreur s'est produite lors de la recherche de l'Oeuvre "+res.getString("titre")+" !";
                ResultSet res2 = outilsBaseSQL.rechercheSQL(query2,erreur2);
                res2.next();
                Etat etat = Etat.valueOf(res.getString("etat"));
                Exemplaire exemplaire = new Exemplaire(res.getInt("idExemplaire"),etat,new Oeuvre(res2.getString("titre"), res2.getDate("datepublication")));
                exemplaires.add(exemplaire);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return exemplaires;
    }

}
