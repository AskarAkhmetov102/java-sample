drop table if exists wheel;
drop table if exists glass;
drop table if exists car;

create table car (
	id bigserial primary key,
	vin varchar(128),
	color varchar(128),
	brand_name varchar(128),
	model_name varchar(128),
	engine_number varchar(128),
	engine_horse_power int
);

create table glass (
	car_id int not null references car(id),
	glass_number varchar(128)
);

create table wheel (
	car_id int not null references car(id),
	radius int,
	is_winter bool
);

