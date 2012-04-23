-----------------------
-- TABLE DES OUTILS  --
-----------------------

---------------------------
-- PRIMARY KEYS CREATION --
---------------------------
ALTER TABLE TOOL ADD CONSTRAINT TOOLPK PRIMARY KEY (TOOL_ID) USING INDEX TABLESPACE TMP00IDX1;

---------------------------
-- FOREIGN KEYS CREATION --
---------------------------
ALTER TABLE SCORE ADD CONSTRAINT TOOLFK1 FOREIGN KEY (CONCEPT_ID) REFERENCES CONCEPT (CONCEPT_ID);