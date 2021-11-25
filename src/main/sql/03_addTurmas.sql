create table turma (
  id bigint(20) primary key,
  `name` varchar(255) not null unique
);

alter table aluno add turma_id bigint(20); 
alter table aluno add foreign key (turma_id) references turma(id);

create table turma_materia (
  id bigint(20) primary key,
  turma_id bigint(20) not null,
  materia_id bigint(20) not null,

  foreign key (turma_id) references turma(id),
  foreign key (materia_id) references materia(id)
);