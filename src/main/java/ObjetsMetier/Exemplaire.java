package ObjetsMetier;

import Utilitaire.Etat;
import Utilitaire.OutilsBaseSQL;

import java.sql.ResultSet;

public class Exemplaire {
    private int id;
    private Etat etat;
    private Oeuvre oeuvre;

    // Constructeur
    public Exemplaire(int id, Etat etat, Oeuvre oeuvre) {
        this.id = id;
        this.etat = etat;
        this.oeuvre = oeuvre;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

    // Méthodes spécifiques
    public static Exemplaire e_identifier(Oeuvre oeuvre) {
        Exemplaire exemplaire = null;
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();

        String query = "SELECT * from Exemplaire " +
                "inner join Emprunt on Exemplaire.idExemplaire = Emprunt.idExemplaire \n" +
                "where Emprunt.statutemprunt != 'EN_COURS' \n" +
                "AND Exemplaire.etat = 'DISPONIBLE' \n" +
                "AND titre = '" + oeuvre.getTitre() + "' \n" +
                "Limit 1";
        String erreur = "Une erreur s'est produite lors de l'identification de l'exemplaire !";
        ResultSet res = outilsBaseSQL.rechercheSQL(query, erreur);

        try {
            while (res.next()) {
                exemplaire = new Exemplaire(res.getInt("idExemplaire"), Etat.valueOf(res.getString("etat")), oeuvre);
            }
        } catch (Exception e){
            System.out.println(e);
        }

        return exemplaire;
    }

    public void maj(Etat etat) {
        this.etat = etat;
    }

}
