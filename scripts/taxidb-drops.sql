-- Drop sekwencji "user_roles_seq"
DROP SEQUENCE user_roles_seq;

-- Drop sekwencji "addresses_seq"
DROP SEQUENCE addresses_seq;

-- Drop sekwencji "departures_seq"
DROP SEQUENCE departures_seq;

-- Drop sekwencji "arrivals_seq"
DROP SEQUENCE arrivals_seq;

-- Drop sekwencji "departments_seq"
DROP SEQUENCE departments_seq;

-- Drop sekwencji "users_seq"
DROP SEQUENCE users_seq;

-- Drop sekwencji "menagers_seq"
DROP SEQUENCE menagers_seq;

-- Drop sekwencji "cars_seq"
DROP SEQUENCE cars_seq;

-- Drop sekwencji "taxi_drivers_seq"
DROP SEQUENCE taxi_drivers_seq;

-- Drop sekwencji "courses_seq"
DROP SEQUENCE courses_seq;

-- Drop sekwencji "reservations_seq"
DROP SEQUENCE reservations_seq;

-- Drop tabeli "user_roles"
DROP TABLE user_roles CASCADE CONSTRAINTS;

-- Drop tabeli "addresses"
DROP TABLE addresses CASCADE CONSTRAINTS;

-- Drop tabeli "departures"
DROP TABLE departures CASCADE CONSTRAINTS;

-- Drop tabeli "arrivals"
DROP TABLE arrivals CASCADE CONSTRAINTS;

-- Drop tabeli "departments"
DROP TABLE departments CASCADE CONSTRAINTS;

-- Drop tabeli "users"
DROP TABLE users CASCADE CONSTRAINTS;

-- Drop tabeli "managers"
DROP TABLE managers CASCADE CONSTRAINTS;

-- Drop tabeli "cars"
DROP TABLE cars CASCADE CONSTRAINTS;

-- Drop tabeli "taxi_drivers"
DROP TABLE taxi_drivers CASCADE CONSTRAINTS;

-- Drop tabeli "courses"
DROP TABLE courses CASCADE CONSTRAINTS;

-- Drop tabeli "reservations"
DROP TABLE reservations CASCADE CONSTRAINTS;
