-------------
-- PROFILE --
-------------
ALTER TABLE PROFILE ADD CONSTRAINT PROFILEPK PRIMARY KEY (PROFILE_ID) USING INDEX TABLESPACE tmp00idx;
COMMIT;

-------------
-- MANAGER --
-------------
ALTER TABLE MANAGER ADD CONSTRAINT MANAGERPK PRIMARY KEY (MANAGER_ID) USING INDEX TABLESPACE tmp00idx;
COMMIT;
ALTER TABLE MANAGER ADD CONSTRAINT MANAGERFK1 FOREIGN KEY (PROFILE_ID) REFERENCES PROFILE (PROFILE_ID);
COMMIT;

---------------
-- COLLEAGUE --
---------------
ALTER TABLE COLLEAGUE ADD CONSTRAINT COLLEAGUE1 PRIMARY KEY (COLLEAGUE_ID) USING INDEX TABLESPACE tmp00idx;
COMMIT;
ALTER TABLE COLLEAGUE ADD CONSTRAINT COLLEAGUEFK1 FOREIGN KEY (PROFILE_ID) REFERENCES PROFILE (PROFILE_ID);
ALTER TABLE COLLEAGUE ADD CONSTRAINT COLLEAGUEFK2 FOREIGN KEY (MANAGER_ID) REFERENCES MANAGER (MANAGER_ID);
COMMIT;

---------------
-- USER_ROLE --
---------------
ALTER TABLE USER_ROLE ADD CONSTRAINT USER_ROLEPK PRIMARY KEY (ROLE_ID) USING INDEX TABLESPACE tmp00idx;
COMMIT;

--------------------
-- AUTHENTICATION --
--------------------
ALTER TABLE AUTHENTICATION ADD CONSTRAINT AUTHENTICATIONPK PRIMARY KEY (AUTHENTICATION_ID) USING INDEX TABLESPACE tmp00idx;
COMMIT;
ALTER TABLE AUTHENTICATION ADD CONSTRAINT AUTHENTICATIONFK1 FOREIGN KEY (COLLEAGUE_ID) REFERENCES COLLEAGUE (COLLEAGUE_ID);
ALTER TABLE AUTHENTICATION ADD CONSTRAINT AUTHENTICATIONFK2 FOREIGN KEY (ROLE_ID) REFERENCES USER_ROLE (ROLE_ID);
COMMIT;

--------------
-- CATEGORY --
--------------
ALTER TABLE CATEGORY ADD CONSTRAINT CATEGORYPK PRIMARY KEY (CATEGORY_ID) USING INDEX TABLESPACE tmp00idx;
COMMIT;

--------------
-- CLIENT --
--------------
ALTER TABLE CLIENT ADD CONSTRAINT CLIENTPK PRIMARY KEY (CLIENT_ID) USING INDEX TABLESPACE tmp00idx;
COMMIT;

-------------
-- CONCEPT --
-------------
ALTER TABLE CONCEPT ADD CONSTRAINT CONCEPTPK PRIMARY KEY (CONCEPT_ID) USING INDEX TABLESPACE tmp00idx;
COMMIT;
ALTER TABLE CONCEPT ADD CONSTRAINT CONCEPTFK FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY (CATEGORY_ID);
COMMIT;

----------
-- TOOL --
----------
ALTER TABLE TOOL ADD CONSTRAINT TOOLPK PRIMARY KEY (TOOL_ID) USING INDEX TABLESPACE tmp00idx;
COMMIT;
ALTER TABLE TOOL ADD CONSTRAINT TOOLFK1 FOREIGN KEY (CONCEPT_ID) REFERENCES CONCEPT (CONCEPT_ID) ON DELETE CASCADE;
COMMIT;

-------------
-- MISSION --
-------------
ALTER TABLE MISSION ADD CONSTRAINT MISSIONPK PRIMARY KEY (MISSION_ID) USING INDEX TABLESPACE tmp00idx;
COMMIT;
ALTER TABLE MISSION ADD CONSTRAINT MISSIONFK1 FOREIGN KEY (COLLEAGUE_ID) REFERENCES COLLEAGUE (COLLEAGUE_ID) ON DELETE CASCADE;
COMMIT;
ALTER TABLE MISSION ADD CONSTRAINT MISSIONFK2 FOREIGN KEY (CLIENT_ID) REFERENCES CLIENT (CLIENT_ID) ON DELETE CASCADE;
COMMIT;

---------------
-- OBJECTIVE --
---------------
ALTER TABLE OBJECTIVE ADD CONSTRAINT OBJECTIVEPK PRIMARY KEY (OBJECTIVE_ID) USING INDEX TABLESPACE tmp00idx;
COMMIT;

----------
-- TASK --
----------
ALTER TABLE TASK ADD CONSTRAINT TASKPK PRIMARY KEY (TASK_ID) USING INDEX TABLESPACE tmp00idx;
COMMIT;

-----------
-- SKILL --
-----------
ALTER TABLE SKILL ADD CONSTRAINT SKILLFK1 FOREIGN KEY (COLLEAGUE_ID) REFERENCES COLLEAGUE (COLLEAGUE_ID);
ALTER TABLE SKILL ADD CONSTRAINT SKILLFK2 FOREIGN KEY (TOOL_ID)  REFERENCES TOOL (TOOL_ID);
COMMIT;

--------------------
-- OBJECTIVE_TASK --
--------------------
ALTER TABLE OBJECTIVE_TASK ADD CONSTRAINT OBJECTIVETASKFK1 FOREIGN KEY (OBJECTIVE_ID) REFERENCES OBJECTIVE (OBJECTIVE_ID);
ALTER TABLE OBJECTIVE_TASK ADD CONSTRAINT OBJECTIVETASKFK2 FOREIGN KEY (TASK_ID) REFERENCES TASK (TASK_ID);
COMMIT;

---------------------------
-- COLLEAGUE_MANAGER_OBJ --
---------------------------
ALTER TABLE COLLEAGUE_MANAGER_OBJ ADD CONSTRAINT COLLEAGUE_MANAGER_OBJFK1 FOREIGN KEY (COLLEAGUE_ID) REFERENCES COLLEAGUE (COLLEAGUE_ID);
ALTER TABLE COLLEAGUE_MANAGER_OBJ ADD CONSTRAINT COLLEAGUE_MANAGER_OBJFK2 FOREIGN KEY (MANAGER_ID) REFERENCES MANAGER (MANAGER_ID);
ALTER TABLE COLLEAGUE_MANAGER_OBJ ADD CONSTRAINT COLLEAGUE_MANAGER_OBJFK3 FOREIGN KEY (OBJECTIVE_ID) REFERENCES OBJECTIVE (OBJECTIVE_ID);
COMMIT;