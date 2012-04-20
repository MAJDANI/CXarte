--------------------------
-- TABLE DES OBJECTIFS  --
--------------------------

---------------------------
-- PRIMARY KEYS CREATION --
---------------------------
ALTER TABLE OBJECTIVE ADD CONSTRAINT OBJECTIVEPK PRIMARY KEY (OBJECTIVE_ID) USING INDEX TABLESPACE TMP00IDX1;
