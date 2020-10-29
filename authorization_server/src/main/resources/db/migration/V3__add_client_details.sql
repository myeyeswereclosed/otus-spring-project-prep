begin;

insert into oauth_client_details(
    client_id, client_secret, scope, authorized_grant_types, access_token_validity, refresh_token_validity
)
-- any_secret
values (
    'any_id', '$2y$12$C50dmWsK/sfxzp79faAaxe/rFQtdIWhxovH4SbXNhPFA3VHoOhw8G', 'read,write', 'password', 10800, -1
);

commit;