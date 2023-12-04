
create table stores (
bizesId varchar(100) primary key,
bizesNm varchar(100),
brchNm varchar(100),
indsLclsCd varchar(100),
indsLclsNm varchar(100),
indsMclsCd varchar(100),
indsMclsNm varchar(100),
indsSclsCd varchar(100),
indsSclsNm varchar(100),
ksicCd varchar(100),
ksicNm varchar(100),
ctprvnCd varchar(100),
ctprvnNm varchar(100),
signguCd varchar(100),
signguNm varchar(100),
adongCd varchar(100),
adongNm varchar(100),
ldongCd varchar(100),
ldongNm varchar(100),
lnoCd varchar(100),
plotSctCd varchar(100),
plotSctNm varchar(100),
lnoMnno varchar(100),
lnoSlno varchar(100),
lnoAdr varchar(100),
rdnmCd varchar(100),
rdnm varchar(100),
bldMnno varchar(100),
bldSlno varchar(100),
bldMngNo varchar(40),
bldNm varchar(100),
rdnmAdr varchar(100),
oldZipcd varchar(100),
newZipcd varchar(100),
dongNo varchar(100),
flrNo varchar(100),
hoNo varchar(100),
lon double,
lat double
);

create table comProp(
id bigint primary key auto_increment,
dealAmount bigint,
buildingArea double,
buildingUse varchar(50),
buildYear varchar(50),
dealYear varchar(50),
plottage double,
dong varchar(50),
sigungu varchar(50),
landUse varchar(50),
dealMonth varchar(50),
buildingType varchar(50),
dealDay varchar(50),
regionalCode varchar(50)
);

create table estates(
id bigint primary key auto_increment,
dealClass varchar(30),
dealAmount bigint,
floor int,
area double,
description varchar(30),
joinYear varchar(5),
joinMonth varchar(5),
joinDay varchar(5),
sido varchar(30),
gugun varchar(30),
dong varchar(30),
jibun varchar(30),
dongCode varchar(30),
lat double,
lon double
);

desc stores;
desc comProp;

select *
from stores;

select count(bizesId)
from stores;

desc dongcodelatlng;

select dongcode, addressCount, sidoName, gugunName, dongName, lat, lon
from dongcodeLatlng;

select distinct substr(dongCode,1,5)
from dongcodeLatlng;

insert into estates (dealClass,dealAmount ,floor ,area ,description,joinYear ,joinMonth ,joinDay ,sido ,gugun,dong ,jibun,dongCode,lat ,lon )
values 
("매매",9000,1,25.7,"매물입니다.1","2023","06","12","서울특별시","강서구","등촌동","120-1", "1234567890","37.56714408016501","126.97578516702059"),
("매매",9000,1,25.7,"매물입니다.2","2023","06","12","서울특별시","강서구","등촌동","120-2", "1234567890","37.56724408016501","126.97778516702059"),
("매매",9000,1,25.7,"매물입니다.3","2023","06","12","서울특별시","강서구","등촌동","120-3", "1234567890","37.56734408016501","126.97978516702059")
;


select dealClass,dealAmount ,floor ,area ,description,joinYear ,joinMonth ,joinDay ,sido ,gugun,dong ,jibun,dongCode,lat ,lon 
from estates
where id = 1;

select dealClass,dealAmount ,floor ,area ,description,joinYear ,joinMonth ,joinDay ,sido ,gugun,dong ,jibun,dongCode,lat ,lon 
from estates
where lat between 37.565 and 37.58
and lon between 126.97 and 126.98;

select count(*)
from stores;

select *
from stores
where 1=1
#indsLclsCd = "G2"
#and signguCd = "11110"
and lat between 37.565 and 37.58
and lon between 126.97 and 126.98;
#where bizesId = "MA010120220808011483"; 

select avg(dealAmount/buildingArea)
from comProp
where regionalCode=substr("1111010100",1,5) and dong="청운동";

select count(*)
from stores
where indsLclsCd= "I2";

select *
from estates;

select *
from dongcodelatlng
order by (abs(lat-37.5459)+abs(lon-126.9634))
limit 1;

select *
from members;