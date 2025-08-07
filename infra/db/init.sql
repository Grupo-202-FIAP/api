CREATE TABLE tb_customer (
    id INT PRIMARY KEY,
    cpf VARCHAR(11)
);

CREATE TABLE tb_user (
    id INT PRIMARY KEY,
    name VARCHAR(45),
    email VARCHAR(45),
    role_id_fk INT,
    employee_id_fk INT,
    customer_id_fk INT,
    FOREIGN KEY (role_id_fk) REFERENCES tb_roles(role_id),
    FOREIGN KEY (employee_id_fk) REFERENCES tb_employee(id),
    FOREIGN KEY (customer_id_fk) REFERENCES tb_customer(id)
);

CREATE TABLE tb_payment (
    payment_id INT PRIMARY KEY,
    status ENUM(
        'PENDING', 'APPROVED', 'AUTHORIZED', 'IN_PROCESS', 'IN_MEDIATION',
        'REJECTED', 'CANCELLED', 'REFUNDED', 'CHARGED_BACK'
    ),
    payment_method ENUM('QRCODE'),
    payment_at_date_time DATETIME
);

CREATE TABLE tb_order (
    order_id INT PRIMARY KEY,
    total_price DECIMAL,
    status ENUM('RECEIVED', 'PREPARING', 'READY', 'COMPLETED'),
    order_date_time DATETIME,
    payment_at_id_fk INT,
    user_id_fk INT,
    FOREIGN KEY (payment_at_id_fk) REFERENCES tb_payment(payment_id),
    FOREIGN KEY (user_id_fk) REFERENCES tb_user(id)
);

CREATE TABLE tb_product (
    product_id INT PRIMARY KEY,
    name VARCHAR(255),
    category ENUM('SNACK', 'SIDE_DISH', 'DRINK', 'DESSERT'),
    unit_price DECIMAL,
    url_image VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE tb_order_item (
    product_id INT,
    order_id INT,
    quantity INT,
    PRIMARY KEY (product_id, order_id),
    FOREIGN KEY (product_id) REFERENCES tb_product(product_id),
    FOREIGN KEY (order_id) REFERENCES tb_order(order_id)
);