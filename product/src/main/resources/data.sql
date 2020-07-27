DROP TABLE IF EXISTS product;

CREATE TABLE product (
  id LONG AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(50) NOT NULL,
  name VARCHAR(250) NOT NULL,
  price DOUBLE NOT NULL,
  brand VARCHAR(250) NOT NULL,
  color VARCHAR(50) DEFAULT NULL,
  modified_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO product (name, code, price, brand, color) VALUES
  ('MacBook Pro 2020', 'MACPRO2020', 1999.99, 'Apple', 'Silver');
INSERT INTO product (name, code, price, brand, color) VALUES
  ('MacBook Air 2018', 'MACAIR2018', 1299.99, 'Apple', 'Grey');
INSERT INTO product (name, code, price, brand, color) VALUES
  ('iPad Mini', 'IPADMINI', 499.00, 'Apple', 'Gold');
INSERT INTO product (name, code, price, brand, color) VALUES
  ('iPad Pro', 'IPADPRO', 999.90, 'Apple', 'White');
