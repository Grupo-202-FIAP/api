CREATE TABLE public.tb_payment (
	id uuid DEFAULT gen_random_uuid() NOT NULL,
	status varchar(255) NOT NULL,
	"method" public."payment_method" NOT NULL,
	payment_date_time timestamp DEFAULT now() NOT NULL,
	updated_at timestamp DEFAULT now() NOT NULL,
	"payment_method" varchar(255) NULL,
	CONSTRAINT tb_payment_payment_method_check CHECK (((payment_method)::text = 'QR_CODE'::text)),
	CONSTRAINT tb_payment_pkey PRIMARY KEY (id)
);