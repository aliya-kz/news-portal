package org.zhumagulova.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Entity
@Table(name="news")
public class News {

    @Column(name="id")
    private long id;

    @Column(name="news_id")
    private long newsId;

    @Column(name="date")
    private LocalDate date;

    @Column(name="title")
    @Size(min = 2, max = 100, message = "Title should be from 2 to 100 characters")
    private String title;

    @Column(name="brief")
    @Size(min = 10, max = 600, message = "Brief content should be from 10 to 600 characters")
    private String brief;

    @Column(name="content")
    @Size(min = 20, max = 5000, message = "Content should be from 20 to 3000 characters")
    private String content;

    @Column(name="language")
    @Min(value = 1, message = "Language id should be not less than 1")
    private long language;

    public News() {
    }
/*
    public static class Builder {
        private long id;
        private long newsId;
        private LocalDate date;
        private String title;
        private String brief;
        private String content;
        private long language;

        public Builder id (long id) {
            this.id = id;
            return this;
        }
        public Builder newsId (long newsId) {
            this.newsId = newsId;
            return this;
        }

        public Builder date (LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder brief(String brief) {
            this.brief = brief;
            return this;
        }

        public Builder content (String content) {
            this.content = content;
            return this;
        }

        public Builder language (long id) {
            this.language = language;
            return this;
        }

        public News build() {
            return new News(this);
        }
    }

    public News (Builder builder) {
        id = builder.id;
        newsId = builder.newsId;
        date = builder.date;
        title = builder.title;
        brief = builder.brief;
        content = builder.content;
        language = builder.language;
    }
*/

    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLanguage(long language) {
        this.language = language;
    }

    public long getNewsId() {
        return newsId;
    }

    public LocalDate getDate() {
        return date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getBrief() {
        return brief;
    }

    public String getContent() {
        return content;
    }

    public long getLanguage() {
        return language;
    }

}
