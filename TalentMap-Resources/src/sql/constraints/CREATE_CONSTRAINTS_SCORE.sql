----------------------
-- TABLE DES NOTES  --
----------------------

---------------------------
-- FOREIGN KEYS CREATION --
---------------------------
ALTER TABLE SCORE ADD CONSTRAINT SCOREFK1 FOREIGN KEY (COLLAB_ID) REFERENCES COLLABORATOR (COLLAB_ID);
ALTER TABLE SCORE ADD CONSTRAINT SCOREFK2 FOREIGN KEY (TOOLS_ID)  REFERENCES TOOLS (COLLAB_ID);