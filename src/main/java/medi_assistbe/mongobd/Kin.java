package medi_assistbe.mongobd;

public class Kin {
    private String id;
    private String name;
    private String email;
    private String contact;
    private String eircode;
    private String relation;
    private String map;


    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEircode() {
        return eircode;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
