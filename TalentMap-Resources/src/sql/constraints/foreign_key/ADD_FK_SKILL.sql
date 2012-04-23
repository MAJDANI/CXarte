---------------------------
-- FOREIGN KEYS CREATION --
---------------------------
ALTER TABLE SKILL ADD CONSTRAINT SKILLFK1 FOREIGN KEY (COLLAB_ID) REFERENCES COLLABORATOR (COLLAB_ID);
ALTER TABLE SKILL ADD CONSTRAINT SKILLFK2 FOREIGN KEY (TOOL_ID)  REFERENCES TOOL (TOOL_ID);

COMMIT;
