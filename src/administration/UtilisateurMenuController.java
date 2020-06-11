package administration;

import administration.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modules.*;
import org.sqlite.core.DB;
import utilisateur.Utilisateur;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class UtilisateurMenuController implements Initializable {

    Utilisateur utilisateur = new Utilisateur();

    @FXML
    private Label dateLabelUtilisateur;

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


    // Variables pour client

    @FXML
    private ImageView imgParking1111;

    @FXML
    private TableView<Client> listeClients;

    @FXML
    private TableColumn<Client, String> col_cin;

    @FXML
    private TableColumn<Client, String> col_nom;

    @FXML
    private TableColumn<Client, String> col_prenom;

    @FXML
    private TableColumn<Client, String> col_tel;

    @FXML
    private TableColumn<Client, String> col_adresse;

    @FXML
    private Button btnChercherClient;

    @FXML
    private AnchorPane principaleFieldsAnchor1111;

    @FXML
    private TextField codeClientField;

    @FXML
    private TextField nomClientField;

    @FXML
    private TextField prenomClientField;

    @FXML
    private TextField adresseClientField;

    @FXML
    private TextField telClientField;

    @FXML
    private TextField rechercherClientField;

    @FXML
    private AnchorPane gestionClients;

    @FXML
    private Label imgPermisName;


    // Variables pour les sanctions

    @FXML
    private AnchorPane gestionSanctions;

    @FXML
    private Button btnafficherListeClientSanctionnes;

    @FXML
    private Label listeClientSanctionneLabel;

    @FXML
    private TableView<TableViewSanction> listeClientSanctionne;

    @FXML
    private TableColumn<TableViewSanction, String> col_codeClientSanctionne;

    @FXML
    private TableColumn<TableViewSanction, String> col_codeReservationSanctionne;

    @FXML
    private TableColumn<TableViewSanction, String> col_codeCOntratSanctionne;

    @FXML
    private TableColumn<TableViewSanction, String> col_matriculeSanctionne;

    @FXML
    private TableColumn<TableViewSanction, String> col_montantSanction;

    @FXML
    private AnchorPane reglerSanctionAnchor;

    @FXML
    private CheckBox reglerSanctionCheck;


    //Traitements

    Connection conn;
    PreparedStatement pat;
    boolean rs;

    ObservableList<String> vehiculesListeComboBox = FXCollections.observableArrayList();
    ObservableList<String> parkingsListeComboBox = FXCollections.observableArrayList();
    ObservableList<String> vehiculesAresererListe = FXCollections.observableArrayList();
    ObservableList<String> clientReservant = FXCollections.observableArrayList();
    ObservableList<String> reservationListeCombobox = FXCollections.observableArrayList();
    ObservableList<String> contratListeCombobox = FXCollections.observableArrayList();
    ObservableList<String> matriculeComboBoxSortirListe = FXCollections.observableArrayList();
    ObservableList<String> matriculeRestituerComboBox = FXCollections.observableArrayList();
    ObservableList<TableViewSanction> clientSanctionneListe = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Générer la date dans un label

        dateLabelUtilisateur.setText("Aujourd'hui : " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd / MM / yyyy")));

        this.populateComboxes();

        String matriculeReserve = null;

        try {
            conn = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            //assert conn != null;
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
            ResultSet res = conn.createStatement().executeQuery("SELECT codeReservation FROM operation WHERE matricule NOT LIKE \" \" AND codeContrat NOT LIKE \" \" AND codeClient NOT LIKE \" \" AND codeFacture NOT LIKE \" \"");

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

        // Pour l'annulation des réservations:

        try {
            ResultSet res = conn.createStatement().executeQuery("SELECT codeReservation, dateDepart, date_validation FROM reservation");

            while (res.next()) {

                String codeReservation = res.getString("codeReservation");
                String dateDepart = res.getString("dateDepart");
                String dateValidation = res.getString("date_validation");

                if (ChronoUnit.DAYS.between(LocalDate.parse(dateDepart), LocalDate.parse(dateValidation)) < 2) {

                    try {
                        pat = conn.prepareStatement("UPDATE reservation SET is_annulee=1 WHERE codeReservation=?");

                        pat.setString(1, codeReservation);

                        rs = pat.execute();

                        JOptionPane.showMessageDialog(null, "la réservation " + codeReservation + " est annulée à cause de retard de validation");

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fonction pour la gestion des clicks de boutton de menu principal

    @FXML
    void handleClicks(ActionEvent event) {
        if (event.getSource() == btnVehicules) {
            parkingImageContainer.toFront();
            btnVehicules.setStyle("-fx-background-color:#9656CD");
            btnParkings.setStyle("");
            btnContrats.setStyle("");
            btnReservations.setStyle("");
            btnFactures.setStyle("");
            btnClients.setStyle("");
            btnSanctions.setStyle("");
            gestionTitre.setText("Gestion des véhicules");
            gestionVehicules.toFront();
        } else if (event.getSource() == btnParkings) {
            vehiculeImageContainer.toFront();
            btnParkings.setStyle("-fx-background-color:#9656CD");
            btnVehicules.setStyle("");
            btnContrats.setStyle("");
            btnReservations.setStyle("");
            btnFactures.setStyle("");
            btnClients.setStyle("");
            btnSanctions.setStyle("");
            gestionTitre.setText("Gestion des parkings");
            gestionParkings.toFront();
        } else if (event.getSource() == btnContrats) {
            parkingImageContainer1.toFront();
            btnContrats.setStyle("-fx-background-color:#9656CD");
            btnVehicules.setStyle("");
            btnParkings.setStyle("");
            btnReservations.setStyle("");
            btnFactures.setStyle("");
            btnClients.setStyle("");
            btnSanctions.setStyle("");
            gestionTitre.setText("Gestion des contrats");
            gestionContrats.toFront();
        } else if (event.getSource() == btnReservations) {
            parkingImageContainer1.toFront();
            btnReservations.setStyle("-fx-background-color:#9656CD");
            btnVehicules.setStyle("");
            btnParkings.setStyle("");
            btnContrats.setStyle("");
            btnFactures.setStyle("");
            btnClients.setStyle("");
            btnSanctions.setStyle("");
            gestionTitre.setText("Gestion des réservations");
            gestionReservations.toFront();
        } else if (event.getSource() == btnFactures) {
            parkingImageContainer1.toFront();
            btnFactures.setStyle("-fx-background-color:#9656CD");
            btnVehicules.setStyle("");
            btnParkings.setStyle("");
            btnContrats.setStyle("");
            btnClients.setStyle("");
            btnSanctions.setStyle("");
            btnReservations.setStyle("");
            gestionTitre.setText("Gestion des factures");
            gestionFactures.toFront();
        } else if (event.getSource() == btnClients) {
            parkingImageContainer1.toFront();
            btnClients.setStyle("-fx-background-color:#9656CD");
            btnFactures.setStyle("");
            btnVehicules.setStyle("");
            btnParkings.setStyle("");
            btnContrats.setStyle("");
            btnSanctions.setStyle("");
            btnReservations.setStyle("");
            gestionTitre.setText("Gestion des clients");
            gestionClients.toFront();
        } else if (event.getSource() == btnSanctions) {
            listeClientSanctionneLabel.setText("Liste des clients sanctionnés jusqu'à " + LocalDate.now());
            parkingImageContainer1.toFront();
            btnSanctions.setStyle("-fx-background-color:#9656CD");
            btnClients.setStyle("");
            btnFactures.setStyle("");
            btnVehicules.setStyle("");
            btnParkings.setStyle("");
            btnContrats.setStyle("");
            btnReservations.setStyle("");
            gestionTitre.setText("Gestion des sanctions");
            gestionSanctions.toFront();
        }
    }

    //Les traitements liées au gestion des véhicules

    @FXML
    void SaveVehicule(ActionEvent event) {

        Vehicule nouveauVehicule = new Vehicule(matriculeField.getText(), marqueField.getText(), typeField.getText(), carburantField.getText(), Double.parseDouble(compteurField.getText()), dateCirculationField.getValue().toString());

        if (utilisateur.modifierVehicule(nouveauVehicule)) {

            JOptionPane.showMessageDialog(null, "Modification avec succès !!");
        } else {
            JOptionPane.showMessageDialog(null, "La modification n'est pas réalisé :(");
        }
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

        if (matriculeField.getText().equals("") || marqueField.getText().equals("") || typeField.getText().equals("") || carburantField.getText().equals("") || compteurField.getText().equals("") || dateCirculationField.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir les champs s'il vous plait !!");
        } else {

            Vehicule nouveauVehicule = new Vehicule(matriculeField.getText(), marqueField.getText(), typeField.getText(), carburantField.getText(), Double.parseDouble(compteurField.getText()), dateCirculationField.getValue().toString());

            if (utilisateur.ajouterVehicule(nouveauVehicule)) {
                JOptionPane.showMessageDialog(null, "Ajout avec succès !!");
            } else {
                JOptionPane.showMessageDialog(null, "L'ajout n'est pas réalisé :(");
            }

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
    void modifierVehicule(ActionEvent event) {

        this.selectionnerVehicule();

    }

    @FXML
    void retourAcceuil(ActionEvent actionEvent) throws IOException {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();

        Stage acceuil = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("fxmls/acceuil.fxml"));
        acceuil.initStyle(StageStyle.UNDECORATED);
        acceuil.setScene(new Scene(root));
        acceuil.show();

    }

    @FXML
    void supprimerVehicule(ActionEvent event) {

        this.selectionnerVehicule();

        Vehicule vehiculeASupprimer = new Vehicule(matriculeField.getText(), marqueField.getText(), typeField.getText(), carburantField.getText(), Double.parseDouble(compteurField.getText()), dateCirculationField.getValue().toString());

        if (utilisateur.supprimerVehicule(vehiculeASupprimer)) {

            JOptionPane.showMessageDialog(null, "Suppression avec succès !!");
        } else {
            JOptionPane.showMessageDialog(null, "La suppression n'est pas réalisé :(");
        }

        this.populateVehiculeListe();

        matriculeField.setText("");
        marqueField.setText("");
        typeField.setText("");
        carburantField.setText("");
        compteurField.setText("");
        dateCirculationField.setValue(null);

        this.populateComboxes();
    }

    @FXML
    void chercherVehicule(ActionEvent event) {

        listeVehiculeParParking2.getItems().clear();

        String matriculeARechercher = rechercherVehiculeField.getText();

        col_matricule2.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        col_marque2.setCellValueFactory(new PropertyValueFactory<>("marque"));
        col_type2.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_carburant2.setCellValueFactory(new PropertyValueFactory<>("carburant"));
        col_compteur2.setCellValueFactory(new PropertyValueFactory<>("compteurKM"));
        col_date2.setCellValueFactory(new PropertyValueFactory<>("dateMiseEnCirculation"));
        col_nParkingAssocie2.setCellValueFactory(new PropertyValueFactory<>("nParkingAssocie"));

        listeVehiculeParParking2.setItems(utilisateur.rechercherVehicule(matriculeARechercher));

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

        col_matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        col_marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_carburant.setCellValueFactory(new PropertyValueFactory<>("carburant"));
        col_compteur.setCellValueFactory(new PropertyValueFactory<>("compteurKM"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateMiseEnCirculation"));

        vehiculeListe.setItems(utilisateur.afficherVehiculeListe());
    }


    //Les traitements liées au gestion des parkings

    public void ajouterParking(ActionEvent actionEvent) {
        if (codeParkingField.getText().equals("") || capaciteField.getText().equals("") || rueField.getText().equals("") || arrondissementField.getText().equals("") || nbrePlacesVidesField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir les champs s'il vous plait !!");
        } else {
            Parking nouveauParking = new Parking(codeParkingField.getText(), Integer.parseInt(capaciteField.getText()), rueField.getText(), arrondissementField.getText(), Integer.parseInt(nbrePlacesVidesField.getText()));

            if (utilisateur.ajouterParking(nouveauParking)) {

                JOptionPane.showMessageDialog(null, "Ajout avec succès !!");
            } else {
                JOptionPane.showMessageDialog(null, "L'ajout n'est pas réalisé :(");
            }

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

        if (utilisateur.supprimerParking(parkingASupprimer)) {

            JOptionPane.showMessageDialog(null, "Suppression avec succès !!");
        } else {
            JOptionPane.showMessageDialog(null, "La suppression n'est pas réalisé :(");
        }

        this.populateVehiculeListeParParking();

        codeParkingField.setText("");
        capaciteField.setText("");
        rueField.setText("");
        arrondissementField.setText("");
        nbrePlacesVidesField.setText("");

        this.populateComboxes();

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
    void exit(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();

    }


    public void afficherListeVehiculesParParking(ActionEvent actionEvent) {
        listeParkings.toFront();

        this.populateVehiculeListeParParking();
    }

    public void SaveParking(ActionEvent actionEvent) {

        Parking nouveauParking = new Parking(codeParkingField.getText(), Integer.parseInt(capaciteField.getText()), rueField.getText(), arrondissementField.getText(), Integer.parseInt(nbrePlacesVidesField.getText()));

        if (utilisateur.modifierParking(nouveauParking)) {

            JOptionPane.showMessageDialog(null, "Modification avec succès !!");
        } else {
            JOptionPane.showMessageDialog(null, "La modification n'est pas réalisé :(");
        }

        this.populateVehiculeListeParParking();

        codeParkingField.setText("");
        capaciteField.setText("");
        rueField.setText("");
        arrondissementField.setText("");
        nbrePlacesVidesField.setText("");

        this.populateComboxes();

    }

    private void populateVehiculeListeParParking() {

        listeVehiculeParParking.getItems().clear();

        col_matricule1.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        col_marque1.setCellValueFactory(new PropertyValueFactory<>("marque"));
        col_type1.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_carburant1.setCellValueFactory(new PropertyValueFactory<>("carburant"));
        col_compteur1.setCellValueFactory(new PropertyValueFactory<>("compteurKM"));
        col_date1.setCellValueFactory(new PropertyValueFactory<>("dateMiseEnCirculation"));
        col_nParkingAssocie1.setCellValueFactory(new PropertyValueFactory<>("nParkingAssocie"));

        listeVehiculeParParking.setItems(utilisateur.afficherListeVehiculeParParking());


        listeParking.getItems().clear();

        col_codeParking.setCellValueFactory(new PropertyValueFactory<>("codeParking"));
        col_capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        col_rue.setCellValueFactory(new PropertyValueFactory<>("rue"));
        col_arrondisselent.setCellValueFactory(new PropertyValueFactory<>("arrondissement"));
        col_nbrePlacesVides.setCellValueFactory(new PropertyValueFactory<>("nbrPlacesVides"));

        listeParking.setItems(utilisateur.afficherParkingListe());

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

        // ComBox pour afficher la liste des véhicule pour les déposer

        try {
            assert con != null;
            ResultSet res = con.createStatement().executeQuery("SELECT matricule FROM vehicule WHERE is_deposer=0");

            while (res.next()) {
                vehiculesListeComboBox.add(res.getString("matricule"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //

        try {
            ResultSet res = con.createStatement().executeQuery("SELECT matricule FROM vehicule WHERE is_reserve=0");

            while (res.next()) {
                vehiculesAresererListe.add(res.getString("matricule"));
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

        try {
            ResultSet res = con.createStatement().executeQuery("SELECT cin FROM client");

            while (res.next()) {
                clientReservant.add(res.getString("cin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

        String parkingARechercher = rechercherParkingField.getText();


        col_codeParking.setCellValueFactory(new PropertyValueFactory<>("codeParking"));
        col_capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        col_rue.setCellValueFactory(new PropertyValueFactory<>("rue"));
        col_arrondisselent.setCellValueFactory(new PropertyValueFactory<>("arrondissement"));
        col_nbrePlacesVides.setCellValueFactory(new PropertyValueFactory<>("nbrPlacesVides"));

        listeParking.setItems(utilisateur.rechercherParking(parkingARechercher));
    }


    public void deposerRestituerAnchor(ActionEvent actionEvent) {
        deposerRestituerAnchor.toFront();
    }

    public void deposerVehicule(ActionEvent actionEvent) {
        String selectedMatriculeComboBox = vehiculeComboBox.getSelectionModel().getSelectedItem();
        String selectedCodeParkingComboBox = parkingComboBox.getSelectionModel().getSelectedItem();

        if (utilisateur.deposerVehiculeDansParking(selectedMatriculeComboBox, selectedCodeParkingComboBox)) {
            JOptionPane.showMessageDialog(null, "Le véhicule a été bien déposé.");
        } else {
            JOptionPane.showMessageDialog(null, "Le véhicule n'est pas déposé correctement.");
        }

        this.populateVehiculeListeParParking();
        this.populateComboxes();
    }

    public void faireSortirOpenAnchor(ActionEvent actionEvent) {

        faireSortirAnchor.toFront();

    }

    public void restituerOpenAnchor(ActionEvent actionEvent) {

        restituerAnchor.toFront();

    }

    public void faireSortirVehicule(ActionEvent actionEvent) {

        String matriculeFaireSortir = matriculeComboBoxSortir.getSelectionModel().getSelectedItem();

        if (utilisateur.faireSortirVehiculeDuParking(matriculeFaireSortir)) {
            JOptionPane.showMessageDialog(null, "Le véhicule a été bien sortis de son parking.");
        } else {
            JOptionPane.showMessageDialog(null, "Le véhicule n'est pas sortis correctement.");
        }

        this.populateComboxes();

    }

    public void restituerVehicule(ActionEvent actionEvent) {

        String matriculeArestituer = matriculeRestituer.getSelectionModel().getSelectedItem();

        if (utilisateur.restituerVehicule(matriculeArestituer)) {
            JOptionPane.showMessageDialog(null, "Le véhicule a été bien réstitué.");
        } else {
            JOptionPane.showMessageDialog(null, "Le véhicule n'est pas réstité correctement.");
        }

        this.populateComboxes();

        // Traitement pour définir les sanctions:

        try {
            assert conn != null;
            PreparedStatement pat = conn.prepareStatement("SELECT codeContrat, codeFacture, codeClient FROM operation WHERE matricule=?");

            pat.setString(1, matriculeArestituer);

            ResultSet res = pat.executeQuery();

            while (res.next()) {

                String codeContrat = res.getString("codeContrat");
                String codeFacture = res.getString("codeFacture");
                String codeClient = res.getString("codeClient");

                PreparedStatement prep = conn.prepareStatement("SELECT dateEcheanceContrat FROM contrat WHERE codeContrat=?");

                prep.setString(1, codeContrat);

                ResultSet resultSet = prep.executeQuery();

                String dateEcheanceContrat = resultSet.getString("dateEcheanceContrat");

                double period = ChronoUnit.DAYS.between(LocalDate.parse(dateEcheanceContrat), LocalDate.now());

                if (period > 0) {

                    try {
                        pat = conn.prepareStatement("UPDATE facture SET montantSanction=montantSanction+? WHERE codeFacture=?");

                        pat.setString(1, Double.toString((period * 2000)));
                        pat.setString(2, codeFacture);

                        rs = pat.execute();

                        JOptionPane.showMessageDialog(null, "Il y a un montant ajouter à la facture " + codeFacture + " comme étant une sanction.");

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    try {
                        pat = conn.prepareStatement("UPDATE client SET is_sanctionné=1 WHERE cin=?");

                        pat.setString(1, codeClient);

                        rs = pat.execute();

                        JOptionPane.showMessageDialog(null, "Le client à CIN " + codeClient + " sera sanctionné à cause du retard de réstitution.");

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Traitement pour les statistiques

        try {
            conn = DBConnect.getConnection();
            pat = conn.prepareStatement("UPDATE statistiques SET nombre_véhicule=nombre_véhicule+1 WHERE mois=?");

            pat.setString(1, LocalDate.now().getMonth().toString());

            rs = pat.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }


    public void retourPrincipaleFieldsParkings(ActionEvent actionEvent) {

        principaleFieldsAnchor.toFront();

    }


    //Les traitements liées au gestion des contrats


    public void ajouterContrat(ActionEvent actionEvent) {

        String selectedReservationComboBox = listeReservationCombobox.getSelectionModel().getSelectedItem();

        if (nContartfield.getText().equals("") || dateContratField.getValue() == null || dateEcheanceContratField.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir les champs s'il vous plait !!");
        } else if (ChronoUnit.DAYS.between(dateContratField.getValue(), dateEcheanceContratField.getValue()) < 0) {

            JOptionPane.showMessageDialog(null, "La date d'échéance du contrat ne doit pas etre avant la date du contrat !!");

        } else {
            Contrat nouveauContrat = new Contrat(nContartfield.getText(), dateContratField.getValue().toString(), dateEcheanceContratField.getValue().toString());

            if (utilisateur.ajouterContrat(nouveauContrat)) {
                JOptionPane.showMessageDialog(null, "Ajout avec succès !!");
            } else {
                JOptionPane.showMessageDialog(null, "L'ajout n'est pas réalisé :(");
            }

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

            try {
                conn = DBConnect.getConnection();
                pat = conn.prepareStatement("UPDATE reservation SET date_validation=? WHERE codeReservation=?");

                pat.setString(1, LocalDate.now().toString());
                pat.setString(2, selectedReservationComboBox);

                rs = pat.execute();

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            nContartfield.setText("");
            dateContratField.setValue(null);
            dateEcheanceContratField.setValue(null);

        }

        this.populateComboxes();

    }

    private void populateContratListe() {

        listeContrats.getItems().clear();

        col_nContrat.setCellValueFactory(new PropertyValueFactory<>("codeContrat"));
        col_dateContrat.setCellValueFactory(new PropertyValueFactory<>("dateContrat"));
        col_dateEcheance.setCellValueFactory(new PropertyValueFactory<>("dateEcheanceContrat"));

        listeContrats.setItems(utilisateur.afficherContratListe());

    }

    public void modifierContrat(ActionEvent actionEvent) {
        this.selectionnerContrat();
    }

    public void supprimerContrat(ActionEvent actionEvent) {
        this.selectionnerContrat();
        Contrat contratASupprimer = new Contrat(nContartfield.getText(), dateContratField.getValue().toString(), dateEcheanceContratField.getValue().toString());

        if (utilisateur.supprimerContrat(contratASupprimer)) {
            JOptionPane.showMessageDialog(null, "Suppression avec succès !!");
        } else {
            JOptionPane.showMessageDialog(null, "La suppression n'est pas réalisé :(");
        }

        this.populateContratListe();

        nContartfield.setText("");
        dateContratField.setValue(null);
        dateEcheanceContratField.setValue(null);

        this.populateComboxes();

    }

    public void afficherListeContrats(ActionEvent actionEvent) {
        listeContrats.toFront();
        this.populateContratListe();

        this.populateContratListe();
    }

    public void saveContrat(ActionEvent actionEvent) {

        Contrat nouveauContrat = new Contrat(nContartfield.getText(), dateContratField.getValue().toString(), dateEcheanceContratField.getValue().toString());

        if (utilisateur.modifierContrat(nouveauContrat)) {
            JOptionPane.showMessageDialog(null, "Modification avec succès !!");
        } else {
            JOptionPane.showMessageDialog(null, "La modification n'est pas réalisé :(");
        }

        this.populateComboxes();

    }

    public void chercherContrat(ActionEvent actionEvent) {
        listeContrats.toFront();

        listeContrats.getItems().clear();

        String contratARechercher = rechercherContratField.getText();

        col_nContrat.setCellValueFactory(new PropertyValueFactory<>("codeContrat"));
        col_dateContrat.setCellValueFactory(new PropertyValueFactory<>("dateContrat"));
        col_dateEcheance.setCellValueFactory(new PropertyValueFactory<>("dateEcheanceContrat"));

        listeContrats.setItems(utilisateur.rechercherContrat(contratARechercher));

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

        if (codeReservationField.getText().equals("") || dateReservationField.getValue() == null || dateDepartField.getValue() == null || dateRetourField.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir les champs s'il vous plait !!");
        } else if (ChronoUnit.DAYS.between(dateReservationField.getValue(), dateDepartField.getValue()) < 0 || ChronoUnit.DAYS.between(dateDepartField.getValue(), dateRetourField.getValue()) < 0) {
            JOptionPane.showMessageDialog(null, "Veuillez vérifier les dates s'il vous plait !!");
        } else {

            Reservation nouvelleReservation = new Reservation(codeReservationField.getText(), dateReservationField.getValue().toString(), dateDepartField.getValue().toString(), dateRetourField.getValue().toString());

            if (utilisateur.ajouterReservation(nouvelleReservation)) {
                JOptionPane.showMessageDialog(null, "Ajout avec succès !!");
            } else {
                JOptionPane.showMessageDialog(null, "L'ajout n'est pas réalisé :(");
            }

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

            try {
                conn = DBConnect.getConnection();
                pat = conn.prepareStatement("UPDATE vehicule SET is_reserve=1 WHERE matricule=?");

                pat.setString(1, matriculeAreserver);

                rs = pat.execute();

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            nContartfield.setText("");
            dateReservationField.setValue(null);
            dateDepartField.setValue(null);
            dateRetourField.setValue(null);

        }
        this.populateComboxes();

    }

    private void populateReservationListe() {

        listeReservations.toFront();
        listeReservations.getItems().clear();

        col_codeReservation.setCellValueFactory(new PropertyValueFactory<>("codeReservation"));
        col_dateReservation.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
        col_dateDepart.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
        col_dateRetour.setCellValueFactory(new PropertyValueFactory<>("dateRetour"));

        listeReservations.setItems(utilisateur.afficherReservationListe());


    }

    public void modifierReservation(ActionEvent actionEvent) {

        this.selectionnerReservation();

    }

    public void supprimerReservation(ActionEvent actionEvent) {

        this.selectionnerReservation();

        Reservation reservationASupprimer = new Reservation(codeReservationField.getText(), dateReservationField.getValue().toString(), dateDepartField.getValue().toString(), dateRetourField.getValue().toString());

        if (utilisateur.supprimerReservation(reservationASupprimer)) {
            JOptionPane.showMessageDialog(null, "Suppression avec succès !!");
        } else {
            JOptionPane.showMessageDialog(null, "La suppression n'est pas réalisé :(");
        }

        this.populateReservationListe();

        nContartfield.setText("");
        dateReservationField.setValue(null);
        dateDepartField.setValue(null);
        dateRetourField.setValue(null);

        this.populateComboxes();

    }

    public void afficherListeReservation(ActionEvent actionEvent) {

        listeReservations.toFront();

        this.populateReservationListe();

    }

    public void afficherListeReservationValidees(ActionEvent actionEvent) {

        listeReservations.toFront();
        listeReservations.getItems().clear();

        col_codeReservation.setCellValueFactory(new PropertyValueFactory<>("codeReservation"));
        col_dateReservation.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
        col_dateDepart.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
        col_dateRetour.setCellValueFactory(new PropertyValueFactory<>("dateRetour"));

        listeReservations.setItems(utilisateur.afficherListeReservationValidees());

    }

    public void afficherListeReservationNonValidees(ActionEvent actionEvent) {

        listeReservations.toFront();
        listeReservations.getItems().clear();

        col_codeReservation.setCellValueFactory(new PropertyValueFactory<>("codeReservation"));
        col_dateReservation.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
        col_dateDepart.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
        col_dateRetour.setCellValueFactory(new PropertyValueFactory<>("dateRetour"));

        listeReservations.setItems(utilisateur.afficherListeReservationNonValidees());

    }

    public void afficherListeReservationAnnulees(ActionEvent actionEvent) {

        listeReservations.toFront();
        listeReservations.getItems().clear();

        col_codeReservation.setCellValueFactory(new PropertyValueFactory<>("codeReservation"));
        col_dateReservation.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
        col_dateDepart.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
        col_dateRetour.setCellValueFactory(new PropertyValueFactory<>("dateRetour"));

        listeReservations.setItems(utilisateur.afficherListeReservationAnnulees());

    }

    public void saveReservation(ActionEvent actionEvent) {
        Reservation reservationAModifier = new Reservation(codeReservationField.getText(), dateReservationField.getValue().toString(), dateDepartField.getValue().toString(), dateRetourField.getValue().toString());

        if (utilisateur.modifierReservation(reservationAModifier)) {
            JOptionPane.showMessageDialog(null, "Modification avec succès !!");
        } else {
            JOptionPane.showMessageDialog(null, "La modification n'est pas réalisé :(");
        }

        this.populateReservationListe();

        nContartfield.setText("");
        dateReservationField.setValue(null);
        dateDepartField.setValue(null);
        dateRetourField.setValue(null);

        this.populateComboxes();
    }

    public void chercherReservation(ActionEvent actionEvent) {

        listeReservations.getItems().clear();

        String reservationARechercher = rechercherReservationField.getText();

        col_codeReservation.setCellValueFactory(new PropertyValueFactory<>("codeReservation"));
        col_dateReservation.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
        col_dateDepart.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
        col_dateRetour.setCellValueFactory(new PropertyValueFactory<>("dateRetour"));

        listeReservations.setItems(utilisateur.rechercherReservation(reservationARechercher));

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

        if (codeFactureField.getText().equals("") || dateFactureField.getValue() == null || montantPayerField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir les champs s'il vous plait !!");
        } else {

            Facture nouvelleFacture = new Facture(codeFactureField.getText(), dateFactureField.getValue().toString(), Double.parseDouble(montantPayerField.getText()));

            if (utilisateur.ajouterFacture(nouvelleFacture)) {
                JOptionPane.showMessageDialog(null, "Ajout avec succès !!");
            } else {
                JOptionPane.showMessageDialog(null, "L'ajout n'est pas réalisé :(");
            }

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

            codeFactureField.setText("");
            dateFactureField.setValue(null);
            montantPayerField.setText("");

        }

        this.populateComboxes();

    }

    private void populateFactureListe() {

        listeFactures.getItems().clear();

        col_codeFacture.setCellValueFactory(new PropertyValueFactory<>("codeFacture"));
        col_dateFacture.setCellValueFactory(new PropertyValueFactory<>("dateFacture"));
        col_montant.setCellValueFactory(new PropertyValueFactory<>("montantPayer"));

        listeFactures.setItems(utilisateur.afficherFactureListe());

    }

    public void modifierFacture(ActionEvent actionEvent) {

        this.selectionnerFacture();

        codeFactureField.setText("");
        dateFactureField.setValue(null);
        montantPayerField.setText("");

    }

    public void supprimerFacture(ActionEvent actionEvent) {

        this.selectionnerFacture();

        Facture factureASupprimer = new Facture(codeFactureField.getText(), dateFactureField.getValue().toString(), Double.parseDouble(montantPayerField.getText()));

        if (utilisateur.supprimerFacture(factureASupprimer)) {
            JOptionPane.showMessageDialog(null, "Suppression avec succès !!");
        } else {
            JOptionPane.showMessageDialog(null, "La suppression n'est pas réalisé :(");
        }

        this.populateFactureListe();

        codeFactureField.setText("");
        dateFactureField.setValue(null);
        montantPayerField.setText("");

        this.populateComboxes();

    }

    public void afficherListeFacture(ActionEvent actionEvent) {

        listeFactures.toFront();

        this.populateFactureListe();

    }

    public void saveFacture(ActionEvent actionEvent) {
        Facture factureAModifier = new Facture(codeFactureField.getText(), dateFactureField.getValue().toString(), Double.parseDouble(montantPayerField.getText()));

        if (utilisateur.modifierFacture(factureAModifier)) {
            JOptionPane.showMessageDialog(null, "Modification avec succès !!");
        } else {
            JOptionPane.showMessageDialog(null, "La modification n'est pas réalisé :(");
        }

        this.populateFactureListe();

        codeFactureField.setText("");
        dateFactureField.setValue(null);
        montantPayerField.setText("");

        this.populateComboxes();
    }

    public void chercherFacture(ActionEvent actionEvent) {

        listeFactures.getItems().clear();

        String factureARechercher = rechercherFactureField.getText();

        col_codeFacture.setCellValueFactory(new PropertyValueFactory<>("codeFacture"));
        col_dateFacture.setCellValueFactory(new PropertyValueFactory<>("dateFacture"));
        col_montant.setCellValueFactory(new PropertyValueFactory<>("montantPayer"));

        listeFactures.setItems(utilisateur.rechercherFacture(factureARechercher));

    }

    public void selectionnerFacture() {
        Facture selectedFacture = listeFactures.getSelectionModel().getSelectedItem();

        codeFactureField.setText(selectedFacture.getCodeFacture());
        dateFactureField.setValue(LocalDate.parse(selectedFacture.getDateFacture()));
        montantPayerField.setText(Double.toString(selectedFacture.getMontantPayer()));

    }


    // les traitements liés au gestion des clients

    public void ajouterClient(ActionEvent actionEvent) {

        if (codeClientField.getText().equals("") || nomClientField.getText().equals("") || prenomClientField.getText().equals("") || adresseClientField.getText().equals("") || telClientField.getText().equals("") || imgPermisName.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir les champs s'il vous plait !!");
        } else {

            Client nouveauClient = new Client(codeClientField.getText(), nomClientField.getText(), prenomClientField.getText(), adresseClientField.getText(), telClientField.getText(), imgPermisName.getText());

            if (utilisateur.ajouterClient(nouveauClient)) {
                JOptionPane.showMessageDialog(null, "Ajout avec succès !!");
            } else {
                JOptionPane.showMessageDialog(null, "L'ajout n'est pas réalisé :(");
            }

            this.populateCLientListe();

            codeClientField.setText("");
            nomClientField.setText("");
            prenomClientField.setText("");
            adresseClientField.setText("");
            telClientField.setText("");
            imgPermisName.setText("");

        }
        this.populateComboxes();

    }

    public void modifierClient(ActionEvent actionEvent) {

        this.selectionnerClient();

    }

    public void supprimerClient(ActionEvent actionEvent) {

        this.selectionnerClient();

        Client clientASupprimer = new Client(codeClientField.getText(), nomClientField.getText(), prenomClientField.getText(), adresseClientField.getText(), telClientField.getText(), imgPermisName.getText());

        if (utilisateur.supprimerClient(clientASupprimer)) {
            JOptionPane.showMessageDialog(null, "Suppression avec succès !!");
        } else {
            JOptionPane.showMessageDialog(null, "La suppression n'est pas réalisé :(");
        }

        this.populateCLientListe();

        codeClientField.setText("");
        nomClientField.setText("");
        prenomClientField.setText("");
        adresseClientField.setText("");
        telClientField.setText("");
        imgPermisName.setText("");

        this.populateComboxes();

    }

    public void afficherListeCLient(ActionEvent actionEvent) {

        listeClients.toFront();

        this.populateCLientListe();

    }

    public void saveClient(ActionEvent actionEvent) {

        Client clientAModifier = new Client(codeClientField.getText(), nomClientField.getText(), prenomClientField.getText(), adresseClientField.getText(), telClientField.getText(), imgPermisName.getText());

        if (utilisateur.modifierClient(clientAModifier)) {
            JOptionPane.showMessageDialog(null, "Modification avec succès !!");
        } else {
            JOptionPane.showMessageDialog(null, "La modification n'est pas réalisé :(");
        }

        this.populateCLientListe();

        codeClientField.setText("");
        nomClientField.setText("");
        prenomClientField.setText("");
        adresseClientField.setText("");
        telClientField.setText("");
        imgPermisName.setText("");

        this.populateComboxes();

    }

    public void chercherClient(ActionEvent actionEvent) {

        listeClients.getItems().clear();

        String clientARechercher = rechercherClientField.getText();

        col_cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));

        listeClients.setItems(utilisateur.rechercherClient(clientARechercher));

    }

    public void choisirImagePermis(ActionEvent actionEvent) {

        FileChooser imgPermisChooser = new FileChooser();
        File selectedFile = imgPermisChooser.showOpenDialog(null);

        if (selectedFile != null) {
            imgPermisName.setText(selectedFile.getAbsolutePath());
        } else {
            JOptionPane.showMessageDialog(null, "Vous n'avez pas choisi un fichier ??");
        }

    }

    public void populateCLientListe() {
        listeClients.getItems().clear();

        col_cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));

        listeClients.setItems(utilisateur.afficherClientListe());
    }

    public void selectionnerClient() {
        Client selectedClient = listeClients.getSelectionModel().getSelectedItem();

        codeClientField.setText(selectedClient.getCin());
        nomClientField.setText(selectedClient.getNom());
        prenomClientField.setText(selectedClient.getPrenom());
        adresseClientField.setText(selectedClient.getAdresse());
        telClientField.setText(selectedClient.getTel());
        imgPermisName.setText(selectedClient.getImgPermis());

    }


    // les traitements liés au gestion des sanctions

    public void afficherListeClientSanctionnes(ActionEvent actionEvent) throws SQLException {

        reglerSanctionAnchor.setVisible(true);

        listeClientSanctionne.getItems().clear();


        col_codeClientSanctionne.setCellValueFactory(new PropertyValueFactory<>("cinClient"));
        col_codeReservationSanctionne.setCellValueFactory(new PropertyValueFactory<>("codeReservation"));
        col_codeCOntratSanctionne.setCellValueFactory(new PropertyValueFactory<>("codeContrat"));
        col_matriculeSanctionne.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        col_montantSanction.setCellValueFactory(new PropertyValueFactory<>("montantSanction"));

        listeClientSanctionne.setItems(utilisateur.afficherListeClientSanctionnes());

    }

    public void reglerSanction(ActionEvent actionEvent) {

        TableViewSanction selectedItem = listeClientSanctionne.getSelectionModel().getSelectedItem();

        if (reglerSanctionCheck.isSelected()) {

            String codeFactureConcerne = selectedItem.getCodeFacture();
            String codeClientConcerne = selectedItem.getCinClient();

            utilisateur.reglerSanction(codeFactureConcerne, codeClientConcerne);

        } else {
            System.out.println("Nothing");
        }

    }
}
