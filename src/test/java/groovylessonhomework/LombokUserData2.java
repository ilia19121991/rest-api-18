package groovylessonhomework;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LombokUserData2 {
    @JsonProperty("data")
    private LombokUser lambokUser;

}
