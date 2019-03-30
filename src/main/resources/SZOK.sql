drop database if exists `SZOK`;
CREATE database `SZOK`;
USE `SZOK`;

drop table if exists `conference`;

create table `conference` (
`conferenceID` int(11) not null,
`conferenceName` varchar(50) not null,
`place` varchar(50) not null,
`maxAvailableSeats` int(11),
`availableSeats` int(11),
`startDate` date not null,
`endDate` date not null,
PRIMARY KEY (`conferenceID`)
);

insert into conference (conferenceID, conferenceName, place, maxAvailableSeats, availableSeats, startDate, endDate) values
(1, 'Ara ararauna', 'Capela', 1000, 1000, '2018-04-29', '2018-11-01'),
(2, 'Felis caracal', 'Trollhättan', 400, 120, '2018-04-02', '2018-10-30'),
(3, 'Spheniscus magellanicus', 'Lahan Sai', 315, 315, '2018-04-27', '2018-08-13'),
(4, 'Climacteris melanura', 'Allen', 800, 810,'2018-03-11', '2018-08-07'),
(5, 'Priodontes maximus', 'Cibanten', 150, 120, '2019-04-30', '2019-07-28'),
(6, 'Junonia genoveua', 'Mundri', 820, 140, '2018-03-30', '2018-08-04'),
(7, 'Tockus erythrorhyncus', 'San Matías', 120, 110,  '2019-04-10', '2019-08-02'),
(8, 'Ceryle rudis', 'El Kef', 500, 100, '2018-04-21', '2018-08-08'),
(9, 'Gabianus pacificus', 'Ballivor', 260, 210, '2019-04-05', '2019-07-27'),
(10, 'Phalacrocorax niger', 'Wangcun', 980, 300, '2020-04-16', '2020-10-16');

drop table if exists `workshop`;
create table `workshop`(
`workshopID` int(11) not null,
`maxAvailableSeats` int(11) not null,
`date` date not null,
 `startHour` time not null,
 `endHour` time not null,
 `conferenceID` int(11) not null,
 `teacherID` int(11) not null,
 primary key (`workshopID`)
  );
 
 drop table if exists `customer'sEmployees`;
 create table `customer'sEmployees`(
 `name` varchar(50) not null,
 `surname` varchar(50) not null,
 `employerID` int(11) not null,
 `email` text default null,
 `employeeID` int(11) not null,
 `conferenceID` int(11) not null,
 primary key (`employeeID`)
 );
 
 drop table if exists `lectureParticipants`;
  create table `lectureParticipants`(
 `employeeID` int(11) not null,
 `clientID` int(11) not null,
 `lectureID` int(11) not null,
  primary key (`employeeID`)
  );
  
  drop table if exists `firms`;
 create table `firms` (
	`fixedDiscounts` int(11) not null,
	`firmID` int(11) not null,
    `firmName` varchar(50) not null,
    `firmEmail` varchar(50) not null,
    primary key (`firmID`)
);

drop table if exists `order/reservation`;
create table `order/reservation`(
	`firmID` int(11) not null,
    `conferenceID` int(11) not null,
    `orderName` varchar(50) not null,
    `orderID` int(11) not null,
    `priceForEach` int(11) not null,
    `orderDate` date not null,
    `numberOfParticipants` int(11) not null,
    primary key (`orderID`)
);

drop table if exists `workshopParticipants`;
create table `workshopParticipants`(
	`employeeID` int(11) not null,
    `firmID` int(11) not null,
    `workshopID` int(11) not null,
    primary key (`employeeID`)
    );

drop table if exists `lecture`;
CREATE TABLE `lecture`(
`lectureID` int (11) not null,
`maxLearnersNUmber` int (11) not null,
`lectureDate` date not null,
`startHour` time not null,
`endHour` time not null,
`teacherID` int(11) not null,
`conferenceID` int (11) not null,
PRIMARY KEY (`lectureID`));

drop table if exists `conferenceParticipants`;
CREATE TABLE `conferenceParticipants`(
`conferenceID` int(11) not null,
`clientID` int (11) not null,
`employeeID` int(11) not null,
PRIMARY KEY(`employeeID`));

drop table if exists `discount`;
 CREATE TABLE `discount`(
 `conferenceID` int(11) not null,
 `discount` decimal (10,2) not null,
 `orderID` int(11) not null,
 PRIMARY KEY(`discount`));
 
drop table if exists `teacher`;
create table `teacher` (
`teacherID` int(11) not null,
`conferenceID` int(11) not null,
`payForHour` int(7) not null,
`firstName` varchar(50),
`surname` varchar(50),
PRIMARY KEY (`teacherID`)
 );

drop table if exists `payments`;
create table `payments`(
`paymentID` int(11) not null,
`orderID` int(11) not null,
`paymentDate` date not null,
`valueOfPayment`int(11) not null,
`firmID` int(11) not null,
primary key (`paymentID`)
);

/* dodawanie foreignKey conferenceID*/
alter table `workshop`
add constraint `workshop_conference_fk` foreign key (`conferenceID`) references `conference`(`conferenceID`);
alter table `lecture`
add constraint `lecture_conference_fk` foreign key (`conferenceID`) references `conference`(`conferenceID`);
alter table `order/reservation`
add constraint `order/reservation_conference_fk` foreign key (`conferenceID`) references `conference`(`conferenceID`);
alter table `customer'sEmployees`
add constraint `customer'sEmployees_conference_fk` foreign key (`conferenceID`) references `conference`(`conferenceID`);
alter table `conferenceParticipants`
add constraint `conferenceParticipants_conference_fk` foreign key (`conferenceID`) references `conference`(`conferenceID`);
alter table `discount`
add constraint `discount_conference_fk` foreign key (`conferenceID`) references `conference`(`conferenceID`);

/* dodawanie foreignKey teacherID*/
alter table `workshop`
add constraint `workshop_teacher_fk` foreign key (`teacherID`) references `teacher`(`teacherID`);
alter table `lecture`
add constraint `lecture_teacher_fk` foreign key (`teacherID`) references `teacher`(`teacherID`);

/*dodawanie FK orderID*/
alter table `payments`
add constraint `payments_order/reservation` foreign key (`orderID`) references `order/reservation` (`orderID`);

/*dodawanie FK employeeID */
alter table `conferenceParticipants`
add constraint `conferenceParticipants_customer'sEmployees_fk` foreign key (`employeeID`) references `customer'sEmployees` 
(`employeeID`);
alter table `workshopParticipants`
add constraint `workshopParticipants_customer'sEmployees_fk` foreign key (`employeeID`) references `customer'sEmployees` 
(`employeeID`);
alter table `lectureParticipants`
add constraint `lectureParticipants_customer'sEmployees_fk` foreign key (`employeeID`) references `customer'sEmployees` 
(`employeeID`);

/*dodawanie FK firmID */ 
alter table `payments`
add constraint `payments_firms_fk` foreign key(`firmID`) references `firms`(`firmID`);
alter table `order/reservation`
add constraint `order/reservation_firms_fk` foreign key(`firmID`) references `firms`(`firmID`);
alter table `customer'semployees`
add constraint `customer'semployees_firms_fk` foreign key(`employerID`) references `firms`(`firmID`);
alter table `conferenceParticipants`
add constraint `conferenceParticipants_firms_fk` foreign key(`clientID`) references `firms`(`firmID`);
alter table `workshopParticipants`
add constraint `workshopParticipants_firms_fk` foreign key(`firmID`) references `firms`(`firmID`);
alter table `lectureParticipants`
add constraint `lectureParticipants_firms_fk` foreign key(`clientID`) references `firms`(`firmID`);

insert into teacher (teacherID, conferenceID, payForHour, firstName, surname) values 
(101, 1, 26, 'Jan', 'Kowalski'),
(201, 2, 21, 'Zofia', 'Nowak'),
(301, 3, 31, 'Alicja', 'Krzysztofik'),
(401, 4, 39, 'Zenon', 'Wilk'),
(501, 5, 23, 'Marian', 'Kowalski'),
(601, 6, 28, 'Olaf', 'Kwiatkowski'),
(701, 7, 32, 'Janina', 'Kwiat'),
(801, 8, 20, 'Dominik', 'Duch'),
(901, 9, 34, 'Krzysztof', 'Żelazko'),
(1001, 10, 38, 'Anna', 'Nowak');

insert into firms (fixedDiscounts, firmID, firmName, firmEmail) values 
(true, 1, 'NYSE', 'adignam0@sun.com'),
(true, 2, 'NYSE', 'fbrinkman1@sina.com.cn'),
(false, 3, 'NASDAQ', 'pshovelbottom2@hibu.com'),
(false, 4, 'NASDAQ', 'pgostick3@soundcloud.com'),
(false, 5, 'NYSE', 'oconfort4@thetimes.co.uk'),
(true, 6, 'NYSE', 'markin5@chron.com'),
(false, 7, 'NASDAQ', 'lpickston6@boston.com'),
(false, 8, 'NYSE', 'bnewbatt7@smh.com.au'),
(true, 9, 'NASDAQ', 'cmann8@tuttocitta.it'),
(true, 10, 'NYSE', 'jpellissier9@constantcontact.com');



insert into workshop (workshopID, maxAvailableSeats, date, startHour, endHour, conferenceID, teacherID) values 
(1, 27, '2018-09-25', '20:05', '12:15', 1, 101),
(2, 40, '2018-06-20', '16:57', '14:21', 2, 201),
(3, 25, '2018-09-18', '12:02', '22:09', 3, 301),
(4, 31, '2018-06-15', '10:51', '7:33', 4, 401),
(5, 48, '2018-08-27', '14:25', '3:36', 5, 501),
(6, 35, '2019-01-17', '21:48', '7:31', 6, 601),
(7, 44, '2018-06-06', '23:24', '17:19', 7, 701),
(8, 47, '2018-12-31', '4:41', '5:54', 8, 801),
(9, 38, '2018-08-29', '23:41', '14:41', 9, 901),
(10, 28, '2018-11-06', '11:42', '3:09', 10, 1001);


insert into lecture (lectureID, maxLearnersNUmber, lectureDate, startHour, endHour, teacherID, conferenceID) values 
(1, 42, '2018-08-15', '17:37', '21:23', 101, 1),
(2, 32, '2018-08-08', '16:30', '18:50', 201, 2),
(3, 59, '2018-10-09', '2:13', '16:32', 301, 3),
(4, 37, '2019-02-01', '3:37', '17:20', 401, 4),
(5, 23, '2019-01-09', '13:54', '10:30', 501, 5),
(6, 55, '2018-10-06', '12:14', '15:15', 601, 6),
(7, 37, '2018-10-02', '11:59', '20:03', 701, 7),
(8, 31, '2018-03-25', '21:30', '3:45', 801, 8),
(9, 51, '2018-09-21', '8:05', '3:33', 901, 9),
(10, 36, '2019-01-22', '11:27', '18:48', 1001, 10);


insert into `customer'sEmployees` (name, surname, employerID, email, employeeID, conferenceID) values 
('Frieda', 'Clausen-Thue', 1, 'fclausenthue0@epa.gov', 1, 1),
('Karrie', 'Woodnutt', 2, 'kwoodnutt1@vinaora.com', 2, 2),
('Lebbie', 'Sisselot', 3, 'lsisselot2@github.com', 3, 3),
('Alexia', 'Short', 4, 'ashort3@vk.com', 4, 4),
('Tynan', 'Tipton', 5, 'ttipton4@imageshack.us', 5, 5),
('Robbie', 'Mitkov', 6, 'rmitkov5@angelfire.com', 6, 6),
('Eirena', 'Arkin', 7, 'earkin6@reverbnation.com', 7, 7),
('Sharla', 'McKerron', 8, 'smckerron7@w3.org', 8, 8),
('Felita', 'Dartnell', 9, 'fdartnell8@apache.org', 9, 9),
('Eddie', 'Wines', 10, 'ewines9@dailymail.co.uk', 10, 10);


insert into `order/reservation` (firmID, conferenceID, orderName, orderID, priceForEach, orderDate, numberOfParticipants) values 
(1, 1, 'Treeflex', 1, 49, '2018-10-20', 471),
(2, 2, 'Zamit', 2, 40, '2018-07-17', 405),
(3, 3, 'Zontrax', 3, 17, '2018-04-19', 283),
(4, 4, 'Toughjoyfax', 4, 27, '2018-04-22', 316),
(5, 5, 'Transcof', 5, 29, '2018-04-17', 244),
(6, 6, 'Domainer', 6, 22, '2018-11-02', 384),
(7, 7, 'Flowdesk', 7, 11, '2018-06-02', 114),
(8, 8, 'Stim', 8, 47, '2019-01-26', 438),
(9, 9, 'Cardguard', 9, 25, '2018-10-30', 214),
(10, 10, 'Zaam-Dox', 10, 46, '2018-08-11', 133);


insert into payments (paymentID, orderID, paymentDate, valueOfPayment, firmID) values 
(1, 1, '2018-10-10', 74, 1),
(2, 2, '2019-01-12', 99, 2),
(3, 3, '2018-08-16', 174, 3),
(4, 4, '2018-05-30', 180, 4),
(5, 5, '2018-10-03', 177, 5),
(6, 6, '2019-01-14', 139, 6),
(7, 7, '2018-12-03', 81, 7),
(8, 8, '2018-10-07', 88, 8),
(9, 9, '2018-12-04', 196, 9),
(10, 10, '2018-07-27', 74, 10);

insert into conferenceParticipants (conferenceID, clientID, employeeID) values 
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 4),
(5, 5, 5),
(6, 6, 6),
(7, 7, 7),
(8, 8, 8),
(9, 9, 9),
(10, 10, 10);

insert into discount (conferenceID, discount, orderID) values (1, 9, 1);
insert into discount (conferenceID, discount, orderID) values (2, 5, 2);
insert into discount (conferenceID, discount, orderID) values (3, 41, 3);
insert into discount (conferenceID, discount, orderID) values (4, 8, 4);
insert into discount (conferenceID, discount, orderID) values (5, 11, 5);
insert into discount (conferenceID, discount, orderID) values (6, 33, 6);
insert into discount (conferenceID, discount, orderID) values (7, 38, 7);
insert into discount (conferenceID, discount, orderID) values (8, 6, 8);
insert into discount (conferenceID, discount, orderID) values (9, 4, 9);
insert into discount (conferenceID, discount, orderID) values (10, 7, 10);

insert into lectureParticipants (employeeID, clientID, lectureID) values (1, 1, 1);
insert into lectureParticipants (employeeID, clientID, lectureID) values (2, 2, 2);
insert into lectureParticipants (employeeID, clientID, lectureID) values (3, 3, 3);
insert into lectureParticipants (employeeID, clientID, lectureID) values (4, 4, 4);
insert into lectureParticipants (employeeID, clientID, lectureID) values (5, 5, 5);
insert into lectureParticipants (employeeID, clientID, lectureID) values (6, 6, 6);
insert into lectureParticipants (employeeID, clientID, lectureID) values (7, 7, 7);
insert into lectureParticipants (employeeID, clientID, lectureID) values (8, 8, 8);
insert into lectureParticipants (employeeID, clientID, lectureID) values (9, 9, 9);
insert into lectureParticipants (employeeID, clientID, lectureID) values (10, 10, 10);


insert into workshopParticipants (employeeID, firmID, workshopID) values (1, 1, 1);
insert into workshopParticipants (employeeID, firmID, workshopID) values (2, 2, 2);
insert into workshopParticipants (employeeID, firmID, workshopID) values (3, 3, 3);
insert into workshopParticipants (employeeID, firmID, workshopID) values (4, 4, 4);
insert into workshopParticipants (employeeID, firmID, workshopID) values (5, 5, 5);
insert into workshopParticipants (employeeID, firmID, workshopID) values (6, 6, 6);
insert into workshopParticipants (employeeID, firmID, workshopID) values (7, 7, 7);
insert into workshopParticipants (employeeID, firmID, workshopID) values (8, 8, 8);
insert into workshopParticipants (employeeID, firmID, workshopID) values (9, 9, 9);
insert into workshopParticipants (employeeID, firmID, workshopID) values (10, 10, 10);

drop view if exists availableConference;
CREATE VIEW availableConference AS
select * from conference
where startDate > curdate();

drop view if exists conference2018;
create VIEW conference2018 AS
select * from conference
where startDate like '2018%';

drop view if exists availableWorkshops;
CREATE VIEW availableWorkshops AS
select * from workshop
having maxavailableseats > 20;

drop view if exists availableWorkshopsAtConf;
create view availableWorkshopsAtConf as
select w.*, c.startDate from workshop w
join conference c
on w.conferenceID = c.conferenceID
where c.startDate > curdate()
having w.maxavailableseats > 0;

drop table if exists `conference_history`;
create table `conference_history` (
`action` varchar(50),
`ID` int(11) auto_increment,
`createdDate` date,
`createdTime` time,
`conferenceID` int(11) not null,
`conferenceName` varchar(50) not null,
`place` varchar(50) not null,
`startDate` date not null,
`endDate` date not null,
PRIMARY KEY (`ID`)
);


drop trigger if exists conference_history_insert;
delimiter //
create trigger `conference_history_insert` after insert on `conference`
FOR EACH ROW
BEGIN
	INSERT INTO `conference_history` (`action`, `createdTime`, `createdDate`, `conferenceID`, `conferenceName`, `place`, `startDate`, `endDate`)
	VALUES ('insert', now(), now(),  new.`conferenceID`, new.`conferenceName`, new.`place`, new.`startdate`, new.`enddate`);
END //
 
delimiter ;

drop trigger if exists conference_history_update;
DELIMITER //
CREATE TRIGGER `conference_history_update` AFTER UPDATE ON `conference`
FOR EACH ROW
BEGIN
	INSERT INTO `conference_history`(`action`, `createdTime`, `createdDate`, `conferenceID`, `conferenceName`, `place`, `startDate`, `endDate`)
	VALUES ('update_old', now(), now(), old.`conferenceID`, old.`conferenceName`, old.`place`, old.`startdate`, old.`enddate`),
    ('update_new', now(), now(), new.`conferenceID`, new.`conferenceName`, new.`place`, new.`startdate`, new.`enddate`);
    
END //

DELIMITER ;

insert into conference (conferenceID, conferenceName, place, startDate, endDate) values
(17, 'Nowa_konferencja', 'Nowa', '2019-05-01', '2019-05-07');

UPDATE `conference` SET `startDate` = '2019-02-01' WHERE `conferenceID` = 10;

drop view if exists availableAtConf;
CREATE VIEW availableAtConf AS
select c.*, sum(o.numberofparticipants) NumberOfParticipants from availableConference c
join `order/reservation` o
on c.conferenceID = o.conferenceID
group by conferenceID
having NumberOfParticipants < maxAvailableSeats;

/*
drop trigger if exists participants_before_insert;
DELIMITER //
CREATE TRIGGER `participants_before_insert` BEFORE INSERT ON `order/reservation`
FOR EACH ROW
BEGIN
	DECLARE orderedSeats int;
    DECLARE takenSeats int;
    DECLARE confID int;
    DECLARE maxAv int;
    SELECT(NEW.`conferenceID`) into confID;
    SELECT(NEW.`numberOfParticipants`) INTO orderedSeats;
    IF exists (select * from availableAtConf where conferenceID = confID) 
    THEN
		SELECT numberOfParticipants from availableAtConf
		where confID = conferenceID
		into takenSeats;
    ELSE 
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'WYBRANA KONFERENCJA JEST NIEDOSTĘPNA';
    END IF;
	SELECT maxAvailableSeats from conference
    where conferenceID = confID
    into maxAv;
   	IF orderedSeats + takenSeats > maxAv THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'BRAK WOLNYCH MIEJSC';
	else
		SIGNAL SQLSTATE '22012'
		SET MESSAGE_TEXT = 'REZERWACJA DOKONANA';
    END IF;    
END //
DELIMITER ;

insert into `order/reservation` (firmID, conferenceID, orderName, orderID, priceForEach, orderDate, numberOfParticipants) values 
(10, 9, 'Treeflex', 15, 49, curdate(), 10);
*/

drop procedure if exists addingOrder;
DELIMITER //
create procedure addingOrder (
	IN conf_ID int(11),
	IN participants int(11))
BEGIN
    DECLARE takenSeats int(11);
    DECLARE maxAv int(11);
    select maxavailableseats from availableAtConf where conferenceID = conf_ID into maxAv;
    select availableSeats from availableAtConf where conferenceID = conf_ID into takenSeats;
	if takenseats + participants >= maxav then
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'BRAK WOLNYCH MIEJSC';
	else
    insert into `order/reservation` (firmID, conferenceID, orderName, orderID, priceForEach, orderDate, numberOfParticipants) values 
	(10, conf_ID, 'ble', 102, 45, curdate(), participants);
	END IF;
    
    UPDATE conference SET availableSeats = takenSeats + participants
    where conferenceID = conf_ID;
END //

DELIMITER ;

call addingOrder(9, 5);

drop user if exists 'finance'@'%';
create user 'finance'@'%' identified by 'pwd';

grant select on szok.* to 'finance'@'%';
show grants for 'finance';

revoke all privileges, grant option from 'finance';
show grants for 'finance';

grant insert on szok.payments to 'finance'@'%';
show grants for 'finance';

drop user 'finance';

