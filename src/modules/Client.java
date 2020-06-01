package modules;

import java.awt.*;

public class Client {

    //Coordonnées d'un client

    private String cin;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private String imgPermis;


    //Constructeur

    public Client(String cin, String nom, String prenom, String adresse, String tel, String imgPermis) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.imgPermis = imgPermis;
    }


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

    public String getImgPermis() {
        return imgPermis;
    }

    public void setImgPermis(String imgPermis) {
        this.imgPermis = imgPermis;
    }
}
