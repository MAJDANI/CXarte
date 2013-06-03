---------------------------
-- FOREIGN KEYS CREATION --
---------------------------
ALTER TABLE EAE ADD CONSTRAINT EAEFK1 FOREIGN KEY (COLLEAGUE_ID)
	  REFERENCES COLLEAGUE (COLLEAGUE_ID) ON DELETE CASCADE ;
 
ALTER TABLE EAE ADD CONSTRAINT EAEFK2 FOREIGN KEY (MANAGER_ID)
	  REFERENCES COLLEAGUE (COLLEAGUE_ID) ;
 
ALTER TABLE EAE ADD CONSTRAINT EAEFK3 FOREIGN KEY (PREVIOUS_EAE_ID)
	  REFERENCES EAE (EAE_ID) ;

ALTER TABLE EAE ADD CONSTRAINT EAEFK4 FOREIGN KEY (EAE_STATE_ID)
	  REFERENCES EAE_STATE (EAE_STATE_ID) ;

COMMIT;

