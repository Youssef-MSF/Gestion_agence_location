package administration.controllers;

import administration.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import modules.Parking;
import modules.Vehicule;
import utilisateur.Utilisateur;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UtilisateurMenuController implements Initializable {

    Utilisateur utilisateur = new Utilisateur();

    @FXML
    private AnchorPane gestionVehicules;

    @FXML
    private AnchorPane gestionParkings;


    @FXML
    private ImageView img;

    @FXML
    private Button btnVehicules;

    @FXML
    private Button btnContrats;

    @FXML
    private Button btnReservations;

    @FXML
    private Button btnClients;

    @FXML
    private Button btnSanctions;

    @FXML
    private Button btnParkings;

    @FXML
    private Button btnFactures;

    //Variables pour es véhicules

    @FXML
    private Button btnChercherVehicule;

    @FXML
    private TextField matriculeField;

    @FXML
    private TextField typeField;

    @FXML
    private TextField marqueField;

    @FXML
    private TextField carburantField;

    @FXML
    private TextField compteurField;

    @FXML
    private TextField nParkingAssocieField;

    @FXML
    private Button btnAjouterVehicule;

    @FXML
    private Button btnModifierVehicule;

    @FXML
    private Button btnSupprimerVehicule;

    @FXML
    private Button btnAfficherListeVehicule;

    @FXML
    private Button acceuil;

    @FXML
    private Button btnSave;

    @FXML
    private Button exit;

    @FXML
    private DatePicker dateCirculationField;

    @FXML
    private TextField rechercherVehiculeField;

    @FXML
    private TableView<Vehicule> vehiculeListe;

    @FXML
    private TableColumn<Vehicule, String> col_matricule;

    @FXML
    private TableColumn<Vehicule, String> col_marque;

    @FXML
    private TableColumn<Vehicule, String> col_type;

    @FXML
    private TableColumn<Vehicule, String> col_carburant;

    @FXML
    private TableColumn<Vehicule, String> col_compteur;

    @FXML
    private TableColumn<Vehicule, String> col_date;

    @FXML
    private TableColumn<Vehicule, String> col_nParkingAssocie;

    @FXML
    private AnchorPane vehiculeImageContainer;

    @FXML
    private Label gestionTitre;

    //Variables pour Parking
    @FXML
    private TextField codeParkingField;

    @FXML
    private TextField arrondissementField;

    @FXML
    private TextField rueField;

    @FXML
    private TextField capaciteField;

    @FXML
    private TextField nbrePlacesVidesField;

    @FXML
    private Button btnAjouterParking;

    @FXML
    private Button btnModifierParking;

    @FXML
    private Button btnSupprimerParking;

    @FXML
    private Button btnAfficherListeVehiculeParParking;

    @FXML
    private ComboBox<String> vehiculeComboBox;

    @FXML
    private ComboBox<String> parkingComboBox;

    @FXML
    private TextField matriculeVehiculeADeposer;

    @FXML
    private AnchorPane deposerRestituerAnchor;

    @FXML
    private AnchorPane principaleFieldsAnchor;


    @FXML
    private TabPane listeParkings;

    @FXML
    private TableView<Vehicule> listeVehiculeParParking;

    @FXML
    private TableColumn<Vehicule, String> col_matricule1;

    @FXML
    private TableColumn<Vehicule, String> col_marque1;

    @FXML
    private TableColumn<Vehicule, String> col_type1;

    @FXML
    private TableColumn<Vehicule, String> col_carburant1;

    @FXML
    private TableColumn<Vehicule, String> col_compteur1;

    @FXML
    private TableColumn<Vehicule, String> col_date1;

    @FXML
    private TableColumn<Vehicule, String> col_nParkingAssocie1;

    @FXML
    private TableView<Parking> listeParking;

    @FXML
    private TableColumn<Parking, String> col_codeParking;

    @FXML
    private TableColumn<Parking, String> col_capacite;

    @FXML
    private TableColumn<Parking, String> col_rue;

    @FXML
    private TableColumn<Parking, String> col_arrondisselent;

    @FXML
    private TableColumn<Parking, String> col_nbrePlacesVides;

    @FXML
    private TextField rechercherParkingField;

    @FXML
    private Button btnChercherParking;

    //Traitements

    Connection conn;
    PreparedStatement pat;
    boolean rs;

    ObservableList<Vehicule> vehcleListe = FXCollections.observableArrayList();
    ObservableList<Parking> parkListe = FXCollections.observableArrayList();
    ObservableList<String> vehiculesListeComboBox = FXCollections.observableArrayList();
    ObservableList<String> parkingsListeComboBox = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vehiculeComboBox.getItems().clear();
        parkingComboBox.getItems().clear();

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            ResultSet res = con.createStatement().executeQuery("SELECT matricule FROM vehicule");

            while (res.next()) {
                vehiculesListeComboBox.add(res.getString("matricule"));
                //parkingsListeComboBox.add(res.getString("codeParking"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ResultSet res = con.createStatement().executeQuery("SELECT codeParking FROM parking");

            while (res.next()) {
                parkingsListeComboBox.add(res.getString("codeParking"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        vehiculeComboBox.setItems(vehiculesListeComboBox);
        parkingComboBox.setItems(parkingsListeComboBox);

    }

    //Les traitements liées au gestion des véhicules

    @FXML
    void SaveVehicule(ActionEvent event) {

        Vehicule nouveauVehicule = new Vehicule(matriculeField.getText(), marqueField.getText(), typeField.getText(), carburantField.getText(), Double.parseDouble(compteurField.getText()), dateCirculationField.getValue().toString());

        utilisateur.modifierVehicule(nouveauVehicule);

        matriculeField.setText("");
        marqueField.setText("");
        typeField.setText("");
        carburantField.setText("");
        compteurField.setText("");
        dateCirculationField.setValue(null);

        this.populateVehiculeListe();

    }

    @FXML
    void afficherListeVehicules(ActionEvent event) {

        vehiculeListe.toFront();

        populateVehiculeListe();

    }

    @FXML
    void ajouterVehicule(ActionEvent event) {

        if (matriculeField.getText().equals("") && marqueField.getText().equals("") && typeField.getText().equals("") && carburantField.getText().equals("") && compteurField.getText().equals("") && dateCirculationField.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir les champs s'il vous plait !!");
        } else {

            Vehicule nouveauVehicule = new Vehicule(matriculeField.getText(), marqueField.getText(), typeField.getText(), carburantField.getText(), Double.parseDouble(compteurField.getText()), dateCirculationField.getValue().toString());

            utilisateur.ajouterVehicule(nouveauVehicule);

            matriculeField.setText("");
            marqueField.setText("");
            typeField.setText("");
            carburantField.setText("");
            compteurField.setText("");
            dateCirculationField.setValue(null);

            populateVehiculeListe();

        }

    }

    @FXML
    void handleClicks(ActionEvent event) {
        if (event.getSource() == btnVehicules) {
            vehiculeListe.toBack();
            btnVehicules.setStyle("-fx-background-color:#9656CD");
            btnParkings.setStyle("");
            gestionTitre.setText("Gestion des véhicules");
            gestionVehicules.toFront();
        } else if (event.getSource() == btnParkings) {
            listeParkings.toBack();
            btnParkings.setStyle("-fx-background-color:#9656CD");
            btnVehicules.setStyle("");
            gestionTitre.setText("Gestion des parkings");
            gestionParkings.toFront();
        }
    }

    @FXML
    void modifierVehicule(ActionEvent event) {

        this.selectionnerVehicule();

    }

    @FXML
    void retourAcceuil(ActionEvent event) {

        System.exit(0);

    }

    @FXML
    void supprimerVehicule(ActionEvent event) {

        this.selectionnerVehicule();

        Vehicule vehiculeASupprimer = new Vehicule(matriculeField.getText(), marqueField.getText(), typeField.getText(), carburantField.getText(), Double.parseDouble(compteurField.getText()), dateCirculationField.getValue().toString());

        utilisateur.supprimerVehicule(vehiculeASupprimer);

        this.populateVehiculeListe();

        matriculeField.setText("");
        marqueField.setText("");
        typeField.setText("");
        carburantField.setText("");
        compteurField.setText("");
        dateCirculationField.setValue(null);
    }

    @FXML
    void chercherVehicule(ActionEvent event) {

        vehiculeListe.toFront();

        vehiculeListe.getItems().clear();

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            pat = con.prepareStatement("SELECT * FROM vehicule WHERE matricule=?");

            pat.setString(1, rechercherVehiculeField.getText());
            ResultSet res = pat.executeQuery();

            while (res.next()) {
                vehcleListe.add(new Vehicule(res.getString("matricule"), res.getString("marque"), res.getString("type"), res.getString("carburant"), Double.parseDouble(res.getString("compteurKM")), res.getString("dateMiseEnCirculation"), res.getString("nParkingAssocie")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        col_matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        col_marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_carburant.setCellValueFactory(new PropertyValueFactory<>("carburant"));
        col_compteur.setCellValueFactory(new PropertyValueFactory<>("compteurKM"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateMiseEnCirculation"));

        vehiculeListe.setItems(vehcleListe);

    }

    public void selectionnerVehicule() {
        Vehicule selectedVehicule = vehiculeListe.getSelectionModel().getSelectedItem();
        matriculeField.setText(selectedVehicule.getMatricule());
        marqueField.setText(selectedVehicule.getMarque());
        typeField.setText(selectedVehicule.getType());
        carburantField.setText(selectedVehicule.getCarburant());
        compteurField.setText(Double.toString(selectedVehicule.getCompteurKM()));
        dateCirculationField.setValue(LocalDate.parse(selectedVehicule.getDateMiseEnCirculation()));
    }

    public void populateVehiculeListe() {
        vehiculeListe.getItems().clear();

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

        col_matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        col_marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_carburant.setCellValueFactory(new PropertyValueFactory<>("carburant"));
        col_compteur.setCellValueFactory(new PropertyValueFactory<>("compteurKM"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateMiseEnCirculation"));

        vehiculeListe.setItems(vehcleListe);
    }


    //Les traitements liées au gestion des parkings

    public void ajouterParking(ActionEvent actionEvent) {
        if (codeParkingField.getText().equals("") && capaciteField.getText().equals("") && rueField.getText().equals("") && arrondissementField.getText().equals("") && nbrePlacesVidesField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir les champs s'il vous plait !!");
        } else {
            Parking nouveauParking = new Parking(codeParkingField.getText(), Integer.parseInt(capaciteField.getText()), rueField.getText(), arrondissementField.getText(), Integer.parseInt(nbrePlacesVidesField.getText()));
            utilisateur.ajouterParking(nouveauParking);

            this.populateVehiculeListeParParking();

            codeParkingField.setText("");
            capaciteField.setText("");
            rueField.setText("");
            arrondissementField.setText("");
            nbrePlacesVidesField.setText("");
        }
    }

    public void modifierParking(ActionEvent actionEvent) {

        this.selectionnerParking();

    }

    public void supprimerParking(ActionEvent actionEvent) {

        this.selectionnerParking();

        Parking parkingASupprimer = new Parking(codeParkingField.getText(), Integer.parseInt(capaciteField.getText()), rueField.getText(), arrondissementField.getText(), Integer.parseInt(nbrePlacesVidesField.getText()));

        utilisateur.supprimerParking(parkingASupprimer);

        this.populateVehiculeListeParParking();

        codeParkingField.setText("");
        capaciteField.setText("");
        rueField.setText("");
        arrondissementField.setText("");
        nbrePlacesVidesField.setText("");

    }

    private void selectionnerParking() {

        Parking selectedParking= listeParking.getSelectionModel().getSelectedItem();

        codeParkingField.setText(selectedParking.getCodeParking());
        capaciteField.setText(Integer.toString(selectedParking.getCapacite()));
        rueField.setText(selectedParking.getRue());
        arrondissementField.setText(selectedParking.getArrondissement());
        nbrePlacesVidesField.setText(Integer.toString(selectedParking.getNbrPlacesVides()));

    }


    //Exit function

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);

    }


    public void afficherListeVehiculesParParking(ActionEvent actionEvent) {
        listeParkings.toFront();

        this.populateVehiculeListeParParking();
    }

    public void SaveParking(ActionEvent actionEvent) {

        Parking nouveauParking = new Parking(codeParkingField.getText(), Integer.parseInt(capaciteField.getText()), rueField.getText(), arrondissementField.getText(), Integer.parseInt(nbrePlacesVidesField.getText()));

        utilisateur.modifierParking(nouveauParking);

        this.populateVehiculeListeParParking();

        codeParkingField.setText("");
        capaciteField.setText("");
        rueField.setText("");
        arrondissementField.setText("");
        nbrePlacesVidesField.setText("");

    }

    private void populateVehiculeListeParParking() {

        listeVehiculeParParking.getItems().clear();

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

        col_matricule1.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        col_marque1.setCellValueFactory(new PropertyValueFactory<>("marque"));
        col_type1.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_carburant1.setCellValueFactory(new PropertyValueFactory<>("carburant"));
        col_compteur1.setCellValueFactory(new PropertyValueFactory<>("compteurKM"));
        col_date1.setCellValueFactory(new PropertyValueFactory<>("dateMiseEnCirculation"));
        col_nParkingAssocie1.setCellValueFactory(new PropertyValueFactory<>("nParkingAssocie"));

        listeVehiculeParParking.setItems(vehcleListe);



        listeParking.getItems().clear();

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

        col_codeParking.setCellValueFactory(new PropertyValueFactory<>("codeParking"));
        col_capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        col_rue.setCellValueFactory(new PropertyValueFactory<>("rue"));
        col_arrondisselent.setCellValueFactory(new PropertyValueFactory<>("arrondissement"));
        col_nbrePlacesVides.setCellValueFactory(new PropertyValueFactory<>("nbrPlacesVides"));

        listeParking.setItems(parkListe);

    }

    @FXML
    void chercherParking(ActionEvent event) {
        listeParkings.toFront();
        listeParking.toFront();

        listeParking.getItems().clear();

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            pat = con.prepareStatement("SELECT * FROM parking WHERE codeParking=?");

            pat.setString(1, rechercherParkingField.getText());
            ResultSet res = pat.executeQuery();

            while (res.next()) {
                parkListe.add(new Parking(res.getString("codeParking"), Integer.parseInt(res.getString("capacite")), res.getString("rue"), res.getString("arrondissement"), Integer.parseInt(res.getString("nbrePlacesVides"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        col_codeParking.setCellValueFactory(new PropertyValueFactory<>("codeParking"));
        col_capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        col_rue.setCellValueFactory(new PropertyValueFactory<>("rue"));
        col_arrondisselent.setCellValueFactory(new PropertyValueFactory<>("arrondissement"));
        col_nbrePlacesVides.setCellValueFactory(new PropertyValueFactory<>("nbrPlacesVides"));

        listeParking.setItems(parkListe);
    }


    public void deposerRestituerAnchor(ActionEvent actionEvent) {
        deposerRestituerAnchor.toFront();
    }

    public void deposerVehicule(ActionEvent actionEvent) {
        String selectedMatriculeComboBox = vehiculeComboBox.getSelectionModel().getSelectedItem();
        String selectedCodeParkingComboBox = parkingComboBox.getSelectionModel().getSelectedItem();

        utilisateur.deposerVehiculeDansParking(selectedMatriculeComboBox, selectedCodeParkingComboBox);

        this.populateVehiculeListeParParking();
    }


    public void retourPrincipaleFieldsParkings(ActionEvent actionEvent) {

        principaleFieldsAnchor.toFront();

    }
}
