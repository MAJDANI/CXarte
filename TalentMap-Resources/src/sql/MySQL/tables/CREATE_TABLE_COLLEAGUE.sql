--------------------
-- TABLE CREATION --
--------------------
DROP TABLE IF EXISTS COLLEAGUE CASCADE ;

CREATE TABLE COLLEAGUE 
   (	COLLEAGUE_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	MANAGER_ID INT, 
	PROFILE_ID INT NOT NULL, 
	LAST_NAME VARCHAR(30) NOT NULL, 
	FIRST_NAME VARCHAR(30) NOT NULL, 
	EMAIL VARCHAR(50) NOT NULL, 
	PHONE VARCHAR(10), 
	EMPLOYMENT_DATE DATE NOT NULL, 
	EXPERIENCE INT NOT NULL, 
	B_ENGINEER_ID INT,
	IS_MANAGER int(1) default 0
   );
-- TABLESPACE tmp00dta;

-- COMMENT ON TABLE COLLEAGUE IS 'Table des collaborateurs';


