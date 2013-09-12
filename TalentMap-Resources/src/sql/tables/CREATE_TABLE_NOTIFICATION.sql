 --------------------
-- TABLE NOTIFICATION --
--------------------
 
CREATE TABLE NOTIFICATION 
   (	
   		COLLEAGUE_ID NUMBER(3,0) NOT NULL, 
		NOTE VARCHAR2(75 BYTE) NOT NULL, 
		DATE_NOTIFICATION DATE
   )
   
TABLESPACE tmp00dta;
   
COMMENT ON TABLE NOTIFICATION IS 'Table des notifications';