--------------------
-- TABLE SECTEUR_ACTIVITE --
--------------------
DROP TABLE IF EXISTS SECTEUR_ACTIVITE CASCADE;

 CREATE TABLE SECTEUR_ACTIVITE 
   (	SECTEUR_ID INT NOT NULL UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
	SECTEUR_NAME VARCHAR(70) NOT NULL
   );
-- TABLESPACE tmp00dta;

-- COMMENT ON TABLE SECTEUR_ACTIVITE IS 'Table des secteurs d'activité des clients';

