-------------------
-- TABLE DES CMs --
-------------------

---------------------------
-- PRIMARY KEYS CREATION --
---------------------------
ALTER TABLE CMANAGER ADD CONSTRAINT CMANAGERPK PRIMARY KEY (CMANAGER_ID) USING INDEX TABLESPACE TMP00IDX1;

---------------------------
-- FOREIGN KEYS CREATION --
---------------------------
ALTER TABLE CMANAGER ADD CONSTRAINT CMANAGERFK1 PRIMARY KEY (TYP_PROFIL_ID) REFERENCES PROFILETYPE (TYP_PROFIL_ID);

