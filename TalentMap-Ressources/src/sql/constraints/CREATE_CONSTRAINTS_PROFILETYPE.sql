-------------------------------
-- TABLE DES TYPE DE PROFILE --
-------------------------------

---------------------------
-- PRIMARY KEYS CREATION --
---------------------------
ALTER TABLE PROFILETYPE ADD CONSTRAINT PROFILETYPEPK PRIMARY KEY (TYPE_PROFIL_ID) USING INDEX TABLESPACE TMP00IDX1;
