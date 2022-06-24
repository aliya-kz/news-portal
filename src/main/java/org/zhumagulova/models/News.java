package org.zhumagulova.models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "news")
@NamedNativeQuery(
        name = "updateNews",
        query = "UPDATE news set title =?, date=?, brief = ?, content = ? where id = ?",
        resultClass= News.class
)
@NamedQuery(
        name = "selectNewsById",
        query = "select n from News n where n.id = :id"
)
public class News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate date;

    @Column(name = "title")
    @Size(min = 2, max = 100, message = "Title should be from 2 to 100 characters")
    private String title;

    @Column(name = "brief")
    @Size(min = 2, max = 500, message = "Brief content should be from 10 to 500 characters")
    private String brief;

    @Column(name = "content")
    @Size(min = 2, max = 2048, message = "Content should be from 20 to 2048 characters")
    private String content;

    @OneToMany(mappedBy = "sourceNews")
    private Set<NewsDuplicate> newsDuplicates;

    @OneToOne(mappedBy = "duplicatedNews")
    private NewsDuplicate duplicate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "language_id")
    private Language language;

    public News() {
    }

    public Set<NewsDuplicate> getNewsDuplicates() {
        return newsDuplicates;
    }

    public void setNewsDuplicates(Set<NewsDuplicate> newsDuplicates) {
        this.newsDuplicates = newsDuplicates;
    }

    public NewsDuplicate getDuplicate() {
        return duplicate;
    }

    public void setDuplicate(NewsDuplicate duplicate) {
        this.duplicate = duplicate;
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
                ", language=" + language +'}';
    }
}
