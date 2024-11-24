package Application.ObjetsMetier;

import java.util.Date;

public class Magazine extends Oeuvre {
    private int numero;
    private String edition;

    // Constructeur
    public Magazine(String titre, int numero, String edition, Date datePublication) {
        super(titre, datePublication); // Appel au constructeur de Oeuvre
        this.numero = numero;
        this.edition = edition;
    }

    // Getters et Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }
}

