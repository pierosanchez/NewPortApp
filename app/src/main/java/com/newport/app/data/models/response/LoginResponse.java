package com.newport.app.data.models.response;

/**
 * Created by tohure on 29/11/17.
 */

public class LoginResponse {

    /**
     * message : success
     * dni : 44502029
     * sap_code : 20000428
     */

    private String message;
    private String dni;
    private String sap_code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getSap_code() {
        return sap_code;
    }

    public void setSap_code(String sap_code) {
        this.sap_code = sap_code;
    }
}
