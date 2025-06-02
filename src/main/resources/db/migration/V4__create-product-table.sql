CREATE TABLE public.tb_products (
	id bigserial NOT NULL,
	"name" varchar(255) NOT NULL,
	category varchar(255) NOT NULL,
	unit_price numeric(38, 2) NULL,
	url_image varchar(255) NULL,
	description varchar(255) NULL,
	created_by_employee_id uuid NULL,
	created_at timestamptz DEFAULT now() NULL,
	updated_at timestamptz DEFAULT now() NULL,
	CONSTRAINT tb_products_name_key UNIQUE (name),
	CONSTRAINT tb_products_pkey PRIMARY KEY (id),
	CONSTRAINT fk_created_by_employee FOREIGN KEY (created_by_employee_id) REFERENCES public.tb_employee(id)
);