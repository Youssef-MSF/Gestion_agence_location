package modules;

public class Facture {

    //informations d'une facture
    private String codeFacture;
    private String dateFacture;
    private double montantPayer;

    public Facture(String codeFacture, String dateFacture, double montantPayer) {
        this.codeFacture = codeFacture;
        this.dateFacture = dateFacture;
        this.montantPayer = montantPayer;
    }

    public String getCodeFacture() {
        return codeFacture;
    }

    public void setCodeFacture(String codeFacture) {
        this.codeFacture = codeFacture;
    }

    public String getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(String dateFacture) {
        this.dateFacture = dateFacture;
    }

    public double getMontantPayer() {
        return montantPayer;
    }

    public void setMontantPayer(double montantPayer) {
        this.montantPayer = montantPayer;
    }
}
