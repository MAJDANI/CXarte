--------------------
-- TABLE CREATION --
--------------------
DROP TABLE IF EXISTS OBJECTIVE_TASK CASCADE ;

CREATE TABLE OBJECTIVE_TASK 
   (	OBJECTIVE_ID INT NOT NULL, 
	TASK_ID INT NOT NULL
   );
-- TABLESPACE tmp00dta;

-- COMMENT ON TABLE OBJECTIVE_TASK IS 'Table de relation entre des objectifs et des tâches';

