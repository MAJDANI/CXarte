/**This script delete all data*/

---DISABLE CONSTRAINT
ALTER TABLE COLLAB_MANAGER_OBJ DISABLE CONSTRAINT COLLAB_MANAGER_OBJFK1;
ALTER TABLE COLLAB_MANAGER_OBJ DISABLE CONSTRAINT COLLAB_MANAGER_OBJFK2;
ALTER TABLE COLLAB_MANAGER_OBJ DISABLE CONSTRAINT COLLAB_MANAGER_OBJFK3;

ALTER TABLE COLLABORATOR DISABLE CONSTRAINT COLLABORATORFK1;
ALTER TABLE COLLABORATOR DISABLE CONSTRAINT COLLABORATORFK2;

ALTER TABLE CONCEPT DISABLE CONSTRAINT CONCEPTFK;

ALTER TABLE MANAGER DISABLE CONSTRAINT MANAGERFK1;

ALTER TABLE MISSION DISABLE CONSTRAINT MISSIONFK1;
ALTER TABLE MISSION DISABLE CONSTRAINT MISSIONFK2;

ALTER TABLE OBJECTIVE_TASK DISABLE CONSTRAINT OBJECTIVETASKFK1;
ALTER TABLE OBJECTIVE_TASK DISABLE CONSTRAINT OBJECTIVETASKFK2;

ALTER TABLE SKILL DISABLE CONSTRAINT SKILLFK1;
ALTER TABLE SKILL DISABLE CONSTRAINT SKILLFK2;

ALTER TABLE TOOL DISABLE CONSTRAINT TOOLFK1;

ALTER TABLE AUTHENTIFICATION DISABLE CONSTRAINT AUTHENTIFICATIONFK1;



----TRUNCATE
TRUNCATE TABLE OBJECTIVE_TASK REUSE STORAGE;
TRUNCATE TABLE TASK REUSE STORAGE;
TRUNCATE TABLE COLLAB_MANAGER_OBJ REUSE STORAGE;
TRUNCATE TABLE OBJECTIVE REUSE STORAGE;
TRUNCATE TABLE MISSION REUSE STORAGE;
TRUNCATE TABLE SKILL REUSE STORAGE;
TRUNCATE TABLE COLLABORATOR REUSE STORAGE;
TRUNCATE TABLE MANAGER REUSE STORAGE;
TRUNCATE TABLE PROFILE REUSE STORAGE;
TRUNCATE TABLE TOOL REUSE STORAGE;
TRUNCATE TABLE CONCEPT REUSE STORAGE;
TRUNCATE TABLE CATEGORY REUSE STORAGE;
TRUNCATE TABLE AUTHENTIFICATION REUSE STORAGE;
TRUNCATE TABLE CLIENT REUSE STORAGE;
COMMIT;


---TODO: Reactivate CONSTRAINT