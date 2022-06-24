package org.zhumagulova.models;


import javax.validation.constraints.Size;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name="languages")
@NamedNativeQuery(
        name = "getLanguageByCode",
        query = "SELECT * from languages where code = ?",
        resultClass=Language.class
)

public class Language implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 2, max = 2, message = "Language code should be 2 characters")
    private String code;

    @OneToMany(mappedBy="language")
    private Set<News> localizedNewsSet;

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

    public Set<News> getLocalizedNewsSet() {
        return localizedNewsSet;
    }

    public void setLocalizedNewsSet(Set<News> localizedNewsSet) {
        this.localizedNewsSet = localizedNewsSet;
    }

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", code='" + code;
    }
}
