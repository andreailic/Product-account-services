-- Database: it70g2019

 insert into proizvodjac
 values(nextval('proizvodjac_id_seq'),'Apple','Lasla Gala 30','0640727072');
  insert into proizvodjac
 values(nextval('proizvodjac_id_seq'),'Milka','Slobodana Bajica 28','0603377409');
  insert into proizvodjac
 values(nextval('proizvodjac_id_seq'),'Nike','Bulevar kralja Petra 12','0645756444');
  insert into proizvodjac
 values(nextval('proizvodjac_id_seq'),'Zara','Narodnog fronta 23E','0640010988');
 
 
 insert into racun
  values(nextval('racun_id_seq'),to_date('22.02.2022.','dd.mm.yyyy.'),'gotovina');
   insert into racun
  values(nextval('racun_id_seq'),to_date('16.05.2022.','dd.mm.yyyy.'),'kartica');
   insert into racun
  values(nextval('racun_id_seq'),to_date('10.01.2019..','dd.mm.yyyy.'),'gotovina');
   insert into racun
  values(nextval('racun_id_seq'),to_date('13.08.2015..','dd.mm.yyyy.'),'na rate');
  
  insert into proizvod
  values(nextval('proizvod_id_seq'),'IWatch',1);
   insert into proizvod
  values(nextval('proizvod_id_seq'),'IphoneX',1);
   insert into proizvod
  values(nextval('proizvod_id_seq'),'IPhone5',1);
   insert into proizvod
  values(nextval('proizvod_id_seq'),'MacBook',1);
   insert into proizvod
  values(nextval('proizvod_id_seq'),'Iphone12',1);
   insert into proizvod
  values(nextval('proizvod_id_seq'),'Zenski donji deo trenerke',3);
   insert into proizvod
  values(nextval('proizvod_id_seq'),'Komplet decija trenerka',3);
   insert into proizvod
  values(nextval('proizvod_id_seq'),'AirForfce muske patike',3);
   insert into proizvod
  values(nextval('proizvod_id_seq'),'Zenske cizme',3);
   insert into proizvod
  values(nextval('proizvod_id_seq'),'Ranac',3);
   insert into proizvod
  values(nextval('proizvod_id_seq'),'Cokolada',2);
   insert into proizvod
  values(nextval('proizvod_id_seq'),'Krem',2);
   insert into proizvod
  values(nextval('proizvod_id_seq'),'Keks',2);
  insert into proizvod
   values(nextval('proizvod_id_seq'),'Haljina',4);
   insert into proizvod
  values(nextval('proizvod_id_seq'),'Naocare',4);
   insert into proizvod
  values(nextval('proizvod_id_seq'),'Torba',4);
  

 insert into stavka_racuna
  values (nextval('stavka_racuna_id_seq'),12,1,'kom',46000,2,1);
   insert into stavka_racuna
  values (nextval('stavka_racuna_id_seq'),5,2,'kom',16000,3,15);
   insert into stavka_racuna
  values (nextval('stavka_racuna_id_seq'),3,3,'kom',8000,1,16);
   insert into stavka_racuna
  values (nextval('stavka_racuna_id_seq'),4,2,'kom',224000,4,4);
   insert into stavka_racuna
  values (nextval('stavka_racuna_id_seq'),8,4,'kom',1800,1,12);
  
  
insert into proizvodjac (id, naziv, adresa, kontakt) values (-100, 'test naziv', 'test adresa', 'test kontakt');
	insert into proizvod (id, naziv, proizvodjac) values (-100,'test naziv','2');
	insert into racun (id, datum, nacin_placanja) values (-100, to_date('22.04.2022.', 'dd.mm.yyyy'),'karticom');
	insert into stavka_racuna (id, redni_broj, kolicina, jedinica_mere, cena, racun, proizvod) values
	    (-100, '7', '5', 'kom', '1500','1','6' );