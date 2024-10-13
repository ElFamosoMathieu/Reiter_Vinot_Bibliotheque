CREATE TYPE EEtat AS ENUM('bon état', 'abimé');

CREATE TYPE EStatutReservation AS ENUM('disponible', 'réservé');

CREATE TYPE EStatutEmprunt AS ENUM('en cours', 'rendu');

CREATE TABLE Usager
(
    idUsager SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL UNIQUE,
    prenom VARCHAR(50) NOT NULL,
    mail VARCHAR(150) NOT NULL
);

CREATE TABLE Oeuvre(
    idOeuvre SERIAL PRIMARY KEY,
    titre VARCHAR(50) NOT NULL UNIQUE,
    datePublication date NOT NULL
);

CREATE TABLE Livre
(
    idLivre SERIAL PRIMARY KEY,
    idOeuvre int NOT NULL,
    auteur VARCHAR(50) NOT NULL,
    FOREIGN KEY (idOeuvre) REFERENCES Oeuvre (idOeuvre)
);

CREATE TABLE Magazine
(
    idMagazine SERIAL PRIMARY KEY,
    idOeuvre int NOT NULL,
    numéro INT NOT NULL,
    édition VARCHAR(50) NOT NULL,
    FOREIGN KEY (idOeuvre) REFERENCES Oeuvre (idOeuvre)
);

CREATE TABLE Exemplaire(
    idExemplaire SERIAL PRIMARY KEY,
    idOeuvre int NOT NULL,
    etat EEtat NOT NULL,
    FOREIGN KEY (idOeuvre) REFERENCES Oeuvre (idOeuvre)
);

CREATE TABLE Reservation(
    idReservation SERIAL PRIMARY KEY,
    idOeuvre int NOT NULL,
    idUsager int NOT NULL,
    dateReservation date NOT NULL,
    statutReservation EStatutReservation NOT NULL,
    FOREIGN KEY (idOeuvre) REFERENCES Oeuvre (idOeuvre),
    FOREIGN KEY (idUsager) REFERENCES Usager (idUsager)
);

CREATE TABLE Emprunt(
    idEmprunt SERIAL PRIMARY KEY,
    idOeuvre int NOT NULL,
    idUsager int NOT NULL,
    dateEmprunt date NOT NULL,
    statutEmprunt EStatutEmprunt NOT NULL,
    FOREIGN KEY (idExemplaire) REFERENCES Exemplaire (idExemplaire),
    FOREIGN KEY (idUsager) REFERENCES Usager (idUsager)
);