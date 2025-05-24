CREATE TABLE tb_order_item(
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL,
    product_id serial NOT NULL,
    quantity INT NOT NULL,
    price_at_purchase NUMERIC(10, 2),
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES tb_orders(id),
    CONSTRAINT fk_product FOREIGN KEY (product_id) references tb_products(id)
    );