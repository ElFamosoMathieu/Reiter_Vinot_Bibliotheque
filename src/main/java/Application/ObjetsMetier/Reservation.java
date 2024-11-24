package Application.ObjetsMetier;

import java.util.Date;
import Application.Utilitaire.StatutReservation;

public class Reservation {
    private Date dateReservation;
    private StatutReservation statutReservation;
    private Oeuvre oeuvre;
    private Usager usager;

    // Constructeur
    public Reservation(Date dateReservation, Oeuvre oeuvre, Usager usager) {
        this.dateReservation = dateReservation;
        this.oeuvre = oeuvre;
        this.usager = usager;
    }

    public Reservation(Date dateReservation, Oeuvre oeuvre, Usager usager, StatutReservation statutReservation) {
        this.dateReservation = dateReservation;
        this.oeuvre = oeuvre;
        this.usager = usager;
        this.statutReservation = statutReservation;
    }

    // Getters et Setters
    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public StatutReservation getStatutReservation() {
        return statutReservation;
    }

    public void setStatutReservation(StatutReservation statutReservation) {
        this.statutReservation = statutReservation;
    }
    public Oeuvre getOeuvre() {
        return oeuvre;
    }
    public Usager getUsager() {
        return usager;
    }

    // Méthodes spécifiques

}
