package IHM;

import Controleur.Gestion_oeuvre;
import Controleur.Gestion_usager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.Date;

import static java.awt.SystemColor.menu;

public class IHM_oeuvre {
    public void creationOeuvre() {
        boolean menu = true;
        Gestion_oeuvre go = new Gestion_oeuvre();
        while(menu){
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez taper \n 1 pour creation d'un Livre \n 2 pour la creation d'un magazine \n 3 pour quitter le menu de creation d'oeuvre");
            int choix = sc.nextInt();
            switch(choix){
                case 1:
                    System.out.println("Veuillez saisir un titre de livre");
                    String titre = sc.next();
                    System.out.println("Veuillez saisir la date de Publication (jj/mm/aaaa)");
                    String stringDate = sc.next();
                    LocalDate date = null;
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        date = LocalDate.parse(stringDate, formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Format de date invalide. Veuillez utiliser le format jj/mm/aaaa.");
                        return;
                    }
                    System.out.println("Veuillez saisir le nom de l'auteur");
                    String auteur = sc.next();
                    go.creationOeuvreLivre(titre,date,auteur);
                    break;
                case 2:
                    System.out.println("Veuillez saisir un titre de livre");
                    String titre2 = sc.next();
                    System.out.println("Veuillez saisir la date de Publication (jj/mm/aaaa)");
                    String stringDate2 = sc.next();
                    LocalDate date2 = null;
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        date2 = LocalDate.parse(stringDate2, formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Format de date invalide. Veuillez utiliser le format jj/mm/aaaa.");
                        return;
                    }
                    System.out.println("Veuillez indiqué le numéro du magazine");
                    int numero = sc.nextInt();
                    System.out.println("Veuillez saisir l'édition du magazine");
                    String edition = sc.next();
                    go.creationOeuvreMagazine(titre2,date2,numero,edition);
                    break;
                case 3:
                    menu = false;
                default:
                    System.out.println("Veuillez saisir un choix Valide");
                    break;

            }
        }
    }

    public void supprimer(){
        Scanner sc = new Scanner(System.in);
        Gestion_oeuvre go = new Gestion_oeuvre();
        System.out.println("Veuillez saisir un titre de livre");
        String titre = sc.next();
        go.supprimer(titre);
    }
}
