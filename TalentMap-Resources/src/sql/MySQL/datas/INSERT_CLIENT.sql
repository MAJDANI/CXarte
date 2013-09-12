---------------------------
--    DATA INSERTION     --
---------------------------
SET FOREIGN_KEY_CHECKS = 0;

Insert into CLIENT (CLIENT_ID,CLIENT_NAME) values ('1','Orange');
Insert into CLIENT (CLIENT_ID,CLIENT_NAME) values ('2','Air France');
Insert into CLIENT (CLIENT_ID,CLIENT_NAME) values ('3','PSA');
Insert into CLIENT (CLIENT_ID,CLIENT_NAME) values ('4','Canal+');
Insert into CLIENT (CLIENT_ID,CLIENT_NAME) values ('5','Intent');
Insert into CLIENT (CLIENT_ID,CLIENT_NAME) values ('6','Total');
Insert into CLIENT (CLIENT_ID,CLIENT_NAME) values ('7','Mattel');
Insert into CLIENT (CLIENT_ID,CLIENT_NAME) values ('8','NavBoard');
Insert into CLIENT (CLIENT_ID,CLIENT_NAME) values ('9','Citroen');
Insert into CLIENT (CLIENT_ID,CLIENT_NAME) values ('10','France Télécom');
Insert into CLIENT (CLIENT_ID,CLIENT_NAME) values ('11','Hermès');

COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

