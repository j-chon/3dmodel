package jp.com.sskprj.dw.three;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ThreeConfiguration extends Configuration {

    @Getter
    @Setter
    @NotEmpty
    @JsonProperty
    private String template;


    @Getter
    @Setter
    @JsonProperty
    @NotEmpty
    private String defaultName = "threeJs";

    @Valid
    @NotNull
    @JsonProperty("database")
    @Getter
    private DataSourceFactory datasource = new DataSourceFactory();


}
