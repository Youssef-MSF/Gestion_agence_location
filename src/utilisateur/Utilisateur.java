package utilisateur;

import administration.DBConnect;
import administration.controllers.UtilisateurMenuController;
import modules.Client;
import modules.Parking;
import modules.Vehicule;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Utilisateur {
    //coordonnées d'un utilisateur

    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String cin;
    private String tel;
    private boolean is_suspend;

    public Utilisateur() {
        this.is_suspend = false;
    }

    public Utilisateur(int id, String nom, String prenom, String adresse, String cin, String tel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.cin = cin;
        this.tel = tel;
    }

    public Utilisateur(String nom, String prenom, String adresse, String cin, String tel, boolean is_suspend) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.cin = cin;
        this.tel = tel;
        this.is_suspend = is_suspend;
    }

    //getters et setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public boolean isIs_suspend() {
        return is_suspend;
    }

    public void setIs_suspend(boolean is_suspend) {
        this.is_suspend = is_suspend;
    }

    boolean rs;
    PreparedStatement pat;
    Connection con = null;

    //différentes fonctions d'un utilisateur

    //Module des véhicules:

    public boolean ajouterVehicule(Vehicule nouveauVehicule) {

        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("INSERT INTO vehicule(matricule, marque, type, carburant, compteurKM, dateMiseEnCirculation, nParkingAssocie) VALUES(?, ?, ?, ?, ?, ?, ?)");

            pat.setString(1, nouveauVehicule.getMatricule());
            pat.setString(2, nouveauVehicule.getMarque());
            pat.setString(3, nouveauVehicule.getType());
            pat.setString(4, nouveauVehicule.getCarburant());
            pat.setString(5, Double.toString(nouveauVehicule.getCompteurKM()));
            pat.setString(6, nouveauVehicule.getDateMiseEnCirculation());
            pat.setString(7, nouveauVehicule.getNParkingAssocie());

            rs = pat.execute();

            if (!rs) {
                JOptionPane.showMessageDialog(null, "Ajout avec succès !!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "L'ajout n'est pas réalisé :(");
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean supprimerVehicule(Vehicule vehiculeASupprimer) {

        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("DELETE FROM vehicule WHERE matricule=?");

            pat.setString(1, vehiculeASupprimer.getMatricule());

            rs = pat.execute();

            if (!rs) {
                JOptionPane.showMessageDialog(null, "Suppression avec succès !!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "La suppression n'est pas réalisé :(");
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean modifierVehicule(Vehicule vehiculeAModifier) {
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("UPDATE vehicule SET matricule=?, marque=?, type=?, carburant=?, compteurKM=?, dateMiseEnCirculation=?, nParkingAssocie=? WHERE matricule=?");

            pat.setString(1, vehiculeAModifier.getMatricule());
            pat.setString(2, vehiculeAModifier.getMarque());
            pat.setString(3, vehiculeAModifier.getType());
            pat.setString(4, vehiculeAModifier.getCarburant());
            pat.setString(5, Double.toString(vehiculeAModifier.getCompteurKM()));
            pat.setString(6, vehiculeAModifier.getDateMiseEnCirculation());
            pat.setString(7, vehiculeAModifier.getNParkingAssocie());
            pat.setString(8, vehiculeAModifier.getMatricule());

            rs = pat.execute();

            if (!rs) {
                JOptionPane.showMessageDialog(null, "Modification avec succès !!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "La modification n'est pas réalisé :(");
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Vehicule rechercherVehicule(String nom) {
        return null;
    }

    public void afficherListeVehicule() {
        UtilisateurMenuController myMenuUser = new UtilisateurMenuController();
        myMenuUser.populateVehiculeListe();
    }

    //Module des Parkings

    public boolean ajouterParking(Parking nouveauParking){
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("INSERT INTO parking(codeParking, capacite, rue, arrondissement, nbrePlacesVides) VALUES(?, ?, ?, ?, ?)");

            pat.setString(1, nouveauParking.getCodeParking());
            pat.setString(2, Integer.toString(nouveauParking.getCapacite()));
            pat.setString(3, nouveauParking.getRue());
            pat.setString(4, nouveauParking.getArrondissement());
            pat.setString(5, Integer.toString(nouveauParking.getNbrPlacesVides()));

            rs = pat.execute();

            if (!rs) {
                JOptionPane.showMessageDialog(null, "Ajout avec succès !!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "L'ajout n'est pas réalisé :(");
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean supprimerParking(Parking parkingASupprimer){
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("DELETE FROM parking WHERE codeParking=?");

            pat.setString(1, parkingASupprimer.getCodeParking());

            rs = pat.execute();

            if (!rs) {
                JOptionPane.showMessageDialog(null, "Suppression avec succès !!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "La suppression n'est pas réalisé :(");
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean modifierParking(Parking parkingAModifier){
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("UPDATE parking SET codeParking=?, capacite=?, rue=?, arrondissement=?, nbrePlacesVides=? WHERE codeParking=?");

            pat.setString(1, parkingAModifier.getCodeParking());
            pat.setString(2, Integer.toString(parkingAModifier.getCapacite()));
            pat.setString(3, parkingAModifier.getRue());
            pat.setString(4, parkingAModifier.getArrondissement());
            pat.setString(5, Integer.toString(parkingAModifier.getNbrPlacesVides()));
            pat.setString(6, parkingAModifier.getCodeParking());

            rs = pat.execute();

            if (!rs) {
                JOptionPane.showMessageDialog(null, "Modification avec succès !!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "La modification n'est pas réalisé :(");
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deposerVehiculeDansParking(Vehicule vehiculeAdeposer, Parking parkingConcerne){
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("UPDATE vehicule SET nParkingAssocie=? WHERE matricule=?");

            pat.setString(2, parkingConcerne.getCodeParking());
            pat.setString(1, vehiculeAdeposer.getMatricule());

            rs = pat.execute();

            if (!rs) {
                JOptionPane.showMessageDialog(null, "Le véhicule a été bien déposer.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Le véhicule n'est pas déposée correctement.");
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


}
