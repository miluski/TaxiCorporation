-- set of available rides (hardcoded)

-- may need to reset the sequences
ALTER SEQUENCE addresses_seq RESTART;
ALTER SEQUENCE departures_seq RESTART;
ALTER SEQUENCE arrivals_seq RESTART;
ALTER SEQUENCE taxi_drivers_seq RESTART;
ALTER SEQUENCE courses_seq RESTART;

-- optional
DELETE FROM courses;
DELETE FROM arrivals;
DELETE FROM taxi_drivers;
DELETE FROM departures;
DELETE FROM addresses;

-- inserts
INSERT INTO addresses (id_address, city, street) VALUES (addresses_seq.NEXTVAL, 'Kielce', 'Świętokrzyska');
INSERT INTO addresses (id_address, city, street) VALUES (addresses_seq.NEXTVAL, 'Kielce', 'Warszawska');
INSERT INTO addresses (id_address, city, street) VALUES (addresses_seq.NEXTVAL, 'Kielce', 'Sandomierska');
INSERT INTO addresses (id_address, city, street) VALUES (addresses_seq.NEXTVAL, 'Kielce', 'Piekoszowska');

INSERT INTO departures (id_departure, id_address, hour) VALUES (departures_seq.NEXTVAL, 1, TO_TIMESTAMP('2024-01-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO departures (id_departure, id_address, hour) VALUES (departures_seq.NEXTVAL, 2, TO_TIMESTAMP('2024-01-02 11:30:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO departures (id_departure, id_address, hour) VALUES (departures_seq.NEXTVAL, 3, TO_TIMESTAMP('2024-01-03 13:45:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO departures (id_departure, id_address, hour) VALUES (departures_seq.NEXTVAL, 4, TO_TIMESTAMP('2024-01-04 15:20:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO departures (id_departure, id_address, hour) VALUES (departures_seq.NEXTVAL, 1, TO_TIMESTAMP('2024-01-05 17:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO departures (id_departure, id_address, hour) VALUES (departures_seq.NEXTVAL, 2, TO_TIMESTAMP('2024-01-06 18:30:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO departures (id_departure, id_address, hour) VALUES (departures_seq.NEXTVAL, 3, TO_TIMESTAMP('2024-01-07 20:15:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO departures (id_departure, id_address, hour) VALUES (departures_seq.NEXTVAL, 4, TO_TIMESTAMP('2024-01-08 22:00:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO arrivals (id_arrival, id_address) VALUES (arrivals_seq.NEXTVAL, 1);
INSERT INTO arrivals (id_arrival, id_address) VALUES (arrivals_seq.NEXTVAL, 2);
INSERT INTO arrivals (id_arrival, id_address) VALUES (arrivals_seq.NEXTVAL, 3);
INSERT INTO arrivals (id_arrival, id_address) VALUES (arrivals_seq.NEXTVAL, 4);
INSERT INTO arrivals (id_arrival, id_address) VALUES (arrivals_seq.NEXTVAL, 1);
INSERT INTO arrivals (id_arrival, id_address) VALUES (arrivals_seq.NEXTVAL, 2);
INSERT INTO arrivals (id_arrival, id_address) VALUES (arrivals_seq.NEXTVAL, 3);
INSERT INTO arrivals (id_arrival, id_address) VALUES (arrivals_seq.NEXTVAL, 4);

-- (without user sequences)
INSERT INTO taxi_drivers (id_driver, id_user) VALUES (taxi_drivers_seq.NEXTVAL, 96);
INSERT INTO taxi_drivers (id_driver, id_user) VALUES (taxi_drivers_seq.NEXTVAL, 97);
INSERT INTO taxi_drivers (id_driver, id_user) VALUES (taxi_drivers_seq.NEXTVAL, 98);
INSERT INTO taxi_drivers (id_driver, id_user) VALUES (taxi_drivers_seq.NEXTVAL, 99);

INSERT INTO courses (id_course, id_departure, id_arrival, id_driver) VALUES (courses_seq.NEXTVAL, 1, 1, 1);
INSERT INTO courses (id_course, id_departure, id_arrival, id_driver) VALUES (courses_seq.NEXTVAL, 2, 2, 2);
INSERT INTO courses (id_course, id_departure, id_arrival, id_driver) VALUES (courses_seq.NEXTVAL, 3, 3, 3);
INSERT INTO courses (id_course, id_departure, id_arrival, id_driver) VALUES (courses_seq.NEXTVAL, 4, 4, 4);
INSERT INTO courses (id_course, id_departure, id_arrival, id_driver) VALUES (courses_seq.NEXTVAL, 5, 5, 1);
INSERT INTO courses (id_course, id_departure, id_arrival, id_driver) VALUES (courses_seq.NEXTVAL, 6, 6, 2);
INSERT INTO courses (id_course, id_departure, id_arrival, id_driver) VALUES (courses_seq.NEXTVAL, 7, 7, 3);
INSERT INTO courses (id_course, id_departure, id_arrival, id_driver) VALUES (courses_seq.NEXTVAL, 8, 8, 4);

INSERT INTO users (id_user, email, rank, username, password, department, city, street, id_user_role)
VALUES (96, 'admin@example.com', 'Admin', 'admin', 'admin123', 'Admin Department', 'CityA', 'StreetA1', 1);

INSERT INTO users (id_user, email, rank, username, password, department, city, street, id_user_role)
VALUES (97, 'driver1@example.com', 'Driver', 'driver1', 'driver123', 'Driver Department', 'CityB', 'StreetB1', 2);

INSERT INTO users (id_user, email, rank, username, password, department, city, street, id_user_role)
VALUES (98, 'driver2@example.com', 'Driver', 'driver2', 'driver123', 'Driver Department', 'CityA', 'StreetA2', 2);

INSERT INTO users (id_user, email, rank, username, password, department, city, street, id_user_role)
VALUES (99, 'passenger1@example.com', 'Passenger', 'passenger1', 'pass123', 'Passenger Department', 'CityA', 'StreetA1', 3);