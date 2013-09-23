--------------------
-- TABLE CREATION --
--------------------
ALTER TABLE EAE DROP FOREIGN KEY EAEFK1 ;
ALTER TABLE EAE DROP FOREIGN KEY EAEFK2 ;
ALTER TABLE EAE DROP FOREIGN KEY EAEFK3 ;
ALTER TABLE EAE DROP FOREIGN KEY EAEFK4 ;

ALTER TABLE OBJECTIVE DROP FOREIGN KEY OBJECTIVEFK3 ;

DROP TABLE IF EXISTS EAE CASCADE;

CREATE TABLE EAE 
   (	EAE_ID 					INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
   		COLLEAGUE_ID 			INT NOT NULL,
   		MANAGER_ID 				INT NOT NULL,
   		EAE_DATE 				DATE NOT NULL,
   		PROFILE_LABEL 			VARCHAR(60),
   		PREVIOUS_EAE_ID 		INT,
		YEAR_SYNTHESIS 			TEXT,
		COLLEAGUES_STRENGTHS 	TEXT,
		COLLEAGUES_WEAKNESSES 	TEXT,
		MEANS_TO_PROGRESS	 	TEXT,
		EAE_STATE_ID			TINYINT UNSIGNED NOT NULL,
		COLLEAGUES_SYNTHESIS 	TEXT,
		MANAGERS_SYNTHESIS 		TEXT,
		OTHER 					TEXT,
		SALARY 					INTEGER UNSIGNED
   );
   
   
ALTER TABLE OBJECTIVE ADD CONSTRAINT OBJECTIVEFK3 FOREIGN KEY (EAE_ID)
	  REFERENCES EAE (EAE_ID) ;

-- TABLESPACE tmp00dta;

-- COMMENT ON TABLE EAE IS 'Table des EAE';

