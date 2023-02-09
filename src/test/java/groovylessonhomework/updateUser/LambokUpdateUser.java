package groovylessonhomework.updateUser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@JsonIgnoreProperties (ignoreUnknown = true)
public class LambokUpdateUser {
    @JsonProperty("name")
    private String name;
    @JsonProperty("job")
    private String job;

    /* @JsonProperty("updatedAt")
    private String updatedAt;*/


}
