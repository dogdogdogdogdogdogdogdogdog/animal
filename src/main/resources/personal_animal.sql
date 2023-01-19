create table personal_animal
(
    animal_id            int auto_increment
        primary key,
    animal_kind          varchar(32)   null,
    animal_variety       varchar(32)   null,
    animal_sex           varchar(32)   null,
    animal_sterilization varchar(32)   null,
    animal_bacterin      varchar(32)   null,
    image_url            varchar(256)  null,
    description          varchar(1024) null,
    created_date         timestamp     not null,
    last_modified_date   timestamp     not null
);

insert into personal_animal (animal_id, animal_kind, animal_variety, animal_sex, animal_sterilization, animal_bacterin, image_url, description, created_date, last_modified_date)
values  (1, '貓', '混種貓', '母', 'T', 'T', 'https://asms.coa.gov.tw/amlapp/upload/pic/36d6cf2d-d694-4101-96e9-e5cc8a3ca448_org.jpg', '受傷', '2023-01-19 22:21:32', '2023-01-19 22:21:32'),
        (2, '貓', '混種貓', '公', 'T', 'T', 'https://asms.coa.gov.tw/amlapp/upload/pic/a0727630-d58d-4797-93a1-9ab1853f8617_org.jpg', '', '2023-01-19 22:22:00', '2023-01-19 22:22:00');
