--------------------
-- TABLE CREATION --
--------------------
DROP TABLE IF EXISTS EAE_STATE CASCADE;

CREATE TABLE EAE_STATE 
   (	EAE_STATE_ID 		TINYINT UNSIGNED NOT NULL PRIMARY KEY, 
   		EAE_STATE_LABEL 	VARCHAR(60) NOT NULL
   );
-- TABLESPACE tmp00dta;

-- COMMENT ON TABLE EAE_STATE IS 'Table des différents etats du cycle de vie d'un EAE';

