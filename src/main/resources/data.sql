begin;

insert into artist_type(id, rehearsal_min_time)
values ('musician', 1), ('band', 3);

insert into room(name, description, area, artist_type, price)
values
   (
        'Молодость и красота',
       'Комната для тех, кто начинает путь к музыкальным вершинам',
       35, 'band', 1000
   ),
   ('Мама-анархия', 'Комната для панков в душе и жанре', 26, 'band', 750),
   ('Стакан портвейна', 'Комната для истинных блюзменов', 30, 'band', 900),
   ('До-ми-соль', 'Вокальная комната', 15, 'musician', 200),
   ('Гром и молния', 'Комната ударных', 15, 'musician', 220),
   ('Элтон Джон', 'Комната фортепиано', 18, 'musician', 250)
;

insert into artist(name, genre, phone, email, password, type)
values
   ('Группа риска', 'Рок', '89031231231', 'riska@mail.ru', '123', 'band'),
   ('Лжедмитрий Маликов', 'Death metal', '89296666666', 'metal@metal.com', '321', 'musician')
;

insert into gear(name, type, description, price)
values
   ('LesPaul', 'guitar', 'Пафосно и дорого', 800),
   ('Fender', 'guitar', 'Классика жанра', 700),
   ('Snare', 'drum', 'Рабочий малый', 100)
;

commit;