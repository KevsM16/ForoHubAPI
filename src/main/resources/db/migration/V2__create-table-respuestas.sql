create table respuestas(
id bigint not null auto_increment,
topico_id bigint not null,
fecha_de_creacion datetime not null,
solucion varchar(200) not null,
primary key(id),
foreign key (topico_id) references topicos(id) on delete cascade on update cascade

)