package administration.controllers;

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
import java.sql.*;

public class AdminLoginController {
    @FXML
    private TextField adminusername;
    @FXML
    private PasswordField adminpassword;
    @FXML
    private Button btnadminlogin;
    Connection conn;
    PreparedStatement pat;
    ResultSet rs;

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxmls/adminMenu.fxml"));
        primaryStage.setTitle("adminMenu");
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void adminLogin(ActionEvent actionEvent) throws Exception {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        String adminUsername = adminusername.getText();
        String adminPassword = adminpassword.getText();

        if (adminUsername.equals("") && adminPassword.equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill the form to login !!");
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
                pat = conn.prepareStatement("select * from users where username=? and password=?");

                pat.setString(1, adminUsername);
                pat.setString(2, adminPassword);

                rs = pat.executeQuery();

                if (rs.next()) {
                    Stage adminMenu = new Stage();
                    start(adminMenu);
                } else {
                    JOptionPane.showMessageDialog(null, "Login failed :(");
                    adminusername.setText("");
                    adminpassword.setText("");
                    adminusername.requestFocus();
                }

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }


    }

}

