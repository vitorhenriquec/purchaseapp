CREATE sequence sq_purchase_idt INCREMENT BY 1
                                START WITH 1
                                MAXVALUE 99999999999
                                MINVALUE 1
                                CACHE 10;

CREATE TABLE purchase (
    id int8,
    description varchar(50) not null,
    created_at date not null,
    amount decimal not null,
    CONSTRAINT purchase_fk PRIMARY KEY(id)
);

comment on column purchase.id is 'Identifier of the purchase';
comment on column purchase.description is 'Description of the purchase';
comment on column purchase.created_at is 'Creation date of the purchase';
comment on column purchase.amount is 'Amount of the purchase';
