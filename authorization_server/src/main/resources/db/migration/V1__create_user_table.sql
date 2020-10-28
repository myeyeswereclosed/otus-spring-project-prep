begin;

create table if not exists rehearsal_base_user(
    id bigserial primary key,
    name varchar(100) not null,
    phone varchar(20) not null unique,
    email varchar(50),
    password varchar not null,
    -- artist?
    role varchar not null default 'user'
);

commit;