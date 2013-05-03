--------------------
-- TABLE FREQUENCY--
--------------------

DROP TABLE FREQUENCY CASCADE CONSTRAINTS;

CREATE TABLE FREQUENCY
   (	
   		FREQUENCY_ID NUMBER(2,0) NOT NULL, 
		FREQUENCY_NAME VARCHAR2(50 BYTE) NOT NULL
   )
TABLESPACE tmp00dta;
   
COMMENT ON TABLE FREQUENCY IS 'Table de reférence des fréquences de mails';