CREATE sequence sq_purchase_idt;

CREATE TABLE purchase (
    id number DEFAULT sq_purchase_idt.NEXTVAL,
    description varchar(50) not null,
    created_at date not null,
    amount decimal not null,
    CONSTRAINT purchase_fk PRIMARY KEY(id)
);

comment on column purchase.id is 'Identifier of the purchase';
comment on column purchase.description is 'Description of the purchase';
comment on column purchase.created_at is 'Creation date of the purchase';
comment on column purchase.amount is 'Amount of the purchase';
