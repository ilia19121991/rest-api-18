package groovylessonhomework.createuser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class LambokCreateUserData {

    @JsonProperty("data")
    private LambokCreateUser lambokCreateUser;
}
