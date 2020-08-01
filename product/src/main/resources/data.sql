DROP TABLE IF EXISTS sku;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS product_category;

CREATE TABLE product_category (
  id LONG AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(50) NOT NULL,
  name VARCHAR(250) NOT NULL,
  modified_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE product (
  id LONG AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(50) NOT NULL,
  name VARCHAR(250) NOT NULL,
  brand VARCHAR(250) NOT NULL,
  category_id LONG NOT NULL,
  modified_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (category_id) REFERENCES product_category(id)
);

CREATE TABLE sku (
  code VARCHAR(50) NOT NULL,
  product_id LONG NOT NULL,
  color VARCHAR(50) NOT NULL,
  size VARCHAR(250) NOT NULL,
  price DOUBLE NOT NULL,
  PRIMARY KEY (product_id, color, size),
  FOREIGN KEY (product_id) REFERENCES product(id)
);

INSERT INTO product_category (id, name, code) VALUES
(1, 'Laptop', 'LAPTOP');

INSERT INTO product_category (id, name, code) VALUES
(2, 'Tablet', 'TABLET');

INSERT INTO product (name, code, brand, category_id) VALUES
  ('MacBook Pro 2020', 'MACPRO2020', 'Apple', 1);
INSERT INTO product (name, code, brand, category_id) VALUES
  ('MacBook Air 2018', 'MACAIR2018', 'Apple', 1);
INSERT INTO product (name, code, brand, category_id) VALUES
  ('iPad Mini', 'IPADMINI', 'Apple', 2);
INSERT INTO product (name, code, brand, category_id) VALUES
  ('iPad Pro', 'IPADPRO', 'Apple', 2);

INSERT INTO sku (code, product_id, color, size, price) VALUES
('MACPRO2020-1', 1, 'Silver', '13inch', 999.90);
INSERT INTO sku (code, product_id, color, size, price) VALUES
('MACPRO2020-2', 1, 'Grey', '15inch', 1299.90);
INSERT INTO sku (code, product_id, color, size, price) VALUES
('MACAIR2018-1', 2, 'Silver', '13inch', 799.90);
INSERT INTO sku (code, product_id, color, size, price) VALUES
('MACPRO2018-2', 2, 'Grey', '15inch', 1099.90);
INSERT INTO sku (code, product_id, color, size, price) VALUES
('IPADMINI2020-1', 3, 'Red', 'XS', 399.90);
INSERT INTO sku (code, product_id, color, size, price) VALUES
('IPADMINI2020-2', 3, 'Green', 'XS', 799.90);
INSERT INTO sku (code, product_id, color, size, price) VALUES
('IPADPRO2020-1', 4, 'Blue', 'XXL', 399.90);
INSERT INTO sku (code, product_id, color, size, price) VALUES
('IPADPRO2020-2', 4, 'White', 'XXL', 799.90);