---------------------------
-- PRIMARY KEYS CREATION --
---------------------------
ALTER TABLE MISSION ADD CONSTRAINT MISSIONPK PRIMARY KEY (MISSION_ID) USING INDEX TABLESPACE tmptestidx;

COMMIT;

