---------------------------
-- PRIMARY KEYS CREATION --
---------------------------
ALTER TABLE COLLABORATOR ADD CONSTRAINT COLLABORATOR1 PRIMARY KEY (COLLAB_ID) USING INDEX TABLESPACE tmp00idx;

COMMIT;