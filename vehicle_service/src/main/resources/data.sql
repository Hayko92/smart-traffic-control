create table address
(
    id       bigint  not null
        constraint table_name_pk
            primary key,
    city     varchar(100)                                                          not null,
    street   varchar(100)                                                          not null,
    building varchar(10)                                                           not null,
    zip_code integer                                                               not null,
    country  varchar(100)                                                          not null
);

alter table address
    owner to postgres;

create table vehicle_service."ownerContact"
(
    id            bigserial
        constraint ownercontact_pk
            primary key,
    email_address varchar(50)                                                   not null,
    phone_number  varchar(50)                                                    not null,
    address_id    integer     not null

);

alter table vehicle_service."ownerContact"
    owner to postgres;

create table vehicle_service.owner
(
    id             serial
        constraint owner_pk
            primary key,
    id_number      varchar(30) not null,
    license_number varchar(30) not null,
    firstname      varchar(50) not null,
    lastname       varchar(50),
    contact_id     integer
        constraint owner_ownercontact_id_fk
            references vehicle_service."ownerContact"
);

alter table vehicle_service.owner
    owner to postgres;

create unique index owner_id_uindex
    on vehicle_service.owner (id);

create unique index ownercontact_id_uindex
    on vehicle_service."ownerContact" (id);

create table vehicle_service.vehicle
(
    id                          bigserial
        constraint vehicle_pk
            primary key,
    vin_number                  varchar(30)              not null,
    plate_number                varchar(20)              not null,
    horse_power                 integer                  not null,
    color                       varchar(30),
    production_year             integer                  not null,
    insurance_expiry_date       timestamp with time zone not null,
    tech_inspection_expiry_date timestamp with time zone not null,
    checked                     boolean default false    not null,
    owner_id                    bigint
        constraint vehicle_owner_id_fk
            references vehicle_service.owner,
    mark_id                     bigint                   not null,
    model_id                    bigint                   not null
);

alter table vehicle_service.vehicle
    owner to postgres;

create unique index vehicle_id_uindex
    on vehicle_service.vehicle (id);

create table vehicle_service.vehicle_mark
(
    id        serial
        constraint vehicle_mark_pk
            primary key,
    mark_name varchar(50) not null
);

alter table vehicle_service.vehicle_mark
    owner to postgres;

create unique index vehicle_mark_id_uindex
    on vehicle_service.vehicle_mark (id);

create table vehicle_service.vehicle_model
(
    id         serial
        constraint vehicle_model_pk
            primary key,
    model_name varchar(50) not null,
    mark_id    integer
        constraint vehicle_model_vehicle_mark_id_fk
            references vehicle_service.vehicle_mark
);

alter table vehicle_service.vehicle_model
    owner to postgres;

create unique index vehicle_model_id_uindex
    on vehicle_service.vehicle_model (id);