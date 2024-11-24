package Application.ObjetsMetier;

import java.util.Date;
import Application.Utilitaire.StatutEmprunt;

public class Emprunt {
    private Date dateEmprunt;
    private StatutEmprunt statutEmprunt;
    private Usager usager;
    private Exemplaire exemplaire;

    // Constructeur
    public Emprunt(Date dateEmprunt, Usager usager, Exemplaire exemplaire) {
        this.dateEmprunt = dateEmprunt;
        this.usager = usager;
        this.exemplaire = exemplaire;
    }
    public Emprunt(Date dateEmprunt, Usager usager, Exemplaire exemplaire, StatutEmprunt statutEmprunt) {
        this.dateEmprunt = dateEmprunt;
        this.usager = usager;
        this.exemplaire = exemplaire;
        this.statutEmprunt = statutEmprunt;
    }

    // Getters et Setters
    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public StatutEmprunt getStatutEmprunt() {
        return statutEmprunt;
    }

    public void setStatutEmprunt(StatutEmprunt statutEmprunt) {
        this.statutEmprunt = statutEmprunt;
    }

    public Usager getUsager() {
        return usager;
    }

    public void setUsager(Usager usager) {
        this.usager = usager;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    // Méthodes spécifiques
    public void maj(Usager usager, Exemplaire exemplaire, StatutEmprunt statutEmprunt) {
        this.usager = usager;
        this.exemplaire = exemplaire;
        this.statutEmprunt = statutEmprunt;
    }
}
