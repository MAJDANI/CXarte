--------------------
-- TABLE CREATION --
--------------------
DROP TABLE COLLEAGUE CASCADE CONSTRAINTS;

CREATE TABLE COLLEAGUE
(
	COLLEAGUE_ID      NUMBER(10,0)    NOT NULL,
	MANAGER_ID   	  NUMBER(10,0),
	PROFILE_ID    	  NUMBER(3,0)    NOT NULL,
	LAST_NAME    	  VARCHAR2(30)   NOT NULL,
	FIRST_NAME   	  VARCHAR2(30)   NOT NULL, 
	EMAIL        	  VARCHAR2(50)   NOT NULL UNIQUE,
	PHONE        	  VARCHAR2(10),
	EMPLOYMENT_DATE   DATE           NOT NULL,
	EXPERIENCE        NUMBER(2,0)    NOT NULL,
	B_ENGINEER_ID	  NUMBER(10,0)    ,
	TITLE  VARCHAR2(2)
)
TABLESPACE tmp00dta;

COMMENT ON TABLE COLLEAGUE IS 'Table des collaborateurs';

-----------------------
-- SEQUENCE CREATION --
-----------------------
DROP SEQUENCE SEQCOLLEAGUE;

CREATE SEQUENCE SEQCOLLEAGUE MINVALUE 1 MAXVALUE 999999 INCREMENT BY 1 START WITH 1 NOCACHE;

COMMIT;
