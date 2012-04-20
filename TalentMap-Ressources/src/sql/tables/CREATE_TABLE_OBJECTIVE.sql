--------------------------
-- TABLE DES OBJECTIFS  --
--------------------------

--------------------
-- TABLE CREATION --
--------------------
DROP TABLE OBJECTIVE CASCADE CONSTRAINTS;
CREATE TABLE OBJECTIVE
(
	OBJECTIVE_ID  VARCHAR2(10) NOT NULL,
	TARGET_DATE   DATE
)
TABLESPACE tmp00dta;

COMMENT ON TABLE OBJECTIVE IS 'Tables des objectifs entretien annuel';

-----------------------
-- SEQUENCE CREATION --
-----------------------
CREATE SEQUENCE SEQOBJECTIVE MINVALUE 1 MAXVALUE 999999 INCREMENT BY 1 START WITH 1 NOCACHE;

---------------------------
-- PRIMARY KEYS CREATION --
---------------------------
ALTER TABLE OBJECTIVE ADD CONSTRAINT OBJECTIVEPK PRIMARY KEY (OBJECTIVE_ID) USING INDEX TABLESPACE TMP00IDX1;
