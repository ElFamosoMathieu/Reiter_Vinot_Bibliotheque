package IHM;

import Controleur.Gestion_emprunt_res;

public class IHM_emprunt_res {

    private Gestion_emprunt_res gestion_emprunt_res;

    public IHM_emprunt_res(){
        this.gestion_emprunt_res = new Gestion_emprunt_res();
    }

    public void reserver(String nom, String titre){
        this.gestion_emprunt_res.reserver(nom, titre);
    }

    public void emprunter(String nom, String titre){
        this.gestion_emprunt_res.emprunter(nom, titre);
    }

    public void annuler(String nom, String titre){
        this.gestion_emprunt_res.annuler(nom, titre);
    }

    public void rendre(String nom, String titre){
        this.gestion_emprunt_res.rendre(nom, titre);
    }

}
