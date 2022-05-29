package org.zhumagulova.models;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="news_translations")
public class LocalizedNews {

    @Id
    @Column (name="id")
private Long id;

    @Column(name="title")
    @Size(min = 2, max = 100, message = "Title should be from 2 to 100 characters")
    private String title;

    @Column(name="brief")
    @Size(min = 10, max = 600, message = "Brief content should be from 10 to 600 characters")
    private String brief;

    @Column(name="content")
    @Size(min = 20, max = 5000, message = "Content should be from 20 to 3000 characters")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "news_id")
    private News news;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name="language_id")
    private Language language;

    public LocalizedNews () {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
