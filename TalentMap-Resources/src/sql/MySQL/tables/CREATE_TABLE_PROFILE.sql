--------------------
-- TABLE CREATION --
--------------------
DROP TABLE IF EXISTS PROFILE CASCADE;

CREATE TABLE PROFILE 
   (	PROFILE_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY , 
	PROFILE_TYPE VARCHAR(20) NOT NULL
   );
-- TABLESPACE tmp00dta;

-- COMMENT ON TABLE PROFILE IS 'Table des types de profil';

