package administration.controllers;

import administration.Administrateur;
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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utilisateur.Utilisateur;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
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

    @FXML
    private Label lineChartTitle;

    @FXML
    private LineChart<String, Number> lineChartStat;

    @FXML
    private Button btnStatistiques;

    @FXML
    private Button btnParametres;

    @FXML
    private AnchorPane statistiquesAnchor;

    @FXML
    private AnchorPane parametresAnchor;

    @FXML
    private TextField usernameUpdateField;

    @FXML
    private TextField passwordUpdateField;

    @FXML
    private TextField confirmationField;

    Connection conn;
    PreparedStatement pat;
    boolean rs;

    ObservableList<Utilisateur> oblist = FXCollections.observableArrayList();

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxmls/utilisateurMenu.fxml"));
        primaryStage.setTitle("adminMenu");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Importer les données dans le graph de statistiques

        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

        try {
            conn = DBConnect.getConnection();
            pat = conn.prepareStatement("SELECT * FROM statistiques");
            ResultSet res = pat.executeQuery();

            while (res.next()) {
                String mois = res.getString("mois");
                int nbreVehicule = Integer.parseInt(res.getString("nombre_véhicule"));

                series.getData().add(new XYChart.Data<String, Number>(mois, nbreVehicule));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        lineChartStat.getData().add(series);

        lineChartTitle.setText("Nombre de véhicules aloués par mois de l'année : " + LocalDate.now().getYear());


        this.populateUtilisateurListe();

    }

    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnUtilisateur) {
            btnUtilisateur.setStyle("-fx-background-color:#9656CD");
            btnStatistiques.setStyle("");
            btnParametres.setStyle("");
            gestionTitre.setText("Gestion des utilisateurs");
            gestionUtilisateurs.toFront();
        } else if (actionEvent.getSource() == btnStatistiques) {
            btnStatistiques.setStyle("-fx-background-color:#9656CD");
            btnUtilisateur.setStyle("");
            btnParametres.setStyle("");
            gestionTitre.setText("Statistiques");
            statistiquesAnchor.toFront();
        } else if (actionEvent.getSource() == btnParametres) {
            btnParametres.setStyle("-fx-background-color:#9656CD");
            btnUtilisateur.setStyle("");
            btnStatistiques.setStyle("");
            gestionTitre.setText("Paramètres");
            parametresAnchor.toFront();
        }
    }

    public void ajouterUtilisateur(ActionEvent actionEvent) {

        Utilisateur nouveauUtilisateur = new Utilisateur(nomField.getText(), prenomField.getText(), adresseField.getText(), telField.getText(), cinField.getText(), false);

        if (admin.ajouterUtilisateur(nouveauUtilisateur)) {
            JOptionPane.showMessageDialog(null, "Ajout avec succès !!");

            nomField.setText("");
            prenomField.setText("");
            adresseField.setText("");
            telField.setText("");
            cinField.setText("");
            this.populateUtilisateurListe();

        } else {
            JOptionPane.showMessageDialog(null, "L'ajout n'est pas réalisé :(");
        }

    }

    public void modifierUtilisateur(ActionEvent actionEvent) {

        this.selectionnerUtilisateur();
    }

    public void supprimerUtilisateur(ActionEvent actionEvent) {

        this.selectionnerUtilisateur();

        Utilisateur utilisateurASupprimer = new Utilisateur(nomField.getText(), prenomField.getText(), adresseField.getText(), cinField.getText(), telField.getText(), false);
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

        Utilisateur utilisateurASuspendre = new Utilisateur(nomField.getText(), prenomField.getText(), adresseField.getText(), cinField.getText(), telField.getText(), false);
        utilisateurASuspendre.setId(Integer.parseInt(idField.getText()));

        admin.suspendreUtilisateur(utilisateurASuspendre);

        this.populateUtilisateurListe();
    }


    public void retourAcceuil(ActionEvent actionEvent) throws IOException {

        Stage acceuil = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("../fxmls/acceuil.fxml"));
        acceuil.initStyle(StageStyle.UNDECORATED);
        acceuil.setScene(new Scene(root));
        acceuil.show();
    }

    public void populateUtilisateurListe() {

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

    public void selectionnerUtilisateur() {
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

    public void passerAuxModules(ActionEvent actionEvent) throws Exception {
        Stage autreModules = new Stage();
        start(autreModules);
    }

    public void exit(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }


    @FXML
    void ModifierAdminInfo(ActionEvent actionEvent) {

        if (usernameUpdateField.getText().equals("") || passwordUpdateField.getText().equals("") || confirmationField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir les champs s'il vous plait.");
        } else if (!(passwordUpdateField.getText().equals(confirmationField.getText()))) {
            JOptionPane.showMessageDialog(null, "Les deux mot de passe ne sont pas les memes !!");
        } else {

            try {
                conn = DBConnect.getConnection();
                pat = conn.prepareStatement("UPDATE admin SET username=?, password=?");

                pat.setString(1, usernameUpdateField.getText());
                pat.setString(2, passwordUpdateField.getText());

                rs = pat.execute();

                if (!rs){
                    JOptionPane.showMessageDialog(null, "Modification avec succès.");
                }else {
                    JOptionPane.showMessageDialog(null, "Erreur lors de la modification");
                }

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

        usernameUpdateField.setText("");
        passwordUpdateField.setText("");
        confirmationField.setText("");

    }

}
