begin;

-- одиночный музыкант(вокалист, ударник) или группа
create table if not exists room_type(
    id serial primary key,
    name varchar(15) not null,
    -- min time in hours (музыкант - 1ч, группа - 3ч)
    rehearsal_min_time smallint not null default 1
);

create table if not exists room_status(
    id varchar(31) not null primary key,
    description varchar not null
);

-- Комнаты на репетиционной базе
create table if not exists room(
    id serial primary key,
    name varchar(63) not null unique,
    description varchar not null,
    area int not null,
    -- можно сделать таблицу статусов с ключом на нее
    -- active, closed
    status_id varchar not null,
    -- для кого предназначена комната
    type_id smallint not null,
    price int not null check (price > 0),
    constraint fk__room__type_id foreign key(type_id)
        references room_type(id) on update cascade on delete restrict,
    constraint fk__room__status foreign key(status_id)
        references room_status(id) on update cascade on delete restrict
);

-- все репетирующие
create table if not exists artist(
    id bigserial primary key,
    name varchar not null,
    genre varchar,
    phone varchar unique not null,
    email varchar
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
    start_datetime timestamp not null,
    room_id int not null,
    price int not null,
    -- reserved, cancelled, finished
    status varchar not null default 'reserved',
    -- paid, not paid
    payment_status varchar not null default 'not-paid',
        constraint fk__rehearsal__artist_id foreign key(artist_id)
    references artist(id) on update cascade on delete cascade,
        constraint fk__rehearsal__room_id foreign key(room_id)
    references room(id) on update cascade on delete restrict
);

create unique index uq__rehearsal__room_id__start_datetime
    on rehearsal(room_id, start_datetime)
    where status = 'RESERVED';

create table if not exists rehearsal_gear(
    id bigserial primary key,
    rehearsal_id bigint not null,
    gear_id int not null,
    constraint fk__rehearsal_gear__rehearsal_id foreign key(rehearsal_id)
        references rehearsal(id) on update cascade on delete cascade,
    constraint fk__rehearsal_gear__gear_id foreign key(gear_id)
        references gear(id) on update cascade on delete cascade
);

commit;