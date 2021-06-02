CREATE TABLE manufacturer (

manufacturerID int NOT NULL AUTO_INCREMENT,

manufacturer varchar(30) NOT NULL,

PRIMARY KEY (manufacturerID)

);

 

CREATE TABLE bike (

bikeID int NOT NULL AUTO_INCREMENT,

manufacturerID int NOT NULL,

model VARCHAR(30) NOT NULL,

year int NOT NULL,

colour VARCHAR(30) NOT NULL,

price decimal(7,2) NOT NULL,

PRIMARY KEY (bikeID),

FOREIGN KEY (manufacturerID) REFERENCES manufacturer(manufacturerID)

);

create table SEC_USER
(
  userId            BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  email             VARCHAR(75) NOT NULL UNIQUE,
  encryptedPassword VARCHAR(128) NOT NULL,
  enabled           BIT NOT NULL 
) ;

create table SEC_ROLE
(
  roleId   BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  roleName VARCHAR(30) NOT NULL UNIQUE
) ;

create table USER_ROLE
(
  ID     BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  userId BIGINT NOT NULL,
  roleId BIGINT NOT NULL
);

alter table USER_ROLE
  add constraint USER_ROLE_UK unique (userId, roleId);

alter table USER_ROLE
  add constraint USER_ROLE_FK1 foreign key (userId)
  references SEC_USER (userId);
 
alter table USER_ROLE
  add constraint USER_ROLE_FK2 foreign key (roleId)
  references SEC_ROLE (roleId);