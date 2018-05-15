create table category
(
    id varchar(100) primary key,
    cname varchar(32) not null unique,
    cdesc varchar(255)
)
create table book
(
    id varchar(100) primary key,
    bname varchar(32) not null unique,
    author varchar(20) not null,
    pageNum int not null,
    price float(6,2) not null,
    description varchar(255),
	path varchar(100) not null,
	oldImageName varchar(100) not null,
	newImageName varchar(100) not null,
	cid varchar(100),
	foreign key(cid) references category(id)    
)

create table orders
(
	id varchar(100) primary key,
	totalMoney float(6,2) not null,
	totalNum int
	
)

create table orderItem
(
	id varchar(100) primary key,
	totalMoney float(6,2) not null,
	num int,
	bid varchar(100),
	foreign key(bid) references book(id)
)
