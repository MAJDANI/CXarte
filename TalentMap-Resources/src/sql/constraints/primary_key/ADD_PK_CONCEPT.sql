---------------------------
-- PRIMARY KEYS CREATION --
---------------------------
ALTER TABLE CONCEPT ADD CONSTRAINT CONCEPTPK PRIMARY KEY (CONCEPT_ID) USING INDEX TABLESPACE tmp00idx;

COMMIT;
