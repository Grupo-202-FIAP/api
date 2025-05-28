CREATE TABLE tb_order (
    id SERIAL PRIMARY KEY,
    identifier VARCHAR(40),
    total_price NUMERIC(10, 2) NOT NULL,
    order_status VARCHAR(255) NOT NULL,
    order_date_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    customer_id UUID NOT NULL,
    payment_fk_id UUID,

    CONSTRAINT fk_payment FOREIGN KEY (payment_fk_id) REFERENCES tb_payment(id) ON DELETE SET NULL,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES tb_customer(id)
);