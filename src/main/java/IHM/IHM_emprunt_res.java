package IHM;

import Controleur.Gestion_emprunt_res;
import Utilitaire.OutilsBaseSQL;

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

    public void rendre(String nom, int id){
        this.gestion_emprunt_res.rendre(nom, id);
    }

    public void supprimerExemplaire(int idExemplaire) {
        this.gestion_emprunt_res.supprimerExemplaire(idExemplaire);
    }

    public void supprimerOeuvre(String titre) {
        this.gestion_emprunt_res.supprimerOeuvre(titre);
    }

    public void supprimerUsager(String nom){
        this.gestion_emprunt_res.supprimerUsager(nom);
    }

}
