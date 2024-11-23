package ObjetsMetier;

import Utilitaire.Etat;

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
    public Exemplaire e_identifier(Oeuvre oeuvre) {
        //a changer avec le SQL
        return this;
    }

    public void maj(Etat etat) {
        this.etat = etat;
    }
}
