------------------------
-- TABLE MISSION_TOOL--
------------------------

DROP TABLE MISSION_TOOL;

CREATE TABLE MISSION_TOOL
(	MISSION_ID NUMBER(10,0) NOT NULL,  
	TOOL_ID NUMBER(6,0) NOT NULL
)
TABLESPACE tmp00dta;

COMMENT ON TABLE MISSION_TOOL IS 'Table associant les missions aux outils';