CREATE TABLE public.tb_payment (
    id uuid DEFAULT gen_random_uuid(),
	payment_date_time timestamp(6) NULL,
	payment_method varchar(255) NULL,
	status varchar(255) NULL,
	updated_at timestamp(6) NULL,
	CONSTRAINT tb_payment_payment_method_check CHECK (((payment_method)::text = 'QR_CODE'::text)),
	CONSTRAINT tb_payment_pkey PRIMARY KEY (id),
	CONSTRAINT tb_payment_status_check CHECK (((status)::text = ANY ((ARRAY['PENDING'::character varying, 'APPROVED'::character varying, 'AUTHORIZED'::character varying, 'IN_PROCESS'::character varying, 'IN_MEDIATION'::character varying, 'REJECTED'::character varying, 'CANCELLED'::character varying, 'REFUNDED'::character varying, 'CHARGED_BACK'::character varying])::text[])))
);