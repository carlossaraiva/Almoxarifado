---Autor: Diego Neves Isidoro, Carlos Saraiva
---Turma: 3°ADS Período: Tarde
---E que começe a bagaça...
---Primeiro deletando tabelas que tenham o mesmo nome da que vão ser criadas
drop table Fornecedor cascade constraints;
---Agora começo a criar as tabelas 
---Criando a tabela Fornecedor
create table Fornecedor
(
  forCodigo int not null,
  forNome varchar2(25) not null,
  forEndereco varchar2(25)not null,
  forTelefone varchar2(10),
---Criando uma restrição para fornecedor com sua chave primaria
  constraint pkForCodigo primary key (forCodigo)
);
---Criando uma sequencia para chave primaria de Fornecedor
create sequence forCodigo start with 1 increment by 1;
select * from Fornecedor;
delete from Fornecedor;