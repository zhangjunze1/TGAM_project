/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/12/24 15:15:12                          */
/*==============================================================*/


drop table if exists address;

drop table if exists coupon;

drop table if exists coupon_holding;

drop table if exists goods;

drop table if exists goods_evaluation;

drop table if exists middle;

drop table if exists mj;

drop table if exists myuser;

drop table if exists orders;

drop table if exists orders_coupons;

drop table if exists out_rider_entry;

drop table if exists outrider;

drop table if exists rider;

drop table if exists rider_entry;

drop table if exists shop;

drop table if exists shop_order;

drop table if exists sort;

/*==============================================================*/
/* Table: address                                               */
/*==============================================================*/
create table address
(
   add_id               bigint not null auto_increment,
   user_id              bigint,
   add_detail           varchar(100) not null,
   add_name             varchar(20) not null,
   add_phone            varchar(20) not null,
   primary key (add_id)
);

/*==============================================================*/
/* Table: coupon                                                */
/*==============================================================*/
create table coupon
(
   coupon_id            bigint not null auto_increment,
   shop_id              bigint,
   coupon_youhui        numeric(8,0) not null,
   coupon_list_num      int not null,
   coupon_time_start    datetime not null,
   coupon_time_finish   datetime not null,
   primary key (coupon_id)
);

/*==============================================================*/
/* Table: coupon_holding                                        */
/*==============================================================*/
create table coupon_holding
(
   coupon_id            bigint not null,
   user_id              bigint not null,
   shop_id              bigint not null,
   quantity             int,
   closing_date         datetime,
   youhui               numeric(8,0),
   primary key (coupon_id, user_id, shop_id)
);

/*==============================================================*/
/* Table: goods                                                 */
/*==============================================================*/
create table goods
(
   goods_id             bigint not null auto_increment,
   shop_id              bigint,
   sort_id              bigint,
   goods_name           varchar(50) not null,
   goods_price          numeric(8,0) not null,
   goods_youhui         numeric(8,0),
   goods_sum            int not null,
   goods_picture        varchar(100),
   goods_start_time     datetime,
   goods_remake         varchar(100),
   primary key (goods_id)
);

/*==============================================================*/
/* Table: goods_evaluation                                      */
/*==============================================================*/
create table goods_evaluation
(
   user_id              bigint not null,
   shop_id              bigint not null,
   goods_id             bigint not null,
   goods_evaluation_to  varchar(20),
   goods_evaluation_time datetime,
   goods_evaluation_photo varchar(500),
   goods_evaluation_star int,
   primary key (user_id, shop_id, goods_id)
);

/*==============================================================*/
/* Table: middle                                                */
/*==============================================================*/
create table middle
(
   middle_id            bigint not null auto_increment,
   middle_name          varchar(50),
   middle_address       varchar(100),
   primary key (middle_id)
);

/*==============================================================*/
/* Table: mj                                                    */
/*==============================================================*/
create table mj
(
   mj_id                bigint not null auto_increment,
   mj_money             numeric(8,0) not null,
   mj_youhui            numeric(8,0) not null,
   mj_flagcoupon        bool not null,
   primary key (mj_id)
);

/*==============================================================*/
/* Table: myuser                                                */
/*==============================================================*/
create table myuser
(
   user_id              bigint not null auto_increment,
   user_name            varchar(20) not null,
   user_sex             varchar(10) not null,
   user_pwd             varchar(10) not null,
   user_phone           varchar(20) not null,
   user_email           varchar(30) not null,
   user_rigister_time   datetime,
   user_money           numeric(8,0),
   primary key (user_id)
);

/*==============================================================*/
/* Table: orders                                                */
/*==============================================================*/
create table orders
(
   order_id             bigint not null auto_increment,
   goods_id             bigint,
   order_sum            int not null,
   order_price          numeric(8,0) not null,
   order_disconut       numeric(8,0),
   primary key (order_id)
);

/*==============================================================*/
/* Table: orders_coupons                                        */
/*==============================================================*/
create table orders_coupons
(
   coupon_id            bigint not null,
   user_id              bigint not null,
   shop_id              bigint not null,
   orders_coupons_need  int not null,
   orders_coupons_already int not null,
   primary key (coupon_id, user_id, shop_id)
);

/*==============================================================*/
/* Table: out_rider_entry                                       */
/*==============================================================*/
create table out_rider_entry
(
   add_id               bigint not null,
   out_rider_id         bigint not null,
   out_rider_entry_time datetime,
   out_rider_entry_evaluation varchar(20),
   out_rider_entry_income numeric(8,0),
   primary key (add_id, out_rider_id)
);

/*==============================================================*/
/* Table: outrider                                              */
/*==============================================================*/
create table outrider
(
   out_rider_id         bigint not null auto_increment,
   out_rider_name       varchar(20) not null,
   out_rider_status     varchar(50) not null,
   out_rider_time       datetime not null,
   out_rider_phone      varchar(20) not null,
   out_rider_pwd        varchar(20) not null,
   primary key (out_rider_id)
);

/*==============================================================*/
/* Table: rider                                                 */
/*==============================================================*/
create table rider
(
   rider_id             bigint not null auto_increment,
   rider_name           varchar(20) not null,
   rider_phone          varchar(20) not null,
   rider_pwd            varchar(20) not null,
   rider_address        varchar(20) not null,
   primary key (rider_id)
);

/*==============================================================*/
/* Table: rider_entry                                           */
/*==============================================================*/
create table rider_entry
(
   rider_id             bigint not null,
   add_id               bigint not null,
   rider_entry_time     datetime,
   rider_entry_evaluation varchar(20),
   rider_entry_income   numeric(8,0),
   primary key (rider_id, add_id)
);

/*==============================================================*/
/* Table: shop                                                  */
/*==============================================================*/
create table shop
(
   shop_id              bigint not null auto_increment,
   mj_id                bigint,
   shop_name            varchar(50) not null,
   shop_star            int,
   shop_per_consume     numeric(8,0),
   shop_total           numeric(8,0),
   shop_count           int,
   shop_phone           varchar(20) not null,
   shop_pwd             varchar(20) not null,
   shop_address         varchar(30) not null,
   shop_picture         varchar(100),
   shop_city            varchar(30),
   primary key (shop_id)
);

/*==============================================================*/
/* Table: shop_order                                            */
/*==============================================================*/
create table shop_order
(
   shop_id              bigint not null,
   mj_id                bigint not null,
   coupon_id            bigint not null,
   rider_id             bigint not null,
   user_id              bigint not null,
   add_id               bigint not null,
   order_id             bigint not null,
   out_rider_id         bigint not null,
   so_old_money         numeric(8,0) not null,
   so_new_money         numeric(8,0),
   so_order_time        datetime,
   so_intime            datetime,
   so_state             varchar(30),
   primary key (shop_id, mj_id, coupon_id, rider_id, user_id, add_id, order_id, out_rider_id)
);

/*==============================================================*/
/* Table: sort                                                  */
/*==============================================================*/
create table sort
(
   sort_id              bigint not null auto_increment,
   sort_name            varchar(50) not null,
   sort_start_time      datetime,
   sort_change_time     datetime,
   sort_remake          varchar(100),
   primary key (sort_id)
);

alter table address add constraint FK_Relationship_1 foreign key (user_id)
      references myuser (user_id) on delete restrict on update restrict;

alter table coupon add constraint FK_Relationship_2 foreign key (shop_id)
      references shop (shop_id) on delete restrict on update restrict;

alter table coupon_holding add constraint FK_coupon_holding foreign key (coupon_id)
      references coupon (coupon_id) on delete restrict on update restrict;

alter table coupon_holding add constraint FK_coupon_holding2 foreign key (user_id)
      references myuser (user_id) on delete restrict on update restrict;

alter table coupon_holding add constraint FK_coupon_holding3 foreign key (shop_id)
      references shop (shop_id) on delete restrict on update restrict;

alter table goods add constraint FK_Relationship_4 foreign key (shop_id)
      references shop (shop_id) on delete restrict on update restrict;

alter table goods add constraint FK_Relationship_5 foreign key (sort_id)
      references sort (sort_id) on delete restrict on update restrict;

alter table goods_evaluation add constraint FK_goods_evaluation foreign key (user_id)
      references myuser (user_id) on delete restrict on update restrict;

alter table goods_evaluation add constraint FK_goods_evaluation2 foreign key (shop_id)
      references shop (shop_id) on delete restrict on update restrict;

alter table goods_evaluation add constraint FK_goods_evaluation3 foreign key (goods_id)
      references goods (goods_id) on delete restrict on update restrict;

alter table orders add constraint FK_Relationship_6 foreign key (goods_id)
      references goods (goods_id) on delete restrict on update restrict;

alter table orders_coupons add constraint FK_orders_coupons foreign key (coupon_id)
      references coupon (coupon_id) on delete restrict on update restrict;

alter table orders_coupons add constraint FK_orders_coupons2 foreign key (user_id)
      references myuser (user_id) on delete restrict on update restrict;

alter table orders_coupons add constraint FK_orders_coupons3 foreign key (shop_id)
      references shop (shop_id) on delete restrict on update restrict;

alter table out_rider_entry add constraint FK_out_rider_entry foreign key (add_id)
      references address (add_id) on delete restrict on update restrict;

alter table out_rider_entry add constraint FK_out_rider_entry2 foreign key (out_rider_id)
      references outrider (out_rider_id) on delete restrict on update restrict;

alter table rider_entry add constraint FK_rider_entry foreign key (rider_id)
      references rider (rider_id) on delete restrict on update restrict;

alter table rider_entry add constraint FK_rider_entry2 foreign key (add_id)
      references address (add_id) on delete restrict on update restrict;

alter table shop add constraint FK_Relationship_3 foreign key (mj_id)
      references mj (mj_id) on delete restrict on update restrict;

alter table shop_order add constraint FK_shop_order foreign key (shop_id)
      references shop (shop_id) on delete restrict on update restrict;

alter table shop_order add constraint FK_shop_order2 foreign key (mj_id)
      references mj (mj_id) on delete restrict on update restrict;

alter table shop_order add constraint FK_shop_order3 foreign key (coupon_id)
      references coupon (coupon_id) on delete restrict on update restrict;

alter table shop_order add constraint FK_shop_order4 foreign key (rider_id)
      references rider (rider_id) on delete restrict on update restrict;

alter table shop_order add constraint FK_shop_order5 foreign key (user_id)
      references myuser (user_id) on delete restrict on update restrict;

alter table shop_order add constraint FK_shop_order6 foreign key (add_id)
      references address (add_id) on delete restrict on update restrict;

alter table shop_order add constraint FK_shop_order7 foreign key (order_id)
      references orders (order_id) on delete restrict on update restrict;

alter table shop_order add constraint FK_shop_order8 foreign key (out_rider_id)
      references outrider (out_rider_id) on delete restrict on update restrict;

