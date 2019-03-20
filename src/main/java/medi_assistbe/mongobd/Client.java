package medi_assistbe.mongobd;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private int clientId;
    private String clientName;
    private String passwordTxt;
    private String tokengiven;

    public int getClientId() {
        return clientId;
    }

    public void setTokengiven(String tokengiven) {
        this.tokengiven = tokengiven;
    }

    public String getTokengiven() {
        return tokengiven;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPasswordTxt() {
        return passwordTxt;
    }

    public void setPasswordTxt(String passwordTxt) {
        this.passwordTxt = passwordTxt;
    }
}