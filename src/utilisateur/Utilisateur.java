package utilisateur;

import administration.DBConnect;
import administration.controllers.UtilisateurMenuController;
import modules.*;

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

    public boolean deposerVehiculeDansParking(String matricule, String codeParking){
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("UPDATE vehicule SET nParkingAssocie=?, is_deposer=1 WHERE matricule=?");

            pat.setString(1, codeParking);
            pat.setString(2, matricule);

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

    public boolean faireSortirVehiculeDuParking(String matricule){
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("UPDATE vehicule SET is_Sortis=1, is_deposer=1 WHERE matricule=?");

            pat.setString(1, matricule);

            rs = pat.execute();

            if (!rs) {
                JOptionPane.showMessageDialog(null, "Le véhicule a été bien sortis de son parking.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Le véhicule n'est pas sortis correctement.");
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean restituerVehicule(String matricule){
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("UPDATE vehicule SET is_Sortis=0, is_deposer=1 WHERE matricule=?");

            pat.setString(1, matricule);

            rs = pat.execute();

            if (!rs) {
                JOptionPane.showMessageDialog(null, "Le véhicule a été bien réstituer.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Le véhicule n'est pas réstituer correctement.");
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    //Module des Contrats

    public boolean ajouterContrat(Contrat nouveauContrat){

        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("INSERT INTO contrat(codeContrat, dateContrat, dateEcheanceContrat) VALUES(?, ?, ?)");

            pat.setString(1, nouveauContrat.getCodeContrat());
            pat.setString(2, nouveauContrat.getDateContrat());
            pat.setString(3, nouveauContrat.getDateEcheanceContrat());

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

    public boolean supprimerContrat(Contrat contratASupprimer){

        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("DELETE FROM contrat WHERE codeContrat=?");

            pat.setString(1, contratASupprimer.getCodeContrat());

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
    public boolean modifierContrat(Contrat contratAModifier){
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("UPDATE contrat SET codeContrat=?, dateContrat=?, dateEcheanceContrat=? WHERE codeContrat=?");

            pat.setString(1, contratAModifier.getCodeContrat());
            pat.setString(2, contratAModifier.getDateContrat());
            pat.setString(3, contratAModifier.getDateEcheanceContrat());
            pat.setString(4, contratAModifier.getCodeContrat());

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

    public void chercherContrat(){

    }
    public void afficherContrat(){

    }



    //Module des Réservations

    public boolean ajouterReservation(Reservation nouvelleReservation){
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("INSERT INTO reservation(codeReservation, dateReservation, dateDepart, dateRetour) VALUES(?, ?, ?, ?)");

            pat.setString(1, nouvelleReservation.getCodeReservation());
            pat.setString(2, nouvelleReservation.getDateReservation());
            pat.setString(3, nouvelleReservation.getDateDepart());
            pat.setString(4, nouvelleReservation.getDateRetour());

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

    public boolean supprimerReservation(Reservation reservationASupprimer){
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("DELETE FROM reservation WHERE codeReservation=?");

            pat.setString(1, reservationASupprimer.getCodeReservation());

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

    public boolean modifierReservation(Reservation reservationAModifier){
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("UPDATE reservation SET codeReservation=?, dateReservation=?, dateDepart=?, dateRetour=? WHERE codeReservation=?");

            pat.setString(1, reservationAModifier.getCodeReservation());
            pat.setString(2, reservationAModifier.getDateReservation());
            pat.setString(3, reservationAModifier.getDateDepart());
            pat.setString(4, reservationAModifier.getDateRetour());
            pat.setString(5, reservationAModifier.getCodeReservation());

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


// Module des factures

    public boolean ajouterFacture(Facture nouvelleFacture){
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("INSERT INTO facture(codeFacture, dateFacture, montant) VALUES(?, ?, ?)");

            pat.setString(1, nouvelleFacture.getCodeFacture());
            pat.setString(2, nouvelleFacture.getDateFacture());
            pat.setString(3, Double.toString(nouvelleFacture.getMontantPayer()));

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

    public boolean supprimerFacture(Facture factureASupprimer){
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("DELETE FROM facture WHERE codeFacture=?");

            pat.setString(1, factureASupprimer.getCodeFacture());

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

    public boolean modifierFacture(Facture factureAModifier){
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("UPDATE facture SET codeFacture=?, dateFacture=?, montant=? WHERE codeFacture=?");

            pat.setString(1, factureAModifier.getCodeFacture());
            pat.setString(2, factureAModifier.getDateFacture());
            pat.setString(3, Double.toString(factureAModifier.getMontantPayer()));
            pat.setString(4, factureAModifier.getCodeFacture());

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

}
