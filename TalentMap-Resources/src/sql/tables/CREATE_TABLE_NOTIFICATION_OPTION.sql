--------------------
-- TABLE NOTIFICATION_OPTION --
-------------------- 
  
CREATE TABLE NOTIFICATION_OPTION
   (	
   		COLLEAGUE_ID NUMBER(3,0) NOT NULL, 
		FREQUENCY_ID NUMBER(2,0)
   )
      
TABLESPACE tmp00dta;
   
COMMENT ON TABLE NOTIFICATION_OPTION IS 'Table des options de notifications';