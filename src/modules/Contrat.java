package modules;

public class Contrat {

    //informations du contrat
    private String code_contrat;
    private String date_contrat;
    private String date_echeance;

    public String getCode_contrat() {
        return code_contrat;
    }

    public void setCode_contrat(String code_contrat) {
        this.code_contrat = code_contrat;
    }

    public String getDate_contrat() {
        return date_contrat;
    }

    public void setDate_contrat(String date_contrat) {
        this.date_contrat = date_contrat;
    }

    public String getDate_echeance() {
        return date_echeance;
    }

    public void setDate_echeance(String date_echeance) {
        this.date_echeance = date_echeance;
    }
}
