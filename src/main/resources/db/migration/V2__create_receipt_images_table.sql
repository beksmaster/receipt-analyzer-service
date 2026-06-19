CREATE TABLE receipt_images (
                                id BIGSERIAL PRIMARY KEY,
                                file_name VARCHAR(255) NOT NULL,
                                file_path VARCHAR(500) NOT NULL,
                                file_size BIGINT NOT NULL,
                                uploaded_at TIMESTAMP NOT NULL
);