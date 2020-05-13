package modules;

public class Vehicule {

    //informations d'un vehicule
    private String matricule;
    private String marque;
    private String type;
    private String carburant;
    private double compteurKM;
    private String dateMiseEnCirculation;
    private String nParkingAssocie;

    public Vehicule(String matricule, String marque, String type, String carburant, double compteurKM, String dateMiseEnCirculation, String nParkingAssocie) {
        this.matricule = matricule;
        this.marque = marque;
        this.type = type;
        this.carburant = carburant;
        this.compteurKM = compteurKM;
        this.dateMiseEnCirculation = dateMiseEnCirculation;
        this.nParkingAssocie = nParkingAssocie;
    }

    public Vehicule(String matricule, String marque, String type, String carburant, double compteurKM, String dateMiseEnCirculation) {
        this.matricule = matricule;
        this.marque = marque;
        this.type = type;
        this.carburant = carburant;
        this.compteurKM = compteurKM;
        this.dateMiseEnCirculation = dateMiseEnCirculation;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCarburant() {
        return carburant;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

    public double getCompteurKM() {
        return compteurKM;
    }

    public void setCompteurKM(double compteurKM) {
        this.compteurKM = compteurKM;
    }

    public String getDateMiseEnCirculation() {
        return dateMiseEnCirculation;
    }

    public void setDateMiseEnCirculation(String dateMiseEnCirculation) {
        this.dateMiseEnCirculation = dateMiseEnCirculation;
    }

    public String getNParkingAssocie() {
        return nParkingAssocie;
    }

    public void setNParkingAssocie(String nParkingAssocie) {
        this.nParkingAssocie = nParkingAssocie;
    }
}
