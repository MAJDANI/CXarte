

ALTER TABLE MISSION_TOOL ADD CONSTRAINT MISSION_TOOL_FK1 FOREIGN KEY (MISSION_ID) REFERENCES MISSION (MISSION_ID) ON DELETE CASCADE;
ALTER TABLE MISSION_TOOL ADD CONSTRAINT MISSION_TOOL_FK2 FOREIGN KEY (TOOL_ID) REFERENCES TOOL (TOOL_ID) ON DELETE CASCADE;
COMMIT;

