------------------------
-- CREATION DES INDEX --
------------------------
DROP INDEX COLLABORATORIDX;
CREATE INDEX COLLABORATORIDX1 ON COLLABORATOR (COLLAB_ID) TABLESPACE tmptestidx;

DROP INDEX CATEGORYIDX;
CREATE INDEX CATEGORYIDX ON CATEGORY (CATEGORY_ID) TABLESPACE tmptestidx;

DROP INDEX CONCEPTIDX1;
CREATE INDEX CONCEPTIDX1 ON CONCEPT (CONCEPT_ID) TABLESPACE tmptestidx;

DROP INDEX CONCEPTIDX2;
CREATE INDEX CONCEPTIDX2 ON CONCEPT (CATEG_ID) TABLESPACE tmptestidx;

DROP INDEX TOOLIDX1;
CREATE INDEX TOOLIDX1 ON TOOL (TOOL_ID) TABLESPACE tmptestidx;

DROP INDEX TOOLIDX2;
CREATE INDEX TOOLIDX2 ON TOOL (CONCEPT_ID) TABLESPACE tmptestidx;

DROP INDEX SCOREIDX1;
CREATE INDEX SCOREIDX1 ON SCORE (COLLAB_ID) TABLESPACE tmptestidx;

DROP INDEX SCOREIDX2;
CREATE INDEX SCOREIDX2 ON SCORE (TOOL_ID) TABLESPACE tmptestidx;

DROP INDEX OBJECTIVEIDX;
CREATE INDEX OBJECTIVEIDX ON OBJECTIVE (OBJECTIVE_ID) TABLESPACE tmptestidx;

DROP INDEX MISSIONIDX1;
CREATE INDEX MISSIONIDX ON MISSION (MISSION_ID) TABLESPACE tmptestidx;

DROP INDEX MISSIONIDX2;
CREATE INDEX MISSIONIDX2 ON MISSION (COLLAB_ID) TABLESPACE tmptestidx;
