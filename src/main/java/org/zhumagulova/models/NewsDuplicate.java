package org.zhumagulova.models;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "news_duplicates")
public class NewsDuplicate implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="news_duplicates_seq_gen")
    @SequenceGenerator(name="news_duplicates_seq_gen", sequenceName="news_duplicates_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "source_id")
    private News sourceNews;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = true, name = "duplicate_id", referencedColumnName = "id")
    private News duplicatedNews;

    public NewsDuplicate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public News getSourceNews() {
        return sourceNews;
    }

    public void setSourceNews(News news) {
        this.sourceNews = news;
    }

    public News getDuplicatedNews() {
        return duplicatedNews;
    }

    public void setDuplicatedNews(News duplicatedNews) {
        this.duplicatedNews = duplicatedNews;
    }


}
