package modules;

public class Contrat {

    //informations du contrat
    private String codeContrat;
    private String dateContrat;
    private String dateEcheanceContrat;

    public Contrat(String codeContrat, String dateContrat, String dateEcheance) {
        this.codeContrat = codeContrat;
        this.dateContrat = dateContrat;
        this.dateEcheanceContrat = dateEcheance;
    }

    public String getCodeContrat() {
        return codeContrat;
    }

    public void setCodeContrat(String codeContrat) {
        this.codeContrat = codeContrat;
    }

    public String getDateContrat() {
        return dateContrat;
    }

    public void setDateContrat(String dateContrat) {
        this.dateContrat = dateContrat;
    }

    public String getDateEcheanceContrat() {
        return dateEcheanceContrat;
    }

    public void setDateEcheanceContrat(String dateEcheanceContrat) {
        this.dateEcheanceContrat = dateEcheanceContrat;
    }
}
