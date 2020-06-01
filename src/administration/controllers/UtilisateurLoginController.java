package administration.controllers;

import administration.DBConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurLoginController {

    @FXML
    private TextField utilisateurUsernameField;

    @FXML
    private PasswordField utilisateurPasswordField;

    @FXML
    private Button exit;

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxmls/utilisateurMenu.fxml"));
        primaryStage.setTitle("adminMenu");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    void exit(ActionEvent actionEvent) {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();

    }

    @FXML
    void utilisateurLogin(ActionEvent actionEvent) {

        String utilisateurUsername = utilisateurUsernameField.getText();
        String utilisateurPassword = utilisateurPasswordField.getText();

        if (utilisateurUsername.equals("") || utilisateurPassword.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir les champs s'il vous plait !!");
        } else {
            try {
                Connection conn = DBConnect.getConnection();
                PreparedStatement pat = conn.prepareStatement("SELECT nom, cin FROM utilisateur WHERE nom=? AND cin=?");

                pat.setString(1, utilisateurUsername);
                pat.setString(2, utilisateurPassword);

                ResultSet rs = pat.executeQuery();

                if (rs.next()) {
                    conn.close();
                    ((Node) actionEvent.getSource()).getScene().getWindow().hide();
                    Stage utilisateurMenu = new Stage();
                    start(utilisateurMenu);
                } else {
                    JOptionPane.showMessageDialog(null, "Login failed :(");
                    utilisateurUsernameField.setText("");
                    utilisateurPasswordField.setText("");
                    utilisateurUsernameField.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
