-- liquibase formatted sql

--changeset etaradaev:2
CREATE INDEX faculty_name_color_index ON faculty (name, color)