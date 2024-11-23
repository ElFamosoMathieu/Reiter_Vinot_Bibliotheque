package IHM;

import Controleur.Gestion_usager;

import java.util.Scanner;

public class IHM_usager {

    public void creationUsager(String nom, String prenom, String mail) {
        Gestion_usager GestionUsager = new Gestion_usager();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Saisir le nom de l'usager.");
        nom = scanner.nextLine();
        System.out.println("Puis le prenom de l'usager.");
        prenom = scanner.nextLine();
        System.out.println("Et pour finir le mail de l'usager.");
        mail = scanner.nextLine();
        try {
            GestionUsager.ajouter(nom, prenom, mail);
        }catch(Exception e){
            System.out.println("Erreur de creation : "+e.getMessage());
        }
    }
}
