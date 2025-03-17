CREATE TABLE humans (
    id SERIAL ,
    name TEXT NOT NULL ,
    age INTEGER CHECK ( age > 0 ) NOT NULL,
    license BOOLEAN DEFAULT FALSE
);

CREATE TABLE cars (
    id SERIAL  ,
    brand VARCHAR(256) NOT NULL ,
    model VARCHAR(256) NOT NULL ,
    price INTEGER DEFAULT 0
);

ALTER TABLE humans ADD PRIMARY KEY (id);
ALTER TABLE cars ADD PRIMARY KEY (id);

ALTER TABLE humans ADD car_id SERIAL REFERENCES cars (id);