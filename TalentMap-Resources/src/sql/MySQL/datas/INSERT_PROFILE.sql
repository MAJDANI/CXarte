---------------------------
--    DATA INSERTION     --
---------------------------
SET FOREIGN_KEY_CHECKS = 0;

Insert into PROFILE (PROFILE_ID,PROFILE_TYPE) values ('1','Développeur');
Insert into PROFILE (PROFILE_ID,PROFILE_TYPE) values ('2','Fonctionnel');
Insert into PROFILE (PROFILE_ID,PROFILE_TYPE) values ('3','Chef de Projet');
Insert into PROFILE (PROFILE_ID,PROFILE_TYPE) values ('4','Architecte Technique');
Insert into PROFILE (PROFILE_ID,PROFILE_TYPE) values ('5','Architecte Fonct.');
Insert into PROFILE (PROFILE_ID,PROFILE_TYPE) values ('6','Testeur');

COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

