# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table client (
  id                            bigint auto_increment not null,
  name                          varchar(32) not null,
  apikey                        varchar(40) not null,
  description                   varchar(255),
  created                       datetime(6) not null,
  updated                       datetime(6) not null,
  constraint pk_client primary key (id)
);

create table account (
  username                      varchar(255) not null,
  password                      varchar(255),
  u_id                          bigint not null,
  type                          integer not null,
  constraint pk_account primary key (username)
);


# --- !Downs

drop table if exists client;

drop table if exists account;

