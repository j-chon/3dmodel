package jp.com.sskprj.dw.three.entity.db;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RequestLoginInfo {

    private String token;

    @JsonProperty("uid")
    private String userId;

}
