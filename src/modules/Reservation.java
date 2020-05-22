package modules;

public class Reservation {

    //informations d'une reservation
    private String codeReservation;
    private String dateReservation;
    private String dateDepart;
    private String dateRetour;

    public Reservation(String codeReservation, String dateReservation, String dateDepart, String dateRetour) {
        this.codeReservation = codeReservation;
        this.dateReservation = dateReservation;
        this.dateDepart = dateDepart;
        this.dateRetour = dateRetour;
    }

    public String getCodeReservation() {
        return codeReservation;
    }

    public void setCodeReservation(String codeReservation) {
        this.codeReservation = codeReservation;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(String dateRetour) {
        this.dateRetour = dateRetour;
    }
}
