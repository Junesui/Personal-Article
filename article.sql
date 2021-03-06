/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/8/29 17:17:09                           */
/*==============================================================*/


drop table if exists article;

drop table if exists article_tag;

drop table if exists comment;

drop table if exists feedback;

drop table if exists friendslink;

drop table if exists message;

drop table if exists oneword;

drop table if exists permission;

drop table if exists role;

drop table if exists role_permission;

drop table if exists siteinfo;

drop table if exists tag;

drop table if exists tool;

drop table if exists type;

drop table if exists user;

drop table if exists user_role;

/*==============================================================*/
/* Table: article                                               */
/*==============================================================*/
create table article
(
   id                   bigint not null auto_increment,
   title                varchar(100) comment '����',
   content              text comment '����',
   description          varchar(255) comment '��������',
   first_picture        varchar(255) comment '��ͼ',
   flag                 varchar(10) comment 'ԭ����ת�أ�����',
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
   primary key (id)
);

alter table article comment '����';

/*==============================================================*/
/* Table: article_tag                                           */
/*==============================================================*/
create table article_tag
(
   id                   bigint(0) not null auto_increment,
   article_id           bigint not null comment '����id',
   tag_id               bigint not null comment '��ǩid',
   is_deleted           boolean comment '�Ƿ�ɾ��',
   primary key (id)
);

alter table article_tag comment '���ºͱ�ǩ';

/*==============================================================*/
/* Table: comment                                               */
/*==============================================================*/
create table comment
(
   id                   bigint not null auto_increment,
   nickname             varchar(30) comment '�ǳ�',
   email                varchar(30) comment '����',
   avatar               varchar(255) comment 'ͷ��',
   content              text comment '��������',
   create_time          datetime not null comment '����ʱ��',
   is_deleted           boolean comment '�Ƿ�ɾ��',
   article_id           bigint not null comment '����id',
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
/* Table: feedback                                              */
/*==============================================================*/
create table feedback
(
   id                   bigint not null auto_increment,
   url                  varchar(255) comment '��������',
   content              text comment '����',
   email                varchar(30) comment '����',
   create_time          datetime not null comment '����ʱ��',
   is_fixed             boolean comment '�Ƿ��޸�',
   is_deleted           boolean comment '�Ƿ�ɾ��',
   reserve1             varchar(255) comment 'Ԥ���ֶ�1',
   reserve2             varchar(255) comment 'Ԥ���ֶ�2',
   reserve3             int comment 'Ԥ���ֶ�3',
   reserve4             int comment 'Ԥ���ֶ�4',
   primary key (id)
);

alter table feedback comment '��������';

/*==============================================================*/
/* Table: friendslink                                           */
/*==============================================================*/
create table friendslink
(
   id                   int not null auto_increment,
   website_url          varchar(255) comment '��վ��ַ',
   website_name         varchar(30) comment '��վ����',
   website_description  varchar(100) comment '��վ���',
   picture_url          varchar(255) comment 'չʾͼƬ',
   is_show              boolean comment '�Ƿ�չʾ',
   priority             int comment '���ȼ���',
   groups               varchar(10) comment '����',
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
   nickname             varchar(30) comment '�ǳ�',
   email                varchar(30) comment '����',
   avatar               varchar(255) comment 'ͷ��',
   content              text comment '��������',
   create_time          datetime not null comment '����ʱ��',
   parent_id            bigint comment '������id',
   is_manager           boolean comment '�Ƿ�������',
   reply_count          int default 0 comment '�ظ�����',
   is_deleted           boolean comment '�Ƿ�ɾ��',
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
/* Table: permission                                            */
/*==============================================================*/
create table permission
(
   id                   int not null auto_increment,
   name                 varchar(10) not null comment 'Ȩ������',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime not null comment '����ʱ��',
   description          varchar(255) comment 'Ȩ������',
   reserve1             varchar(255) comment 'Ԥ���ֶ�1',
   reserve2             varchar(255) comment 'Ԥ���ֶ�2',
   reserve3             int comment 'Ԥ���ֶ�3',
   reserve4             int comment 'Ԥ���ֶ�4',
   primary key (id)
);

alter table permission comment 'Ȩ�ޱ�';

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   id                   int not null auto_increment,
   name                 varchar(10) not null comment '��ɫ����',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime not null comment '����ʱ��',
   description          varchar(255) comment '��ɫ����',
   reserve1             varchar(255) comment 'Ԥ���ֶ�1',
   reserve2             varchar(255) comment 'Ԥ���ֶ�2',
   reserve3             int comment 'Ԥ���ֶ�3',
   reserve4             int comment 'Ԥ���ֶ�4',
   primary key (id)
);

alter table role comment '��ɫ��';

/*==============================================================*/
/* Table: role_permission                                       */
/*==============================================================*/
create table role_permission
(
   id                   bigint not null,
   role_id              int not null comment '��ɫid',
   permission_id        int not null comment 'Ȩ��id',
   reserve1             varchar(255) comment 'Ԥ���ֶ�1',
   reserve2             varchar(255) comment 'Ԥ���ֶ�2',
   reserve3             int comment 'Ԥ���ֶ�3',
   reserve4             int comment 'Ԥ���ֶ�4',
   primary key (id)
);

alter table role_permission comment '��ɫȨ�ޱ�';

/*==============================================================*/
/* Table: siteinfo                                              */
/*==============================================================*/
create table siteinfo
(
   view_count           bigint default 0 comment '�ܷ��ʴ���',
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
   name                 varchar(10) comment '����',
   description          varchar(255) comment '����',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime not null comment '����ʱ��',
   is_deleted           boolean comment '�Ƿ�ɾ��',
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
   name                 varchar(30) comment '����',
   url                  varchar(255) comment '����',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime not null comment '����ʱ��',
   is_deleted           boolean comment '�Ƿ�ɾ��',
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
   name                 varchar(10) comment '����',
   description          varchar(255) comment '����',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime not null comment '����ʱ��',
   is_deleted           boolean comment '�Ƿ�ɾ��',
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
   id                   bigint not null,
   nickname             varchar(30) comment '�ǳ�',
   username             varchar(30) comment '�û���',
   password             varchar(255) comment '����',
   email                varchar(30) comment '����',
   phone_number         varchar(20) comment '�绰����',
   address              varchar(50) comment '�û���ַ',
   avatar               varchar(255) comment 'ͷ��',
   level                int comment '�û��ȼ�',
   is_manager           boolean comment '�Ƿ��ǹ���Ա',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime not null comment '����ʱ��',
   last_login_time      datetime not null comment '���һ�ε�¼ʱ��',
   token                varchar(255) comment 'cookie���',
   reserve1             varchar(255) comment 'Ԥ���ֶ�1',
   reserve2             varchar(255) comment 'Ԥ���ֶ�2',
   reserve3             int comment 'Ԥ���ֶ�3',
   reserve4             int comment 'Ԥ���ֶ�4',
   primary key (id)
);

alter table user comment '�û�';

/*==============================================================*/
/* Table: user_role                                             */
/*==============================================================*/
create table user_role
(
   id                   bigint not null,
   user_id              bigint not null comment '�û�id',
   role_id              int not null comment '��ɫid',
   reserve1             varchar(255) comment 'Ԥ���ֶ�1',
   reserve2             varchar(255) comment 'Ԥ���ֶ�2',
   reserve3             int comment 'Ԥ���ֶ�3',
   reserve4             int comment 'Ԥ���ֶ�4',
   primary key (id)
);

alter table user_role comment '�û���ɫ��';

alter table article add constraint FK_article_fk_type_id foreign key (type_id)
      references type (id) on delete restrict on update restrict;

alter table article add constraint FK_article_fk_user_id foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table article_tag add constraint FK_article_tag_fk_article_id foreign key (article_id)
      references article (id) on delete restrict on update restrict;

alter table article_tag add constraint FK_article_tag_fk_tag_id foreign key (tag_id)
      references tag (id) on delete restrict on update restrict;

alter table comment add constraint FK_comment_fk_article_id foreign key (article_id)
      references article (id) on delete restrict on update restrict;

alter table role_permission add constraint FK_role_permission_fk_permission_id foreign key (permission_id)
      references permission (id) on delete restrict on update restrict;

alter table role_permission add constraint FK_role_permission_fk_role_id foreign key (role_id)
      references role (id) on delete restrict on update restrict;

alter table user_role add constraint FK_user_role_fk_role_id foreign key (role_id)
      references role (id) on delete restrict on update restrict;

alter table user_role add constraint FK_user_role_fk_user_id foreign key (user_id)
      references user (id) on delete restrict on update restrict;

