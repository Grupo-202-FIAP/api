CREATE TYPE order_status AS ENUM ('RECEIVED', 'PREPARING', 'READY', 'COMPLETED', 'CANCELLED');

CREATE TYPE payment_status AS ENUM (
    'PENDING', 'APPROVED', 'AUTHORIZED', 'IN_PROCESS', 'IN_MEDIATION',
    'REJECTED', 'CANCELLED', 'REFUNDED', 'CHARGED_BACK'
);

CREATE TYPE payment_method AS ENUM ('QRCODE');

CREATE TABLE tb_order (
    order_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    total_price NUMERIC(10, 2) NOT NULL,
    status order_status NOT NULL,
    order_date_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    payment_fk_id UUID,
    user_fk_id UUID,

    CONSTRAINT fk_payment FOREIGN KEY (payment_fk_id) REFERENCES tb_payment(payment_id) ON DELETE SET NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_fk_id) REFERENCES tb_user(user_id) ON DELETE CASCADE
);

CREATE TABLE tb_payment(
    payment_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    status payment_status NOT NULL,
    method payment_method NOT NULL,
    payment_date_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL
);

