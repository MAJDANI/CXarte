---------------------------
--    DATA INSERTION     --
---------------------------
SET FOREIGN_KEY_CHECKS = 0;

Insert into EAE (EAE_ID,COLLEAGUE_ID,MANAGER_ID, EAE_DATE, EAE_STATE_ID) values (1,63,62,'2013-06-25',1);
Insert into EAE (EAE_ID,COLLEAGUE_ID,MANAGER_ID, EAE_DATE, EAE_STATE_ID) values (2,64,62,'2013-06-24',2);
Insert into EAE (EAE_ID,COLLEAGUE_ID,MANAGER_ID, EAE_DATE, EAE_STATE_ID) values (3,65,62,'2013-05-25',3);


COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

