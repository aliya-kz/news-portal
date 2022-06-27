package org.zhumagulova.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name="news")
public class News implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="news_seq_gen")
    @SequenceGenerator(name="news_seq_gen", sequenceName="news_sequence", allocationSize = 1)

    @Column(name = "id")
    private Long id;

   @OneToMany(mappedBy="news")
    private Set<LocalizedNews> localizedNewsSet;

    public News() {
    }

    public News(Long id) {
        this.id = id;
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
