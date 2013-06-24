/**This script delete all data*/

-- DISABLE FK CONSTRAINTS

ALTER TABLE COLLEAGUE_MANAGER_OBJ DISABLE CONSTRAINT COLLEAGUE_MANAGER_OBJFK1;
ALTER TABLE COLLEAGUE_MANAGER_OBJ DISABLE CONSTRAINT COLLEAGUE_MANAGER_OBJFK2;
ALTER TABLE COLLEAGUE_MANAGER_OBJ DISABLE CONSTRAINT COLLEAGUE_MANAGER_OBJFK3;

ALTER TABLE COLLEAGUE DISABLE CONSTRAINT COLLEAGUEFK1;
ALTER TABLE COLLEAGUE DISABLE CONSTRAINT COLLEAGUEFK3;

ALTER TABLE CONCEPT DISABLE CONSTRAINT CONCEPTFK;


ALTER TABLE MISSION DISABLE CONSTRAINT MISSIONFK1;
ALTER TABLE MISSION DISABLE CONSTRAINT MISSIONFK2;

ALTER TABLE OBJECTIVE_TASK DISABLE CONSTRAINT OBJECTIVETASKFK1;
ALTER TABLE OBJECTIVE_TASK DISABLE CONSTRAINT OBJECTIVETASKFK2;

ALTER TABLE SKILL DISABLE CONSTRAINT SKILLFK1;
ALTER TABLE SKILL DISABLE CONSTRAINT SKILLFK2;

ALTER TABLE TOOL DISABLE CONSTRAINT TOOLFK1;

ALTER TABLE AUTHENTICATION DISABLE CONSTRAINT AUTHENTICATIONFK1;
ALTER TABLE AUTHENTICATION DISABLE CONSTRAINT AUTHENTICATIONFK2;


----TRUNCATE
TRUNCATE TABLE OBJECTIVE_TASK REUSE STORAGE;
TRUNCATE TABLE TASK REUSE STORAGE;
TRUNCATE TABLE COLLEAGUE_MANAGER_OBJ REUSE STORAGE;
TRUNCATE TABLE OBJECTIVE REUSE STORAGE;
TRUNCATE TABLE MISSION REUSE STORAGE;
TRUNCATE TABLE SKILL REUSE STORAGE;
TRUNCATE TABLE COLLEAGUE REUSE STORAGE;
TRUNCATE TABLE PROFILE REUSE STORAGE;
TRUNCATE TABLE TOOL REUSE STORAGE;
TRUNCATE TABLE CONCEPT REUSE STORAGE;
TRUNCATE TABLE CATEGORY REUSE STORAGE;
TRUNCATE TABLE AUTHENTICATION REUSE STORAGE;
TRUNCATE TABLE BUSINESS_ENGINEER REUSE STORAGE;
TRUNCATE TABLE CLIENT REUSE STORAGE;
COMMIT;


---TODO: Reactivate CONSTRAINT