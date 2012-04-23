----------------------------------------------------------------------
-- TABLE DE LIAISON DES MANAGER DES COLLABORATEURS ET DES OBJECTIFS --
----------------------------------------------------------------------

--------------------
-- TABLE CREATION --
--------------------
DROP TABLE PROFILE CASCADE CONSTRAINTS;

CREATE TABLE PROFILE
(
	PROFILE_ID      VARCHAR2(10) NOT NULL,
	PROFILE_TYPE    VARCHAR2(20) NOT NULL
)
TABLESPACE tmp00dta;

COMMENT ON TABLE IS 'Table des types de profil';

-----------------------
-- SEQUENCE CREATION --
-----------------------
CREATE SEQUENCE SEQPROFILE MINVALUE 1 MAXVALUE 999999 INCREMENT BY 1 START WITH 1 NOCACHE;
