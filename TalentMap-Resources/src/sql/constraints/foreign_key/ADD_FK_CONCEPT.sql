---------------------------
-- FOREIGN KEYS CREATION --
---------------------------
ALTER TABLE CONCEPT ADD CONSTRAINT CONCEPTFK FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY (CATEGORY_ID);

COMMIT;
