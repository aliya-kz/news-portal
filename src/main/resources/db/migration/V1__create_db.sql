create sequence hibernate_sequence start 1 increment 1;

create table languages
(
    id  bigserial not null,
    code varchar(2),
    primary key (id)
);

create table news
(
    id          int8 not null,
    brief       varchar(500),
    content     varchar(2048),
    date        date,
    title       varchar(100),
    language_id int8,
    primary key (id)
);

create table news_duplicates
(
    id           int8 not null,
    duplicate_id int8,
    source_id    int8,
    primary key (id)
);

alter table if exists news
    add constraint news_languages_fk foreign key (language_id) references languages;
alter table if exists news_duplicates
    add constraint news_duplicates_duplicate_fk foreign key (duplicate_id) references news;
alter table if exists news_duplicates
    add constraint news_duplicates_source_fk foreign key (source_id) references news;



