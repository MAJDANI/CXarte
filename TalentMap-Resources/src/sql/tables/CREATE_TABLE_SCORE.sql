--------------------
-- TABLE CREATION --
--------------------
DROP TABLE SCORE_TOOL_COLLAB CASCADE CONSTRAINTS;

CREATE TABLE SCORE_TOOL_COLLAB
(
	COLLAB_ID  VARCHAR2(10) NOT NULL,
	TOOL_ID    VARCHAR2(10) NOT NULL,
	SCORE      NUMBER(1)
)
TABLESPACE tmp00dta;

COMMENT ON TABLE IS 'Table des notes par outil et par collaborateur';

-----------------------
-- SEQUENCE CREATION --
-----------------------
CREATE SEQUENCE SEQSCORE_TOOL_COLLAB MINVALUE 1 MAXVALUE 999999 INCREMENT BY 1 START WITH 1 NOCACHE;
