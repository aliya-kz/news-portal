insert into news (title, brief, content, language_id, date)
values ('first news', 'first news brief', 'first news contentfffffffffffff', 1, '2022-05-06'),
       ('первые новости', 'коротко новость 1', 'новость 1 содержание', 2, '2022-05-06'),
       ('second', 'secondnews brief', 'second news contentfffffffffffff', 1, '2022-05-07'),
       ('вторые новости', 'коротко новость 2', 'новость 2 содержание аааааааааааааааааа', 2, '2022-05-07');



insert into news_duplicates (source_id, duplicate_id)
values (1, 2),
       (3, 4),
       (2, 1),
       (4, 3);