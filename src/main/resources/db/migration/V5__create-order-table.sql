CREATE TYPE order_status AS ENUM ('RECEIVED', 'PREPARING', 'READY', 'COMPLETED', 'CANCELLED');

CREATE TABLE tb_order (
    id SERIAL PRIMARY KEY,
    identifier varchar(7) NOT NULL,
    total_price NUMERIC(10, 2) NOT NULL,
    status order_status NOT NULL,
    order_date_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,

    customer_id UUID NOT NULL,
    payment_fk_id UUID,

    CONSTRAINT fk_payment FOREIGN KEY (payment_fk_id) REFERENCES tb_payment(id) ON DELETE SET NULL,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES tb_customer(id)
);