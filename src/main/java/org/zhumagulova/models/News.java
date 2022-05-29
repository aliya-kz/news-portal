package org.zhumagulova.models;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name="news")
public class News {
    @Id
    private Long id;

    @OneToMany(mappedBy="news")
    private Set<LocalizedNews> localizedNewsSet;

    @Column(name="date")
    private LocalDate date;

    public News() {
    }


}
