-- Create a blank database as will be used by the WoW Auction House framework
drop table auction;
drop table item;
drop table player;
drop table updates;

create table updates (
	update_id integer not null auto_increment primary key,
	update_time integer not null,
	realm varchar(40)
);


create table players (
	player_id varchar(20) primary key,
	realm varchar(40) not null,
	faction char(1) not null,
	constraint player_faction check (faction in ('H', 'A'))
);

create table items (
	item_id decimal(6,0) primary key,
	item_name varchar(256)
);

create table auctions (
	auction_id decimal(9,0) not null,
	update_id integer not null,
	item_id decimal(6,0) not null,
	player_id varchar(20) not null,
	auction_house char(1) not null,
	bid decimal(12, 0),
	buyout decimal(12, 0),
	quantity decimal(3, 0),
	time_left char(1),
	primary key (auction_id, update_id),
	foreign key (update_id) references updates(update_id),
	foreign key (item_id) references items(item_id),
	foreign key (player_id) references players(player_id),
	constraint auction_ah check (auction_house in ('A', 'H', 'N')),
	constraint auction_tl check (time_left in ('S', 'M', 'L', 'V'))
);