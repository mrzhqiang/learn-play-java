# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  id                            bigint auto_increment not null,
  number                        varchar(16) not null,
  password                      varchar(16) not null,
  level                         integer not null,
  user_id                       bigint,
  version                       bigint not null,
  created                       datetime(6) not null,
  updated                       datetime(6) not null,
  constraint pk_account primary key (id)
);

create table client (
  id                            bigint auto_increment not null,
  name                          varchar(32) not null,
  apikey                        varchar(40) not null,
  description                   varchar(255),
  version                       bigint not null,
  created                       datetime(6) not null,
  updated                       datetime(6) not null,
  constraint pk_client primary key (id)
);

create table user (
  id                            bigint auto_increment not null,
  nickname                      varchar(24),
  sex                           varchar(1) not null,
  age                           integer(3) not null,
  birthday                      datetime(6),
  blood                         varchar(6),
  profession                    varchar(32),
  location                      varchar(128),
  school                        varchar(32),
  company                       varchar(32),
  phone                         varchar(24),
  email                         varchar(128),
  description                   varchar(200),
  version                       bigint not null,
  created                       datetime(6) not null,
  updated                       datetime(6) not null,
  constraint ck_user_sex check ( sex in ('O','M','F')),
  constraint pk_user primary key (id)
);

alter table account add constraint fk_account_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_account_user_id on account (user_id);


# --- !Downs

alter table account drop foreign key fk_account_user_id;
drop index ix_account_user_id on account;

drop table if exists account;

drop table if exists client;

drop table if exists user;

