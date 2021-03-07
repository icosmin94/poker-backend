CREATE TABLE users (
   id          varchar(50) CONSTRAINT idkey PRIMARY KEY,
   email       varchar(50) NOT NULL,
   enabled     BOOLEAN  NOT NULL,
   first_name   varchar(50),
   last_name    varchar(50),
   username    varchar(50)
);