create table users (
    id bigint not null generated always as identity,
    display_name text not null
);