--------------------
-- TABLE CREATION --
--------------------
DROP TABLE TASK CASCADE CONSTRAINTS;

CREATE TABLE TASK
(
	TASK_ID      VARCHAR2(10) NOT NULL,
	NAME         VARCHAR2(10) NOT NULL,
	TASK_TYPE    VARCHAR2(10) NOT NULL,
	TARGET_DATE  DATE,
	PRIORITY     NUMBER(1),
	EFFORT       NUMBER(1)  
)
TABLESPACE tmp00dta;

COMMENT ON TABLE IS 'Table des tâches relative à l''atteinte d''un objectif';

-----------------------
-- SEQUENCE CREATION --
-----------------------
CREATE SEQUENCE SEQTASK MINVALUE 1 MAXVALUE 999999 INCREMENT BY 1 START WITH 1 NOCACHE;
