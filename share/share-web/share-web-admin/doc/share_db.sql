/*==============================================================*/
/* Database name:  share                                        */
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014/3/9 0:19:08                             */
/*==============================================================*/


drop database if exists share;

/*==============================================================*/
/* Database: share                                              */
/*==============================================================*/
create database share;

use share;

/*==============================================================*/
/* Table: ARTICLE                                               */
/*==============================================================*/
create table ARTICLE
(
   ARTICLE_ID           int not null auto_increment,
   ARTICLE_TITLE        varchar(50) not null,
   ARTICLE_CONTENT      text not null,
   ARTICLE_URL          varchar(50),
   ARTICLE_FROM         varchar(20),
   ARTICLE_ATHOR        varchar(20) not null,
   STATUS               char(1) not null comment '0待审核；1 审核通过 ；2已发布；3下架',
   UPDATE_TIME          datetime not null,
   CREATE_TIME          datetime not null,
   IS_ORIGINAL          char(1) not null comment '0否；1是',
   IS_VALID             char(1) not null comment '0未删除；1删除',
   primary key (ARTICLE_ID)
)
ENGINE=InnoDB  DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: ARTICLE_HOT                                           */
/*==============================================================*/
create table ARTICLE_HOT
(
   HOT_ID               int not null auto_increment,
   ARTICLE_ID           int not null,
   REPRINT_COUNT        int not null default 0,
   primary key (HOT_ID)
)
ENGINE=InnoDB  DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: ARTICLE_KIND                                          */
/*==============================================================*/
create table ARTICLE_KIND
(
   ARTICLE_KIND_ID      int not null auto_increment,
   KIND_NAME            varchar(30) not null,
   STATUS               char(1) not null comment '0无效；1有效',
   UPDATE_TIME          datetime not null,
   CREATE_TIME          datetime not null,
   ADMIN_ID             int,
   PARENT_KIND_ID       int not null default 0,
   primary key (ARTICLE_KIND_ID)
)
ENGINE=InnoDB  DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: ARTICLE_KIND_REL                                      */
/*==============================================================*/
create table ARTICLE_KIND_REL
(
   ARTICLE_KIND_REL_ID  int not null auto_increment,
   ARTICLE_ID           int,
   ARTICLE_KIND_ID      int not null,
   STATUS               char(1) not null comment '0无效；1有效',
   UPDATE_TIME          datetime not null,
   CREATE_TIME          datetime not null,
   ADMIN_ID             int,
   primary key (ARTICLE_KIND_REL_ID)
)
ENGINE=InnoDB  DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: ARTICLE_LABEL                                         */
/*==============================================================*/
create table ARTICLE_LABEL
(
   LABEL_ID             int not null auto_increment,
   LABEL_NAME           varchar(20) not null,
   STATUS               char(1) not null comment '0启用；1未用',
   CREATE_TIME          datetime not null,
   primary key (LABEL_ID)
)
ENGINE=InnoDB  DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: ARTICLE_LABEL_REL                                     */
/*==============================================================*/
create table ARTICLE_LABEL_REL
(
   ARTICLE_LABEL_REL_ID int not null,
   ARTICLE_ID           int not null,
   LABEL_ID             int not null,
   CARETE_TIME          datetime not null,
   primary key (ARTICLE_LABEL_REL_ID)
)
ENGINE=InnoDB  DEFAULT CHARSET=utf8;

alter table ARTICLE_HOT add constraint FK_ARTICLE_HOT_REF_ARTICLE foreign key (ARTICLE_ID)
      references ARTICLE (ARTICLE_ID) on delete restrict on update restrict;

alter table ARTICLE_KIND_REL add constraint FK_ARTICLE_KIND_REL_REF_ARTICLE foreign key (ARTICLE_ID)
      references ARTICLE (ARTICLE_ID) on delete restrict on update restrict;

alter table ARTICLE_KIND_REL add constraint FK_ARTICLE_KIND_REL_REF_ARTICLE_KIND foreign key (ARTICLE_KIND_ID)
      references ARTICLE_KIND (ARTICLE_KIND_ID) on delete restrict on update restrict;

alter table ARTICLE_LABEL_REL add constraint FK_ARTICLE_LABEL_REL_REF_ATRICLE foreign key (ARTICLE_ID)
      references ARTICLE (ARTICLE_ID) on delete restrict on update restrict;

alter table ARTICLE_LABEL_REL add constraint FK_ATRICLE_LABEL_REL_REF_ARTICLE_LABEL foreign key (LABEL_ID)
      references ARTICLE_LABEL (LABEL_ID) on delete restrict on update restrict;

