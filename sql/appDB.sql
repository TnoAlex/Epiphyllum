create table usd_comment
(
    id      varchar(128) not null
        primary key,
    comment blob         not null
);

create table usd_group
(
    id                varchar(128)  not null
        primary key,
    group_name        varchar(255)  null,
    group_description varchar(1024) null,
    group_ico         blob          null,
    create_time       datetime      null,
    create_user       varchar(128)  null
);

create table usd_group_post
(
    id       varchar(128) not null
        primary key,
    group_id varchar(128) null,
    post_id  varchar(128) null
);

create table usd_group_user
(
    id       varchar(128) not null
        primary key,
    group_id varchar(128) not null,
    uid      varchar(128) not null
);

create table usd_notice
(
    id          varchar(128) not null
        primary key,
    context     blob         not null,
    create_time datetime     not null,
    create_by   varchar(128) not null comment '创建者'
);

create table usd_notice_user
(
    id        varchar(128) not null
        primary key,
    uid       varchar(128) not null,
    notice_id varchar(128) not null,
    status    int          not null comment '0,1已读未读'
);

create table usd_post
(
    id           varchar(128) not null comment '帖子id'
        primary key,
    post_content blob         null comment '帖子内容(JSON)',
    create_time  datetime     null,
    status       int          not null comment '1：正常,0：隐藏',
    likes        int          null comment '点赞数',
    favorites    int          null comment '收藏数'
);

create table usd_race
(
    ` id`            varchar(128)  not null comment '主键'
        primary key,
    race_start_time  datetime      not null comment '竞赛开始时间',
    race_end_time    datetime      not null comment '竞赛结束时间',
    race_description varchar(1024) null comment '竞赛描述',
    race_addition    blob          not null comment '竞赛附加信息(JSON 包含图片)',
    status           int           not null comment '-1：未开始 0：进行中 1：已结束 ',
    organizer        varchar(128)  not null comment '主办方',
    race_name        varchar(255)  not null comment '竞赛名称'
);

create table usd_race_annex
(
    id      varchar(128) not null
        primary key,
    uid     varchar(128) not null,
    race_id varchar(128) not null,
    annex   varchar(128) not null
);

create table usd_user
(
    uid        varchar(128) not null comment '用户id',
    signature  varchar(128) null comment '用户签名',
    occupation varchar(64)  null comment '用户职业',
    portrait   blob         null comment '用户头像',
    id         varchar(128) not null comment '主键'
        primary key,
    nick_name  varchar(128) not null
);

create table usd_user_awards
(
    id      varchar(128) not null
        primary key,
    uid     varchar(128) not null,
    race_id varchar(128) not null,
    award   varchar(255) null
);

create table usd_user_favorites
(
    id      varchar(128) not null
        primary key,
    post_id varchar(128) null,
    uid     varchar(128) null
);

create table usd_user_post
(
    id      varchar(128) not null
        primary key,
    uid     varchar(128) null,
    post_id varchar(128) null
);

create table usd_user_post_comment
(
    id      varchar(128) not null
        primary key,
    post_id varchar(128) not null,
    uid     varchar(128) not null
);

create table usd_user_race
(
    id      varchar(128) not null
        primary key,
    uid     varchar(128) not null,
    race_id varchar(128) not null
);

create definer = root@localhost event raceTimmer on schedule
    every '1' DAY
        starts '2022-09-23 10:20:28'
    enable
    do
    UPDATE usd_race SET `status` = CASE 
	WHEN (race_start_time<NOW() AND race_end_time>NOW()) THEN 0
	WHEN (race_start_time>NOW()) THEN -1
	WHEN (race_end_time<NOW()) THEN 1
END;

