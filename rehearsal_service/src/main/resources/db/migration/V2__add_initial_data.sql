begin;

insert into room_type(name, rehearsal_min_time) values ('musician', 1), ('band', 3);

insert into room_status(id, description)
values
    ('active', 'Available for rehearsals'),
    ('closed', 'Temporarily closed. Available only for admins')
;

insert into room(name, description, area, type_id, status_id, price)
values
    (
        'Молодость и красота',
        'Комната для тех, кто начинает путь к музыкальным вершинам',
        35, 2, 'active', 1000
    ),
    ('Мама-анархия', 'Комната для панков в душе и жанре', 26, 2, 'active', 750),
    ('Стакан портвейна', 'Комната для истинных блюзменов', 30, 2, 'active', 900),
    ('До-ми-соль', 'Вокальная комната', 15, 1, 'active', 200),
    ('Гром и молния', 'Комната ударных', 15, 1, 'active', 220),
    ('Элтон Джон', 'Комната фортепиано', 18, 1, 'active', 250)
;

insert into artist(name, genre, phone, email)
values
    ('Группа риска', 'Рок', '89031231231', 'riska@mail.ru'),
    ('Лжедмитрий Маликов', 'Death metal', '89296666666', 'metal@metal.com')
;

insert into gear(name, type, description, price)
values
    ('LesPaul', 'guitar', 'Пафосно и дорого', 800),
    ('Fender', 'guitar', 'Классика жанра', 700),
    ('Snare', 'drum', 'Рабочий малый', 100)
;

commit;