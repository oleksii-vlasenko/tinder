create table users
(
    id serial not null
        constraint users_pk
            primary key,
    name varchar not null,
    image varchar not null,
    likes integer[] not null,
    dislikes integer[] not null
);

create table messages
(
    id serial not null
        constraint messages_pk
            primary key,
    send int not null,
    receive int not null,
    text varchar not null,
    time timestamp not null
);

create table authentications
(
    id serial not null
        constraint authentications_pk
            primary key,
    user_id int not null,
    login varchar not null,
    password varchar not null
);

