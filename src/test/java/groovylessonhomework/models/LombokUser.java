package groovylessonhomework.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties (ignoreUnknown = true)
public class LombokUser {
        private Integer id;
        private String email;
        @JsonProperty("first_name")
        private String firstname;
        @JsonProperty("last_name")
        private String lastname;




}


