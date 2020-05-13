package modules;

import java.awt.*;

public class Client {

    //Coordonnées d'un client

    private String cin;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private Image img_permis;

    //getters & setters


    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Image getImg_permis() {
        return img_permis;
    }

    public void setImg_permis(Image img_permis) {
        this.img_permis = img_permis;
    }
}
