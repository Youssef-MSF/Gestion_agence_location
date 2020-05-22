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
import modules.*;
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
    private AnchorPane parkingImageContainer;

    @FXML
    private TableView<Vehicule> listeVehiculeParParking2;

    @FXML
    private TableColumn<Vehicule, String> col_matricule2;

    @FXML
    private TableColumn<Vehicule, String> col_marque2;

    @FXML
    private TableColumn<Vehicule, String> col_type2;

    @FXML
    private TableColumn<Vehicule, String> col_carburant2;

    @FXML
    private TableColumn<Vehicule, String> col_compteur2;

    @FXML
    private TableColumn<Vehicule, String> col_date2;

    @FXML
    private TableColumn<Vehicule, String> col_nParkingAssocie2;

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
    private AnchorPane faireSortirAnchor;

    @FXML
    private AnchorPane restituerAnchor;

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

    @FXML
    private ComboBox<String> matriculeComboBoxSortir;

    @FXML
    private ComboBox<String> matriculeRestituer;

    // Variables pour contrats


    @FXML
    private AnchorPane gestionContrats;

    @FXML
    private Button btnAjouterContrat;

    @FXML
    private Button btnModifierContrat;

    @FXML
    private Button btnSupprimerContrat;

    @FXML
    private Button btnAfficherListesContrats;

    @FXML
    private Button acceuil11;

    @FXML
    private Button btnSaveModifContrat;

    @FXML
    private TextField rechercherContratField;

    @FXML
    private AnchorPane parkingImageContainer1;

    @FXML
    private ImageView imgParking1;

    @FXML
    private TableView<Contrat> listeContrats;

    @FXML
    private TableColumn<Contrat, String> col_nContrat;

    @FXML
    private TableColumn<Contrat, String> col_dateContrat;

    @FXML
    private TableColumn<Contrat, String> col_dateEcheance;

    @FXML
    private Button btnChercherContrat;

    @FXML
    private AnchorPane principaleFieldsAnchor1;

    @FXML
    private TextField nContartfield;

    @FXML
    private DatePicker dateContratField;

    @FXML
    private DatePicker dateEcheanceContratField;

    // Variables pour réservations

    @FXML
    private AnchorPane gestionReservations;

    @FXML
    private Button btnAjouterReservation;

    @FXML
    private Button btnModifierReservation;

    @FXML
    private Button btnSupprimerReservation;

    @FXML
    private Button btnAfficherListesReservations;

    @FXML
    private Button acceuil111;

    @FXML
    private Button btnSaveModifReseravtion;

    @FXML
    private TextField rechercherReservationField;

    @FXML
    private AnchorPane parkingImageContainer11;

    @FXML
    private ImageView imgParking11;

    @FXML
    private TableView<Reservation> listeReservations;

    @FXML
    private TableColumn<Reservation, String> col_codeReservation;

    @FXML
    private TableColumn<Reservation, String> col_dateReservation;

    @FXML
    private TableColumn<Reservation, String> col_dateDepart;

    @FXML
    private TableColumn<Reservation, String> col_dateRetour;

    @FXML
    private Button btnChercherReservation;

    @FXML
    private AnchorPane principaleFieldsAnchor11;

    @FXML
    private TextField codeReservationField;

    @FXML
    private DatePicker dateReservationField;

    @FXML
    private DatePicker dateDepartField;

    @FXML
    private DatePicker dateRetourField;

    @FXML
    private ComboBox<String> listeVehiculeAreserver;

    @FXML
    private ComboBox<String> listeClientReserver;

    @FXML
    private ComboBox<String> listeReservationCombobox;


    // Variables pour gestion des factures

    @FXML
    private AnchorPane gestionFactures;

    @FXML
    private Button btnAjouterFacture;

    @FXML
    private Button btnModifierFacture;

    @FXML
    private Button btnSupprimerFacture;

    @FXML
    private Button btnAfficherListesFacture;

    @FXML
    private Button acceuil1111;

    @FXML
    private Button btnSaveModifFacture;

    @FXML
    private TextField rechercherFactureField;

    @FXML
    private AnchorPane parkingImageContainer111;

    @FXML
    private ImageView imgParking111;

    @FXML
    private TableView<Facture> listeFactures;

    @FXML
    private TableColumn<Facture, String> col_codeFacture;

    @FXML
    private TableColumn<Facture, String> col_dateFacture;

    @FXML
    private TableColumn<Facture, Double> col_montant;

    @FXML
    private Button btnChercherFacture;

    @FXML
    private AnchorPane principaleFieldsAnchor111;

    @FXML
    private TextField codeFactureField;

    @FXML
    private DatePicker dateFactureField;

    @FXML
    private TextField montantPayerField;

    @FXML
    private ComboBox<String> listeContratsCombobox;


    //Traitements

    Connection conn;
    PreparedStatement pat;
    boolean rs;

    ObservableList<Vehicule> vehcleListe = FXCollections.observableArrayList();
    ObservableList<Parking> parkListe = FXCollections.observableArrayList();
    ObservableList<String> vehiculesListeComboBox = FXCollections.observableArrayList();
    ObservableList<String> parkingsListeComboBox = FXCollections.observableArrayList();
    ObservableList<String> vehiculesAresererListe = FXCollections.observableArrayList();
    ObservableList<String> clientReservant = FXCollections.observableArrayList();
    ObservableList<String> reservationListeCombobox = FXCollections.observableArrayList();
    ObservableList<Contrat> contratListe = FXCollections.observableArrayList();
    ObservableList<Reservation> reservationListe = FXCollections.observableArrayList();
    ObservableList<Facture> factureListe = FXCollections.observableArrayList();
    ObservableList<String> contratListeCombobox = FXCollections.observableArrayList();
    ObservableList<String> matriculeComboBoxSortirListe = FXCollections.observableArrayList();
    ObservableList<String> matriculeRestituerComboBox = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.populateComboxes();

        String matriculeReserve = null;

        try {
            conn = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert conn != null;
            ResultSet res = conn.createStatement().executeQuery("SELECT matricule FROM operation");

            while (res.next()) {
                matriculeReserve = res.getString("matricule");

                try {
                    pat = conn.prepareStatement("UPDATE vehicule SET is_reserve=1 WHERE matricule=?");

                    pat.setString(1, matriculeReserve);

                    rs = pat.execute();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Pour la validation des réservations

        String codeReservationAvalide = null;

        try {
            assert conn != null;
            ResultSet res = conn.createStatement().executeQuery("SELECT codeReservation FROM operation WHERE matricule NOT LIKE \"\" AND codeContrat NOT LIKE \"\" AND codeClient NOT LIKE \"\" AND codeFacture NOT LIKE \"\"");

            while (res.next()) {
                codeReservationAvalide = res.getString("codeReservation");

                try {
                    pat = conn.prepareStatement("UPDATE reservation SET is_valid=1 WHERE codeReservation=?");

                    pat.setString(1, codeReservationAvalide);

                    rs = pat.execute();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
    public void afficherListeVehicules(ActionEvent event) {

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

        this.populateComboxes();

    }

    @FXML
    void handleClicks(ActionEvent event) {
        if (event.getSource() == btnVehicules) {
            parkingImageContainer.toFront();
            btnVehicules.setStyle("-fx-background-color:#9656CD");
            btnParkings.setStyle("");
            btnContrats.setStyle("");
            btnReservations.setStyle("");
            btnFactures.setStyle("");
            gestionTitre.setText("Gestion des véhicules");
            gestionVehicules.toFront();
        } else if (event.getSource() == btnParkings) {
            vehiculeImageContainer.toFront();
            btnParkings.setStyle("-fx-background-color:#9656CD");
            btnVehicules.setStyle("");
            btnContrats.setStyle("");
            btnReservations.setStyle("");
            btnFactures.setStyle("");
            gestionTitre.setText("Gestion des parkings");
            gestionParkings.toFront();
        } else if (event.getSource() == btnContrats) {
            parkingImageContainer1.toFront();
            btnContrats.setStyle("-fx-background-color:#9656CD");
            btnVehicules.setStyle("");
            btnParkings.setStyle("");
            btnReservations.setStyle("");
            btnFactures.setStyle("");
            gestionTitre.setText("Gestion des contrats");
            gestionContrats.toFront();
        } else if (event.getSource() == btnReservations) {
            parkingImageContainer1.toFront();
            btnReservations.setStyle("-fx-background-color:#9656CD");
            btnVehicules.setStyle("");
            btnParkings.setStyle("");
            btnContrats.setStyle("");
            btnFactures.setStyle("");
            gestionTitre.setText("Gestion des réservations");
            gestionReservations.toFront();
        } else if (event.getSource() == btnFactures) {
            parkingImageContainer1.toFront();
            btnFactures.setStyle("-fx-background-color:#9656CD");
            btnVehicules.setStyle("");
            btnParkings.setStyle("");
            btnContrats.setStyle("");
            btnReservations.setStyle("");
            gestionTitre.setText("Gestion des factures");
            gestionFactures.toFront();
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

        listeVehiculeParParking2.getItems().clear();

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


        col_matricule2.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        col_marque2.setCellValueFactory(new PropertyValueFactory<>("marque"));
        col_type2.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_carburant2.setCellValueFactory(new PropertyValueFactory<>("carburant"));
        col_compteur2.setCellValueFactory(new PropertyValueFactory<>("compteurKM"));
        col_date2.setCellValueFactory(new PropertyValueFactory<>("dateMiseEnCirculation"));
        col_nParkingAssocie2.setCellValueFactory(new PropertyValueFactory<>("nParkingAssocie"));

        listeVehiculeParParking2.setItems(vehcleListe);

        listeVehiculeParParking2.toFront();

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
        this.populateComboxes();
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

        Parking selectedParking = listeParking.getSelectionModel().getSelectedItem();

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

    public void populateComboxes() {
        vehiculeComboBox.getItems().clear();
        parkingComboBox.getItems().clear();
        listeVehiculeAreserver.getItems().clear();

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
            ResultSet res = con.createStatement().executeQuery("SELECT matricule FROM vehicule WHERE is_reserve=0");

            while (res.next()) {
                vehiculesAresererListe.add(res.getString("matricule"));
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

        /*try {
            ResultSet res = con.createStatement().executeQuery("SELECT cin FROM client");

            while (res.next()) {
                clientReservant.add(res.getString("cin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        try {
            ResultSet res = con.createStatement().executeQuery("SELECT codeReservation FROM operation WHERE codeContrat=\" \"");

            while (res.next()) {
                reservationListeCombobox.add(res.getString("codeReservation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ResultSet res = con.createStatement().executeQuery("SELECT codeContrat FROM operation WHERE codeFacture=\" \"");

            while (res.next()) {
                contratListeCombobox.add(res.getString("codeContrat"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ResultSet res = con.createStatement().executeQuery("SELECT matricule FROM vehicule WHERE is_deposer=1 AND is_Sortis=0");

            while (res.next()) {
                matriculeComboBoxSortirListe.add(res.getString("matricule"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ResultSet res = con.createStatement().executeQuery("SELECT matricule FROM vehicule WHERE is_deposer=1 AND is_Sortis=1");

            while (res.next()) {
                matriculeRestituerComboBox.add(res.getString("matricule"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        vehiculeComboBox.setItems(vehiculesListeComboBox);
        parkingComboBox.setItems(parkingsListeComboBox);
        listeVehiculeAreserver.setItems(vehiculesAresererListe);
        listeClientReserver.setItems(clientReservant);
        listeReservationCombobox.setItems(reservationListeCombobox);
        listeContratsCombobox.setItems(contratListeCombobox);
        matriculeComboBoxSortir.setItems(matriculeComboBoxSortirListe);
        matriculeRestituer.setItems(matriculeRestituerComboBox);
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


    //Les traitements liées au gestion des contrats


    public void ajouterContrat(ActionEvent actionEvent) {

        String selectedReservationComboBox = listeReservationCombobox.getSelectionModel().getSelectedItem();

        if (nContartfield.getText().equals("") && dateContratField.getValue() == null && dateEcheanceContratField.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir les champs s'il vous plait !!");
        } else {
            Contrat nouveauContrat = new Contrat(nContartfield.getText(), dateContratField.getValue().toString(), dateEcheanceContratField.getValue().toString());
            utilisateur.ajouterContrat(nouveauContrat);

            this.populateContratListe();

            try {
                conn = DBConnect.getConnection();
                pat = conn.prepareStatement("UPDATE operation SET codeContrat=? WHERE codeReservation=?");

                pat.setString(1, nouveauContrat.getCodeContrat());
                pat.setString(2, selectedReservationComboBox);

                rs = pat.execute();

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            nContartfield.setText("");
            dateContratField.setValue(null);
            dateEcheanceContratField.setValue(null);
        }

    }

    private void populateContratListe() {

        listeContrats.getItems().clear();

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

        col_nContrat.setCellValueFactory(new PropertyValueFactory<>("codeContrat"));
        col_dateContrat.setCellValueFactory(new PropertyValueFactory<>("dateContrat"));
        col_dateEcheance.setCellValueFactory(new PropertyValueFactory<>("dateEcheanceContrat"));

        listeContrats.setItems(contratListe);

    }

    public void modifierContrat(ActionEvent actionEvent) {
        this.selectionnerContrat();
    }

    public void supprimerContrat(ActionEvent actionEvent) {
        this.selectionnerContrat();
        Contrat contratASupprimer = new Contrat(nContartfield.getText(), dateContratField.getValue().toString(), dateEcheanceContratField.getValue().toString());

        utilisateur.supprimerContrat(contratASupprimer);

        this.populateContratListe();

        nContartfield.setText("");
        dateContratField.setValue(null);
        dateEcheanceContratField.setValue(null);
    }

    public void afficherListeContrats(ActionEvent actionEvent) {
        listeContrats.toFront();
        this.populateContratListe();

        this.populateContratListe();
    }

    public void saveContrat(ActionEvent actionEvent) {

        Contrat nouveauContrat = new Contrat(nContartfield.getText(), dateContratField.getValue().toString(), dateEcheanceContratField.getValue().toString());
        utilisateur.modifierContrat(nouveauContrat);

    }

    public void chercherContrat(ActionEvent actionEvent) {
        listeContrats.toFront();

        listeContrats.getItems().clear();

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            pat = con.prepareStatement("SELECT * FROM contrat WHERE codeContrat=?");

            pat.setString(1, rechercherContratField.getText());
            ResultSet res = pat.executeQuery();

            while (res.next()) {
                contratListe.add(new Contrat(res.getString("codeContrat"), res.getString("dateContrat"), res.getString("dateEcheanceContrat")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        col_nContrat.setCellValueFactory(new PropertyValueFactory<>("codeContrat"));
        col_dateContrat.setCellValueFactory(new PropertyValueFactory<>("dateContrat"));
        col_dateEcheance.setCellValueFactory(new PropertyValueFactory<>("dateEcheanceContrat"));

        listeContrats.setItems(contratListe);

    }

    public void selectionnerContrat() {

        Contrat selectedContrat = listeContrats.getSelectionModel().getSelectedItem();

        nContartfield.setText(selectedContrat.getCodeContrat());
        dateContratField.setValue(LocalDate.parse(selectedContrat.getDateContrat()));
        dateEcheanceContratField.setValue(LocalDate.parse(selectedContrat.getDateEcheanceContrat()));

    }


    //Les traitements liées au gestion des réservations


    public void ajouterReservation(ActionEvent actionEvent) {

        String matriculeAreserver = listeVehiculeAreserver.getSelectionModel().getSelectedItem();
        String clientReservant = listeClientReserver.getSelectionModel().getSelectedItem();

        Reservation nouvelleReservation = new Reservation(codeReservationField.getText(), dateReservationField.getValue().toString(), dateDepartField.getValue().toString(), dateRetourField.getValue().toString());
        utilisateur.ajouterReservation(nouvelleReservation);

        this.populateReservationListe();

        try {
            conn = DBConnect.getConnection();
            pat = conn.prepareStatement("INSERT INTO operation(codeReservation, matricule, codeClient) VALUES(?, ?, ?)");

            pat.setString(1, nouvelleReservation.getCodeReservation());
            pat.setString(2, matriculeAreserver);
            pat.setString(3, clientReservant);

            rs = pat.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        nContartfield.setText("");
        dateReservationField.setValue(null);
        dateDepartField.setValue(null);
        dateRetourField.setValue(null);

    }

    private void populateReservationListe() {

        listeReservations.toFront();
        listeReservations.getItems().clear();

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

        col_codeReservation.setCellValueFactory(new PropertyValueFactory<>("codeReservation"));
        col_dateReservation.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
        col_dateDepart.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
        col_dateRetour.setCellValueFactory(new PropertyValueFactory<>("dateRetour"));

        listeReservations.setItems(reservationListe);


    }

    public void modifierReservation(ActionEvent actionEvent) {

        this.selectionnerReservation();

    }

    public void supprimerReservation(ActionEvent actionEvent) {

        this.selectionnerReservation();

        Reservation reservationASupprimer = new Reservation(codeReservationField.getText(), dateReservationField.getValue().toString(), dateDepartField.getValue().toString(), dateRetourField.getValue().toString());

        utilisateur.supprimerReservation(reservationASupprimer);

        this.populateReservationListe();

        nContartfield.setText("");
        dateReservationField.setValue(null);
        dateDepartField.setValue(null);
        dateRetourField.setValue(null);

    }

    public void afficherListeReservation(ActionEvent actionEvent) {

        this.populateReservationListe();

    }

    public void afficherListeReservationValidees(ActionEvent actionEvent) {

        listeReservations.toFront();
        listeReservations.getItems().clear();

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

        col_codeReservation.setCellValueFactory(new PropertyValueFactory<>("codeReservation"));
        col_dateReservation.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
        col_dateDepart.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
        col_dateRetour.setCellValueFactory(new PropertyValueFactory<>("dateRetour"));

        listeReservations.setItems(reservationListe);

    }

    public void afficherListeReservationNonValidees(ActionEvent actionEvent) {

        listeReservations.toFront();
        listeReservations.getItems().clear();

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

        col_codeReservation.setCellValueFactory(new PropertyValueFactory<>("codeReservation"));
        col_dateReservation.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
        col_dateDepart.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
        col_dateRetour.setCellValueFactory(new PropertyValueFactory<>("dateRetour"));

        listeReservations.setItems(reservationListe);

    }

    public void afficherListeReservationAnnulees(ActionEvent actionEvent) {

        listeReservations.toFront();
        listeReservations.getItems().clear();

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

        col_codeReservation.setCellValueFactory(new PropertyValueFactory<>("codeReservation"));
        col_dateReservation.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
        col_dateDepart.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
        col_dateRetour.setCellValueFactory(new PropertyValueFactory<>("dateRetour"));

        listeReservations.setItems(reservationListe);

    }

    public void saveReservation(ActionEvent actionEvent) {
        Reservation reservationAModifier = new Reservation(codeReservationField.getText(), dateReservationField.getValue().toString(), dateDepartField.getValue().toString(), dateRetourField.getValue().toString());
        utilisateur.modifierReservation(reservationAModifier);

        this.populateReservationListe();

        nContartfield.setText("");
        dateReservationField.setValue(null);
        dateDepartField.setValue(null);
        dateRetourField.setValue(null);
    }

    public void chercherReservation(ActionEvent actionEvent) {

        listeReservations.getItems().clear();

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            pat = con.prepareStatement("SELECT * FROM reservation WHERE codeReservation=?");

            pat.setString(1, rechercherReservationField.getText());
            ResultSet res = pat.executeQuery();

            while (res.next()) {
                reservationListe.add(new Reservation(res.getString("codeReservation"), res.getString("dateReservation"), res.getString("dateDepart"), res.getString("dateRetour")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        col_codeReservation.setCellValueFactory(new PropertyValueFactory<>("codeReservation"));
        col_dateReservation.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
        col_dateDepart.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
        col_dateRetour.setCellValueFactory(new PropertyValueFactory<>("dateRetour"));

        listeReservations.setItems(reservationListe);

    }

    public void selectionnerReservation() {
        Reservation selectedReservation = listeReservations.getSelectionModel().getSelectedItem();

        codeReservationField.setText(selectedReservation.getCodeReservation());
        dateReservationField.setValue(LocalDate.parse(selectedReservation.getDateReservation()));
        dateDepartField.setValue(LocalDate.parse(selectedReservation.getDateDepart()));
        dateRetourField.setValue(LocalDate.parse(selectedReservation.getDateRetour()));
    }

    // les traitements liés au gestion des factures

    public void ajouterFacture(ActionEvent actionEvent) {

        String selectedContratComboBox = listeContratsCombobox.getSelectionModel().getSelectedItem();

        Facture nouvelleFacture = new Facture(codeFactureField.getText(), dateFactureField.getValue().toString(), Double.parseDouble(montantPayerField.getText()));

        utilisateur.ajouterFacture(nouvelleFacture);

        this.populateFactureListe();

        try {
            conn = DBConnect.getConnection();
            pat = conn.prepareStatement("UPDATE operation SET codeFacture=? WHERE codeContrat=?");

            pat.setString(1, nouvelleFacture.getCodeFacture());
            pat.setString(2, selectedContratComboBox);

            rs = pat.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    private void populateFactureListe() {

        listeFactures.getItems().clear();

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

        col_codeFacture.setCellValueFactory(new PropertyValueFactory<>("codeFacture"));
        col_dateFacture.setCellValueFactory(new PropertyValueFactory<>("dateFacture"));
        col_montant.setCellValueFactory(new PropertyValueFactory<>("montantPayer"));

        listeFactures.setItems(factureListe);


    }

    public void modifierFacture(ActionEvent actionEvent) {

        this.selectionnerFacture();

    }

    public void supprimerFacture(ActionEvent actionEvent) {

        this.selectionnerFacture();

        Facture factureASupprimer = new Facture(codeFactureField.getText(), dateFactureField.getValue().toString(), Double.parseDouble(montantPayerField.getText()));

        utilisateur.supprimerFacture(factureASupprimer);

        this.populateFactureListe();

        codeFactureField.setText("");
        dateFactureField.setValue(null);
        montantPayerField.setText("");

    }

    public void afficherListeFacture(ActionEvent actionEvent) {

        this.populateFactureListe();

    }

    public void saveFacture(ActionEvent actionEvent) {
        Facture factureAModifier = new Facture(codeFactureField.getText(), dateFactureField.getValue().toString(), Double.parseDouble(montantPayerField.getText()));
        utilisateur.modifierFacture(factureAModifier);

        this.populateFactureListe();

        codeFactureField.setText("");
        dateFactureField.setValue(null);
        montantPayerField.setText("");
    }

    public void chercherFacture(ActionEvent actionEvent) {

        listeFactures.getItems().clear();

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            pat = con.prepareStatement("SELECT * FROM facture WHERE codeFacture=?");

            pat.setString(1, rechercherFactureField.getText());
            ResultSet res = pat.executeQuery();

            while (res.next()) {
                factureListe.add(new Facture(res.getString("codeFacture"), res.getString("dateFacture"), Double.parseDouble(res.getString("montant"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        col_codeFacture.setCellValueFactory(new PropertyValueFactory<>("codeFacture"));
        col_dateFacture.setCellValueFactory(new PropertyValueFactory<>("dateFacture"));
        col_montant.setCellValueFactory(new PropertyValueFactory<>("montantPayer"));

        listeFactures.setItems(factureListe);

    }

    public void selectionnerFacture() {
        Facture selectedFacture = listeFactures.getSelectionModel().getSelectedItem();

        codeFactureField.setText(selectedFacture.getCodeFacture());
        dateFactureField.setValue(LocalDate.parse(selectedFacture.getDateFacture()));
        montantPayerField.setText(Double.toString(selectedFacture.getMontantPayer()));

    }

    public void faireSortirOpenAnchor(ActionEvent actionEvent) {

    faireSortirAnchor.toFront();

    }

    public void restituerOpenAnchor(ActionEvent actionEvent){

        restituerAnchor.toFront();

    }

    public void faireSortirVehicule(ActionEvent actionEvent) {

        String matriculeFaireSortir = matriculeComboBoxSortir.getSelectionModel().getSelectedItem();

        utilisateur.faireSortirVehiculeDuParking(matriculeFaireSortir);

    }

    public void restituerVehicule(ActionEvent actionEvent) {

        String matriculeArestituer = matriculeRestituer.getSelectionModel().getSelectedItem();

        utilisateur.restituerVehicule(matriculeArestituer);

    }
}
