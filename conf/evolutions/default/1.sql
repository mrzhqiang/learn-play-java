# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  username                      varchar(255) not null,
  password                      varchar(255),
  u_id                          bigint not null,
  type                          integer not null,
  constraint pk_account primary key (username)
);


# --- !Downs

drop table if exists account;

