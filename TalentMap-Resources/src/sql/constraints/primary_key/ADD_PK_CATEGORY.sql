---------------------------
-- PRIMARY KEYS CREATION --
---------------------------
ALTER TABLE AUTHENTIFICATION ADD CONSTRAINT AUTHENTIFICATIONPK PRIMARY KEY (AUTHENTIFICATION_ID) USING INDEX TABLESPACE tmp00idx;

COMMIT;