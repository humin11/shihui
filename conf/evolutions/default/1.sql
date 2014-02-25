# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table attribution (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  rules                     varchar(255),
  layer                     varchar(255),
  coll_id                   bigint,
  constraint pk_attribution primary key (id))
;

create table baoliao (
  id                        bigint auto_increment not null,
  url                       varchar(255),
  content                   longtext,
  user_id                   bigint,
  is_read                   tinyint(1) default 0,
  create_at                 datetime,
  update_at                 datetime,
  release_at                datetime,
  constraint pk_baoliao primary key (id))
;

create table brand (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_brand primary key (id))
;

create table collector (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  site_name                 varchar(255),
  type_name                 varchar(255),
  url                       varchar(255),
  item_rules                varchar(255),
  min_pages                 integer,
  max_pages                 integer,
  is_active                 tinyint(1) default 0,
  create_at                 datetime,
  update_at                 datetime,
  constraint pk_collector primary key (id))
;

create table collector_item (
  id                        bigint auto_increment not null,
  url                       varchar(255),
  coll_id                   bigint,
  collector_count           integer,
  disconnected_count        integer,
  response_code             varchar(255),
  status                    varchar(255),
  create_at                 datetime,
  update_at                 datetime,
  constraint pk_collector_item primary key (id))
;

create table comment (
  id                        bigint auto_increment not null,
  user_id                   bigint,
  goods_id                  bigint,
  content                   varchar(255),
  create_at                 datetime,
  update_at                 datetime,
  constraint pk_comment primary key (id))
;

create table egg (
  id                        bigint auto_increment not null,
  user_id                   bigint,
  goods_id                  bigint,
  create_at                 datetime,
  update_at                 datetime,
  constraint pk_egg primary key (id))
;

create table flower (
  id                        bigint auto_increment not null,
  user_id                   bigint,
  goods_id                  bigint,
  create_at                 datetime,
  update_at                 datetime,
  constraint pk_flower primary key (id))
;

create table goods (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  source                    varchar(255),
  price                     double,
  original_price            double,
  description               longtext,
  original_url              varchar(255),
  brand_id                  bigint,
  model_id                  bigint,
  types_id                  bigint,
  is_release                tinyint(1) default 0,
  comment                   longtext,
  create_at                 datetime,
  update_at                 datetime,
  release_at                datetime,
  constraint pk_goods primary key (id))
;

create table goods_pic (
  id                        bigint auto_increment not null,
  original                  varchar(255),
  tiny                      varchar(255),
  small                     varchar(255),
  middle                    varchar(255),
  larger                    varchar(255),
  huge                      varchar(255),
  is_cover                  tinyint(1) default 0,
  goods_id                  bigint,
  constraint pk_goods_pic primary key (id))
;

create table linked_account (
  id                        bigint auto_increment not null,
  user_id                   bigint,
  provider_user_id          varchar(255),
  provider_key              varchar(255),
  constraint pk_linked_account primary key (id))
;

create table product_model (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  brand_id                  bigint,
  constraint pk_product_model primary key (id))
;

create table product_type (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  parent_name               varchar(255),
  parent_type_id            bigint,
  constraint pk_product_type primary key (id))
;

create table reply (
  id                        bigint auto_increment not null,
  user_id                   bigint,
  content                   varchar(255),
  comment_id                bigint,
  create_at                 datetime,
  update_at                 datetime,
  constraint pk_reply primary key (id))
;

create table security_role (
  id                        bigint auto_increment not null,
  role_name                 varchar(255),
  constraint pk_security_role primary key (id))
;

create table token_action (
  id                        bigint auto_increment not null,
  token                     varchar(255),
  target_user_id            bigint,
  type                      varchar(2),
  created                   datetime,
  expires                   datetime,
  constraint ck_token_action_type check (type in ('EV','PR')),
  constraint uq_token_action_token unique (token),
  constraint pk_token_action primary key (id))
;

create table users (
  id                        bigint auto_increment not null,
  email                     varchar(255),
  name                      varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  last_login                datetime,
  active                    tinyint(1) default 0,
  email_validated           tinyint(1) default 0,
  constraint pk_users primary key (id))
;

create table user_permission (
  id                        bigint auto_increment not null,
  value                     varchar(255),
  constraint pk_user_permission primary key (id))
;


create table users_security_role (
  users_id                       bigint not null,
  security_role_id               bigint not null,
  constraint pk_users_security_role primary key (users_id, security_role_id))
;

create table users_user_permission (
  users_id                       bigint not null,
  user_permission_id             bigint not null,
  constraint pk_users_user_permission primary key (users_id, user_permission_id))
;
alter table attribution add constraint fk_attribution_coll_1 foreign key (coll_id) references collector (id) on delete restrict on update restrict;
create index ix_attribution_coll_1 on attribution (coll_id);
alter table baoliao add constraint fk_baoliao_user_2 foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_baoliao_user_2 on baoliao (user_id);
alter table collector_item add constraint fk_collector_item_coll_3 foreign key (coll_id) references collector (id) on delete restrict on update restrict;
create index ix_collector_item_coll_3 on collector_item (coll_id);
alter table comment add constraint fk_comment_user_4 foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_comment_user_4 on comment (user_id);
alter table comment add constraint fk_comment_goods_5 foreign key (goods_id) references goods (id) on delete restrict on update restrict;
create index ix_comment_goods_5 on comment (goods_id);
alter table egg add constraint fk_egg_user_6 foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_egg_user_6 on egg (user_id);
alter table egg add constraint fk_egg_goods_7 foreign key (goods_id) references goods (id) on delete restrict on update restrict;
create index ix_egg_goods_7 on egg (goods_id);
alter table flower add constraint fk_flower_user_8 foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_flower_user_8 on flower (user_id);
alter table flower add constraint fk_flower_goods_9 foreign key (goods_id) references goods (id) on delete restrict on update restrict;
create index ix_flower_goods_9 on flower (goods_id);
alter table goods add constraint fk_goods_brand_10 foreign key (brand_id) references brand (id) on delete restrict on update restrict;
create index ix_goods_brand_10 on goods (brand_id);
alter table goods add constraint fk_goods_model_11 foreign key (model_id) references product_model (id) on delete restrict on update restrict;
create index ix_goods_model_11 on goods (model_id);
alter table goods add constraint fk_goods_types_12 foreign key (types_id) references product_type (id) on delete restrict on update restrict;
create index ix_goods_types_12 on goods (types_id);
alter table goods_pic add constraint fk_goods_pic_goods_13 foreign key (goods_id) references goods (id) on delete restrict on update restrict;
create index ix_goods_pic_goods_13 on goods_pic (goods_id);
alter table linked_account add constraint fk_linked_account_user_14 foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_linked_account_user_14 on linked_account (user_id);
alter table product_model add constraint fk_product_model_brand_15 foreign key (brand_id) references brand (id) on delete restrict on update restrict;
create index ix_product_model_brand_15 on product_model (brand_id);
alter table product_type add constraint fk_product_type_parentType_16 foreign key (parent_type_id) references product_type (id) on delete restrict on update restrict;
create index ix_product_type_parentType_16 on product_type (parent_type_id);
alter table reply add constraint fk_reply_user_17 foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_reply_user_17 on reply (user_id);
alter table reply add constraint fk_reply_comment_18 foreign key (comment_id) references comment (id) on delete restrict on update restrict;
create index ix_reply_comment_18 on reply (comment_id);
alter table token_action add constraint fk_token_action_targetUser_19 foreign key (target_user_id) references users (id) on delete restrict on update restrict;
create index ix_token_action_targetUser_19 on token_action (target_user_id);



alter table users_security_role add constraint fk_users_security_role_users_01 foreign key (users_id) references users (id) on delete restrict on update restrict;

alter table users_security_role add constraint fk_users_security_role_security_role_02 foreign key (security_role_id) references security_role (id) on delete restrict on update restrict;

alter table users_user_permission add constraint fk_users_user_permission_users_01 foreign key (users_id) references users (id) on delete restrict on update restrict;

alter table users_user_permission add constraint fk_users_user_permission_user_permission_02 foreign key (user_permission_id) references user_permission (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table attribution;

drop table baoliao;

drop table brand;

drop table collector;

drop table collector_item;

drop table comment;

drop table egg;

drop table flower;

drop table goods;

drop table goods_pic;

drop table linked_account;

drop table product_model;

drop table product_type;

drop table reply;

drop table security_role;

drop table token_action;

drop table users;

drop table users_security_role;

drop table users_user_permission;

drop table user_permission;

SET FOREIGN_KEY_CHECKS=1;

