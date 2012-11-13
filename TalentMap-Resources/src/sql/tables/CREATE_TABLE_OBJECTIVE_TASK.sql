--------------------
-- TABLE CREATION --
--------------------
DROP TABLE OBJECTIVE_TASK CASCADE CONSTRAINTS;

CREATE TABLE OBJECTIVE_TASK
(
	OBJECTIVE_ID  NUMBER(2) NOT NULL,
	TASK_ID       NUMBER(2) NOT NULL
)
TABLESPACE tmp00dta;

COMMENT ON TABLE OBJECTIVE_TASK IS 'Table de relation entre des objectifs et des tâches';

-----------------------
-- SEQUENCE CREATION --
-----------------------
DROP SEQUENCE SEQOBJECTIVE_TASK;

CREATE SEQUENCE SEQOBJECTIVE_TASK MINVALUE 1 MAXVALUE 999999 INCREMENT BY 1 START WITH 1 NOCACHE;

COMMIT;