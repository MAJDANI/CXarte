---------------------------
-- PRIMARY KEYS CREATION --
---------------------------
ALTER TABLE TOOL ADD CONSTRAINT TOOLPK PRIMARY KEY (TOOL_ID) USING INDEX TABLESPACE tmptestidx;

COMMIT;