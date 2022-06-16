package org.zhumagulova.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name="news")
public class News implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(id, news.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
