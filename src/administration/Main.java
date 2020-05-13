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
        primaryStage.setTitle("Hello World");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        PreparedStatement pat;
        Connection con = null;
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("CREATE TABLE IF NOT EXISTS vehicule (id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    matricule VARCHAR(100),\n" +
                    "    marque VARCHAR(100),\n" +
                    "    type VARCHAR(255),\n" +
                    "    carburant VARCHAR(255),\n" +
                    "    compteurKM VARCHAR(10),\n" +
                    "    dateMiseEnCirculation VARCHAR(100),\n" +
                    "    nParkingAssocie VARCHAR(100))");

            rs = pat.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("CREATE TABLE IF NOT EXISTS utilisateur (id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    nom VARCHAR(100),\n" +
                    "    prenom VARCHAR(100),\n" +
                    "    adresse VARCHAR(255),\n" +
                    "    tel VARCHAR(255),\n" +
                    "    cin VARCHAR(10),\n" +
                    "    is_suspend tinyint(1))");

            rs = pat.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("CREATE TABLE IF NOT EXISTS parking (id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    codeParking VARCHAR(100),\n" +
                    "    capacite INTEGER,\n" +
                    "    rue VARCHAR(255),\n" +
                    "    arrondissement VARCHAR(255),\n" +
                    "    nbrePlacesVides INTEGER DEFAULT 0)");

            rs = pat.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        launch(args);
    }
}
