package medi_assistbe.mongobd;

import org.springframework.data.annotation.Id;

public class Vitals {

    private  String vitalbloodpressure;
    private String vitalweightkgsbmi;
    private String vitalheartbeat;
    private String vitalcholesterol;
    private String vitalbodytemperature;
    private String vitalheartrate;
    private String vitalrespiratoryrate;
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVitalbloodpressure() {
        return vitalbloodpressure;
    }

    public String getVitalweightkgsbmi() {
        return vitalweightkgsbmi;
    }

    public String getVitalheartbeat() {
        return vitalheartbeat;
    }

    public String getVitalcholesterol() {
        return vitalcholesterol;
    }

    public String getVitalbodytemperature() {
        return vitalbodytemperature;
    }

    public String getVitalheartrate() {
        return vitalheartrate;
    }

    public String getVitalrespiratoryrate() {
        return vitalrespiratoryrate;
    }

    public void setVitalbloodpressure(String vitalbloodpressure) {
        this.vitalbloodpressure = vitalbloodpressure;
    }

    public void setVitalweightkgsbmi(String vitalweightkgsbmi) {
        this.vitalweightkgsbmi = vitalweightkgsbmi;
    }

    public void setVitalheartbeat(String vitalheartbeat) {
        this.vitalheartbeat = vitalheartbeat;
    }

    public void setVitalcholesterol(String vitalcholesterol) {
        this.vitalcholesterol = vitalcholesterol;
    }

    public void setVitalbodytemperature(String vitalbodytemperature) {
        this.vitalbodytemperature = vitalbodytemperature;
    }

    public void setVitalheartrate(String vitalheartrate) {
        this.vitalheartrate = vitalheartrate;
    }

    public void setVitalrespiratoryrate(String vitalrespiratoryrate) {
        this.vitalrespiratoryrate = vitalrespiratoryrate;
    }
}
