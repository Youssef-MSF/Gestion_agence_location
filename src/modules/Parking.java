package modules;

public class Parking {

    //informations d'un parking
    private String codeParking;
    private int capacite;
    private String rue;
    private String arrondissement;
    private int nbrPlacesVides;

    public Parking(String codeParking, int capacite, String rue, String arrondissement, int nbrPlacesVides) {
        this.codeParking = codeParking;
        this.capacite = capacite;
        this.rue = rue;
        this.arrondissement = arrondissement;
        this.nbrPlacesVides = nbrPlacesVides;
    }

    public String getCodeParking() {
        return codeParking;
    }

    public void setCodeParking(String codeParking) {
        this.codeParking = codeParking;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getArrondissement() {
        return arrondissement;
    }

    public void setArrondissement(String arrondissement) {
        this.arrondissement = arrondissement;
    }

    public int getNbrPlacesVides() {
        return nbrPlacesVides;
    }

    public void setNbrPlacesVides(int nbrPlacesVides) {
        this.nbrPlacesVides = nbrPlacesVides;
    }
}
