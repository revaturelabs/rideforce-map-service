/* map-service */ 

drop table address cascade constraints;
drop table favorite_addresses cascade constraints;

create table address (
	address varchar2(255 char) not null primary key,
	latitude float(126),
	longitude float(126)
);

create table favorite_addresses (
	location_id number(10) not null primary key,
	address varchar2(85 char),
	latitude float(126),
	longitude float(126),
	favorited_location_name varchar2(255 char),
	user_id number(10)
);

drop sequence hibernate_sequence;
create sequence hibernate_sequence;

commit;