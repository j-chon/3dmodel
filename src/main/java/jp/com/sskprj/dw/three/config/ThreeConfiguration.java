package jp.com.sskprj.dw.three.config;

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

//    public void setClassLoaderForTemplateLoading(ClassLoader classLoader, String basePackagePath) {
//        freemarker.template.Configuration fmcg = new freemarker.template.Configuration(
//                freemarker.template.Configuration.VERSION_2_3_0);
//        fmcg.setClassLoaderForTemplateLoading(classLoader,basePackagePath);
//    }

}
