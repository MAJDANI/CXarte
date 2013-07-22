--------------------
-- VIEW CREATION --
--------------------  

CREATE OR REPLACE VIEW VIEW1 (CATEGORY_ID, CATEGORY_NAME, CONCEPT_ID, CONCEPT_NAME, TOOL_ID, TOOL_NAME) AS 
  SELECT cy.CATEGORY_ID, cy.CATEGORY_NAME, 
            ct.CONCEPT_ID, ct.CONCEPT_NAME, 
            tl.TOOL_ID, tl.TOOL_NAME
  FROM CATEGORY cy, CONCEPT ct, TOOL tl
  WHERE tl.concept_id = ct.concept_id
  AND cy.category_id = ct.category_id;