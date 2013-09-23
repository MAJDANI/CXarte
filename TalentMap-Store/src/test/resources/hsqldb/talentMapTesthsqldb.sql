CREATE TABLE USER_ROLE(
	ROLE_ID NUMERIC(3,0) NOT NULL, 
	LABEL VARCHAR(50) NOT NULL);

CREATE TABLE PROFILE (
	PROFILE_ID NUMERIC(3,0) NOT NULL,
	PROFILE_TYPE VARCHAR(20) NOT NULL);
	
CREATE SEQUENCE SEQPROFILE START WITH 1  INCREMENT BY 1;
	
	
CREATE TABLE BUSINESS_ENGINEER (
	B_ENGINEER_ID NUMERIC(10,0)  NOT NULL,
	FIRST_NAME VARCHAR(40) NOT NULL,
	LAST_NAME VARCHAR(40) NOT NULL,
	BUSINESS_UNIT VARCHAR(40) NOT NULL);
	
CREATE TABLE COLLEAGUE (
	COLLEAGUE_ID NUMERIC(10,0) NOT NULL,
	MANAGER_ID NUMERIC(10,0),
	PROFILE_ID NUMERIC(3,0) NOT NULL,
	LAST_NAME VARCHAR(30) NOT NULL,
	FIRST_NAME VARCHAR(30) NOT NULL, 
	EMAIL VARCHAR(50) NOT NULL ,
	PHONE VARCHAR(10),
	EMPLOYMENT_DATE DATE NOT NULL,
	EXPERIENCE NUMERIC(2,0) NOT NULL,
	B_ENGINEER_ID NUMERIC(10,0),
	TITLE VARCHAR(2),
	UNIQUE(EMAIL));
	
CREATE SEQUENCE SEQCOLLEAGUE START WITH 1  INCREMENT BY 1;
	
CREATE TABLE AUTHENTICATION (
	LOGIN VARCHAR(50) NOT NULL,
	PASSWORD VARCHAR(50) NOT NULL, 
	COLLEAGUE_ID NUMERIC(10,0) NOT NULL,
	ROLE_ID NUMERIC(3,0) NOT NULL);
	
CREATE TABLE CATEGORY (
	CATEGORY_ID     NUMERIC(6,0) NOT NULL,
	CATEGORY_NAME   VARCHAR(20) NOT NULL);	
	
CREATE SEQUENCE SEQCATEGORY START WITH 1 INCREMENT BY 1;
	
CREATE TABLE CONCEPT (
	CONCEPT_ID    NUMERIC(6,0)  NOT NULL,
	CATEGORY_ID   NUMERIC(6,0)  NOT NULL,
	CONCEPT_NAME  VARCHAR(20) NOT NULL);
	
CREATE SEQUENCE SEQCONCEPT	START WITH 1 INCREMENT BY 1;
	
CREATE TABLE TOOL (
	TOOL_ID         NUMERIC(6,0) NOT NULL,
	CONCEPT_ID      NUMERIC(6,0) NOT NULL,
	TOOL_NAME       VARCHAR(30) NOT NULL);

CREATE SEQUENCE SEQTOOL START WITH 1 INCREMENT BY 1;
	
CREATE TABLE CLIENT (
	CLIENT_ID     NUMERIC(10) NOT NULL,
	CLIENT_NAME   VARCHAR(30) NOT NULL);
	
CREATE SEQUENCE SEQCLIENT START WITH 1 INCREMENT BY 1; 
	
CREATE TABLE SKILL (
	COLLEAGUE_ID  	NUMERIC(10) NOT NULL,
	TOOL_ID    		NUMERIC(6) NOT NULL,
	USE_FREQUENCY   NUMERIC(1) NOT NULL,
	NO_USING_TIME   NUMERIC(1) NOT NULL,
	SCORE      		NUMERIC(1) NOT NULL);
	
CREATE SEQUENCE SEQSKILL START WITH 1 INCREMENT BY 1;	
	
CREATE TABLE MISSION (
	MISSION_ID     NUMERIC(10) NOT NULL,
	COLLEAGUE_ID   NUMERIC(10) NOT NULL,
	TITLE		   VARCHAR(60) NOT NULL,
	PLACE		   VARCHAR(60) NOT NULL,
	CLIENT_ID      NUMERIC(10) NOT NULL,
	NOTES		   VARCHAR(100),
	START_DATE     DATE NOT NULL,
	END_DATE       DATE);	

	
CREATE TABLE MISSION_TOOL(	
	MISSION_ID NUMERIC(10,0) NOT NULL, 
	TOOL_ID NUMERIC(6,0) NOT NULL);	
	
CREATE SEQUENCE SEQMISSION START WITH 1 INCREMENT BY 1; 
	

CREATE TABLE EAE( 
   	EAE_ID 					NUMERIC(10,0) NOT NULL, 
   	COLLEAGUE_ID 			NUMERIC(10,0) NOT NULL,
   	MANAGER_ID 				NUMERIC(10,0) NOT NULL,
   	EAE_DATE 				DATE NOT NULL,
   	PROFILE_LABEL 			VARCHAR(60),
   	PREVIOUS_EAE_ID 		NUMERIC(10,0),
	COLLEAGUES_STRENGTHS 	VARCHAR(1000),
	COLLEAGUES_WEAKNESSES 	VARCHAR(1000),
	EAE_STATE_ID			NUMERIC(1,0) NOT NULL,
	COLLEAGUES_SYNTHESIS 	VARCHAR(1000),
	MANAGERS_SYNTHESIS 		VARCHAR(1000),
	OTHER 					VARCHAR(500));
   
CREATE SEQUENCE SEQEAE START WITH 1 INCREMENT BY 1; 

CREATE TABLE EAE_STATE(	
	EAE_STATE_ID 		NUMERIC(1,0) NOT NULL, 
   	EAE_STATE_LABEL 	VARCHAR(60) NOT NULL);

CREATE TABLE OBJECTIVE_SCORE(	
	OBJECTIVE_SCORE_ID 		NUMERIC(1,0) NOT NULL, 
   	OBJECTIVE_SCORE_LABEL 	VARCHAR(60) NOT NULL);

CREATE TABLE OBJECTIVE(	
	OBJECTIVE_ID 			NUMERIC(10,0) NOT NULL, 
   	COLLEAGUE_ID 			NUMERIC(10,0) NOT NULL,
   	MANAGER_ID 				NUMERIC(10,0) NOT NULL,
   	EAE_ID 					NUMERIC(10,0) NOT NULL,
   	TITLE 					VARCHAR(60) NOT NULL,
   	GOAL 					VARCHAR(200) NOT NULL,
	TARGET_DATE 			DATE,
	INDICATORS 				VARCHAR(100),
	MEANS 					VARCHAR(100),
	COL_OBJ_SCORE_ID		NUMERIC(1,0),
	MAN_OBJ_SCORE_ID 		NUMERIC(1,0),
	MOTIVES_OR_RESTRAINTS 	VARCHAR(100));


ALTER TABLE	USER_ROLE ADD CONSTRAINT USER_ROLEPK PRIMARY KEY (ROLE_ID);
ALTER TABLE PROFILE ADD CONSTRAINT PROFILEPK PRIMARY KEY (PROFILE_ID);
ALTER TABLE BUSINESS_ENGINEER ADD CONSTRAINT BUSINESS_ENGINEERPK PRIMARY KEY (B_ENGINEER_ID);
ALTER TABLE COLLEAGUE ADD CONSTRAINT COLLEAGUE1 PRIMARY KEY (COLLEAGUE_ID);
ALTER TABLE COLLEAGUE ADD CONSTRAINT COLLEAGUEFK1 FOREIGN KEY (PROFILE_ID) REFERENCES PROFILE (PROFILE_ID);
ALTER TABLE COLLEAGUE ADD CONSTRAINT COLLEAGUEFK3 FOREIGN KEY (B_ENGINEER_ID) REFERENCES BUSINESS_ENGINEER (B_ENGINEER_ID);
ALTER TABLE AUTHENTICATION ADD CONSTRAINT AUTHENTICATIONPK PRIMARY KEY (LOGIN);
ALTER TABLE AUTHENTICATION ADD CONSTRAINT AUTHENTICATIONFK1 FOREIGN KEY (COLLEAGUE_ID) REFERENCES COLLEAGUE (COLLEAGUE_ID) ON DELETE CASCADE;
ALTER TABLE AUTHENTICATION ADD CONSTRAINT AUTHENTICATIONFK2 FOREIGN KEY (ROLE_ID) REFERENCES USER_ROLE (ROLE_ID);

ALTER TABLE CATEGORY ADD CONSTRAINT CATEGORYPK PRIMARY KEY (CATEGORY_ID);
ALTER TABLE CONCEPT ADD CONSTRAINT CONCEPTPK PRIMARY KEY (CONCEPT_ID);
ALTER TABLE CONCEPT ADD CONSTRAINT CONCEPTFK FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY (CATEGORY_ID) ON DELETE CASCADE;
ALTER TABLE TOOL ADD CONSTRAINT TOOLPK PRIMARY KEY (TOOL_ID);
ALTER TABLE TOOL ADD CONSTRAINT TOOLFK1 FOREIGN KEY (CONCEPT_ID) REFERENCES CONCEPT (CONCEPT_ID) ON DELETE CASCADE;
ALTER TABLE CLIENT ADD CONSTRAINT CLIENTPK PRIMARY KEY (CLIENT_ID);
ALTER TABLE SKILL ADD CONSTRAINT SKILLPK PRIMARY KEY (COLLEAGUE_ID,TOOL_ID);
ALTER TABLE SKILL ADD CONSTRAINT SKILLFK1 FOREIGN KEY (COLLEAGUE_ID) REFERENCES COLLEAGUE (COLLEAGUE_ID) ON DELETE CASCADE;
ALTER TABLE SKILL ADD CONSTRAINT SKILLFK2 FOREIGN KEY (TOOL_ID)  REFERENCES TOOL (TOOL_ID);
ALTER TABLE MISSION ADD CONSTRAINT MISSIONPK PRIMARY KEY (MISSION_ID);
ALTER TABLE MISSION ADD CONSTRAINT MISSIONFK1 FOREIGN KEY (COLLEAGUE_ID) REFERENCES COLLEAGUE (COLLEAGUE_ID) ON DELETE CASCADE;
ALTER TABLE MISSION ADD CONSTRAINT MISSIONFK2 FOREIGN KEY (CLIENT_ID) REFERENCES CLIENT (CLIENT_ID) ON DELETE CASCADE;

ALTER TABLE MISSION_TOOL ADD CONSTRAINT  MISSION_TOOL_PK PRIMARY KEY (MISSION_ID, TOOL_ID);
ALTER TABLE MISSION_TOOL ADD CONSTRAINT MISSION_TOOL_FK1 FOREIGN KEY (MISSION_ID) REFERENCES MISSION (MISSION_ID) ON DELETE CASCADE;
ALTER TABLE MISSION_TOOL ADD CONSTRAINT MISSION_TOOL_FK2 FOREIGN KEY (TOOL_ID) REFERENCES TOOL (TOOL_ID) ON DELETE CASCADE;

ALTER TABLE EAE ADD CONSTRAINT EAE_PK PRIMARY KEY (EAE_ID);
ALTER TABLE EAE_STATE ADD CONSTRAINT EAE_STATE_PK PRIMARY KEY (EAE_STATE_ID);

ALTER TABLE EAE ADD CONSTRAINT EAEFK1 FOREIGN KEY (COLLEAGUE_ID) REFERENCES COLLEAGUE (COLLEAGUE_ID) ON DELETE CASCADE ;
ALTER TABLE EAE ADD CONSTRAINT EAEFK2 FOREIGN KEY (MANAGER_ID) REFERENCES COLLEAGUE (COLLEAGUE_ID) ;
ALTER TABLE EAE ADD CONSTRAINT EAEFK3 FOREIGN KEY (PREVIOUS_EAE_ID) REFERENCES EAE (EAE_ID) ;
ALTER TABLE EAE ADD CONSTRAINT EAEFK4 FOREIGN KEY (EAE_STATE_ID) REFERENCES EAE_STATE (EAE_STATE_ID) ;

ALTER TABLE OBJECTIVE_SCORE ADD CONSTRAINT OBJECTIVE_SCORE_PK PRIMARY KEY (OBJECTIVE_SCORE_ID);

ALTER TABLE OBJECTIVE ADD CONSTRAINT OBJECTIVE_PK PRIMARY KEY (OBJECTIVE_ID);
ALTER TABLE OBJECTIVE ADD CONSTRAINT OBJECTIVEFK1 FOREIGN KEY (COLLEAGUE_ID) REFERENCES COLLEAGUE (COLLEAGUE_ID) ON DELETE CASCADE ;
ALTER TABLE OBJECTIVE ADD CONSTRAINT OBJECTIVEFK2 FOREIGN KEY (MANAGER_ID) REFERENCES COLLEAGUE (COLLEAGUE_ID) ;
ALTER TABLE OBJECTIVE ADD CONSTRAINT OBJECTIVEFK3 FOREIGN KEY (EAE_ID) REFERENCES EAE (EAE_ID) ;
ALTER TABLE OBJECTIVE ADD CONSTRAINT OBJECTIVEFK4 FOREIGN KEY (COL_OBJ_SCORE_ID) REFERENCES OBJECTIVE_SCORE (OBJECTIVE_SCORE_ID) ;
ALTER TABLE OBJECTIVE ADD CONSTRAINT OBJECTIVEFK5 FOREIGN KEY (MAN_OBJ_SCORE_ID) REFERENCES OBJECTIVE_SCORE (OBJECTIVE_SCORE_ID) ;


