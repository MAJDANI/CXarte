--------------------
-- TABLE CREATION --
--------------------
DROP TABLE MISSION CASCADE CONSTRAINTS;

CREATE TABLE MISSION
(
	MISSION_ID     NUMBER(2) NOT NULL,
	COLLAB_ID      NUMBER(2) NOT NULL,
	CLIENT         VARCHAR2(30) NOT NULL,
	START_DATE     DATE NOT NULL,
	END_DATE       DATE
)
TABLESPACE tmp00dta;

COMMENT ON TABLE MISSION IS 'Table des missions';

-----------------------
-- SEQUENCE CREATION --
-----------------------
DROP SEQUENCE SEQMISSION;

CREATE SEQUENCE SEQMISSION MINVALUE 1 MAXVALUE 999999 INCREMENT BY 1 START WITH 1 NOCACHE;

COMMIT;