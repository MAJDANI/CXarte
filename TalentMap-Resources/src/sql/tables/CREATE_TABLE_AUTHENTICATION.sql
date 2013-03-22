--------------------
-- TABLE CREATION --
--------------------
DROP TABLE AUTHENTICATION CASCADE CONSTRAINTS;

CREATE TABLE AUTHENTICATION
(	
	LOGIN   VARCHAR2(50) 		   NOT NULL,
	PASSWORD   VARCHAR2(50)        NOT NULL,	
	COLLEAGUE_ID  NUMBER(3,0)      NOT NULL,
	ROLE_ID    NUMBER(1,0)         NOT NULL
)
TABLESPACE tmp00dta;

COMMENT ON TABLE AUTHENTICATION IS 'Table des authentifications';

COMMIT;