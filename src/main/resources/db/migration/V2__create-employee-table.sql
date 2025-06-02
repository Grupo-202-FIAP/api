CREATE TABLE public.tb_employee (
	id uuid DEFAULT gen_random_uuid() NOT NULL,
	"name" varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	cpf varchar(255) NOT NULL,
	"role" varchar(255) NOT NULL,
	created_at timestamp DEFAULT now() NOT NULL,
	updated_at timestamp DEFAULT now() NOT NULL,
	"password" varchar(255) NOT NULL,
	CONSTRAINT tb_employee_cpf_key UNIQUE (cpf),
	CONSTRAINT tb_employee_email_key UNIQUE (email),
	CONSTRAINT tb_employee_pkey PRIMARY KEY (id)
);