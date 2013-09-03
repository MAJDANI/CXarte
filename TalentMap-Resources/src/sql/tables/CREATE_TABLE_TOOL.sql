--------------------
-- TABLE CREATION --
--------------------
DROP TABLE TOOL CASCADE CONSTRAINTS;

CREATE TABLE TOOL
(
	TOOL_ID         NUMBER(6,0) NOT NULL,
	CONCEPT_ID      NUMBER(6,0) NOT NULL,
	TOOL_NAME       VARCHAR2(30) NOT NULL
)
TABLESPACE tmp00dta;

COMMENT ON TABLE TOOL IS 'Table des outils';

-----------------------
-- SEQUENCE CREATION --
-----------------------
DROP SEQUENCE SEQTOOL;

CREATE SEQUENCE SEQTOOL MINVALUE 1 MAXVALUE 999999 INCREMENT BY 1 START WITH 1 NOCACHE;

COMMIT;