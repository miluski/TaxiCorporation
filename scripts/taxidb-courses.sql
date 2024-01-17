-- set of available rides (hardcoded)

-- may need to reset the sequences
ALTER SEQUENCE addresses_seq RESTART;
ALTER SEQUENCE departures_seq RESTART;
ALTER SEQUENCE arrivals_seq RESTART;
ALTER SEQUENCE taxi_drivers_seq RESTART;

-- optional
DELETE FROM arrivals;
DELETE FROM taxi_drivers;
DELETE FROM departures;
DELETE FROM addresses;

-- inserts

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