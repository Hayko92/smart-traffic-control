create table captureGIcrossRoad
(
    id           bigserial
        constraint capture_pk
            primary key,
    photo_url    varchar(500) not null,
    capture_time timestamp    not null,
    number       varchar(7)   not null
);

alter table captureGIcrossRoad
    owner to postgres;

create unique index capture_id_uindex
    on captureGIcrossRoad (id);
create table vehicle
(
    id                          bigserial
        constraint vehicle_pk
            primary key,
    number                      varchar(7) not null,
    registration_certificate    varchar(8) not null,
    owner_id                    bigint     not null,
    checked                     boolean default false,
    insurance_expiry_date       date       not null,
    tech_inspection_expiry_date date
);

alter table vehicle
    owner to postgres;

create unique index vehicle_number_uindex
    on vehicle (number);

create unique index vehicle_registration_certificate_uindex
    on vehicle (registration_certificate);

