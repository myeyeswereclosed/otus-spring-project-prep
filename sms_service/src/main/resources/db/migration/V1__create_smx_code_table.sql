begin;

create table if not exists sms_code(
    id bigserial primary key,
    phone varchar not null,
    value varchar not null,
    created_at timestamp not null default now(),
    expires_at timestamp not null,
    actual boolean default true
);

create unique index if not exists uq__sms_code__phone__actual on sms_code(phone, actual) where actual;

commit;