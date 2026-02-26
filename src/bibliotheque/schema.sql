/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Kalala
 * Created: 26 févr. 2026
 */
-- 1. Création de la base de données
CREATE DATABASE IF NOT EXISTS registre_bibliotheque;
USE registre_bibliotheque;

-- 2. Création de la table des Livres
CREATE TABLE livres (
    id_livre INT PRIMARY KEY AUTO_INCREMENT,
    titre VARCHAR(200) NOT NULL,
    auteur VARCHAR(150),
    categorie VARCHAR(100),
    disponible BOOLEAN DEFAULT TRUE
);

-- 3. Création de la table des Lecteurs (Membres)
CREATE TABLE lecteurs (
    id_lecteur INT PRIMARY KEY AUTO_INCREMENT,
    nom_complet VARCHAR(150) NOT NULL,
    telephone VARCHAR(20)
);

-- 4. Création de la table des Emprunts (Le Registre)
CREATE TABLE emprunts (
    id_emprunt INT PRIMARY KEY AUTO_INCREMENT,
    id_livre INT,
    id_lecteur INT,
    date_emprunt DATE,
    date_retour_prevue DATE,
    statut VARCHAR(50) DEFAULT 'En cours',
    FOREIGN KEY (id_livre) REFERENCES livres(id_livre),
    FOREIGN KEY (id_lecteur) REFERENCES lecteurs(id_lecteur)
);