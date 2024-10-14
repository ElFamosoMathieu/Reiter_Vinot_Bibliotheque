package IHM;

import Controleur.Gestion_usager;

public class IHM_usager {

    public void creationUsager(String nom, String prenom, String mail) {
        Gestion_usager GestionUsager = new Gestion_usager();
        GestionUsager.creationUsager(nom, prenom, mail);
    }
}
