drop table if exists engine;

create table engine (
	id bigserial primary key,
	number varchar(128),
	fuel_type varchar(16),
	engine_type varchar(16)
);