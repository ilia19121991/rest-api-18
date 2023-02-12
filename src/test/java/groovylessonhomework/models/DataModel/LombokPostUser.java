package groovylessonhomework.models.DataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LombokPostUser {
        @JsonProperty("name")
        private String name;
        @JsonProperty("job")
        private String job;
        @JsonProperty("id")
        private String id;
        @JsonProperty("createdAt")
        private String createdAt;
}
