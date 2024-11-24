package Application.ObjetsMetier;

import java.util.Date;

public class Livre extends Oeuvre {
    private String auteur;

    // Constructeur
    public Livre(String titre, String auteur, Date datePublication) {
        super(titre, datePublication); // Appel au constructeur de Oeuvre
        this.auteur = auteur;
    }

    // Getters et Setters
    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
}

