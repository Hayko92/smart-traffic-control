create table vehicle
(
    checked boolean default false,
    tech_inspection_expiry_date timestamp with time zone not null,
    insurance_expiry_date timestamp with time zone not null,
    plate_number varchar(7) not null,
    id bigserial,
    constraint vehicle_pk
        primary key (id)
);

create unique index vehicle_id_uindex
    on vehicle (id);

