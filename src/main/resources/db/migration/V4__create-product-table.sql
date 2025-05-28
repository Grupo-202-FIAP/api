CREATE TABLE tb_products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    category VARCHAR(255) NOT NULL,
    unit_price NUMERIC(15, 2),
    url_image TEXT,
    description TEXT,
    created_by_employee_id UUID,
    created_at TIMESTAMPTZ DEFAULT now(),
    updated_at TIMESTAMPTZ DEFAULT now(),

    CONSTRAINT fk_created_by_employee FOREIGN KEY (created_by_employee_id) REFERENCES tb_employee(id)
);

