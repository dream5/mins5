/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014/5/27 7:09:10                            */
/*==============================================================*/

drop database if exists share;

/*==============================================================*/
/* Database: share                                              */
/*==============================================================*/
create database share;

use share;

drop table if exists admin;

drop table if exists article;

drop table if exists article_copy;

drop table if exists article_hot;

drop table if exists article_kind;

drop table if exists article_kind_rel;

drop table if exists article_label;

drop table if exists article_label_rel;

drop table if exists article_recommend;

drop table if exists user;

drop table if exists attachment;

/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
create table admin
(
   admin_id             int not null auto_increment,
   user_name            varchar(20) not null,
   nick_name            varchar(20),
   real_name            varchar(20),
   password             varchar(50) not null,
   create_time          datetime not null,
   primary key (admin_id)
)
engine=innodb default charset=utf8;

/*==============================================================*/
/* Table: article                                               */
/*==============================================================*/
create table article
(
   article_id           int not null auto_increment,
   article_title        varchar(200) not null,
   article_content      text not null,
   article_url          varchar(200),
   article_from         varchar(100),
   article_author       varchar(100) not null,
   status               char(1) not null comment '0待审核；1 审核通过 ；2已发布；3下架',
   update_time          datetime not null,
   create_time          datetime not null,
   is_original          char(1) not null comment '0否；1是',
   is_valid             char(1) not null comment '0未删除；1删除',
   primary key (article_id)
)
engine=innodb default charset=utf8;

/*==============================================================*/
/* Table: article_copy                                          */
/*==============================================================*/
create table article_copy
(
   article_id           int not null auto_increment,
   article_title        varchar(200) not null,
   article_content      text not null,
   article_url          varchar(200),
   article_from         varchar(100),
   article_author       varchar(100) not null,
   status               char(1) not null comment '0待审核；1 审核通过 ；2已发布；3下架',
   update_time          datetime not null,
   create_time          datetime not null,
   is_original          char(1) not null comment '0否；1是',
   is_valid             char(1) not null comment '0未删除；1删除',
   primary key (article_id)
)
engine=innodb default charset=utf8;

/*==============================================================*/
/* Table: article_hot                                           */
/*==============================================================*/
create table article_hot
(
   hot_id               int not null auto_increment,
   article_id           int not null,
   reprint_count        int not null default 0,
   primary key (hot_id)
)
engine=innodb default charset=utf8;

/*==============================================================*/
/* Table: article_kind                                          */
/*==============================================================*/
create table article_kind
(
   article_kind_id      int not null auto_increment,
   kind_name            varchar(30) not null,
   kind_pinyin          varchar(30) not null,
   status               char(1) not null comment '0无效；1有效',
   update_time          datetime not null,
   create_time          datetime not null,
   admin_id             int,
   parent_kind_id       int not null default 0,
   primary key (article_kind_id)
)
engine=innodb default charset=utf8;

/*==============================================================*/
/* Table: article_kind_rel                                      */
/*==============================================================*/
create table article_kind_rel
(
   article_kind_rel_id  int not null auto_increment,
   article_id           int not null,
   article_kind_id      int not null,
   status               char(1) not null comment '0无效；1有效',
   update_time          datetime not null,
   create_time          datetime not null,
   admin_id             int,
   primary key (article_kind_rel_id)
)
engine=innodb default charset=utf8;

/*==============================================================*/
/* Table: article_label                                         */
/*==============================================================*/
create table article_label
(
   label_id             int not null auto_increment,
   label_name           varchar(20) not null,
   status               char(1) not null comment '0未启用；1启用',
   create_time          datetime not null,
   primary key (label_id)
)
engine=innodb default charset=utf8;

/*==============================================================*/
/* Table: article_label_rel                                     */
/*==============================================================*/
create table article_label_rel
(
   article_label_rel_id int not null auto_increment,
   article_id           int not null,
   label_id             int not null,
   carete_time          datetime not null,
   primary key (article_label_rel_id)
)
engine=innodb default charset=utf8;

/*==============================================================*/
/* Table: article_recommend                                     */
/*==============================================================*/
create table article_recommend
(
   recommend_id         int not null auto_increment,
   recommend_position   char(1) not null comment '0主页；1详情页',
   recommend_sort       int not null,
   article_id           int not null,
   primary key (recommend_id)
)
engine=innodb default charset=utf8;

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   user_id              int not null auto_increment,
   user_name            varchar(20) not null,
   nick_name            varchar(20),
   real_name            varchar(20),
   password             varchar(50) not null,
   gender               char(1) not null,
   create_time          datetime not null,
   primary key (user_id)
)
engine=innodb default charset=utf8;

/*==============================================================*/
/* Table: attachment                                            */
/*==============================================================*/
create table attachment
(
   attachment_id        int not null auto_increment,
   art_article_id       int,
   article_id           int not null,
   attachment_name      varchar(100) not null,
   attachment_type      varchar(10) not null,
   create_date          datetime not null,
   attachmen_sts        char(1) comment '0：无效；1：有效',
   large                varchar(200),
   midsize              varchar(200),
   small                varchar(200),
   attachment_old_name  varchar(100),
   primary key (attachment_id)
)
engine=innodb default charset=utf8;

alter table article_hot add constraint fk_article_hot_ref_article foreign key (article_id)
      references article (article_id) on delete restrict on update restrict;

alter table article_kind_rel add constraint fk_article_kind_rel_ref_article foreign key (article_id)
      references article (article_id) on delete restrict on update restrict;

alter table article_kind_rel add constraint fk_article_kind_rel_ref_article_kind foreign key (article_kind_id)
      references article_kind (article_kind_id) on delete restrict on update restrict;

alter table article_label_rel add constraint fk_article_label_rel_ref_atricle foreign key (article_id)
      references article (article_id) on delete restrict on update restrict;

alter table article_label_rel add constraint fk_atricle_label_rel_ref_article_label foreign key (label_id)
      references article_label (label_id) on delete restrict on update restrict;

alter table article_recommend add constraint fk_article_recommend_rel_article foreign key (article_id)
      references article (article_id) on delete restrict on update restrict;

alter table attachment add constraint fk_attachment_ref_article foreign key (art_article_id)
      references article (article_id) on delete restrict on update restrict;

