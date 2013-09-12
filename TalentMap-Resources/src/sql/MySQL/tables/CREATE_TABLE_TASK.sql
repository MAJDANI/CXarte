--------------------
-- TABLE CREATION --
--------------------
DROP TABLE IF EXISTS TASK CASCADE;

 CREATE TABLE TASK 
   (	TASK_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	NAME VARCHAR(10) NOT NULL, 
	TASK_TYPE VARCHAR(10) NOT NULL, 
	TARGET_DATE DATE, 
	PRIORITY INT, 
	EFFORT INT
   );
-- TABLESPACE tmp00dta;

-- COMMENT ON TABLE TASK IS 'Table des tâches relative à l''atteinte d''un objectif';

