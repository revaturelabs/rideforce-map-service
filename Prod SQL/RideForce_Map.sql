/* map-service */ 

drop table address cascade constraints;

create table address (
	cached_id number not null primary key,
	address varchar2(85 char) not null,
	city varchar2(85 char),
	state_code varchar2(2 char),
	zip_code varchar2(85 char),
	latitude float(126),
	longitude float(126)
);

drop sequence hibernate_sequence;
create sequence hibernate_sequence;

commit;