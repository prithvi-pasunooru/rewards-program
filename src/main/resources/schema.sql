
CREATE TABLE transactions (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 customer_id BIGINT,
 amount DOUBLE,
 transaction_date DATE
);
