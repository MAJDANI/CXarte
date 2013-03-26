---------------------------
-- FOREIGN KEYS CREATION --
---------------------------
ALTER TABLE SKILL ADD CONSTRAINT SKILLFK1 FOREIGN KEY (COLLEAGUE_ID)
	  REFERENCES COLLEAGUE (COLLEAGUE_ID) ON DELETE CASCADE ;
 
  ALTER TABLE SKILL ADD CONSTRAINT SKILLFK2 FOREIGN KEY (TOOL_ID)
	  REFERENCES TOOL (TOOL_ID) ;
	  
COMMIT;
