package ObjetsMetier;

public class Usager {
    private String nom;
    private String prenom;
    private String mail;


    public Usager(String nom, String prenom, String mail)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getMail() {
        return this.mail;
    }
}
