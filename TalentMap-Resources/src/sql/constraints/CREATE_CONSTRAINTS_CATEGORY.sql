--------------------------
-- TABLE DES CATEGORIES --
--------------------------

---------------------------
-- PRIMARY KEYS CREATION --
---------------------------
ALTER TABLE CATEGORY ADD CONSTRAINT CATEGORYPK PRIMARY KEY (CATEGORY_ID) USING INDEX TABLESPACE TMP00IDX1;
