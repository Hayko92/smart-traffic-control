create table if not exists vehicle
(
    id bigserial
        constraint vehicle_pk
            primary key,
    plate_number varchar(7) not null,
    insurance_expiry_date timestamp with time zone not null,
    tech_inspection_expiry_date timestamp with time zone not null,
    checked boolean default false
);

alter table vehicle owner to postgres;

create unique index if not exists vehicle_id_uindex
    on vehicle (id);

