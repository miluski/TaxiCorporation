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
INSERT INTO users (id_user, email, username, password, department, city, street, id_user_role)
VALUES (96, 'admin@example.com', 'Admin',  'Admin@1234', 'Admin Department', 'CityA', 'StreetA1', 1);
INSERT INTO users (id_user, email, username, password, department, city, street, id_user_role)
VALUES (97, 'driver1@example.com', 'Driver1', 'Driver@1234', 'Driver Department', 'CityB', 'StreetB1', 5);
INSERT INTO users (id_user, email, username, password, department, city, street, id_user_role)
VALUES (98, 'driver2@example.com', 'Driver2', 'Driver@1234', 'Driver Department', 'CityA', 'StreetA2', 5);
INSERT INTO users (id_user, email, username, password, department, city, street, id_user_role)
VALUES (99, 'passenger1@example.com', 'Passenger', 'Pass@1234', 'Passenger Department', 'CityA', 'StreetA1', 6);

INSERT INTO addresses (id_address, city, street) VALUES (addresses_seq.NEXTVAL, 'Kielce', 'Świętokrzyska');
INSERT INTO addresses (id_address, city, street) VALUES (addresses_seq.NEXTVAL, 'Kielce', 'Warszawska');
INSERT INTO addresses (id_address, city, street) VALUES (addresses_seq.NEXTVAL, 'Kielce', 'Sandomierska');
INSERT INTO addresses (id_address, city, street) VALUES (addresses_seq.NEXTVAL, 'Kielce', 'Piekoszowska');

INSERT INTO departures (id_departure, id_address, hour) VALUES (departures_seq.NEXTVAL, 1, TO_TIMESTAMP('10:00', 'HH24:MI'));
INSERT INTO departures (id_departure, id_address, hour) VALUES (departures_seq.NEXTVAL, 2, TO_TIMESTAMP('11:30', 'HH24:MI'));
INSERT INTO departures (id_departure, id_address, hour) VALUES (departures_seq.NEXTVAL, 3, TO_TIMESTAMP('13:45', 'HH24:MI'));
INSERT INTO departures (id_departure, id_address, hour) VALUES (departures_seq.NEXTVAL, 4, TO_TIMESTAMP('15:20', 'HH24:MI'));
INSERT INTO departures (id_departure, id_address, hour) VALUES (departures_seq.NEXTVAL, 1, TO_TIMESTAMP('17:00', 'HH24:MI'));
INSERT INTO departures (id_departure, id_address, hour) VALUES (departures_seq.NEXTVAL, 2, TO_TIMESTAMP('18:30', 'HH24:MI'));
INSERT INTO departures (id_departure, id_address, hour) VALUES (departures_seq.NEXTVAL, 3, TO_TIMESTAMP('20:15', 'HH24:MI'));
INSERT INTO departures (id_departure, id_address, hour) VALUES (departures_seq.NEXTVAL, 4, TO_TIMESTAMP('22:00', 'HH24:MI'));

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

INSERT INTO courses (id_course, id_departure, id_arrival, id_driver) VALUES (courses_seq.NEXTVAL, 1, 2, 1);
INSERT INTO courses (id_course, id_departure, id_arrival, id_driver) VALUES (courses_seq.NEXTVAL, 2, 3, 2);
INSERT INTO courses (id_course, id_departure, id_arrival, id_driver) VALUES (courses_seq.NEXTVAL, 2, 4, 3);
INSERT INTO courses (id_course, id_departure, id_arrival, id_driver) VALUES (courses_seq.NEXTVAL, 3, 4, 3);
INSERT INTO courses (id_course, id_departure, id_arrival, id_driver) VALUES (courses_seq.NEXTVAL, 4, 8, 4);
INSERT INTO courses (id_course, id_departure, id_arrival, id_driver) VALUES (courses_seq.NEXTVAL, 5, 7, 1);
INSERT INTO courses (id_course, id_departure, id_arrival, id_driver) VALUES (courses_seq.NEXTVAL, 6, 5, 2);
INSERT INTO courses (id_course, id_departure, id_arrival, id_driver) VALUES (courses_seq.NEXTVAL, 5, 4, 3);
INSERT INTO courses (id_course, id_departure, id_arrival, id_driver) VALUES (courses_seq.NEXTVAL, 4, 3, 1);
INSERT INTO courses (id_course, id_departure, id_arrival, id_driver) VALUES (courses_seq.NEXTVAL, 4, 2, 1);
INSERT INTO courses (id_course, id_departure, id_arrival, id_driver) VALUES (courses_seq.NEXTVAL, 3, 2, 2);
INSERT INTO courses (id_course, id_departure, id_arrival, id_driver) VALUES (courses_seq.NEXTVAL, 2, 1, 3);