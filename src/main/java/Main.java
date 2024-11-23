import Controleur.Gestion_exemplaire;
import Controleur.Gestion_usager;
import IHM.IHM_oeuvre;
import IHM.IHM_usager;
import Utilitaire.Etat;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        boolean menu = true;
        Scanner scanner = new Scanner(System.in);
        while (menu){
            int choix = 0;
            System.out.println("Faite votre choix : ");
            afficherMenu();
            choix = scanner.nextInt();;
            System.out.println(choix);
            switch (choix){
                case 1:
                    System.out.println("Lancement IHM creation d'un usager");
                    String nom = "";
                    String prenom = "";
                    String mail = "";
                    IHM_usager IHMusager = new IHM_usager();
                    IHMusager.creationUsager(nom,prenom,mail);
                    break;
                case 4:
                    System.out.println("Lancement IHM creation d'une oeuvre");
                    IHM_oeuvre IHMOeuvre = new IHM_oeuvre();
                    IHMOeuvre.creationOeuvre();
                    break;
                case 5:
                    System.out.println("Lancement IHM suppression d'une oeuvre");
                    IHM_oeuvre IHMOeuvre2 = new IHM_oeuvre();
                    IHMOeuvre2.supprimer();
                    break;
                case 10:
                    System.out.println("Lancement de la procedure pour quitter le programme");
                    menu = false;
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
        }
    }

    private static void afficherMenu() {
        System.out.println("1. Ajouter un usager");
        System.out.println("2. Work in progress");
        System.out.println("3. Work in progress");
        System.out.println("4. Ajouter une oeuvre");
        System.out.println("5. Supprimer une oeuvre");
        System.out.println("10. Quitter le programme");
    }
}
