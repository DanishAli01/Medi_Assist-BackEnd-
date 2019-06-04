package medi_assistbe.mongobd;

public class MedicalHistory {

    public String getIllness() {
        return illness;
    }

    public String getDate() {
        return date;
    }

    public String getDetails() {
        return details;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "MedicalHistory{" +
                "illness='" + illness + '\'' +
                ", date='" + date + '\'' +
                ", details='" + details + '\'' +
                '}';
    }

    private String map;
    private String illness;
    private String date;
    private String details;


}
