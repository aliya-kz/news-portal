package org.zhumagulova.models;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name="languages")
public class Language {
    @Id
    private long id;

    @Size(min = 2, max = 2, message = "Language code should be 2 characters")
    private String code;

    public Language() {}

    public Language(String code) {
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
