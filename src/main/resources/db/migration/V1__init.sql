-- V1__init_schema.sql
-- Employee Table
CREATE TABLE employee
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    name     VARCHAR(255) NOT NULL
);

-- Project Table
CREATE TABLE project
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Time Record Table
CREATE TABLE time_record
(
    id          BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL REFERENCES employee (id),
    project_id  BIGINT NOT NULL REFERENCES project (id),
    time_from   TIMESTAMP NOT NULL,
    time_to     TIMESTAMP NOT NULL
);

-- Index for time range filtering
CREATE INDEX idx_time_record_time_from ON time_record (time_from);

-- Indexes for join performance
CREATE INDEX idx_time_record_employee_id ON time_record (employee_id);
CREATE INDEX idx_time_record_project_id ON time_record (project_id);
