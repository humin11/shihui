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

alter table attribution add constraint fk_attribution_coll_1 foreign key (coll_id) references collector (id) on delete restrict on update restrict;
create index ix_attribution_coll_1 on attribution (coll_id);
alter table collector_item add constraint fk_collector_item_coll_2 foreign key (coll_id) references collector (id) on delete restrict on update restrict;
create index ix_collector_item_coll_2 on collector_item (coll_id);
alter table goods add constraint fk_goods_brand_3 foreign key (brand_id) references brand (id) on delete restrict on update restrict;
create index ix_goods_brand_3 on goods (brand_id);
alter table goods add constraint fk_goods_model_4 foreign key (model_id) references product_model (id) on delete restrict on update restrict;
create index ix_goods_model_4 on goods (model_id);
alter table goods add constraint fk_goods_types_5 foreign key (types_id) references product_type (id) on delete restrict on update restrict;
create index ix_goods_types_5 on goods (types_id);
alter table goods_pic add constraint fk_goods_pic_goods_6 foreign key (goods_id) references goods (id) on delete restrict on update restrict;
create index ix_goods_pic_goods_6 on goods_pic (goods_id);
alter table product_model add constraint fk_product_model_brand_7 foreign key (brand_id) references brand (id) on delete restrict on update restrict;
create index ix_product_model_brand_7 on product_model (brand_id);
alter table product_type add constraint fk_product_type_parentType_8 foreign key (parent_type_id) references product_type (id) on delete restrict on update restrict;
create index ix_product_type_parentType_8 on product_type (parent_type_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table attribution;

drop table brand;

drop table collector;

drop table collector_item;

drop table goods;

drop table goods_pic;

drop table product_model;

drop table product_type;

SET FOREIGN_KEY_CHECKS=1;

