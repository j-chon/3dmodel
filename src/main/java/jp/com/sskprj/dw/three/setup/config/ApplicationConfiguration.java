package jp.com.sskprj.dw.three.setup.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ToString
public class ApplicationConfiguration extends Configuration {

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

    @Valid
    @NotNull
    @Getter
    @Setter
    @JsonProperty
    private boolean fireBaseAuth = false;

    @NotNull
    @Getter
    @Setter
    @JsonProperty("dbAccess")
    private boolean dbAccess = false;

    @Getter
    @Setter
    @JsonProperty("firebaseTokenPath")
    private String firebaseTokenPath = null;

    @Getter
    @Setter
    @JsonProperty("firebaseDBUrl")
    private String firebaseDBUrl = null;

}
