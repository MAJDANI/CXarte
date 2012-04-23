----------------------------------------------------------------------
-- TABLE DE LIAISON DES MANAGER DES COLLABORATEURS ET DES OBJECTIFS --
----------------------------------------------------------------------

--------------------
-- TABLE CREATION --
--------------------
DROP TABLE COLLAB_MANAGER_OBJ CASCADE CONSTRAINTS;

CREATE TABLE COLLAB_MANAGER_OBJ
(
	COLLAB_ID      VARCHAR2(10) NOT NULL,
	MANAGER_ID   VARCHAR2(10) NOT NULL,
	OBJ_EA_ID      VARCHAR2(10) NOT NULL,
	EA_DATE        DATE,
	EA_DOC         BLOB
)
TABLESPACE tmp00dta;

COMMENT ON TABLE IS 'Table de relation entre un CM, un collaborateur et un EA';

-----------------------
-- SEQUENCE CREATION --
-----------------------
CREATE SEQUENCE SEQCOLLAB_MANAGER_OBJ MINVALUE 1 MAXVALUE 999999 INCREMENT BY 1 START WITH 1 NOCACHE;
