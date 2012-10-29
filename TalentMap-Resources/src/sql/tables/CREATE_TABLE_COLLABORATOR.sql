--------------------
-- TABLE CREATION --
--------------------
DROP TABLE COLLABORATOR CASCADE CONSTRAINTS;

CREATE TABLE COLLABORATOR
(
	COLLAB_ID    	  NUMBER(3,0)    NOT NULL,
	MANAGER_ID   	  NUMBER(3,0)    NOT NULL,
	PROFILE_ID    	  NUMBER(1,0)    NOT NULL,
	LAST_NAME    	  VARCHAR2(30)   NOT NULL,
	FIRST_NAME   	  VARCHAR2(30)   NOT NULL, 
	EMAIL        	  VARCHAR2(50)   NOT NULL,
	PHONE        	  NUMBER(10,0),
	EMPLOYMENT_DATE   DATE           NOT NULL,
	EXPERIENCE        NUMBER(2,0)    NOT NULL,
	BUSINESS_ENGINEER VARCHAR2(40)
)
TABLESPACE tmp00dta;

COMMENT ON TABLE COLLABORATOR IS 'Table des collaborateurs';

-----------------------
-- SEQUENCE CREATION --
-----------------------
DROP SEQUENCE SEQCOLLABORATOR;

CREATE SEQUENCE SEQCOLLABORATOR MINVALUE 1 MAXVALUE 999999 INCREMENT BY 1 START WITH 1 NOCACHE;

COMMIT;
