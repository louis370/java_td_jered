/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Kalala
 * Created: 26 févr. 2026
 */

CREATE DATABASE ma_bibliotheque;
USE ma_bibliotheque;

-- Table des livres
CREATE TABLE livres (
    id_livre INT PRIMARY KEY AUTO_INCREMENT,
    titre VARCHAR(150) NOT NULL,
    auteur VARCHAR(100),
    categorie VARCHAR(50),
    disponible BOOLEAN DEFAULT TRUE
);

-- Table des membres
CREATE TABLE membres (
    id_membre INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100),
    telephone VARCHAR(20)
);

-- Table des emprunts
CREATE TABLE emprunts (
    id_emprunt INT PRIMARY KEY AUTO_INCREMENT,
    id_livre INT,
    id_membre INT,
    date_sortie DATE,
    date_retour_prevue DATE,
    statut VARCHAR(20) DEFAULT 'En cours',
    FOREIGN KEY (id_livre) REFERENCES livres(id_livre),
    FOREIGN KEY (id_membre) REFERENCES membres(id_membre)
);