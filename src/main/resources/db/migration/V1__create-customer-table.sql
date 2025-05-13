CREATE TABLE tb_user(
    id UUID PRIMARY KEY NOT NULL,
    name varchar(255),
    email varchar(255) UNIQUE,
    cpf VARCHAR(11) UNIQUE,
    password varchar (255),
    role varchar (100)
)