
    create table Condicion (
        clase_condicion varchar(31) not null,
        id bigint not null auto_increment,
        cantidadAnios integer,
        comparador varchar(50),
        nombre varchar(50),
        nombreIndicador varchar(50),
        tipoTaxatividad varchar(50),
        valorDeReferencia decimal(19,2),
        peso integer,
        meto_id bigint not null,
        primary key (id)
    )

    create table Cuenta (
        id bigint not null auto_increment,
        nombre varchar(50) not null,
        primary key (id)
    )

    create table CuentaPeriodo (
        id bigint not null auto_increment,
        valor decimal(19,2) not null,
        cta_id bigint not null,
        peri_id bigint not null,
        primary key (id)
    )

    create table Empresa (
        id bigint not null auto_increment,
        nombre varchar(50) not null,
        symbol varchar(10) not null,
        primary key (id)
    )

    create table Indicador (
        id bigint not null auto_increment,
        formula varchar(255) not null,
        nombre varchar(50) not null,
        primary key (id)
    )

    create table Metodologia (
        id bigint not null auto_increment,
        nombre varchar(50) not null,
        primary key (id)
    )

    create table Periodo (
        id bigint not null auto_increment,
        anio integer not null,
        empr_id bigint not null,
        primary key (id)
    )

    alter table Cuenta 
        add constraint UK_99rqen5hdevgtb8u1876amlnm  unique (nombre)

    alter table Empresa 
        add constraint UK_23bgfpowtja5np3y209g0yng1  unique (symbol)

    alter table Indicador 
        add constraint UK_9hccvtvr87pangjn6epwd3a3b  unique (nombre)

    alter table Metodologia 
        add constraint UK_963acgbjnl67ty9oeo7klkyby  unique (nombre)

    alter table Condicion 
        add constraint FK_3d7daqivg8fl1kv56225qk7kw 
        foreign key (meto_id) 
        references Metodologia (id)

    alter table CuentaPeriodo 
        add constraint FK_7kdphsp2wfbykk0swjg5qjjcj 
        foreign key (cta_id) 
        references Cuenta (id)

    alter table CuentaPeriodo 
        add constraint FK_b334jrk71d12oo4vg86f0mjhf 
        foreign key (peri_id) 
        references Periodo (id)

    alter table Periodo 
        add constraint FK_3trwnrl099c9k4461wesd5ad2 
        foreign key (empr_id) 
        references Empresa (id)
