package jp.com.sskprj.dw.three.view;

import lombok.Getter;
import lombok.Setter;

public class Person {

    @Getter
    @Setter
    private String name;

    public Person(String name) {
        this.name = name;
    }
}
