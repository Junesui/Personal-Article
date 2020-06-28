/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/6/27 16:17:02                           */
/*==============================================================*/


drop table if exists blog;

drop table if exists blog_tag;

drop table if exists comment;

drop table if exists friendslink;

drop table if exists message;

drop table if exists oneword;

drop table if exists siteinfo;

drop table if exists tag;

drop table if exists tool;

drop table if exists type;

drop table if exists user;

/*==============================================================*/
/* Table: blog                                                  */
/*==============================================================*/
create table blog
(
   id                   bigint not null auto_increment,
   title                varchar(255) comment '����',
   content              text comment '����',
   first_picture        varchar(255) comment '��ͼ',
   flag                 varchar(255) comment 'ԭ����ת�أ�����',
   original_link        varchar(255) comment 'ԭ������',
   view_count           int default 0 comment '�鿴����',
   comment_count        int default 0 comment '��������',
   like_count           int default 0 comment '��������',
   down_count           int default 0 comment '��������',
   is_appreciated       boolean comment '�Ƿ�������',
   is_shared            boolean comment '�Ƿ�������',
   is_commented         boolean comment '�Ƿ�������',
   is_deleted           boolean comment '�Ƿ�ɾ��',
   is_recommend         boolean comment '�Ƿ��Ƽ�',
   is_top               boolean comment '�Ƿ��ö�',
   is_privated          boolean comment '�Ƿ���Լ��ɼ�',
   is_published         boolean comment '1������0�ݸ�',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime not null comment '����ʱ��',
   type_id              bigint not null comment '����id',
   user_id              bigint not null comment '����id',
   reserve1             varchar(255) comment 'Ԥ���ֶ�1',
   reserve2             varchar(255) comment 'Ԥ���ֶ�2',
   reserve3             int comment 'Ԥ���ֶ�3',
   reserve4             int comment 'Ԥ���ֶ�4',
   description          varchar(255) comment '��������',
   primary key (id)
);

alter table blog comment '����';

/*==============================================================*/
/* Table: blog_tag                                              */
/*==============================================================*/
create table blog_tag
(
   id                   bigint(0) not null auto_increment,
   blog_id              bigint not null comment '����id',
   tag_id               bigint not null comment '��ǩid',
   primary key (id)
);

alter table blog_tag comment '���ͺͱ�ǩ';

/*==============================================================*/
/* Table: comment                                               */
/*==============================================================*/
create table comment
(
   id                   bigint not null auto_increment,
   nickname             varchar(255) comment '�ǳ�',
   email                varchar(255) comment '����',
   avatar               varchar(255) comment 'ͷ��',
   content              text comment '��������',
   create_time          datetime not null comment '����ʱ��',
   blog_id              bigint not null comment '����id',
   parent_id            bigint comment '������id',
   is_manager           boolean comment '�Ƿ�������',
   reply_count          int default 0 comment '�ظ�����',
   like_count           int default 0 comment '��������',
   down_count           int default 0 comment '��������',
   reserve1             varchar(255) comment 'Ԥ���ֶ�1',
   reserve2             varchar(255) comment 'Ԥ���ֶ�2',
   reserve3             int comment 'Ԥ���ֶ�3',
   reserve4             int comment 'Ԥ���ֶ�4',
   primary key (id)
);

alter table comment comment '����';

/*==============================================================*/
/* Table: friendslink                                           */
/*==============================================================*/
create table friendslink
(
   id                   int not null auto_increment,
   website_url          varchar(255) comment '��վ��ַ',
   website_name         varchar(255) comment '��վ����',
   website_description  varchar(255) comment '��վ���',
   picture_url          varchar(255) comment 'չʾͼƬ',
   is_show              boolean comment '�Ƿ�չʾ',
   priority             int comment '���ȼ���',
   groups               varchar(255) comment '����',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '����ʱ��',
   view_count           bigint comment '�������',
   reserve1             int comment 'Ԥ���ֶ�1',
   reserve2             int comment 'Ԥ���ֶ�2',
   reserve3             varchar(255) comment 'Ԥ���ֶ�3',
   reserve4             varchar(255) comment 'Ԥ���ֶ�4',
   primary key (id)
);

alter table friendslink comment '������';

/*==============================================================*/
/* Table: message                                               */
/*==============================================================*/
create table message
(
   id                   bigint not null auto_increment,
   nickname             varchar(255) comment '�ǳ�',
   email                varchar(255) comment '����',
   avatar               varchar(255) comment 'ͷ��',
   content              text comment '��������',
   create_time          datetime not null comment '����ʱ��',
   parent_id            bigint comment '������id',
   is_manager           boolean comment '�Ƿ�������',
   reply_count          int default 0 comment '�ظ�����',
   reserve1             varchar(255) comment 'Ԥ���ֶ�1',
   reserve2             varchar(255) comment 'Ԥ���ֶ�2',
   reserve3             int comment 'Ԥ���ֶ�3',
   reserve4             int comment 'Ԥ���ֶ�4',
   primary key (id)
);

alter table message comment '����';

/*==============================================================*/
/* Table: oneword                                               */
/*==============================================================*/
create table oneword
(
   id                   bigint not null auto_increment,
   picture              varchar(255) comment 'ͼƬ',
   content              varchar(255) comment '����',
   is_deleted           boolean comment '�Ƿ�ɾ��',
   is_published         boolean comment '�Ƿ񷢲�',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime not null comment '����ʱ��',
   reserve1             varchar(255) comment 'Ԥ���ֶ�1',
   reserve2             varchar(255) comment 'Ԥ���ֶ�2',
   reserve3             int comment 'Ԥ���ֶ�3',
   reserve4             int comment 'Ԥ���ֶ�4',
   primary key (id)
);

alter table oneword comment 'ÿ��һ�仰';

/*==============================================================*/
/* Table: siteinfo                                              */
/*==============================================================*/
create table siteinfo
(
   view_count           bigint comment '�ܷ��ʴ���',
   lastlogin_time       datetime comment '���һ�ε�½ʱ��',
   reserve1             varchar(255) comment 'Ԥ���ֶ�1',
   reserve2             varchar(255) comment 'Ԥ���ֶ�2',
   reserve3             int comment 'Ԥ���ֶ�3',
   reserve4             int comment 'Ԥ���ֶ�4'
);

alter table siteinfo comment '��վ��Ϣ';

/*==============================================================*/
/* Table: tag                                                   */
/*==============================================================*/
create table tag
(
   id                   bigint not null auto_increment,
   name                 varchar(255) comment '����',
   description          varchar(255) comment '����',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime not null comment '����ʱ��',
   reserve1             varchar(255) comment 'Ԥ���ֶ�1',
   reserve2             varchar(255) comment 'Ԥ���ֶ�2',
   reserve3             int comment 'Ԥ���ֶ�3',
   reserve4             int comment 'Ԥ���ֶ�4',
   primary key (id)
);

alter table tag comment '��ǩ';

/*==============================================================*/
/* Table: tool                                                  */
/*==============================================================*/
create table tool
(
   id                   int not null auto_increment,
   name                 varchar(255) comment '����',
   url                  varchar(255) comment '����',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime not null comment '����ʱ��',
   reserve1             varchar(255) comment 'Ԥ���ֶ�1',
   reserve2             varchar(255) comment 'Ԥ���ֶ�2',
   reserve3             int comment 'Ԥ���ֶ�3',
   reserve4             int comment 'Ԥ���ֶ�4',
   primary key (id)
);

alter table tool comment '�����ϵĹ���';

/*==============================================================*/
/* Table: type                                                  */
/*==============================================================*/
create table type
(
   id                   bigint not null auto_increment,
   name                 varchar(255) comment '����',
   description          varchar(255) comment '����',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime not null comment '����ʱ��',
   reserve1             varchar(255) comment 'Ԥ���ֶ�1',
   reserve2             varchar(255) comment 'Ԥ���ֶ�2',
   reserve3             int comment 'Ԥ���ֶ�3',
   reserve4             int comment 'Ԥ���ֶ�4',
   primary key (id)
);

alter table type comment '����';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   bigint not null auto_increment,
   nickname             varchar(255) comment '�ǳ�',
   username             varchar(255) comment '�û���',
   password             varchar(255) comment '����',
   email                varchar(255) comment '����',
   phone_number         varchar(20) comment '�绰����',
   avatar               varchar(255) comment 'ͷ��',
   is_manager           boolean comment '�Ƿ��ǹ���Ա',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime not null comment '����ʱ��',
   token                varchar(255) comment 'cookie���',
   reserve1             varchar(255) comment 'Ԥ���ֶ�1',
   reserve2             varchar(255) comment 'Ԥ���ֶ�2',
   reserve3             int comment 'Ԥ���ֶ�3',
   reserve4             int comment 'Ԥ���ֶ�4',
   primary key (id)
);

alter table user comment '�û�';

alter table blog add constraint FK_blog_fk_type_id foreign key (type_id)
      references type (id) on delete restrict on update restrict;

alter table blog add constraint FK_blog_fk_user_id foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table blog_tag add constraint FK_blog_tag_fk_blog_id foreign key (blog_id)
      references blog (id) on delete restrict on update restrict;

alter table blog_tag add constraint FK_blog_tag_fk_tag_id foreign key (tag_id)
      references tag (id) on delete restrict on update restrict;

alter table comment add constraint FK_comment_fk_blog_id foreign key (blog_id)
      references blog (id) on delete restrict on update restrict;

