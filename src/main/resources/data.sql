-- Initialisation des tables
INSERT INTO Country(id, code, name) VALUES
    (1, 'FR', 'France'), -- Les clés sont auto-générées
    (2, 'UK', 'United Kingdom'),
    (3, 'US', 'United States of America');
-- On peut fixer les clés auto-générées, mais il faut ensuite
-- réinitialiser le compteur de clé auto-générée
-- Attention : la syntaxe est différente selon le SGBD utilisé
-- Ici la syntaxe pour le SGBD H2
ALTER TABLE Country ALTER COLUMN id RESTART WITH 4;

-- On peut utiliser des sous-requêtes pour récupérer
-- la clé étrangère de la table Country
INSERT INTO CITY(name, population, country_id) VALUES
    ('Paris', 12, SELECT id FROM Country WHERE code = 'FR'),
    ('London', 18, SELECT id FROM Country WHERE code = 'UK'),
    ('New York', 27, SELECT id FROM Country WHERE code = 'US');