package groovylessonhomework.models.DataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties (ignoreUnknown = true)
public class LombokDataModel {
    private LombokPostUser lombokPostUser;

}
