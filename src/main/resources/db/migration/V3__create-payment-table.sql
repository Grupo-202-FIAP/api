CREATE TYPE payment_status AS ENUM (
    'PENDING', 'APPROVED', 'AUTHORIZED', 'IN_PROCESS', 'IN_MEDIATION',
    'REJECTED', 'CANCELLED', 'REFUNDED', 'CHARGED_BACK'
);

CREATE TYPE payment_method AS ENUM ('QRCODE');

CREATE TABLE tb_payment (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    status payment_status NOT NULL,
    method payment_method NOT NULL,
    payment_date_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL
);