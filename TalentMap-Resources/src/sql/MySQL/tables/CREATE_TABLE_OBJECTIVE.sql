--------------------
-- TABLE CREATION --
--------------------
DROP TABLE IF EXISTS OBJECTIVE CASCADE;

CREATE TABLE OBJECTIVE 
   (	OBJECTIVE_ID 			INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
   		COLLEAGUE_ID 			INT NOT NULL,
   		MANAGER_ID 				INT NOT NULL,
   		EAE_ID 					INT NOT NULL,
   		TITLE 					VARCHAR(60) NOT NULL,
   		GOAL 					VARCHAR(200) NOT NULL,
		TARGET_DATE 			DATE,
		INDICATORS 				LONGTEXT,
		MEANS 					LONGTEXT,
		COL_OBJ_SCORE_ID		TINYINT UNSIGNED,
		MAN_OBJ_SCORE_ID 		TINYINT UNSIGNED,
		MOTIVES_OR_RESTRAINTS 	LONGTEXT
   );
-- TABLESPACE tmp00dta;

-- COMMENT ON TABLE OBJECTIVE IS 'Table des objectifs des EA';

