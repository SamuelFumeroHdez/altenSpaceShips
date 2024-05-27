CREATE TABLE space_ship (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    serie VARCHAR(255) NOT NULL
);


INSERT INTO space_ship (name, serie) VALUES ('X-Wing', 'Star Wars');
INSERT INTO space_ship (name, serie) VALUES ('Millennium Falcon', 'Star Wars');
INSERT INTO space_ship (name, serie) VALUES ('USS Enterprise', 'Star Trek');
INSERT INTO space_ship (name, serie) VALUES ('Battlestar Galactica', 'Battlestar Galactica');
INSERT INTO space_ship (name, serie) VALUES ('Endurance', 'Interstellar');
