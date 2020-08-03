DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS shipment;
DROP TABLE IF EXISTS order_details;

CREATE TABLE order_details (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  order_status VARCHAR(20) NOT NULL,
  ordered_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  order_total DOUBLE NOT NULL,
  modified_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE order_item (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(50) NOT NULL,
  name VARCHAR(50) NOT NULL,
  brand VARCHAR(50) NOT NULL,
  price DOUBLE NOT NULL,
  color VARCHAR(20) NOT NULL,
  size VARCHAR(20) NOT NULL,
  order_id BIGINT NOT NULL,
  FOREIGN KEY (order_id) REFERENCES order_details(id),
  modified_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE payment (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(50) NOT NULL,
  amount DOUBLE NOT NULL,
  card_number VARCHAR(250) NOT NULL,
  card_type VARCHAR(250) NOT NULL,
  order_id BIGINT NOT NULL,
  FOREIGN KEY (order_id) REFERENCES order_details(id)
);

CREATE TABLE shipment (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  shipment_code VARCHAR(50) NOT NULL,
  shipment_method VARCHAR(50) NOT NULL,
  shipping_charge DOUBLE NOT NULL,
  shipping_address VARCHAR(250) NOT NULL,
  order_id BIGINT NOT NULL,
  FOREIGN KEY (order_id) REFERENCES order_details(id)
);

INSERT INTO order_details (id, order_status, order_total) VALUES
(1, 'Ordered', 125.80);


INSERT INTO shipment (shipment_code, shipment_method, shipping_charge, shipping_address, order_id) VALUES
('DHL12345', 'DHL', 10.90, '111 Nguyen Trai, District 1, HCMC', 1);

INSERT INTO payment (code, amount, card_number, card_type, order_id) VALUES
  ('VISAPAY90001', 125.80, '411111111111111111', 'VISA', 1);

INSERT INTO order_item (code, name, brand, color, size, price, order_id) VALUES
('MACPRO2020-1', 'Macbook Pro 2020', 'Apple', 'Silver', '13inch', 2399.90, 1);
INSERT INTO order_item (code, name, brand, color, size, price, order_id) VALUES
('IPADMINI2018-1', 'iPad Mini 2018', 'Apple', 'Grey', '9inch', 999.90, 1);
