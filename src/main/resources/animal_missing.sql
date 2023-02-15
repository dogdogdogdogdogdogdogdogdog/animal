create table missing_animal
(
    animal_id    int auto_increment
        primary key,
    user_id      int           not null,
    name         varchar(32)   null,
    kind         varchar(32)   not null,
    variety      varchar(256)  null,
    bodysize     varchar(32)   null,
    sex          varchar(16)   null,
    color        varchar(32)   null,
    age          varchar(32)   null,
    description  varchar(2048) null,
    image_url    varchar(1024) null,
    area         varchar(32)   not null,
    missing_date timestamp     not null,
    created_date timestamp     not null
);


