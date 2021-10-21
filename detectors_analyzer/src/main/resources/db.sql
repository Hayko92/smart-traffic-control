create database smart_traffic_control
    with owner postgres;

create table capture
(
    id           bigserial
        constraint capture_pk
            primary key,
    "photo_URL"  varchar(500) not null,
    capture_time timestamp    not null
);

alter table capture
    owner to postgres;

create unique index capture_id_uindex
    on capture (id);


-- auto-generated definition
create table vehicle
(
    id  bigint  default nextval('"Vehicle_id_seq"'::regclass) not null
        constraint vehicle_pk
            primary key,
    number                      varchar(7)                                            not null,
    registration_certificate    varchar(8)                                            not null,
    owner_id                    bigint                                                not null,
    checked                     boolean default false,
    insurance_expiry_date       date                                                  not null,
    tech_inspection_expiry_date integer                                               not null
);

alter table vehicle
    owner to postgres;

create unique index vehicle_id_uindex
    on vehicle (id);

create unique index vehicle_number_uindex
    on vehicle (number);

create unique index vehicle_registration_certificate_uindex
    on vehicle (registration_certificate);

