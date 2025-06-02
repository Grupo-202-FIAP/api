CREATE TABLE public.tb_customer (
    id uuid DEFAULT gen_random_uuid(),
	cpf varchar(255) NULL,
	created_at timestamp(6) NULL,
	email varchar(255) NULL,
	"name" varchar(255) NULL,
	"role" varchar(255) NULL,
	updated_at timestamp(6) NULL,
	CONSTRAINT tb_customer_pkey PRIMARY KEY (id),
	CONSTRAINT tb_customer_role_check CHECK (((role)::text = ANY ((ARRAY['ROLE_CUSTOMER'::character varying, 'ROLE_ADMIN'::character varying, 'ROLE_MANAGER'::character varying, 'ROLE_KITCHEN'::character varying, 'ROLE_DELIVERY'::character varying, 'ROLE_GUEST'::character varying])::text[]))),
	CONSTRAINT uk4xad1enskw4j1t2866f7sodrx UNIQUE (email),
	CONSTRAINT uk6kehl1759lbapal6o3bmmrp7i UNIQUE (cpf)
);
