---------------------------
-- FOREIGN KEYS CREATION --
---------------------------
ALTER TABLE MISSION ADD CONSTRAINT MISSIONFK PRIMARY KEY (COLLAB_ID) REFERENCES COLLABORATOR (COLLAB_ID);

COMMIT;
