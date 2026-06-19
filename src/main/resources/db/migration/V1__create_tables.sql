CREATE TABLE receipts (
                          id BIGSERIAL PRIMARY KEY,
                          store_name VARCHAR(255),
                          purchase_date TIMESTAMP,
                          total_amount NUMERIC(10,2)
);

CREATE TABLE products (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255),
                          price NUMERIC(10,2),
                          quantity INTEGER,
                          receipt_id BIGINT REFERENCES receipts(id)
);