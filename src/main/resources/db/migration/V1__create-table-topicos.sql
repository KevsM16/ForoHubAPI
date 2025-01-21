create table topicos (
id bigint not null auto_increment,
usuario_id bigint not null,
titulo varchar(100) not null,
mensaje varchar(150) not null,
fecha_de_creacion datetime not null,
autor varchar(100) not null,
curso varchar(100) not null,
primary key(id)
);