CREATE TABLE public.tb_order_item (
	id uuid NOT NULL,
	price_at_purchase numeric(38, 2) NULL,
	quantity int4 NULL,
	order_id int8 NULL,
	product_id int8 NULL,
	CONSTRAINT tb_order_item_pkey PRIMARY KEY (id),
	CONSTRAINT fk11kgiid478vipvuwfkxs47t86 FOREIGN KEY (product_id) REFERENCES public.tb_products(id),
	CONSTRAINT fkgeobgl2xu916he8vhljktwxnx FOREIGN KEY (order_id) REFERENCES public.tb_order(id)
);