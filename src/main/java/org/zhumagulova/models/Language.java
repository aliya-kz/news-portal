package org.zhumagulova.models;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name="languages")
public class Language implements Serializable {
    @Id
    private long id;

    @Size(min = 2, max = 2, message = "Language code should be 2 characters")
    private String code;

    @OneToMany(mappedBy="language")
    private Set<LocalizedNews> localizedNewsSet;

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

    public Set<LocalizedNews> getLocalizedNewsSet() {
        return localizedNewsSet;
    }

    public void setLocalizedNewsSet(Set<LocalizedNews> localizedNewsSet) {
        this.localizedNewsSet = localizedNewsSet;
    }
}
