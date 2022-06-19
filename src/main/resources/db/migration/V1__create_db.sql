set client_encoding to 'UTF-8';
create table if not exists languages (
                           id bigserial primary key,
                           code varchar (2) unique
);

create table if not exists news(
    id bigserial primary key
);

create table if not exists localized_news(
                               id bigserial primary key,
                               news_id bigserial not null references news(id) on delete cascade,
                               date date not null,
                               title varchar(100) not null,
                               brief varchar(500) not null,
                               content varchar(2048) not null,
                               language_id  bigserial not null references languages(id),
                               UNIQUE (news_id, language_id)
);

CREATE SEQUENCE if not exists localized_news_sequence
    INCREMENT 1
    MAXVALUE 9223372036854775807
    START 1;

insert into languages (id, code) values (1,'en'), (2,'ru');
insert into news (id) values (1);
insert into localized_news (news_id, title, brief, content, language_id, date) values (1, 'first news', 'first news brief', 'first news contentfffffffffffff', 1, '2022-05-06'), (1, 'первые новости', 'коротко новость 1', 'новость 1 содержание', 2, '2022-05-06');
insert into news (id) values (2);
insert into localized_news (news_id, title, brief, content, language_id, date) values (2, 'second', 'secondnews brief', 'second news contentfffffffffffff', 1,'2022-05-07'), (2, 'вторыеновости', 'коротко новость 2', 'новость 2 содержание аааааааааааааааааа', 2,'2022-05-07');
