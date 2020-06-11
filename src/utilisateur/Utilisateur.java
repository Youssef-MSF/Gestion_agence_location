package utilisateur;

import administration.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modules.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                return true;
            } else {
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
                return true;
            } else {
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
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public ObservableList<Vehicule> rechercherVehicule(String nom) {
        ObservableList<Vehicule> vehcleListe = FXCollections.observableArrayList();

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            pat = con.prepareStatement("SELECT * FROM vehicule WHERE matricule=?");

            pat.setString(1, nom);
            ResultSet res = pat.executeQuery();

            while (res.next()) {
                vehcleListe.add(new Vehicule(res.getString("matricule"), res.getString("marque"), res.getString("type"), res.getString("carburant"), Double.parseDouble(res.getString("compteurKM")), res.getString("dateMiseEnCirculation"), res.getString("nParkingAssocie")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehcleListe;
    }

    public ObservableList<Vehicule> afficherVehiculeListe() {

        ObservableList<Vehicule> vehcleListe = FXCollections.observableArrayList();
        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM vehicule");

            while (res.next()) {
                vehcleListe.add(new Vehicule(res.getString("matricule"), res.getString("marque"), res.getString("type"), res.getString("carburant"), Double.parseDouble(res.getString("compteurKM")), res.getString("dateMiseEnCirculation")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehcleListe;
    }

    //Module des Parkings

    public boolean ajouterParking(Parking nouveauParking) {
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
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean supprimerParking(Parking parkingASupprimer) {
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("DELETE FROM parking WHERE codeParking=?");

            pat.setString(1, parkingASupprimer.getCodeParking());

            rs = pat.execute();

            if (!rs) {
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean modifierParking(Parking parkingAModifier) {
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
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deposerVehiculeDansParking(String matricule, String codeParking) {
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("UPDATE vehicule SET nParkingAssocie=?, is_deposer=1, is_sortis=0 WHERE matricule=?");

            pat.setString(1, codeParking);
            pat.setString(2, matricule);

            rs = pat.execute();

            if (!rs) {
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean faireSortirVehiculeDuParking(String matricule) {
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("UPDATE vehicule SET is_Sortis=1 WHERE matricule=?");

            pat.setString(1, matricule);

            rs = pat.execute();

            if (!rs) {
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean restituerVehicule(String matricule) {
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("UPDATE vehicule SET is_Sortis=0, is_deposer=1, is_reserve=0 WHERE matricule=?");

            pat.setString(1, matricule);

            rs = pat.execute();

            if (!rs) {
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public ObservableList<Parking> afficherParkingListe() {

        ObservableList<Parking> parkListe = FXCollections.observableArrayList();

        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM parking");

            while (res.next()) {
                parkListe.add(new Parking(res.getString("codeParking"), Integer.parseInt(res.getString("capacite")), res.getString("rue"), res.getString("arrondissement"), Integer.parseInt(res.getString("nbrePlacesVides"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parkListe;
    }

    public ObservableList<Vehicule> afficherListeVehiculeParParking() {

        ObservableList<Vehicule> vehcleListe = FXCollections.observableArrayList();

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM vehicule WHERE nParkingAssocie NOT LIKE \"\"");

            while (res.next()) {
                vehcleListe.add(new Vehicule(res.getString("matricule"), res.getString("marque"), res.getString("type"), res.getString("carburant"), Double.parseDouble(res.getString("compteurKM")), res.getString("dateMiseEnCirculation"), res.getString("nParkingAssocie")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehcleListe;

    }

    public ObservableList<Parking> rechercherParking(String codeParking) {

        ObservableList<Parking> parkListe = FXCollections.observableArrayList();

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            pat = con.prepareStatement("SELECT * FROM parking WHERE codeParking=?");

            pat.setString(1, codeParking);
            ResultSet res = pat.executeQuery();

            while (res.next()) {
                parkListe.add(new Parking(res.getString("codeParking"), Integer.parseInt(res.getString("capacite")), res.getString("rue"), res.getString("arrondissement"), Integer.parseInt(res.getString("nbrePlacesVides"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parkListe;

    }


    //Module des Contrats

    public boolean ajouterContrat(Contrat nouveauContrat) {

        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("INSERT INTO contrat(codeContrat, dateContrat, dateEcheanceContrat) VALUES(?, ?, ?)");

            pat.setString(1, nouveauContrat.getCodeContrat());
            pat.setString(2, nouveauContrat.getDateContrat());
            pat.setString(3, nouveauContrat.getDateEcheanceContrat());

            rs = pat.execute();

            if (!rs) {
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean supprimerContrat(Contrat contratASupprimer) {

        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("DELETE FROM contrat WHERE codeContrat=?");

            pat.setString(1, contratASupprimer.getCodeContrat());

            rs = pat.execute();

            if (!rs) {
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean modifierContrat(Contrat contratAModifier) {
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("UPDATE contrat SET codeContrat=?, dateContrat=?, dateEcheanceContrat=? WHERE codeContrat=?");

            pat.setString(1, contratAModifier.getCodeContrat());
            pat.setString(2, contratAModifier.getDateContrat());
            pat.setString(3, contratAModifier.getDateEcheanceContrat());
            pat.setString(4, contratAModifier.getCodeContrat());

            rs = pat.execute();

            if (!rs) {
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public ObservableList<Contrat> afficherContratListe() {

        ObservableList<Contrat> contratListe = FXCollections.observableArrayList();

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM contrat");

            while (res.next()) {
                contratListe.add(new Contrat(res.getString("codeContrat"), res.getString("dateContrat"), res.getString("dateEcheanceContrat")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contratListe;
    }


    public ObservableList<Contrat> rechercherContrat(String codeContrat) {

        ObservableList<Contrat> contratListe = FXCollections.observableArrayList();

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            pat = con.prepareStatement("SELECT * FROM contrat WHERE codeContrat=?");

            pat.setString(1, codeContrat);
            ResultSet res = pat.executeQuery();

            while (res.next()) {
                contratListe.add(new Contrat(res.getString("codeContrat"), res.getString("dateContrat"), res.getString("dateEcheanceContrat")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contratListe;

    }

    //Module des Réservations

    public boolean ajouterReservation(Reservation nouvelleReservation) {
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("INSERT INTO reservation(codeReservation, dateReservation, dateDepart, dateRetour) VALUES(?, ?, ?, ?)");

            pat.setString(1, nouvelleReservation.getCodeReservation());
            pat.setString(2, nouvelleReservation.getDateReservation());
            pat.setString(3, nouvelleReservation.getDateDepart());
            pat.setString(4, nouvelleReservation.getDateRetour());

            rs = pat.execute();

            if (!rs) {
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean supprimerReservation(Reservation reservationASupprimer) {
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("DELETE FROM reservation WHERE codeReservation=?");

            pat.setString(1, reservationASupprimer.getCodeReservation());

            rs = pat.execute();

            if (!rs) {
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean modifierReservation(Reservation reservationAModifier) {
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
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public ObservableList<Reservation> afficherReservationListe() {

        ObservableList<Reservation> reservationListe = FXCollections.observableArrayList();

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM reservation");

            while (res.next()) {
                reservationListe.add(new Reservation(res.getString("codeReservation"), res.getString("dateReservation"), res.getString("dateDepart"), res.getString("dateRetour")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservationListe;
    }

    public ObservableList<Reservation> afficherListeReservationValidees() {

        ObservableList<Reservation> reservationListe = FXCollections.observableArrayList();

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM reservation WHERE is_valid=1");

            while (res.next()) {
                reservationListe.add(new Reservation(res.getString("codeReservation"), res.getString("dateReservation"), res.getString("dateDepart"), res.getString("dateRetour")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservationListe;

    }

    public ObservableList<Reservation> afficherListeReservationNonValidees() {

        ObservableList<Reservation> reservationListe = FXCollections.observableArrayList();

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM reservation WHERE is_valid=0");

            while (res.next()) {
                reservationListe.add(new Reservation(res.getString("codeReservation"), res.getString("dateReservation"), res.getString("dateDepart"), res.getString("dateRetour")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservationListe;
    }


    public ObservableList<Reservation> afficherListeReservationAnnulees() {

        ObservableList<Reservation> reservationListe = FXCollections.observableArrayList();

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM reservation WHERE is_annulee=1");

            while (res.next()) {
                reservationListe.add(new Reservation(res.getString("codeReservation"), res.getString("dateReservation"), res.getString("dateDepart"), res.getString("dateRetour")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservationListe;
    }

    public ObservableList<Reservation> rechercherReservation(String codeReservation) {

        ObservableList<Reservation> reservationListe = FXCollections.observableArrayList();

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            pat = con.prepareStatement("SELECT * FROM reservation WHERE codeReservation=?");

            pat.setString(1, codeReservation);
            ResultSet res = pat.executeQuery();

            while (res.next()) {
                reservationListe.add(new Reservation(res.getString("codeReservation"), res.getString("dateReservation"), res.getString("dateDepart"), res.getString("dateRetour")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservationListe;

    }

// Module des clients

    public boolean ajouterClient(Client nouveauClient) {
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("INSERT INTO client(cin, nom, prenom, adresse, tel, imgPermis) VALUES(?, ?, ?, ?, ?, ?)");

            pat.setString(1, nouveauClient.getCin());
            pat.setString(2, nouveauClient.getNom());
            pat.setString(3, nouveauClient.getPrenom());
            pat.setString(4, nouveauClient.getAdresse());
            pat.setString(5, nouveauClient.getTel());
            pat.setString(6, nouveauClient.getImgPermis());

            rs = pat.execute();

            if (!rs) {
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean supprimerClient(Client clientASupprimer) {
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("DELETE FROM client WHERE cin=?");

            pat.setString(1, clientASupprimer.getCin());

            rs = pat.execute();

            if (!rs) {
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean modifierClient(Client clientAModifier) {
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("UPDATE client SET cin=?, nom=?, prenom=?, adresse=?, tel=?, imgPermis=? WHERE cin=?");

            pat.setString(1, clientAModifier.getCin());
            pat.setString(2, clientAModifier.getNom());
            pat.setString(3, clientAModifier.getPrenom());
            pat.setString(4, clientAModifier.getAdresse());
            pat.setString(5, clientAModifier.getTel());
            pat.setString(6, clientAModifier.getImgPermis());
            pat.setString(7, clientAModifier.getCin());

            rs = pat.execute();

            if (!rs) {
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public ObservableList<Client> afficherClientListe() {

        ObservableList<Client> clientListe = FXCollections.observableArrayList();

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM client");

            while (res.next()) {
                clientListe.add(new Client(res.getString("cin"), res.getString("nom"), res.getString("prenom"), res.getString("adresse"), res.getString("tel"), res.getString("imgPermis")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientListe;

    }


    public ObservableList<Client> rechercherClient(String cin) {

        ObservableList<Client> clientListe = FXCollections.observableArrayList();

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            pat = con.prepareStatement("SELECT * FROM client WHERE cin=?");

            pat.setString(1, cin);
            ResultSet res = pat.executeQuery();

            while (res.next()) {
                clientListe.add(new Client(res.getString("cin"), res.getString("nom"), res.getString("prenom"), res.getString("adresse"), res.getString("tel"), res.getString("imgPermis")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientListe;
    }


// Module des factures

    public boolean ajouterFacture(Facture nouvelleFacture) {
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("INSERT INTO facture(codeFacture, dateFacture, montant) VALUES(?, ?, ?)");

            pat.setString(1, nouvelleFacture.getCodeFacture());
            pat.setString(2, nouvelleFacture.getDateFacture());
            pat.setString(3, Double.toString(nouvelleFacture.getMontantPayer()));

            rs = pat.execute();

            if (!rs) {
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean supprimerFacture(Facture factureASupprimer) {
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("DELETE FROM facture WHERE codeFacture=?");

            pat.setString(1, factureASupprimer.getCodeFacture());

            rs = pat.execute();

            if (!rs) {
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean modifierFacture(Facture factureAModifier) {
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("UPDATE facture SET codeFacture=?, dateFacture=?, montant=? WHERE codeFacture=?");

            pat.setString(1, factureAModifier.getCodeFacture());
            pat.setString(2, factureAModifier.getDateFacture());
            pat.setString(3, Double.toString(factureAModifier.getMontantPayer()));
            pat.setString(4, factureAModifier.getCodeFacture());

            rs = pat.execute();

            if (!rs) {
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public ObservableList<Facture> rechercherFacture(String codeFacture) {

        ObservableList<Facture> factureListe = FXCollections.observableArrayList();

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            pat = con.prepareStatement("SELECT * FROM facture WHERE codeFacture=?");

            pat.setString(1, codeFacture);
            ResultSet res = pat.executeQuery();

            while (res.next()) {
                factureListe.add(new Facture(res.getString("codeFacture"), res.getString("dateFacture"), Double.parseDouble(res.getString("montant"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return factureListe;
    }

    public ObservableList<Facture> afficherFactureListe() {

        ObservableList<Facture> factureListe = FXCollections.observableArrayList();

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM facture");

            while (res.next()) {
                factureListe.add(new Facture(res.getString("codeFacture"), res.getString("dateFacture"), Double.parseDouble(res.getString("montant"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return factureListe;
    }


    // Module des sanctions


    public ObservableList<TableViewSanction> afficherListeClientSanctionnes() throws SQLException {

        ObservableList<TableViewSanction> clientSanctionneListe = FXCollections.observableArrayList();

        String codeClient = null;
        String codeReservation = null;
        String codeFacture = null;
        String matricule = null;
        String codeContrat = null;
        String montantSaction = null;

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            PreparedStatement pat = con.prepareStatement("SELECT cin FROM client WHERE is_sanctionné=1");

            ResultSet res = pat.executeQuery();

            while (res.next()) {

                codeClient = res.getString("cin");

                PreparedStatement prep = con.prepareStatement("SELECT * FROM operation WHERE codeClient=?");

                prep.setString(1, codeClient);

                ResultSet resultSet = prep.executeQuery();

                codeReservation = resultSet.getString("codeReservation");
                codeContrat = resultSet.getString("codeContrat");
                codeFacture = resultSet.getString("codeFacture");
                matricule = resultSet.getString("matricule");

                try {
                    PreparedStatement prepStat = con.prepareStatement("SELECT montantSanction FROM facture WHERE codeFacture=?");

                    prepStat.setString(1, codeFacture);

                    ResultSet resultSet1 = prepStat.executeQuery();

                    while (resultSet1.next()) {
                        montantSaction = resultSet1.getString("montantSanction");
                        clientSanctionneListe.add(new TableViewSanction(codeClient, codeReservation, codeContrat, codeFacture, matricule, montantSaction));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        con.close();

        return clientSanctionneListe;

    }


    public void reglerSanction(String codeFacture, String codeClient) {
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("UPDATE facture SET montantSanction=0 WHERE codeFacture=?");

            pat.setString(1, codeFacture);

            rs = pat.execute();

            if (!rs) {
                JOptionPane.showMessageDialog(null, "La sanction a été bien réglée.");
            } else {
                JOptionPane.showMessageDialog(null, "La sanction n'a pas été bien réglée.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("UPDATE client SET is_sanctionné=0 WHERE cin=?");

            pat.setString(1, codeClient);

            pat.execute();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
