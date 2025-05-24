CREATE TABLE tb_orders(
    id IDENTITY PRIMARY KEY NOT NULL,
    identifier varchar(7) NOT NULL,
    customer_id UUID NOT NULL,
    total_price NUMERIC(10,2) NOT NULL,
    payment_status varchar(255) NOT NULL,
    orders_status varchar(255) NOT NULL,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES tb_customer(id)
);