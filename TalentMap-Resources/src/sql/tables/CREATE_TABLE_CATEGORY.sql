--------------------
-- TABLE CREATION --
--------------------
DROP TABLE CATEGORY CASCADE CONSTRAINTS;

CREATE TABLE CATEGORY
(
	CATEGORY_ID     NUMBER(3,0) NOT NULL,
	CATEGORY_NAME   VARCHAR2(20) NOT NULL
)
TABLESPACE tmp00dta;

COMMENT ON TABLE CATEGORY IS 'Table des Catégories';

-----------------------
-- SEQUENCE CREATION --
-----------------------
DROP SEQUENCE SEQCATEGORY;

CREATE SEQUENCE SEQCATEGORY MINVALUE 1 MAXVALUE 999999 INCREMENT BY 1 START WITH 1 NOCACHE;

COMMIT;