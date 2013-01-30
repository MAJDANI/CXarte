---------------------------
-- FOREIGN KEYS CREATION --
---------------------------
ALTER TABLE MISSION ADD CONSTRAINT MISSIONFK1 FOREIGN KEY (COLLEAGUE_ID) REFERENCES COLLEAGUE (COLLEAGUE_ID) ON DELETE CASCADE;
ALTER TABLE MISSION ADD CONSTRAINT MISSIONFK2 FOREIGN KEY (CLIENT_ID) REFERENCES CLIENT (CLIENT_ID) ON DELETE CASCADE;

COMMIT;
