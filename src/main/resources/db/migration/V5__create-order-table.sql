CREATE TABLE public.tb_order (
    id uuid DEFAULT gen_random_uuid(),
	identifier varchar(255) NULL,
	order_date_time timestamp(6) NULL,
	order_status varchar(255) NULL,
	total_price numeric(38, 2) NULL,
	updated_at timestamp(6) NULL,
	customer_id uuid NULL,
	payment_fk_id uuid NULL,
	CONSTRAINT tb_order_order_status_check CHECK (((order_status)::text = ANY ((ARRAY['RECEIVED'::character varying, 'PREPARING'::character varying, 'READY'::character varying, 'COMPLETED'::character varying, 'CANCELLED'::character varying])::text[]))),
	CONSTRAINT tb_order_pkey PRIMARY KEY (id),
	CONSTRAINT fkdqstnermwywp3x7im630tsksh FOREIGN KEY (payment_fk_id) REFERENCES public.tb_payment(id),
	CONSTRAINT fkqcp43jdylvf2riad5s1x1i2dn FOREIGN KEY (customer_id) REFERENCES public.tb_customer(id)
);