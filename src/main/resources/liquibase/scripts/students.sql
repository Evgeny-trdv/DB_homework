-- liquibase formatted sql

--changeset etaradaev:1
CREATE INDEX student_name_index ON student (name)