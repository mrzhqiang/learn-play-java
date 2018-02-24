# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table api_key (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  created                       datetime(6),
  updated                       datetime(6),
  constraint pk_api_key primary key (id)
);

create table account (
  username                      varchar(255) not null,
  password                      varchar(255),
  u_id                          bigint not null,
  type                          integer not null,
  constraint pk_account primary key (username)
);


# --- !Downs

drop table if exists api_key;

drop table if exists account;

