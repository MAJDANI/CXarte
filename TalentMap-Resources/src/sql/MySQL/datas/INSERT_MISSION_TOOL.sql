---------------------------
--    DATA INSERTION     --
---------------------------
SET FOREIGN_KEY_CHECKS = 0;

Insert into MISSION_TOOL (MISSION_ID,TOOL_ID) values ('72','2');
Insert into MISSION_TOOL (MISSION_ID,TOOL_ID) values ('72','3');
Insert into MISSION_TOOL (MISSION_ID,TOOL_ID) values ('76','2');
Insert into MISSION_TOOL (MISSION_ID,TOOL_ID) values ('76','5');
Insert into MISSION_TOOL (MISSION_ID,TOOL_ID) values ('95','5');

COMMIT;

SET FOREIGN_KEY_CHECKS = 1;