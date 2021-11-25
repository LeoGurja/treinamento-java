create table materia (
  id bigint(20) primary key,
  `name` varchar(255) not null unique,
  `description` varchar(255) not null,
  professor_id bigint(20) not null,

  foreign key (professor_id) references professor(id)
);