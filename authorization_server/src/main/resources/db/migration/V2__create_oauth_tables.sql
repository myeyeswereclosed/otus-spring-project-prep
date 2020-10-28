begin;

create table if not exists oauth_client_details (
    client_id varchar(256) primary key,
    resource_ids varchar(256),
    client_secret varchar(256),
    scope varchar(256),
    authorized_grant_types varchar(256),
    web_server_redirect_uri varchar(256),
    authorities varchar(256),
    access_token_validity int,
    refresh_token_validity int,
    additional_information varchar(4096),
    autoapprove varchar(256)
);

create table if not exists oauth_client_token (
    token_id varchar(256),
    token bytea,
    authentication_id varchar(256),
    user_name varchar(256),
    client_id varchar(256)
);

create table if not exists oauth_access_token (
    token_id varchar(256),
    token bytea,
    authentication_id varchar(256),
    user_name varchar(256),
    client_id varchar(256),
    authentication bytea,
    refresh_token varchar(256)
);

create table if not exists oauth_refresh_token (
    token_id varchar(256),
    token bytea,
    authentication bytea
);

commit;