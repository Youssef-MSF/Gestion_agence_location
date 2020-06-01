package administration.controllers;

import animatefx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AcceuilController implements Initializable {

    @FXML
    private Label dateLabel;

    @FXML
    private Label titreLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dateLabel.setText("Le : " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd / MM / yyyy")));

        new Jello(titreLabel).play();

    }

    public void start(Stage primaryStage, String fxmlFichier) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxmls/"+fxmlFichier));
        primaryStage.setTitle("adminMenu");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        new FadeIn(root).play();
    }

    public void exit(ActionEvent actionEvent) {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();

    }

    public void passerAdmin(ActionEvent actionEvent) throws Exception {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();

        Stage adminLogin = new Stage();
        start(adminLogin, "adminLogin.fxml");

    }

    public void passerUtilisateur(ActionEvent actionEvent) throws Exception {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();

        Stage utilisateurLogin = new Stage();
        start(utilisateurLogin, "utilisateurLogin.fxml");

    }
}
