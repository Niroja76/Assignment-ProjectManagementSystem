-- Projects Table
CREATE TABLE IF NOT EXISTS projects (
    project_id BIGSERIAL PRIMARY KEY,
    project_name VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    start_date DATE,
    end_date DATE,
    project_status VARCHAR(50) NOT NULL
);

-- Team Members Table
CREATE TABLE IF NOT EXISTS team_members (
    member_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role VARCHAR(100) NOT NULL,
    department VARCHAR(100)
);

-- Tasks Table
CREATE TABLE IF NOT EXISTS tasks (
    task_id BIGSERIAL PRIMARY KEY,
    task_title VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    priority VARCHAR(50),
    status VARCHAR(50) NOT NULL,
    due_date DATE,
    project_id BIGINT REFERENCES projects(project_id),
    member_id BIGINT REFERENCES team_members(member_id)
);