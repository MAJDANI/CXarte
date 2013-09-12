---------------------------
--    DATA INSERTION     --
---------------------------
SET FOREIGN_KEY_CHECKS = 0;

Insert into MANAGER (MANAGER_ID,PROFILE_ID,LAST_NAME,FIRST_NAME,EMAIL,PHONE,EMPLOYMENT_DATE,EXPERIENCE,BUSINESS_ENGINEER) values ('1','6','MARIE-SAINTE','Jean-Max','j.marie-sainte@novediagroup.com','102030405',str_to_date('23/04/10','%d/%m/%Y'),'7','Julie VIGNERON');
Insert into MANAGER (MANAGER_ID,PROFILE_ID,LAST_NAME,FIRST_NAME,EMAIL,PHONE,EMPLOYMENT_DATE,EXPERIENCE,BUSINESS_ENGINEER) values ('2','2','GERME','Kelly','k.germe@novediagroup.com','102030405',str_to_date('23/04/10','%d/%m/%Y'),'11','Renaud MIGNE');
Insert into MANAGER (MANAGER_ID,PROFILE_ID,LAST_NAME,FIRST_NAME,EMAIL,PHONE,EMPLOYMENT_DATE,EXPERIENCE,BUSINESS_ENGINEER) values ('3','1','BONVOISIN','Sébastien','s.bonvoisin@novediagroup.com','102030405',str_to_date('23/04/10','%d/%m/%Y'),'8','Deborah ATTIAS');
Insert into MANAGER (MANAGER_ID,PROFILE_ID,LAST_NAME,FIRST_NAME,EMAIL,PHONE,EMPLOYMENT_DATE,EXPERIENCE,BUSINESS_ENGINEER) values ('4','4','BRULATOUT','Eric','e.brulatout@novediagroup.com','102030405',str_to_date('23/04/10','%d/%m/%Y'),'9','Renaud MIGNE');
Insert into MANAGER (MANAGER_ID,PROFILE_ID,LAST_NAME,FIRST_NAME,EMAIL,PHONE,EMPLOYMENT_DATE,EXPERIENCE,BUSINESS_ENGINEER) values ('5','1','ACKERMANN','Bruno','b.ackermann@novediagroup.com','102030405',str_to_date('23/04/10','%d/%m/%Y'),'12','Renaud MIGNE');
Insert into MANAGER (MANAGER_ID,PROFILE_ID,LAST_NAME,FIRST_NAME,EMAIL,PHONE,EMPLOYMENT_DATE,EXPERIENCE,BUSINESS_ENGINEER) values ('6','6','DANTO','Fanny','f.danto@novedia-solutions.com','102030405',str_to_date('23/04/10','%d/%m/%Y'),'5','Julie VIGNERON');
Insert into MANAGER (MANAGER_ID,PROFILE_ID,LAST_NAME,FIRST_NAME,EMAIL,PHONE,EMPLOYMENT_DATE,EXPERIENCE,BUSINESS_ENGINEER) values ('7','1','DEJONGHE','Xavier','x.dejonghe@novedia-solutions.com','102030405',str_to_date('23/04/10','%d/%m/%Y'),'6','Julie VIGNERON');

COMMIT;

SET FOREIGN_KEY_CHECKS = 0;


