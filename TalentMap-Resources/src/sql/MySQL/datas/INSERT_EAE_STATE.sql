---------------------------
--    DATA INSERTION     --
---------------------------
SET FOREIGN_KEY_CHECKS = 0;

Insert into EAE_STATE (EAE_STATE_ID,EAE_STATE_LABEL) values ('1','OPEN');
Insert into EAE_STATE (EAE_STATE_ID,EAE_STATE_LABEL) values ('2','VALIDATED');
Insert into EAE_STATE (EAE_STATE_ID,EAE_STATE_LABEL) values ('3','CLOSED');


COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
