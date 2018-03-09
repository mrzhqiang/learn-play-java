# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  id                            bigint auto_increment not null,
  number                        varchar(16) not null,
  password                      varchar(16) not null,
  level                         integer not null,
  user_id                       bigint not null,
  version                       bigint not null,
  created                       datetime(6) not null,
  modified                      datetime(6) not null,
  constraint uq_account_number unique (number),
  constraint uq_account_user_id unique (user_id),
  constraint pk_account primary key (id)
);

create table client (
  id                            bigint auto_increment not null,
  name                          varchar(24) not null,
  apikey                        varchar(40) not null,
  description                   varchar(255),
  version                       bigint not null,
  created                       datetime(6) not null,
  modified                      datetime(6) not null,
  constraint uq_client_name unique (name),
  constraint uq_client_apikey unique (apikey),
  constraint pk_client primary key (id)
);

create table token (
  id                            bigint auto_increment not null,
  access_token                  varchar(255) not null,
  refresh_token                 varchar(255) not null,
  expires_in                    bigint not null,
  account_id                    bigint not null,
  version                       bigint not null,
  created                       datetime(6) not null,
  modified                      datetime(6) not null,
  constraint uq_token_access_token unique (access_token),
  constraint uq_token_refresh_token unique (refresh_token),
  constraint pk_token primary key (id)
);

create table user (
  id                            bigint auto_increment not null,
  nickname                      varchar(16) not null,
  sex                           varchar(1) not null,
  age                           integer not null,
  birthday                      datetime(6),
  blood                         varchar(16),
  profession                    varchar(16),
  location                      varchar(255),
  school                        varchar(32),
  company                       varchar(32),
  phone                         varchar(11),
  email                         varchar(64),
  signature                     varchar(140),
  description                   varchar(128),
  version                       bigint not null,
  created                       datetime(6) not null,
  modified                      datetime(6) not null,
  constraint ck_user_sex check ( sex in ('M','F')),
  constraint pk_user primary key (id)
);

alter table account add constraint fk_account_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;

alter table token add constraint fk_token_account_id foreign key (account_id) references account (id) on delete restrict on update restrict;
create index ix_token_account_id on token (account_id);


# --- !Downs

alter table account drop foreign key fk_account_user_id;

alter table token drop foreign key fk_token_account_id;
drop index ix_token_account_id on token;

drop table if exists account;

drop table if exists client;

drop table if exists token;

drop table if exists user;

