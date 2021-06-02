insert into SEC_User (email, encryptedPassword, ENABLED)
values ('frank@symphonybikes.com', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', 1);

insert into SEC_User (email, encryptedPassword, ENABLED)
values ('dan@symphonybikes.com', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

 
insert into sec_role (roleName)
values ('ROLE_USER');
 
insert into sec_role (roleName)
values ('ROLE_ADMIN');
 

 
insert into user_role (userId, roleId)
values (1, 1);
 
insert into user_role (userId, roleId)
values (1, 2);
 
insert into user_role (userId, roleId)
values (2, 1);


insert into manufacturer (manufacturer) values ('Giant');
insert into manufacturer (manufacturer) values ('Trek');
insert into manufacturer (manufacturer) values ('Pivot');
insert into manufacturer (manufacturer) values ('Rocky Mountain');
insert into manufacturer (manufacturer) values ('Santa Cruz');
insert into manufacturer (manufacturer) values ('Ibis');
insert into manufacturer (manufacturer) values ('Orbea');