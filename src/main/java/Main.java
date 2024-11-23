import Controleur.Gestion_exemplaire;
import Controleur.Gestion_usager;
import IHM.IHM_usager;

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
        System.out.println("10. Quitter le programme");
    }
}
