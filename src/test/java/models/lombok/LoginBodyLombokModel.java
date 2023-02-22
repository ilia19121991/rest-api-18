package models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginBodyLombokModel {

    // "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }"

    private String email, password;

}
