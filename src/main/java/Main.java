import IHM.IHM_usager;

import java.util.Scanner;

public class Main {
    public static Scanner scanner;
    public static void main(String[] args){
        boolean menu = true;
        scanner = new Scanner(System.in);
        while (menu){
            int choix = 0;
            System.out.println("faite votre choix");
            afficherMenu();
            choix = scanner.nextInt();;
            System.out.println(choix);
            switch (choix){
                case 1:
                    System.out.println("Lancement IHM creation d'un usager");
                    String nom = "";
                    String prenom = "";
                    String mail = "";
                    creationUsager(nom,prenom,mail);
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
        System.out.println("Vous avez choix votre choix");
        System.out.println("1. Ajouter un usager");
        System.out.println("2. Work in progress");
        System.out.println("3. Work in progress");
        System.out.println("10. Quitter le programme");

    }

    public static void creationUsager(String nom, String prenom, String mail){
        scanner = new Scanner(System.in);
        System.out.println("Saisir le nom de l'usager.");
        nom = scanner.nextLine();
        System.out.println("Puis le prenom de l'usager.");
        prenom = scanner.nextLine();
        System.out.println("Et pour finir le mail de l'usager.");
        mail = scanner.nextLine();
        IHM_usager IHMusager = new IHM_usager();
        try {
            IHMusager.creationUsager(nom, prenom, mail);
        }catch(Exception e){
            System.out.println("Erreur de creation : "+e.getMessage());
        }
    }
}
