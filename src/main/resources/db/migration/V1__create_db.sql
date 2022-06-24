create sequence news_duplicates_sequence start 1 increment 1;
create sequence news_sequence start 1 increment 1;
create table languages
(
    id   bigserial not null,
    code varchar(255),
    primary key (id)
);

create table news
(
    id          bigserial not null,
    brief       varchar(255),
    content     varchar(255),
    date        date,
    title       varchar(255),
    language_id int8,
    primary key (id)
);

create table news_duplicates
(
    id           bigserial not null,
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


