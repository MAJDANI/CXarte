---------------------------
-- PRIMARY KEYS CREATION --
---------------------------
ALTER TABLE USER_ROLE ADD CONSTRAINT USER_ROLEPK PRIMARY KEY (ROLE_ID) USING INDEX TABLESPACE tmp00idx;

COMMIT;