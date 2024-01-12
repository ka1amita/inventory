create table items
(
    id              bigint       not null auto_increment primary key,
    creation_date   date         null,
    description     varchar(255) null,
    enabled         bit          not null,
    expiration_date date         null,
    title           varchar(255) null,
    toxic           bit          not null,
    borrower_id     bigint       null,
    constraint `foreign_key_borrower` foreign key (borrower_id) references borrowers (id)
);

create table items_seq
(
    next_val bigint null
);

insert into items_seq
values (1);
