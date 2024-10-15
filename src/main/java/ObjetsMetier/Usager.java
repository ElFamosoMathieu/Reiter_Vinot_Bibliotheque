package ObjetsMetier;

import Utilitaire.OutilsBaseSQL;

import java.sql.ResultSet;

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

    public static Usager e_identifier(String nom){

        Usager usager = null;
        OutilsBaseSQL outilsBaseSQL = OutilsBaseSQL.getInstance();

        String query = "SELECT * from Usager where nom = '" + nom + "'";
        String erreur = "Une erreur s'est produite lors de l'identification de l'usager !";
        ResultSet res = outilsBaseSQL.rechercheSQL(query, erreur);

        try {
            while (res.next()) {
                usager = new Usager(res.getString("nom"), res.getString("prenom"), res.getString("mail"));
            }
        } catch (Exception e){
            System.out.println(e);
        }

        return usager;
    }
}
