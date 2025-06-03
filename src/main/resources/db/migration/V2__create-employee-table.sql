CREATE TABLE public.tb_employee (
  id uuid DEFAULT gen_random_uuid(),
	cpf varchar(255) NULL,
	created_at timestamp(6) NULL,
	email varchar(255) NULL,
	"name" varchar(255) NULL,
	"role" varchar(255) NULL,
	updated_at timestamp(6) NULL,
	"password" varchar(255) NULL,
	CONSTRAINT tb_employee_pkey PRIMARY KEY (id),
	CONSTRAINT tb_employee_role_check CHECK (((role)::text = ANY ((ARRAY['ROLE_CUSTOMER'::character varying, 'ROLE_ADMIN'::character varying, 'ROLE_MANAGER'::character varying, 'ROLE_KITCHEN'::character varying, 'ROLE_DELIVERY'::character varying, 'ROLE_GUEST'::character varying])::text[])))
);
