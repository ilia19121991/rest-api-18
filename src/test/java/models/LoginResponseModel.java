package models;

public class LoginResponseModel {
    // { "token": "QpwL5tke4Pnpja7X4" }

    private String token;

    public String getToken() {

        return token;
    }

    public void setToken(String token) {

        this.token = token;
    }


    // for classic Pojo model
//    @Override
//    public String toString() {
//        return "{\"token=\"" + token + "\"}";
//    }

}
