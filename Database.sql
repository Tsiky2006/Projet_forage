CREATE DATABASE IF NOT EXISTS projet_forage;
USE projet_forage;

-- =========================
-- TABLE DEMANDE
-- =========================
CREATE TABLE demande (
    id_demande INT AUTO_INCREMENT PRIMARY KEY,
    reference VARCHAR(50) NOT NULL UNIQUE,
    nom_demandeur VARCHAR(150) NOT NULL,
    lieu VARCHAR(150) NOT NULL,
    fokontany VARCHAR(100),
    district VARCHAR(100),
    region VARCHAR(100),
    date_demande DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- =========================
-- TABLE STATUT
-- =========================
CREATE TABLE statut (
    id_statut INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL UNIQUE
);

-- =========================
-- TABLE ASSOCIATIVE DEMANDE_STATUT
-- =========================
CREATE TABLE demande_statut (
    id_demande_statut INT AUTO_INCREMENT PRIMARY KEY,
    id_demande INT NOT NULL,
    id_statut INT NOT NULL,
    date_statut DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_demande_statut_demande
        FOREIGN KEY (id_demande) REFERENCES demande(id_demande)
        ON DELETE CASCADE,

    CONSTRAINT fk_demande_statut_statut
        FOREIGN KEY (id_statut) REFERENCES statut(id_statut)
        ON DELETE CASCADE
);

-- =========================
-- TABLE TYPE_DEVIS
-- =========================
CREATE TABLE type_devis (
    id_type_devis INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL UNIQUE
);

-- =========================
-- TABLE DEVIS
-- =========================
CREATE TABLE devis (
    id_devis INT AUTO_INCREMENT PRIMARY KEY,
    id_demande INT NOT NULL,
    id_type_devis INT NOT NULL,
    observation TEXT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_devis_demande
        FOREIGN KEY (id_demande) REFERENCES demande(id_demande)
        ON DELETE CASCADE,

    CONSTRAINT fk_devis_type
        FOREIGN KEY (id_type_devis) REFERENCES type_devis(id_type_devis)
        ON DELETE CASCADE
);

-- =========================
-- TABLE DETAIL_DEVIS
-- =========================
CREATE TABLE detail_devis (
    id_detail_devis INT AUTO_INCREMENT PRIMARY KEY,
    id_devis INT NOT NULL,
    libelle VARCHAR(150) NOT NULL,
    quantite INT NOT NULL,
    prix_unitaire DECIMAL(12,2) NOT NULL,

    CONSTRAINT fk_detail_devis
        FOREIGN KEY (id_devis) REFERENCES devis(id_devis)
        ON DELETE CASCADE
);

INSERT INTO statut (libelle) VALUES
('Demande créée'),
('Devis créé'),
('Étude en cours'),
('Forage suspendu'),
('Demande clôturée');

INSERT INTO type_devis (libelle) VALUES
('Étude'),
('Forage');

INSERT INTO demande (
    reference,
    nom_demandeur,
    lieu,
    fokontany,
    district,
    region
) VALUES (
    'DEM-2026-0001',
    'Rakoto Jean',
    'Ambohidratrimo',
    'Ambodimita',
    'Antananarivo Avaradrano',
    'Analamanga'
);

INSERT INTO demande_statut (
    id_demande,
    id_statut
) VALUES (
    1,
    1
);

INSERT INTO devis (
    id_demande,
    id_type_devis,
    observation
) VALUES (
    1,
    2,
    'Devis pour travaux de forage'
);

