package medi_assistbe.mongobd;

import org.springframework.data.annotation.Id;

import java.util.List;

public class PersonalProfile {

    @Override
    public String toString() {
        return "PersonalProfile{" +
                "id='" + id + '\'' +
                ", eircode='" + eircode + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", home='" + home + '\'' +
                ", bloodgroup='" + bloodgroup + '\'' +
                ", Name='" + Name + '\'' +
                ", gender='" + gender + '\'' +
                ", dateofbirth='" + dateofbirth + '\'' +
                ", tokengiven='" + tokengiven + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }


    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getName() {
        return Name;
    }

    public String getGender() {
        return gender;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }


    public String getEircode() {
        return eircode;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getHome() {
        return home;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }


    public String getId() {
        return id;
    }

    public void setTokengiven(String tokengiven) {
        this.tokengiven = tokengiven;
    }

    public String getTokengiven() {
        return tokengiven;
    }



    @Id
    private String id;
    private String eircode;
    private String mobile;
    private String email;
    private String home;
    private String bloodgroup;
    private String Name;
    private String gender;
    private String dateofbirth;
    private String tokengiven;
    private String password;


}
