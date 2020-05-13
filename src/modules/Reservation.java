package modules;

public class Reservation {

    //informations d'une reservation
    private String code_reservation;
    private String date_reservation;
    private String date_depart;
    private String date_retour;

    public String getCode_reservation() {
        return code_reservation;
    }

    public void setCode_reservation(String code_reservation) {
        this.code_reservation = code_reservation;
    }

    public String getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(String date_reservation) {
        this.date_reservation = date_reservation;
    }

    public String getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(String date_depart) {
        this.date_depart = date_depart;
    }

    public String getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(String date_retour) {
        this.date_retour = date_retour;
    }
}
