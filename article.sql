/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/7/1 21:19:29                            */
/*==============================================================*/


drop table if exists article;

drop table if exists article_tag;

drop table if exists comment;

drop table if exists feedback;

drop table if exists friendslink;

drop table if exists message;

drop table if exists oneword;

drop table if exists siteinfo;

drop table if exists tag;

drop table if exists tool;

drop table if exists type;

drop table if exists user;

/*==============================================================*/
/* Table: article                                               */
/*==============================================================*/
create table article
(
   id                   bigint not null auto_increment,
   title                varchar(100) comment '标题',
   content              text comment '内容',
   description          varchar(255) comment '文章描述',
   first_picture        varchar(255) comment '首图',
   flag                 varchar(10) comment '原创，转载，翻译',
   original_link        varchar(255) comment '原文链接',
   view_count           int default 0 comment '查看次数',
   comment_count        int default 0 comment '评论数量',
   like_count           int default 0 comment '点赞数量',
   down_count           int default 0 comment '反对数量',
   is_appreciated       boolean comment '是否开启赞赏',
   is_shared            boolean comment '是否开启分享',
   is_commented         boolean comment '是否开启评论',
   is_deleted           boolean comment '是否删除',
   is_recommend         boolean comment '是否推荐',
   is_top               boolean comment '是否置顶',
   is_privated          boolean comment '是否仅自己可见',
   is_published         boolean comment '1发布，0草稿',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   type_id              bigint not null comment '类型id',
   user_id              bigint not null comment '作者id',
   reserve1             varchar(255) comment '预留字段1',
   reserve2             varchar(255) comment '预留字段2',
   reserve3             int comment '预留字段3',
   reserve4             int comment '预留字段4',
   primary key (id)
);

alter table article comment '文章';

/*==============================================================*/
/* Table: article_tag                                           */
/*==============================================================*/
create table article_tag
(
   id                   bigint(0) not null auto_increment,
   article_id           bigint not null comment '文章id',
   tag_id               bigint not null comment '标签id',
   is_deleted           boolean comment '是否删除',
   primary key (id)
);

alter table article_tag comment '文章和标签';

/*==============================================================*/
/* Table: comment                                               */
/*==============================================================*/
create table comment
(
   id                   bigint not null auto_increment,
   nickname             varchar(30) comment '昵称',
   email                varchar(30) comment '邮箱',
   avatar               varchar(255) comment '头像',
   content              text comment '评论内容',
   create_time          datetime not null comment '创建时间',
   is_deleted           boolean comment '是否删除',
   article_id           bigint not null comment '文章id',
   parent_id            bigint comment '父评论id',
   is_manager           boolean comment '是否是作者',
   reply_count          int default 0 comment '回复数量',
   like_count           int default 0 comment '点赞数量',
   down_count           int default 0 comment '反对数量',
   reserve1             varchar(255) comment '预留字段1',
   reserve2             varchar(255) comment '预留字段2',
   reserve3             int comment '预留字段3',
   reserve4             int comment '预留字段4',
   primary key (id)
);

alter table comment comment '评论';

/*==============================================================*/
/* Table: feedback                                              */
/*==============================================================*/
create table feedback
(
   id                   bigint not null auto_increment,
   url                  varchar(255) comment '反馈链接',
   content              text comment '内容',
   email                varchar(30) comment '邮箱',
   create_time          datetime not null comment '创建时间',
   is_fixed             boolean comment '是否修复',
   is_deleted           boolean comment '是否删除',
   reserve1             varchar(255) comment '预留字段1',
   reserve2             varchar(255) comment '预留字段2',
   reserve3             int comment '预留字段3',
   reserve4             int comment '预留字段4',
   primary key (id)
);

alter table feedback comment '反馈建议';

/*==============================================================*/
/* Table: friendslink                                           */
/*==============================================================*/
create table friendslink
(
   id                   int not null auto_increment,
   website_url          varchar(255) comment '网站地址',
   website_name         varchar(30) comment '网站名称',
   website_description  varchar(100) comment '网站简介',
   picture_url          varchar(255) comment '展示图片',
   is_show              boolean comment '是否展示',
   priority             int comment '优先级别',
   groups               varchar(10) comment '分组',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   view_count           bigint comment '浏览次数',
   reserve1             int comment '预留字段1',
   reserve2             int comment '预留字段2',
   reserve3             varchar(255) comment '预留字段3',
   reserve4             varchar(255) comment '预留字段4',
   primary key (id)
);

alter table friendslink comment '友人链';

/*==============================================================*/
/* Table: message                                               */
/*==============================================================*/
create table message
(
   id                   bigint not null auto_increment,
   nickname             varchar(30) comment '昵称',
   email                varchar(30) comment '邮箱',
   avatar               varchar(255) comment '头像',
   content              text comment '评论内容',
   create_time          datetime not null comment '创建时间',
   parent_id            bigint comment '父评论id',
   is_manager           boolean comment '是否是作者',
   reply_count          int default 0 comment '回复数量',
   is_deleted           boolean comment '是否删除',
   reserve1             varchar(255) comment '预留字段1',
   reserve2             varchar(255) comment '预留字段2',
   reserve3             int comment '预留字段3',
   reserve4             int comment '预留字段4',
   primary key (id)
);

alter table message comment '留言';

/*==============================================================*/
/* Table: oneword                                               */
/*==============================================================*/
create table oneword
(
   id                   bigint not null auto_increment,
   picture              varchar(255) comment '图片',
   content              varchar(255) comment '内容',
   is_deleted           boolean comment '是否删除',
   is_published         boolean comment '是否发布',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   reserve1             varchar(255) comment '预留字段1',
   reserve2             varchar(255) comment '预留字段2',
   reserve3             int comment '预留字段3',
   reserve4             int comment '预留字段4',
   primary key (id)
);

alter table oneword comment '每日一句话';

/*==============================================================*/
/* Table: siteinfo                                              */
/*==============================================================*/
create table siteinfo
(
   view_count           bigint default 0 comment '总访问次数',
   reserve1             varchar(255) comment '预留字段1',
   reserve2             varchar(255) comment '预留字段2',
   reserve3             int comment '预留字段3',
   reserve4             int comment '预留字段4'
);

alter table siteinfo comment '网站信息';

/*==============================================================*/
/* Table: tag                                                   */
/*==============================================================*/
create table tag
(
   id                   bigint not null auto_increment,
   name                 varchar(10) comment '名字',
   description          varchar(255) comment '描述',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   is_deleted           boolean comment '是否删除',
   reserve1             varchar(255) comment '预留字段1',
   reserve2             varchar(255) comment '预留字段2',
   reserve3             int comment '预留字段3',
   reserve4             int comment '预留字段4',
   primary key (id)
);

alter table tag comment '标签';

/*==============================================================*/
/* Table: tool                                                  */
/*==============================================================*/
create table tool
(
   id                   int not null auto_increment,
   name                 varchar(30) comment '名字',
   url                  varchar(255) comment '链接',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   is_deleted           boolean comment '是否删除',
   reserve1             varchar(255) comment '预留字段1',
   reserve2             varchar(255) comment '预留字段2',
   reserve3             int comment '预留字段3',
   reserve4             int comment '预留字段4',
   primary key (id)
);

alter table tool comment '导航上的工具';

/*==============================================================*/
/* Table: type                                                  */
/*==============================================================*/
create table type
(
   id                   bigint not null auto_increment,
   name                 varchar(10) comment '名字',
   description          varchar(255) comment '描述',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   is_deleted           boolean comment '是否删除',
   reserve1             varchar(255) comment '预留字段1',
   reserve2             varchar(255) comment '预留字段2',
   reserve3             int comment '预留字段3',
   reserve4             int comment '预留字段4',
   primary key (id)
);

alter table type comment '分类';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   bigint not null auto_increment,
   nickname             varchar(30) comment '昵称',
   username             varchar(30) comment '用户名',
   password             varchar(255) comment '密码',
   email                varchar(30) comment '邮箱',
   phone_number         varchar(20) comment '电话号码',
   address              varchar(50) comment '用户地址',
   avatar               varchar(255) comment '头像',
   level                int comment '用户等级',
   is_manager           boolean comment '是否是管理员',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   last_login_time      datetime not null comment '最后一次登录时间',
   token                varchar(255) comment 'cookie标记',
   reserve1             varchar(255) comment '预留字段1',
   reserve2             varchar(255) comment '预留字段2',
   reserve3             int comment '预留字段3',
   reserve4             int comment '预留字段4',
   primary key (id)
);

alter table user comment '用户';

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

