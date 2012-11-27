---------------------------
-- PRIMARY KEYS CREATION --
---------------------------
ALTER TABLE TASK ADD CONSTRAINT TASKPK PRIMARY KEY (TASK_ID) USING INDEX TABLESPACE tmptestidx;

COMMIT;