package org.zhumagulova.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "localized_news",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"news_id", "language_id"}))
@NamedNativeQuery(
        name = "updateLocalizedNews",
        query = "UPDATE localized_news set title =?, brief = ?, content = ? where news_id = ? and language_id = ?",
        resultClass=LocalizedNews.class
)
@NamedQuery(
        name = "selectLocalizedNewsById",
        query = "select ln from LocalizedNews ln where ln.news.id = :id and ln.language.id= :langId"
)
public class LocalizedNews implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="localized_news_seq_gen")
    @SequenceGenerator(name="localized_news_seq_gen", sequenceName="localized_news_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate date;

    @Column(name = "title")
    @Size(min = 2, max = 100, message = "Title should be from 2 to 100 characters")
    private String title;

    @Column(name = "brief")
    @Size(min = 10, max = 500, message = "Brief content should be from 10 to 500 characters")
    private String brief;

    @Column(name = "content")
    @Size(min = 20, max = 2048, message = "Content should be from 20 to 2048 characters")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "news_id")
    private News news;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "language_id")
    private Language language;

    public LocalizedNews() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "LocalizedNews{" +
                "id=" + id +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", content='" + content + '\'' +
                ", news=" + news +
                ", language=" + language +'}';
    }
}
