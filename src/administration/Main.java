package administration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main extends Application {

    Connection conn;
    PreparedStatement pat;
    boolean rs;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxmls/utilisateurMenu.fxml"));
        primaryStage.setTitle("Menu d'utilisateur");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        PreparedStatement pat;
        Connection con = null;
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("CREATE TABLE IF NOT EXISTS vehicule (id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    matricule VARCHAR(100) DEFAULT \" \",\n" +
                    "    marque VARCHAR(100) DEFAULT \" \",\n" +
                    "    type VARCHAR(255) DEFAULT \" \",\n" +
                    "    carburant VARCHAR(255) DEFAULT \" \",\n" +
                    "    compteurKM VARCHAR(10) DEFAULT \" \",\n" +
                    "    dateMiseEnCirculation VARCHAR(100) DEFAULT \" \",\n" +
                    "    nParkingAssocie VARCHAR(100) DEFAULT \" \",\n" +
                    "    is_Sortis INTEGER DEFAULT 0,\n" +
                    "    is_deposer INTEGER DEFAULT 0,\n" +
                    "    is_reserve INTEGER DEFAULT 0)");

            rs = pat.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("CREATE TABLE IF NOT EXISTS utilisateur (id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    nom VARCHAR(100) DEFAULT \" \",\n" +
                    "    prenom VARCHAR(100) DEFAULT \" \",\n" +
                    "    adresse VARCHAR(255) DEFAULT \" \",\n" +
                    "    tel VARCHAR(255) DEFAULT \" \",\n" +
                    "    cin VARCHAR(10) DEFAULT \" \",\n" +
                    "    is_suspend tinyint(1))");

            rs = pat.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("CREATE TABLE IF NOT EXISTS parking (id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    codeParking VARCHAR(100) DEFAULT \" \",\n" +
                    "    capacite INTEGER DEFAULT \" \",\n" +
                    "    rue VARCHAR(255) DEFAULT \" \",\n" +
                    "    arrondissement VARCHAR(255) DEFAULT \" \",\n" +
                    "    nbrePlacesVides INTEGER DEFAULT 0)");

            rs = pat.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("CREATE TABLE IF NOT EXISTS contrat (id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    codeContrat VARCHAR(100) DEFAULT \" \",\n" +
                    "    dateContrat VARCHAR(255) DEFAULT \" \",\n" +
                    "    dateEcheanceContrat VARCHAR(255) DEFAULT \" \")");

            rs = pat.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("CREATE TABLE IF NOT EXISTS reservation (id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    codeReservation VARCHAR(100) DEFAULT \" \",\n" +
                    "    dateReservation VARCHAR(255) DEFAULT \" \",\n" +
                    "    dateDepart VARCHAR(255) DEFAULT \" \",\n" +
                    "    dateRetour VARCHAR(255) DEFAULT \" \",\n" +
                    "    is_annulee INTEGER DEFAULT 0,\n" +
                    "    is_valid INTEGER DEFAULT 0)");

            rs = pat.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("CREATE TABLE IF NOT EXISTS facture (id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    codeFacture VARCHAR(100) DEFAULT \" \",\n" +
                    "    dateFacture VARCHAR(255) DEFAULT \" \",\n" +
                    "    montant VARCHAR(255) DEFAULT \" \")");

            rs = pat.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("CREATE TABLE IF NOT EXISTS operation (id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    codeReservation VARCHAR(100) DEFAULT \" \",\n" +
                    "    codeContrat VARCHAR(255) DEFAULT \" \",\n" +
                    "    matricule VARCHAR(255) DEFAULT \" \",\n" +
                    "    codeClient VARCHAR(255) DEFAULT \" \",\n" +
                    "    codeFacture VARCHAR(255) DEFAULT \" \" )");

            rs = pat.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}
