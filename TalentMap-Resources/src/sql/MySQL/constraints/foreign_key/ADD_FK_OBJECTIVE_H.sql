---------------------------
-- FOREIGN KEYS CREATION --
---------------------------
ALTER TABLE OBJECTIVE_H ADD CONSTRAINT OBJECTIVE_HFK1 FOREIGN KEY (COLLEAGUE_ID)
	  REFERENCES COLLEAGUE_H (COLLEAGUE_ID) ON DELETE CASCADE ;
 
ALTER TABLE OBJECTIVE_H ADD CONSTRAINT OBJECTIVE_HFK2 FOREIGN KEY (MANAGER_ID)
	  REFERENCES COLLEAGUE (COLLEAGUE_ID) ;
 
ALTER TABLE OBJECTIVE_H ADD CONSTRAINT OBJECTIVE_HFK3 FOREIGN KEY (EAE_ID)
	  REFERENCES EAE_H (EAE_ID) ;
	  
COMMIT;