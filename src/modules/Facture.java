package modules;

public class Facture {

    //informations d'une facture
    private String code_facture;
    private String date_facture;
    private double montant_payer;

    public String getCode_facture() {
        return code_facture;
    }

    public void setCode_facture(String code_facture) {
        this.code_facture = code_facture;
    }

    public String getDate_facture() {
        return date_facture;
    }

    public void setDate_facture(String date_facture) {
        this.date_facture = date_facture;
    }

    public double getMontant_payer() {
        return montant_payer;
    }

    public void setMontant_payer(double montant_payer) {
        this.montant_payer = montant_payer;
    }
}
