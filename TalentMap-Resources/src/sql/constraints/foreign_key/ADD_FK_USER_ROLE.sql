---------------------------
-- FOREIGN KEYS CREATION --
---------------------------
ALTER TABLE USER_ROLE ADD CONSTRAINT USER_ROLEFK1 FOREIGN KEY (ROLE_ID) REFERENCES AUTHENTICATION (ROLE_ID);

COMMIT;