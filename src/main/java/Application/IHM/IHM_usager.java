package Application.IHM;


import Application.Controleur.Gestion_usager;
import Application.ObjetsMetier.Usager;

import java.util.List;

public class IHM_usager {

    public static List<Usager> getAllUsagers() {
        Gestion_usager gu = new Gestion_usager();
        return gu.getAllUsagers();
    }
}

