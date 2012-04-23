---------------------------
-- FOREIGN KEYS CREATION --
---------------------------
ALTER TABLE COLLAB_MANAGER_OBJ ADD CONSTRAINT COLLAB_MANAGER_OBJFK1 FOREIGN KEY (COLLAB_ID) REFERENCES COLLABORATOR (COLLAB_ID);
ALTER TABLE COLLAB_MANAGER_OBJ ADD CONSTRAINT COLLAB_MANAGER_OBJFK2 FOREIGN KEY (MANAGER_ID) REFERENCES MANAGER (MANAGER_ID);
ALTER TABLE COLLAB_MANAGER_OBJ ADD CONSTRAINT COLLAB_MANAGER_OBJFK3 FOREIGN KEY (OBJECTIVE_ID) REFERENCES OBJECTIVE (OBJECTIVE_ID);

COMMIT;