--------------------
-- TABLE CREATION --
--------------------
DROP TABLE SKILL CASCADE CONSTRAINTS;

CREATE TABLE SKILL
(
	COLLEAGUE_ID  	NUMBER(3) NOT NULL,
	TOOL_ID    		NUMBER(2) NOT NULL,
	USE_FREQUENCY   NUMBER(1) NOT NULL,
	NO_USING_TIME   NUMBER(1) NOT NULL,
	SCORE      		NUMBER(1) NOT NULL
)
TABLESPACE tmp00dta;

COMMENT ON TABLE SKILL IS 'Table des compétences';

-----------------------
-- SEQUENCE CREATION --
-----------------------
DROP SEQUENCE SEQSKILL;

CREATE SEQUENCE SEQSKILL MINVALUE 1 MAXVALUE 999999 INCREMENT BY 1 START WITH 1 NOCACHE;

COMMIT;
