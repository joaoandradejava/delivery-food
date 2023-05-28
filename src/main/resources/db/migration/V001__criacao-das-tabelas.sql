    create table categoria (
        id bigint not null,
        nome varchar(255) not null,
        primary key (id)
    );
    
    create table endereco (
        id bigint not null,
        bairro varchar(255) not null,
        cep varchar(255) not null,
        cidade varchar(255) not null,
        complemento varchar(255),
        estado varchar(255) not null,
        numero varchar(255) not null,
        rua varchar(255) not null,
        usuario_id bigint not null,
        primary key (id)
    );
    
    create table endereco_de_entrega (
        id bigint not null,
        complemento varchar(255) not null,
        endereco varchar(255) not null,
        primary key (id)
    );
    
    create table item_pedido (
        id bigint not null,
        preco_unitario numeric(38,2) not null,
        quantidade bigint not null,
        pedido_id bigint not null,
        produto_id varchar(255) not null,
        primary key (id)
    );
    
    create table pedido (
        id bigint not null,
        data_cancelamento timestamp(6),
        data_concluido timestamp(6),
        data_pedido timestamp(6),
        observacao varchar(255),
        status varchar(255) not null check (status in ('AGUARDANDO_PAGAMENTO','CANCELADO','CONCLUIDO','SAIU_PARA_ENTREGA','PREPARANDO_O_PEDIDO')),
        subtotal numeric(38,2) not null,
        taxa_entrega numeric(38,2) not null,
        total numeric(38,2) not null,
        cliente_id bigint not null,
        endereco_de_entrega_id bigint not null,
        primary key (id)
    );
    
    create table perfil (
        usuario_id bigint not null,
        perfils varchar(255) check (perfils in ('COMUM','ADMINISTRADOR'))
    );
    
    create table produto (
        id varchar(255) not null,
        data_atualizacao timestamp(6),
        data_cadastro timestamp(6),
        foto_url TEXT,
        is_disponivel boolean,
        nome varchar(255) not null,
        preco numeric(38,2) not null,
        primary key (id)
    );
    
    create table produto_categorias (
        produto_id varchar(255) not null,
        categoria_id bigint not null,
        primary key (produto_id, categoria_id)
    );
    
    create table usuario (
        id bigint not null,
        data_atualizacao timestamp(6),
        data_cadastro timestamp(6),
        email varchar(255) not null,
        foto_perfil TEXT,
        nome varchar(255) not null,
        senha varchar(255) not null,
        telefone_celular varchar(255) not null,
        primary key (id)
    );

    alter table if exists pedido 
       drop constraint if exists unique_endereco_de_entrega_id;

    alter table if exists pedido 
       add constraint unique_endereco_de_entrega_id unique (endereco_de_entrega_id);

    alter table if exists usuario 
       drop constraint if exists unique_email;

    alter table if exists usuario 
       add constraint unique_email unique (email);
       
    create sequence seq_categoria start with 1 increment by 1;
    create sequence seq_endereco start with 1 increment by 1;
    create sequence seq_endereco_de_entrega start with 1 increment by 1;
    create sequence seq_item_pedido start with 1 increment by 1;
    create sequence seq_pedido start with 1 increment by 1;
    create sequence seq_usuario start with 1 increment by 1;

    alter table if exists endereco 
       add constraint fk_usuario 
       foreign key (usuario_id) 
       references usuario;

    alter table if exists item_pedido 
       add constraint fk_pedido 
       foreign key (pedido_id) 
       references pedido;

    alter table if exists item_pedido 
       add constraint fk_produto 
       foreign key (produto_id) 
       references produto;

    alter table if exists pedido 
       add constraint fk_cliente 
       foreign key (cliente_id) 
       references usuario;

    alter table if exists pedido 
       add constraint fk_endereco_de_entrega 
       foreign key (endereco_de_entrega_id) 
       references endereco_de_entrega;

    alter table if exists perfil 
       add constraint fk_usuario 
       foreign key (usuario_id) 
       references usuario;

    alter table if exists produto_categorias 
       add constraint fk_categoria 
       foreign key (categoria_id) 
       references categoria;

    alter table if exists produto_categorias 
       add constraint fk_produto 
       foreign key (produto_id) 
       references produto;