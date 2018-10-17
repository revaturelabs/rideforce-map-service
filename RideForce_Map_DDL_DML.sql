DROP TABLE ADDRESS;

CREATE TABLE address (
    address     VARCHAR2(100) NOT NULL,
    latitude    NUMBER(12,8),
    longitude   NUMBER(12,8)
);

ALTER TABLE address ADD CONSTRAINT address_pk PRIMARY KEY ( address );


Insert into ADDRESS (ADDRESS,LATITUDE,LONGITUDE) values ('11730 Plaza America Dr #205, Reston, VA 20190',38.953414,-77.350533);
Insert into ADDRESS (ADDRESS,LATITUDE,LONGITUDE) values ('2100 Astoria Cir, Herndon, VA 20170',38.96787,-77.414742);
Insert into ADDRESS (ADDRESS,LATITUDE,LONGITUDE) values ('503 Pride Ave, Herndon, VA 20170',38.966271,-77.388985);
