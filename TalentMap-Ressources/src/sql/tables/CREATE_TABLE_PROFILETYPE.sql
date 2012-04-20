-------------------------------
-- TABLE DES TYPE DE PROFILE --
-------------------------------

--------------------
-- TABLE CREATION --
--------------------
DROP TABLE PROFILETYPE CASCADE CONSTRAINTS;
CREATE TABLE PROFILETYPE
(
	TYP_PROFIL_ID   VARCHAR2(10) NOT NULL,
	PROFIL           VARCHAR2(20) 
)
TABLESPACE tmp00dta;

COMMENT ON TABLE PROFILETYPE IS 'Tables des types profil';

-----------------------
-- SEQUENCE CREATION --
-----------------------
CREATE SEQUENCE SEQTPROFTYP MINVALUE 1 MAXVALUE 999999 INCREMENT BY 1 START WITH 1 NOCACHE;

---------------------------
-- PRIMARY KEYS CREATION --
---------------------------
ALTER TABLE PROFILETYPE ADD CONSTRAINT PROFILETYPEPK PRIMARY KEY (TYPE_PROFIL_ID) USING INDEX TABLESPACE TMP00IDX1;
