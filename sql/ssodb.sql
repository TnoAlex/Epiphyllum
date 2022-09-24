create table oauth_client_details
(
    id                     bigint       not null comment 'id'
        primary key,
    app_name               varchar(30)  null comment '应用名称',
    app_key                varchar(60)  null comment '应用key',
    app_secret             varchar(60)  null comment '应用密钥',
    resource_ids           varchar(255) null comment '资源id集合',
    scope                  varchar(255) null comment 'oauth权限范围',
    authorized_grant_types varchar(255) null comment '支持的授权类型',
    authorities            varchar(255) null comment 'security权限值',
    access_token_validity  int          null comment 'access_token有效时间',
    refresh_token_validity int          null comment 'refresh_token有效时间',
    additional_information text         null comment '客户端的其他信息，必须为json格式',
    archived               int          null comment '是否已存档',
    trusted                int          null comment '是否是信任的,0:不受信任，1：信任的',
    autoapprove            varchar(20)  null comment '是否自动跳过approve操作，默认为false，取值范围：true,false,read,write',
    create_by              varchar(60)  null comment '创建人',
    create_time            datetime     null comment '创建时间',
    update_by              varchar(60)  null comment '更新人',
    update_time            datetime     null comment '更新时间',
    redirect_uri           varchar(255) null comment '重定向Uri',
    app_public_key         blob         null comment '客户端唯一密钥'
)
    charset = utf8;

create table oauth_role
(
    id          varchar(128) not null
        primary key,
    role_code   varchar(20)  null comment '角色编码',
    role_name   varchar(20)  null comment '角色名称',
    create_by   bigint       null comment '创建人',
    create_time datetime     null on update CURRENT_TIMESTAMP comment '创建时间',
    update_by   bigint       null comment '更新人',
    update_time datetime     null on update CURRENT_TIMESTAMP comment '更新时间',
    can_exudes  tinyint      null comment '是否可被用户泛化',
    role_level  int          null comment '权限等级'
)
    charset = utf8mb4;

create table oauth_user
(
    id          varchar(128) not null
        primary key,
    account     varchar(64)  null comment '登录帐号',
    user_name   varchar(64)  null comment '用户姓名',
    password    varchar(100) null comment '用户密码',
    phone       varchar(11)  null comment '手机号',
    gender      tinyint(1)   null comment '性别，1：男，2：女',
    status      tinyint(1)   null comment '状态，0：正常，1：冻结',
    del_flag    tinyint(1)   null comment '删除标识，0：正常，1：已删除',
    create_by   bigint       null comment '创建人',
    create_time datetime     null on update CURRENT_TIMESTAMP comment '创建时间',
    update_by   bigint       null comment '更新人',
    update_time datetime     null on update CURRENT_TIMESTAMP comment '更新时间',
    addition    varchar(128) null
)
    charset = utf8mb4;

create table oauth_user_client
(
    id        varchar(128) not null
        primary key,
    client_id varchar(128) not null,
    uid       varchar(128) not null
);

create table oauth_user_role
(
    id      varchar(128) not null
        primary key,
    user_id varchar(128) null comment '用户id',
    role_id varchar(128) null comment '角色id'
)
    charset = utf8mb4;

create table oauth_verify
(
    id          varchar(128) not null
        primary key,
    request_id  varchar(128) not null,
    create_time datetime     not null,
    status      tinyint      not null,
    pass_user   varchar(255) null,
    pass_time   datetime     null,
    addition    blob         null
);

