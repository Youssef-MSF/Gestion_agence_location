package administration;

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
        Parent root = FXMLLoader.load(getClass().getResource("fxmls/adminMenu.fxml"));
        primaryStage.setTitle("adminMenu");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void adminLogin(ActionEvent actionEvent) throws Exception {
        String adminUsername = adminusername.getText();
        String adminPassword = adminpassword.getText();

        if (adminUsername.equals("") || adminPassword.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir les champs s'il vous plait !!");
        } else {
            try {
                conn = DBConnect.getConnection();
                pat = conn.prepareStatement("SELECT * FROM admin WHERE username=? AND password=?");

                pat.setString(1, adminUsername);
                pat.setString(2, adminPassword);

                rs = pat.executeQuery();

                if (rs.next()) {
                    ((Node) actionEvent.getSource()).getScene().getWindow().hide();
                    Stage adminMenu = new Stage();
                    start(adminMenu);

                    conn.close();
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

    public void exit(ActionEvent actionEvent){
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

}

