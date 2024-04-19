-- Initialisation des tables
INSERT INTO Country(id, code, name, url) VALUES
    (1, 'FR', 'France', 'https://media.istockphoto.com/id/924891460/fr/photo/tour-eiffel-%C3%A0-paris-france.jpg?b=1&s=612x612&w=0&k=20&c=VcaYT6Pg9441Hnb3i5LzeFOogJGdAs1A3wD5O392zlw='), -- Les clés sont auto-générées
    (2, 'UK', 'United Kingdom', 'https://st4.depositphotos.com/1611230/20177/i/450/depositphotos_201775890-stock-photo-big-ben-house-parliament-night.jpg'),
    (3, 'US', 'United States of America', 'https://www.shutterstock.com/image-photo/road-monument-valley-navajo-nation-260nw-1896432379.jpg'),
    (4, 'CH', 'Chine', 'https://media.istockphoto.com/id/506393198/fr/photo/grande-muraille-de-chine.jpg?s=1024x1024&w=is&k=20&c=T_wLeXTinGrbyWOvuuwIFpH491CPYl148f2Rp75qad0=');
-- On peut fixer les clés auto-générées, mais il faut ensuite
-- réinitialiser le compteur de clé auto-générée
-- Attention : la syntaxe est différente selon le SGBD utilisé
-- Ici la syntaxe pour le SGBD H2
ALTER TABLE Country ALTER COLUMN id RESTART WITH 4;

-- On peut utiliser des sous-requêtes pour récupérer
-- la clé étrangère de la table Country
INSERT INTO CITY(name, population, country_id) VALUES
    ('Paris', 12, SELECT id FROM Country WHERE code = 'FR'),
    ('Castres', 232, SELECT id FROM Country WHERE code = 'FR'),
    ('London', 18, SELECT id FROM Country WHERE code = 'UK'),
    ('New York', 27, SELECT id FROM Country WHERE code = 'US'),
    ('Pékin', 345,SELECT id FROM Country WHERE code = 'CH');
