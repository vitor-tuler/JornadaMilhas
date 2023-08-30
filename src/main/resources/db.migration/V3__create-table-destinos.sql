create table destinos(
    id          bigint not null auto_increment,
    nome        varchar(255) not null,
    preco       float not null,
    imagePath   varchar(255),
    ativo       tinyint not null,

    primary key(id)
);