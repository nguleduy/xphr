-- V1__init_schema.sql
CREATE TABLE employee
(
    id   BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE project
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE time_record
(
    id          BIGSERIAL PRIMARY KEY,
    employee_id BIGSERIAL    NOT NULL REFERENCES employee (id),
    project_id  BIGSERIAL    NOT NULL REFERENCES project (id),
    time_from   TIMESTAMP NOT NULL,
    time_to     TIMESTAMP NOT NULL
);

CREATE INDEX idx_time_record_time_from ON time_record (time_from);