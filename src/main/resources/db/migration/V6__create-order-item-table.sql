CREATE TABLE public.tb_order_item (
	id uuid NOT NULL,
	quantity int4 NOT NULL,
	price_at_purchase numeric(38, 2) NULL,
	product_id bigserial NOT NULL,
	order_id serial4 NOT NULL,
	CONSTRAINT tb_order_item_pkey PRIMARY KEY (id),
	CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES public.tb_order(id),
	CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES public.tb_products(id)
);