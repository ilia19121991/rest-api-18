package lesson20.models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponseLombokModel {
    // { "token": "QpwL5tke4Pnpja7X4" }

    private String token;

}
