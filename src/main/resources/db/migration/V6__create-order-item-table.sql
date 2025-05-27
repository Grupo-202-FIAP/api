CREATE TABLE tb_order_item(
    id UUID PRIMARY KEY,
    quantity INT NOT NULL,
    price_at_purchase NUMERIC(10, 2),
    product_id SERIAL NOT NULL,
    order_id SERIAL NOT NULL,

    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES tb_order(id),
    CONSTRAINT fk_product FOREIGN KEY (product_id) references tb_products(id)
);