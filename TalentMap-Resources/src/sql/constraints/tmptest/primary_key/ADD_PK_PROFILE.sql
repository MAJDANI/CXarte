---------------------------
-- PRIMARY KEYS CREATION --
---------------------------
ALTER TABLE PROFILE ADD CONSTRAINT PROFILEPK PRIMARY KEY (PROFILE_ID) USING INDEX TABLESPACE tmptestidx;

COMMIT;

