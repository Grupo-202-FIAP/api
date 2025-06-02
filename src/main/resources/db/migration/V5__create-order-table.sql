CREATE TABLE public.tb_order (
	id serial4 NOT NULL,
	identifier varchar(255) NULL,
	total_price numeric(38, 2) NOT NULL,
	order_status varchar(255) NOT NULL,
	order_date_time timestamp DEFAULT now() NOT NULL,
	updated_at timestamp DEFAULT now() NOT NULL,
	customer_id uuid NOT NULL,
	payment_fk_id uuid NULL,
	CONSTRAINT tb_order_pkey PRIMARY KEY (id),
	CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES public.tb_customer(id),
	CONSTRAINT fk_payment FOREIGN KEY (payment_fk_id) REFERENCES public.tb_payment(id) ON DELETE SET NULL
);