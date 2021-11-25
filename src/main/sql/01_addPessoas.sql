create table aluno (
  id bigint(20) primary key,
  cpf varchar(11) not null unique,
  email varchar(255) not null unique,
  rg varchar(9) not null unique,
  'name' varchar(255) not null,
  'address' varchar(255) not null,
  password_digest varchar(255),
  phone_number varchar(11),
);

create table professor (
  id bigint(20) primary key,
  cpf varchar(11) not null unique,
  email varchar(255) not null unique,
  rg varchar(9) not null unique,
  'name' varchar(255) not null,
  'address' varchar(255) not null,
  password_digest varchar(255),
  phone_number varchar(11),
);