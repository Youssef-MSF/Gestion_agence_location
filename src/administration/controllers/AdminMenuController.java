package administration.controllers;

import administration.Administrateur;
import administration.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utilisateur.Utilisateur;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {

    Administrateur admin = new Administrateur();


    @FXML
    private TableColumn<Utilisateur, String> col_id;

    @FXML
    private TableColumn<Utilisateur, String> col_nom;

    @FXML
    private TableColumn<Utilisateur, String> col_prenom;

    @FXML
    private TableColumn<Utilisateur, String> col_adresse;

    @FXML
    private TableColumn<Utilisateur, String> col_tel;

    @FXML
    private TableColumn<Utilisateur, String> col_cin;

    @FXML
    private TableView<Utilisateur> utilisateursListe;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;

    @FXML
    private Button btnSuspendre;

    @FXML
    private Button btnSave;

    @FXML
    private Button acceuil;

    @FXML
    private Button btnUtilisateur;
    @FXML
    private Button btnContrat;
    @FXML
    private Button btnReservations;
    @FXML
    private AnchorPane gestionUtilisateurs;
    @FXML
    private Label gestionTitre;

    @FXML
    private TextField idField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField adresseField;

    @FXML
    private TextField telField;

    @FXML
    private TextField cinField;

    Connection conn;
    PreparedStatement pat;
    boolean rs;

    ObservableList<Utilisateur> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        PreparedStatement pat;
        Connection con = null;
        /*try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("CREATE TABLE IF NOT EXISTS utilisateur (id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                    "    nom VARCHAR(100),\n" +
                    "    prenom VARCHAR(100),\n" +
                    "    adresse VARCHAR(255),\n" +
                    "    tel VARCHAR(255),\n" +
                    "    cin VARCHAR(10),\n" +
                    "    is_suspend tinyint(1))");

            rs = pat.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/


        this.populateUtilisateurListe();

    }

    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnUtilisateur) {
            btnUtilisateur.setStyle("-fx-background-color:#9656CD");
            btnContrat.setStyle("");
            gestionTitre.setText("Gestion des utilisateurs");
        } else if (actionEvent.getSource() == btnContrat) {
            btnContrat.setStyle("-fx-background-color:#9656CD");
            btnUtilisateur.setStyle("");
            gestionTitre.setText("Gestion des contrat");
        }
    }

    public void ajouterUtilisateur(ActionEvent actionEvent) {

        Utilisateur nouveauUtilisateur = new Utilisateur(nomField.getText(), prenomField.getText(), adresseField.getText(), telField.getText(), cinField.getText(), false);

        admin.ajouterUtilisateur(nouveauUtilisateur);
        nomField.setText("");
        prenomField.setText("");
        adresseField.setText("");
        telField.setText("");
        cinField.setText("");
        this.populateUtilisateurListe();

    }

    public void modifierUtilisateur(ActionEvent actionEvent) {

        this.selectionnerUtilisateur();
    }

    public void supprimerUtilisateur(ActionEvent actionEvent) {

        this.selectionnerUtilisateur();

        Utilisateur utilisateurASupprimer = new Utilisateur(nomField.getText(), prenomField.getText(), adresseField.getText(), cinField.getText(), telField.getText(),false);
        utilisateurASupprimer.setId(Integer.parseInt(idField.getText()));

        admin.supprimerUtilisateur(utilisateurASupprimer);

        nomField.setText("");
        prenomField.setText("");
        adresseField.setText("");
        telField.setText("");
        cinField.setText("");

        this.populateUtilisateurListe();

    }

    public void suspendreUtilisateur(ActionEvent actionEvent) {

        this.selectionnerUtilisateur();

        Utilisateur utilisateurASuspendre = new Utilisateur(nomField.getText(), prenomField.getText(), adresseField.getText(), cinField.getText(), telField.getText(),false);
        utilisateurASuspendre.setId(Integer.parseInt(idField.getText()));

        admin.suspendreUtilisateur(utilisateurASuspendre);

        this.populateUtilisateurListe();
    }



    public void retourAcceuil(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void populateUtilisateurListe(){

        utilisateursListe.getItems().clear();

        Connection con = null;
        try {
            con = DBConnect.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            assert con != null;
            ResultSet res = con.createStatement().executeQuery("select * from utilisateur");

            while (res.next()) {
                oblist.add(new Utilisateur(Integer.parseInt(res.getString("id")), res.getString("nom"), res.getString("prenom"), res.getString("adresse"), res.getString("tel"), res.getString("cin")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("cin"));
        col_cin.setCellValueFactory(new PropertyValueFactory<>("tel"));

        utilisateursListe.setItems(oblist);

    }

    public void selectionnerUtilisateur(){
        Utilisateur selectedUtilisateur = utilisateursListe.getSelectionModel().getSelectedItem();
        idField.setText(Integer.toString(selectedUtilisateur.getId()));
        nomField.setText(selectedUtilisateur.getNom());
        prenomField.setText(selectedUtilisateur.getPrenom());
        adresseField.setText(selectedUtilisateur.getAdresse());
        telField.setText(selectedUtilisateur.getTel());
        cinField.setText(selectedUtilisateur.getCin());
    }

    public void Save(ActionEvent actionEvent) {
        Utilisateur nouveauUtilisateur = new Utilisateur(nomField.getText(), prenomField.getText(), adresseField.getText(), telField.getText(), cinField.getText(), false);
        nouveauUtilisateur.setId(Integer.parseInt(idField.getText()));
        admin.modifierUtilisateur(nouveauUtilisateur);

        nomField.setText("");
        prenomField.setText("");
        adresseField.setText("");
        telField.setText("");
        cinField.setText("");

        this.populateUtilisateurListe();
    }
}
