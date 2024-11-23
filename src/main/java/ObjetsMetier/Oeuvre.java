package ObjetsMetier;

import java.util.Date;
import util.Etat;

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
    public Oeuvre identifier(String titre) {
        return this.titre.equals(titre) ? this : null;
    }
}
