package administration;

import com.sun.webkit.network.Util;
import utilisateur.Utilisateur;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Administrateur extends Utilisateur {

    //fonctions didées à l'administrateur

    public boolean ajouterUtilisateur(Utilisateur nouveauUtilisateur) {
        boolean rs;
        PreparedStatement pat;
        Connection con = null;
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("INSERT INTO utilisateur(nom, prenom, adresse, tel, cin) VALUES(?, ?, ?, ?, ?)");

            pat.setString(1, nouveauUtilisateur.getNom());
            pat.setString(2, nouveauUtilisateur.getPrenom());
            pat.setString(3, nouveauUtilisateur.getAdresse());
            pat.setString(4, nouveauUtilisateur.getCin());
            pat.setString(5, nouveauUtilisateur.getTel());


            rs = pat.execute();

            if (!rs) {
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;

    }


    public boolean supprimerUtilisateur(Utilisateur utilisateurASupprime){
        boolean rs;
        PreparedStatement pat;
        Connection con = null;
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("DELETE FROM utilisateur WHERE id=?");

            pat.setString(1, Integer.toString(utilisateurASupprime.getId()));
            System.out.println(utilisateurASupprime.getId());

            rs = pat.execute();

            if (!rs) {
                JOptionPane.showMessageDialog(null, "Suppression avec succès !!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "La suppression n'est pas réalisé :(");
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void suspendreUtilisateur(Utilisateur utilisateurASuspendre){
        boolean rs;
        PreparedStatement pat;
        Connection con = null;
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("UPDATE utilisateur SET is_suspend=true WHERE id=?");

            pat.setString(1, Integer.toString(utilisateurASuspendre.getId()));

            rs = pat.execute();

            if (!rs) {
                JOptionPane.showMessageDialog(null, "suspension avec succès !!");
            } else {
                JOptionPane.showMessageDialog(null, "La suspension n'est pas réalisé :(");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean modifierUtilisateur(Utilisateur utilisateurAModifier){
        boolean rs;
        PreparedStatement pat;
        Connection con = null;
        try {
            con = DBConnect.getConnection();
            pat = con.prepareStatement("UPDATE utilisateur SET nom=?, prenom=?, adresse=?, tel=?, cin=? WHERE id=?");

            pat.setString(1, utilisateurAModifier.getNom());
            pat.setString(2, utilisateurAModifier.getPrenom());
            pat.setString(3, utilisateurAModifier.getAdresse());
            pat.setString(4, utilisateurAModifier.getTel());
            pat.setString(5, utilisateurAModifier.getCin());
            pat.setString(6, Integer.toString(utilisateurAModifier.getId()));

            rs = pat.execute();

            if (!rs) {
                JOptionPane.showMessageDialog(null, "Modification avec succès !!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "La modification n'est pas réalisé :(");
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }



}
