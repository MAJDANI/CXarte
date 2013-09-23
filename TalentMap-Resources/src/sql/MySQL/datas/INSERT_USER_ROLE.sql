---------------------------
--    DATA INSERTION     --
---------------------------
SET FOREIGN_KEY_CHECKS = 0;

Insert into USER_ROLE (ROLE_ID,LABEL) values ('1','Ressources Humaines');
Insert into USER_ROLE (ROLE_ID,LABEL) values ('2','Consultant Manager');
Insert into USER_ROLE (ROLE_ID,LABEL) values ('3','Ingénieur d''affaires');
Insert into USER_ROLE (ROLE_ID,LABEL) values ('4','Consultant');
Insert into USER_ROLE (ROLE_ID,LABEL) values ('5','Administrateur');

SET FOREIGN_KEY_CHECKS = 1;