insert into languages (id, code)
values (1, 'en'),
       (2, 'ru');

insert into news (id, title, brief, content, language_id, date)
values (nextval('news_sequence'), 'first news', 'first news brief', 'first news contentfffffffffffff', 1, '2022-05-06'),
       (nextval('news_sequence'), 'первые новости', 'коротко новость 1', 'новость 1 содержание', 2, '2022-05-06'),
       (nextval('news_sequence'), 'second', 'secondnews brief', 'second news contentfffffffffffff', 1, '2022-05-07'),
       (nextval('news_sequence'), 'вторые новости', 'коротко новость 2', 'новость 2 содержание аааааааааааааааааа', 2,
        '2022-05-07');

insert into news_duplicates (id, source_id, duplicate_id)
values (nextval('news_duplicates_sequence'), 1, 2),
       (nextval('news_duplicates_sequence'),3, 4),
       (nextval('news_duplicates_sequence'),2, 1),
       (nextval('news_duplicates_sequence'),4, 3)