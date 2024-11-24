package Application.IHM;

import Application.Controleur.Gestion_oeuvre;
import Application.Controleur.Gestion_usager;
import Application.ObjetsMetier.Oeuvre;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

import static java.awt.SystemColor.menu;

public class IHM_oeuvre {
    public static List<Oeuvre> getAllOeuvres() {
        Gestion_oeuvre go = new Gestion_oeuvre();
        return go.getAllOeuvres();
    }
}
