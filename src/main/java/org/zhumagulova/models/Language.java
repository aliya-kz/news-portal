package org.zhumagulova.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Language {
    @Min(value = 1, message = "Language id should be not less than 1")
    private int id;

    @Size(min = 2, max = 2, message = "Language code should be 2 characters")
    private String code;

    public Language(String code) {
        this.code = code;
    }

    public Language() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
