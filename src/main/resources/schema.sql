set mode MySQL;

CREATE TABLE IF NOT EXISTS product (
    id integer NOT NULL auto_increment,
    name varchar,
    sku varchar,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS  warehouse (
    id integer NOT NULL auto_increment,
    name varchar,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS  stock (
    product_id integer,
    warehouse_id integer,
    qty integer,
    PRIMARY KEY (product_id, warehouse_id)
)