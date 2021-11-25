create table nota (
  id bigint(20) primary key,
  grade int not null,
  aluno_id bigint(20) not null,
  materia_id bigint(20) not null,

  foreign key (aluno_id) references aluno(id),
  foreign key (materia_id) references materia(id),
);