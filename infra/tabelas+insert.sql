--CREATE TABLE tb_customer (
--    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
--    name VARCHAR(255),
--    email VARCHAR(255) UNIQUE,
--    cpf VARCHAR(20) UNIQUE,
--    role VARCHAR(20) NOT NULL,
--    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
--    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL
--);
--
--CREATE TABLE tb_employee (
--    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
--    name VARCHAR(255) NOT NULL,
--    email VARCHAR(255) NOT NULL UNIQUE,
--    cpf VARCHAR(20) NOT NULL UNIQUE,
--    role VARCHAR(20) NOT NULL,
--    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
--    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
--    password VARCHAR(255) NOT NULL
--);
--
--CREATE TYPE payment_status AS ENUM (
--    'PENDING', 'APPROVED', 'AUTHORIZED', 'IN_PROCESS', 'IN_MEDIATION',
--    'REJECTED', 'CANCELLED', 'REFUNDED', 'CHARGED_BACK'
--);
--
--CREATE TYPE payment_method AS ENUM ('QRCODE');
--
--CREATE TABLE tb_payment (
--    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
--    status payment_status NOT NULL,
--    method payment_method NOT NULL,
--    payment_date_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
--    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL
--);
--
--CREATE TABLE tb_products (
--    id SERIAL PRIMARY KEY,
--    name VARCHAR(255) NOT NULL UNIQUE,
--    category VARCHAR(255) NOT NULL,
--    unit_price NUMERIC(15, 2),
--    url_image TEXT,
--    description TEXT,
--    created_by_employee_id UUID,
--    created_at TIMESTAMPTZ DEFAULT now(),
--    updated_at TIMESTAMPTZ DEFAULT now(),
--
--    CONSTRAINT fk_created_by_employee FOREIGN KEY (created_by_employee_id) REFERENCES tb_employee(id)
--);
--
--CREATE TABLE tb_order (
--    id SERIAL PRIMARY KEY,
--    identifier VARCHAR(40),
--    total_price NUMERIC(10, 2) NOT NULL,
--    order_status VARCHAR(255) NOT NULL,
--    order_date_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
--    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
--    customer_id UUID NOT NULL,
--    payment_fk_id UUID,
--
--    CONSTRAINT fk_payment FOREIGN KEY (payment_fk_id) REFERENCES tb_payment(id) ON DELETE SET NULL,
--    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES tb_customer(id)
--);
--
--CREATE TABLE tb_order_item(
--    id UUID PRIMARY KEY,
--    quantity INT NOT NULL,
--    price_at_purchase NUMERIC(10, 2),
--    product_id SERIAL NOT NULL,
--    order_id SERIAL ,
--    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES tb_order(id),
--    CONSTRAINT fk_product FOREIGN KEY (product_id) references tb_products(id)
--);


-- CLIENTES
INSERT INTO tb_customer (id, name, email, cpf, role) VALUES
('00000000-0000-0000-0000-000000000001', 'Customer User', 'customer@example.com', '00000000001', 'ROLE_CUSTOMER'),
('00000000-0000-0000-0000-000000000002', 'Guest User', 'guest@example.com', '00000000002', 'ROLE_GUEST');

-- FUNCIONÁRIOS
INSERT INTO tb_employee (id, name, email, cpf, role, password) VALUES
('10000000-0000-0000-0000-000000000001', 'Admin User', 'admin@company.com', '11111111101', 'ROLE_ADMIN', '$2a$12$FMJfJzFOUovojmLFl4kFa.dSXoe/PEaeLgZOTJxPi.U.HxYs5OHpS'),
('10000000-0000-0000-0000-000000000002', 'Manager User', 'manager@company.com', '11111111102', 'ROLE_MANAGER', '$2a$12$FMJfJzFOUovojmLFl4kFa.dSXoe/PEaeLgZOTJxPi.U.HxYs5OHpS'),
('10000000-0000-0000-0000-000000000003', 'Kitchen User', 'kitchen@company.com', '11111111103', 'ROLE_KITCHEN', '$2a$12$FMJfJzFOUovojmLFl4kFa.dSXoe/PEaeLgZOTJxPi.U.HxYs5OHpS'),
('10000000-0000-0000-0000-000000000004', 'Delivery User', 'delivery@company.com', '11111111104', 'ROLE_DELIVERY', '$2a$12$FMJfJzFOUovojmLFl4kFa.dSXoe/PEaeLgZOTJxPi.U.HxYs5OHpS');

-- PAGAMENTOS
INSERT INTO tb_payment (id, status, method) VALUES
('20000000-0000-0000-0000-000000000001', 'PENDING', 'QRCODE'),
('20000000-0000-0000-0000-000000000002', 'APPROVED', 'QRCODE'),
('20000000-0000-0000-0000-000000000003', 'AUTHORIZED', 'QRCODE'),
('20000000-0000-0000-0000-000000000004', 'IN_PROCESS', 'QRCODE'),
('20000000-0000-0000-0000-000000000005', 'IN_MEDIATION', 'QRCODE'),
('20000000-0000-0000-0000-000000000006', 'REJECTED', 'QRCODE'),
('20000000-0000-0000-0000-000000000007', 'CANCELLED', 'QRCODE'),
('20000000-0000-0000-0000-000000000008', 'REFUNDED', 'QRCODE'),
('20000000-0000-0000-0000-000000000009', 'CHARGED_BACK', 'QRCODE');

-- PRODUTOS
INSERT INTO tb_products (name, category, unit_price, url_image, description, created_by_employee_id) VALUES
('Burger', 'SANDWICHES', 15.00, 'https://img.com/burger.png', 'Delicious artisan burger', '10000000-0000-0000-0000-000000000001'),
('Fries', 'SIDES', 8.00, 'https://img.com/fries.png', 'Crispy fries', '10000000-0000-0000-0000-000000000002'),
('Soda', 'DRINKS', 5.00, 'https://img.com/soda.png', '350ml soda can', '10000000-0000-0000-0000-000000000003'),
('Ice Cream', 'DESSERTS', 10.00, 'https://img.com/icecream.png', 'Ice cream bowl', '10000000-0000-0000-0000-000000000003');

-- PEDIDOS
INSERT INTO tb_order (identifier, total_price, order_status, payment_fk_id, customer_id) VALUES
('ORD001', 100.00, 'RECEIVED', '20000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001'),
('ORD002', 150.00, 'PREPARING', '20000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000001'),
('ORD003', 200.00, 'READY', '20000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000001'),
('ORD004', 250.00, 'COMPLETED', '20000000-0000-0000-0000-000000000004', '00000000-0000-0000-0000-000000000001'),
('ORD005', 50.00, 'CANCELLED', '20000000-0000-0000-0000-000000000005', '00000000-0000-0000-0000-000000000001');

-- PEDIDOS_PRODUTOS
INSERT INTO tb_order_item (id, order_id, product_id, quantity, price_at_purchase) VALUES
('50000000-0000-0000-0000-000000000001', 1, 1, 2, 15.00),
('50000000-0000-0000-0000-000000000002', 1, 2, 1, 8.00),
('50000000-0000-0000-0000-000000000003', 2, 3, 2, 5.00),
('50000000-0000-0000-0000-000000000004', 3, 4, 1, 10.00),
('50000000-0000-0000-0000-000000000005', 4, 1, 1, 15.00),
('50000000-0000-0000-0000-000000000006', 5, 2, 2, 8.00);


select * from tb_employee te ;
select * from tb_customer;
