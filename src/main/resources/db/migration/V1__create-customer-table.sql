CREATE TABLE public.tb_customer (
	id uuid DEFAULT gen_random_uuid() NOT NULL,
	name varchar(255) NULL,
	email varchar(255) NULL,
	cpf varchar(255) NULL,
	"role" varchar(255) NOT NULL,
	created_at timestamp DEFAULT now() NOT NULL,
	updated_at timestamp DEFAULT now() NOT NULL,
	CONSTRAINT tb_customer_cpf_key UNIQUE (cpf),
	CONSTRAINT tb_customer_email_key UNIQUE (email),
	CONSTRAINT tb_customer_pkey PRIMARY KEY (id)
);
