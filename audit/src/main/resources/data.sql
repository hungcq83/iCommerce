DROP TABLE IF EXISTS audit;

CREATE TABLE audit (
  id LONG AUTO_INCREMENT  PRIMARY KEY,
  product_code VARCHAR(50) DEFAULT NULL,
  activity VARCHAR(50) NOT NULL,
  query VARCHAR(50) DEFAULT NULL,
  filter VARCHAR(50) DEFAULT NULL,
  sort_by VARCHAR(50) DEFAULT NULL,
  sort_order VARCHAR(4) DEFAULT 'asc',
  page SMALLINT DEFAULT 0,
  time_stamp DATETIME DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO audit (product_code, activity) VALUES
  ('MACBOOK001', 'View');

INSERT INTO audit (activity, query, filter, sort_by, sort_order, page) VALUES
  ('Search', 'MacBook Pro', 'color:silver', 'price', 'asc', 0);