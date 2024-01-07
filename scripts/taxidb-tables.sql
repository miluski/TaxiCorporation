-- Tabela "user_roles"
CREATE TABLE user_roles (
    id_user_role NUMBER PRIMARY KEY,
    name VARCHAR2(255)
);

-- Tabela "addresses"
CREATE TABLE addresses (
    id_address NUMBER PRIMARY KEY,
    city VARCHAR2(255),
    street VARCHAR2(255)
);

-- Tabela "departures"
CREATE TABLE departures (
    id_departure NUMBER PRIMARY KEY,
    id_address NUMBER REFERENCES addresses(id_address),
    hour TIMESTAMP
);

-- Tabela "arrivals"
CREATE TABLE arrivals (
    id_arrival NUMBER PRIMARY KEY,
    id_address NUMBER REFERENCES addresses(id_address)
);

-- Tabela "departments"
CREATE TABLE departments (
    id_department NUMBER PRIMARY KEY,
    name VARCHAR2(255),
    id_address NUMBER REFERENCES addresses(id_address)
);

-- Tabela "users"
CREATE TABLE users (
    id_user NUMBER PRIMARY KEY,
    email VARCHAR2(255),
    username VARCHAR2(255),
    password VARCHAR2(255),
    department VARCHAR2(255),
    city VARCHAR2(255),
    street VARCHAR2(255),
    loggedIn VARCHAR2(5),
    id_user_role NUMBER REFERENCES user_roles(id_user_role)
);

-- Tabela "managers"
CREATE TABLE managers (
    id_manager NUMBER PRIMARY KEY,
    id_user NUMBER REFERENCES users(id_user) UNIQUE,
    id_department NUMBER REFERENCES departments(id_department)
);

-- Tabela "cars"
CREATE TABLE cars (
    id_car NUMBER PRIMARY KEY,
    model VARCHAR2(255),
    model_year NUMBER,
    department_name VARCHAR2(255)
);

-- Tabela "taxi_drivers"
CREATE TABLE taxi_drivers (
    id_driver NUMBER PRIMARY KEY,
    id_user NUMBER REFERENCES users(id_user) UNIQUE
);

-- Tabela "courses"
CREATE TABLE courses (
    id_course NUMBER PRIMARY KEY,
    id_departure NUMBER REFERENCES departures(id_departure),
    id_arrival NUMBER REFERENCES arrivals(id_arrival),
    id_driver NUMBER REFERENCES taxi_drivers(id_driver)
);

-- Tabela "passengers"
CREATE TABLE passengers (
    id_passenger NUMBER PRIMARY KEY,
    id_user NUMBER REFERENCES users(id_user),
    id_course NUMBER REFERENCES courses(id_course)
);
