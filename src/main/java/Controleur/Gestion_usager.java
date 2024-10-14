package Controleur;

import ObjetsMetier.Usager;

public class Gestion_usager {
    public void creationUsager(String nom, String prenom, String mail) {
        Usager usager = new Usager(nom, prenom, mail);
        System.out.println("Usager: " + usager.getNom() +" "+usager.getPrenom() + " avec le mail : "+usager.getMail()+ " a bien etais ajouter");

    }
}
