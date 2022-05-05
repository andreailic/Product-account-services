-- Database: it70g2019
drop table if exists racun cascade;
drop table if exists proizvodjac cascade;
drop table if exists proizvod cascade;
drop table if exists stavka_racuna cascade;
create table racun
(
id integer primary key,
datum date,
nacin_placanja varchar(200)
);

create table proizvodjac
(
id integer primary key,
naziv varchar(50),
adresa varchar(200),
kontakt varchar(100)
);

create table proizvod
(
 id integer primary key,
 naziv varchar(50),
 proizvodjac integer not null,
 constraint fk_proizvod_proizvodjac foreign key(proizvodjac) references proizvodjac(id)
);

create table stavka_racuna
(
 id integer primary key,
 redni_broj integer,
 kolicina integer,
 jedinica_mere varchar(50),
 cena numeric,
 racun integer not null,
 proizvod integer not null,
 constraint fk_stavka_racuna_racun foreign key(racun) references racun(id),
 constraint fk_stavka_racuna_proizvod foreign key(proizvod) references proizvod(id)
);

create index idx_pk_proizvodjac on proizvodjac(id);
create index idx_pk_racun on racun(id);
create index idx_pk_proizvod on proizvod(id);
create index idx_pk_stavka_racuna on stavka_racuna(id);

create index idx_fk_proizvod_proizvodjac on proizvod(proizvodjac);
create index idx_fk_stavka_racuna_racun on stavka_racuna(racun);
create index idx_fk_stavka_racuna_proizvod on stavka_racuna(proizvod);

drop sequence if exists proizvodjac_id_seq;
create sequence proizvodjac_id_seq
minvalue 0
start with 1;

drop sequence if exists racun_id_seq;
create sequence racun_id_seq
minvalue 0
start with 1;

drop sequence if exists proizvod_id_seq;
create sequence proizvod_id_seq
minvalue 0
start with 1;

drop sequence if exists stavka_racuna_id_seq;
create sequence stavka_racuna_id_seq
minvalue 0
start with 1;