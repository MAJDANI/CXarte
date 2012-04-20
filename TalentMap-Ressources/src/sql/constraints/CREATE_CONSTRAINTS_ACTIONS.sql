-----------------------
-- TABLE DES ACTIONS --
-----------------------

---------------------------
-- PRIMARY KEYS CREATION --
---------------------------
ALTER TABLE ACTIONS ADD CONSTRAINT ACTIONSPK PRIMARY KEY (ACTION_ID) USING INDEX TABLESPACE TMP00IDX1;
