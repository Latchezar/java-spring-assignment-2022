create table word_relations
(
    id bigint primary key auto_increment ,
    word_one text not null ,
    word_two text not null ,
    relation varchar(100) not null
);