create table basic_credentials (
    user_id bigint not null primary key references users(id),
    username text not null unique,
    password text not null
);