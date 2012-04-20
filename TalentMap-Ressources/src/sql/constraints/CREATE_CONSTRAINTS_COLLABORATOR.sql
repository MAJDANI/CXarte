------------------------------
-- TABLE DES COLLABORATEURS --
------------------------------

---------------------------
-- PRIMARY KEYS CREATION --
---------------------------
ALTER TABLE COLLABORATOR ADD CONSTRAINT COLLABORATOR1 PRIMARY KEY (COLLAB_ID) USING INDEX TABLESPACE tmp00idx;

---------------------------
-- FOREIGN KEYS CREATION --
---------------------------
ALTER TABLE COLLABORATOR ADD CONSTRAINT COLLABORATORFK1 FOREIGN KEY (TYPE_PROFIL_ID) REFERENCES PROFILETYPE (TYPE_PROFIL_ID);
ALTER TABLE COLLABORATOR ADD CONSTRAINT COLLABORATORFK2 FOREIGN KEY (CMANAGER_ID) REFERENCES CMANAGER (CMANAGER_ID);
