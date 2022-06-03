package org.zhumagulova.models;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="news")
public class News {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

   @OneToMany(mappedBy="news")
    private Set<LocalizedNews> localizedNewsSet;


    public News() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   public Set<LocalizedNews> getLocalizedNewsSet() {
        return localizedNewsSet;
    }

   public void setLocalizedNewsSet(Set<LocalizedNews> localizedNewsSet) {
        this.localizedNewsSet = localizedNewsSet;
    }

}
