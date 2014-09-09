-- Création de la base de données et du USER
DROP DATABASE IF EXISTS talent_map;
CREATE DATABASE IF NOT EXISTS  `talent_map` CHARACTER SET = 'utf8';
GRANT ALL PRIVILEGES ON  `talent_map`.* TO  'talentmapuser'@'localhost' IDENTIFIED BY 'talentmapuser';