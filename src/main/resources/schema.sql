alter table article drop foreign key const_foreign_key_article_to_user;

alter table comment drop foreign key const_foreign_key_comment_to_article;

alter table comment drop foreign key const_foreign_key_comment_to_user;


drop table if exists user;

drop table if exists article;

drop table if exists comment;

create table user (
    user_id bigint not null auto_increment,
    created_at datetime(6),
    updated_at datetime(6),
    email varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (user_id)
) engine=InnoDB;

create table article (
    article_id bigint not null auto_increment,
    created_at datetime(6),
    updated_at datetime(6),
    title varchar(255),
    content varchar(255),
    user_id bigint,
    primary key (article_id)
) engine=InnoDB;

create table comment (
    comment_id bigint not null auto_increment,
    created_at datetime(6),
    updated_at datetime(6),
    content varchar(255),
    article_id bigint,
    user_id bigint,
    primary key (comment_id)
) engine=InnoDB;

alter table article add constraint const_foreign_key_article_to_user
    foreign key (user_id) references user (user_id);

alter table comment add constraint const_foreign_key_comment_to_article
   foreign key (article_id) references article (article_id);

alter table comment add constraint const_foreign_key_comment_to_user
    foreign key (user_id) references user (user_id);

