create table users (
    id bigint not null primary key generated always as identity,
    display_name text not null
);