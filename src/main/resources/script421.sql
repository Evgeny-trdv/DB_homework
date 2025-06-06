ALTER TABLE student
ADD CONSTRAINT age_positive CHECK ( age > 16 );

ALTER TABLE student
ALTER COLUMN age SET DEFAULT 20;

ALTER TABLE student
ALTER COLUMN name SET NOT NULL;

ALTER TABLE student
ADD CONSTRAINT name_unique UNIQUE (name);

ALTER TABLE faculty
ADD CONSTRAINT name_color_unique UNIQUE (name, color);

