package Application.ObjetsMetier;

import java.sql.ResultSet;
import java.util.Date;
import Application.Utilitaire.Etat;
import Application.Utilitaire.OutilsBaseSQL;

public class Oeuvre {
    private String titre;
    private Date datePublication;
    private Etat etat;

    // Constructeur
    public Oeuvre(String titre, Date datePublication) {
        this.titre = titre;
        this.datePublication = datePublication;
    }

    // Getters et Setters
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    // Méthodes spécifiques
    public static Oeuvre e_identifier(String titre){
        Oeuvre oeuvre = null;
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();

        String query = "SELECT * from Oeuvre where titre = '" + titre + "'";
        String erreur = "Une erreur s'est produite lors de l'identification de l'oeuvre !";
        ResultSet res = outilsBaseSQL.rechercheSQL(query, erreur);

        try {
            while (res.next()) {
                oeuvre = new Oeuvre(res.getString("titre"), res.getDate("datePublication"));
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return oeuvre;
    }
}
