---------------------------
-- FOREIGN KEYS CREATION --
---------------------------
ALTER TABLE COLLEAGUE_MANAGER_OBJ ADD CONSTRAINT COLLEAGUE_MANAGER_OBJFK1 FOREIGN KEY (COLLEAGUE_ID) REFERENCES COLLEAGUE (COLLEAGUE_ID);
ALTER TABLE COLLEAGUE_MANAGER_OBJ ADD CONSTRAINT COLLEAGUE_MANAGER_OBJFK2 FOREIGN KEY (MANAGER_ID) REFERENCES MANAGER (MANAGER_ID);
ALTER TABLE COLLEAGUE_MANAGER_OBJ ADD CONSTRAINT COLLEAGUE_MANAGER_OBJFK3 FOREIGN KEY (OBJECTIVE_ID) REFERENCES OBJECTIVE (OBJECTIVE_ID);

COMMIT;