CREATE TYPE EEtat AS ENUM('DISPONIBLE', 'INDISPONIBLE');

CREATE TYPE EStatutReservation AS ENUM('NON_RESERVEE', 'RESERVEE');

CREATE TYPE EStatutEmprunt AS ENUM('EN_COURS', 'TERMINE');

CREATE TABLE Usager
(
    nom VARCHAR(50) PRIMARY KEY,
    prenom VARCHAR(50) NOT NULL,
    mail VARCHAR(150) NOT NULL
);

CREATE TABLE Oeuvre(
    titre VARCHAR(50) PRIMARY KEY,
    datePublication date NOT NULL
);

CREATE TABLE Livre
(
    idLivre SERIAL PRIMARY KEY,
    titre VARCHAR(50) NOT NULL,
    auteur VARCHAR(50) NOT NULL,
    FOREIGN KEY (titre) REFERENCES Oeuvre (titre)
);

CREATE TABLE Magazine
(
    idMagazine SERIAL PRIMARY KEY,
    titre VARCHAR(50) NOT NULL,
    numéro INT NOT NULL,
    édition VARCHAR(50) NOT NULL,
    FOREIGN KEY (titre) REFERENCES Oeuvre (titre)
);

CREATE TABLE Exemplaire(
    idExemplaire SERIAL PRIMARY KEY,
    titre VARCHAR(50) NOT NULL,
    etat EEtat NOT NULL,
    FOREIGN KEY (titre) REFERENCES Oeuvre (titre)
);

CREATE TABLE Reservation(
    idReservation SERIAL PRIMARY KEY,
    titre VARCHAR(50) NOT NULL,
    nom VARCHAR(50) NOT NULL,
    dateReservation date NOT NULL,
    statutReservation EStatutReservation NOT NULL,
    FOREIGN KEY (titre) REFERENCES Oeuvre (titre),
    FOREIGN KEY (nom) REFERENCES Usager (nom)
);

CREATE TABLE Emprunt(
    idEmprunt SERIAL PRIMARY KEY,
    idExemplaire int NOT NULL,
    nom VARCHAR(50) NOT NULL,
    dateEmprunt date NOT NULL,
    statutEmprunt EStatutEmprunt NOT NULL,
    FOREIGN KEY (idExemplaire) REFERENCES Exemplaire (idExemplaire),
    FOREIGN KEY (nom) REFERENCES Usager (nom)
);