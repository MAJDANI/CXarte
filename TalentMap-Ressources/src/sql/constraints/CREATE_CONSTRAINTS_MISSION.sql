-------------------------
-- TABLE DES MISSIONS  --
-------------------------

---------------------------
-- PRIMARY KEYS CREATION --
---------------------------
ALTER TABLE MISSION ADD CONSTRAINT MISSIONPK PRIMARY KEY (MISSION_ID) USING INDEX TABLESPACE TMP00IDX1;

---------------------------
-- FOREIGN KEYS CREATION --
---------------------------
ALTER TABLE SCORE ADD CONSTRAINT MISSIONFK1 FOREIGN KEY (COLLAB_ID) REFERENCES COLLABORATOR (COLLAB_ID);
