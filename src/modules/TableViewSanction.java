package modules;

public class TableViewSanction {

    private String cinClient;
    private String codeReservation;
    private String codeContrat;
    private String codeFacture;
    private String matricule;
    private String montantSanction;


    public TableViewSanction(String cinClient, String codeReservation, String codeContrat, String codeFacture, String matricule, String montantSanction) {
        this.cinClient = cinClient;
        this.codeReservation = codeReservation;
        this.codeContrat = codeContrat;
        this.codeFacture = codeFacture;
        this.matricule = matricule;
        this.montantSanction = montantSanction;
    }

    public String getCinClient() {
        return cinClient;
    }

    public void setCinClient(String cinClient) {
        this.cinClient = cinClient;
    }

    public String getCodeReservation() {
        return codeReservation;
    }

    public void setCodeReservation(String codeReservation) {
        this.codeReservation = codeReservation;
    }

    public String getCodeContrat() {
        return codeContrat;
    }

    public void setCodeContrat(String codeContrat) {
        this.codeContrat = codeContrat;
    }

    public String getCodeFacture() {
        return codeFacture;
    }

    public void setCodeFacture(String codeFacture) {
        this.codeFacture = codeFacture;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMontantSanction() {
        return montantSanction;
    }

    public void setMontantSanction(String montantSanction) {
        this.montantSanction = montantSanction;
    }
}
