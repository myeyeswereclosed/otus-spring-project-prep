begin;

drop table if exists rehearsal_gear cascade;
drop table if exists gear cascade;
drop table if exists room cascade;
drop table if exists artist cascade;
drop table if exists rehearsal cascade;
drop table if exists artist_type cascade;

-- одиночный музыкант(вокалист, ударник) или группа
create table if not exists artist_type(
    id varchar(15) not null primary key,
    -- min time in hours (музыкант - 1ч, группа - 3ч)
    rehearsal_min_time smallint not null default 1
);

-- Комнаты на репетиционной базе
create table if not exists room(
    id serial primary key,
    name varchar(63) not null unique,
    description varchar not null,
    area int not null,
    -- можно сделать таблицу статусов с ключом на нее
    -- active, closed
    status varchar not null default 'active',
    -- для кого предназначена комната
    artist_type varchar not null,
    price int not null check (price > 0),
    constraint fk__room__artist_type foreign key(artist_type)
        references artist_type(id) on update cascade on delete restrict
);

-- все репетирующие
create table if not exists artist(
    id bigserial primary key,
    name varchar not null,
    genre varchar,
    -- -> login
    phone varchar not null,
    email varchar,
    password varchar not null,
    type varchar not null,
    constraint fk__artist_type foreign key(type)
        references artist_type(id) on update cascade on delete restrict
);

-- оборудование, доступное для аренды на репетицию
create table if not exists gear(
    id serial primary key,
    name varchar not null,
    -- тип: гитара, усилитель и тп
    type varchar not null,
    description varchar not null,
    price int not null default 0
);

create table if not exists rehearsal(
    id bigserial primary key,
    artist_id bigint not null,
    start_datetime timestamptz not null,
    room_id int not null,
    -- reserved, cancelled, finished
    status varchar not null default 'reserved',
    -- paid, not paid
    payment_status varchar not null default 'not-paid',
    constraint fk__rehearsal__artist_id foreign key(artist_id)
        references artist(id) on update cascade on delete cascade,
    constraint fk__rehearsal__room_id foreign key(room_id)
        references room(id) on update cascade on delete restrict

);

create table if not exists rehearsal_gear(
    id bigserial not null,
    rehearsal_id bigint not null,
    gear_id int not null,
    constraint fk__rehearsal_gear__rehearsal_id foreign key(rehearsal_id)
        references rehearsal(id) on update cascade on delete cascade,
    constraint fk__rehearsal_gear__gear_id foreign key(gear_id)
        references gear(id) on update cascade on delete cascade
);

commit;