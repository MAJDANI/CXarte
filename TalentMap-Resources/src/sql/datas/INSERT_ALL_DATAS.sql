---------------------------
--    CATEGORY     --
---------------------------
INSERT INTO CATEGORY (CATEGORY_ID, CATEGORY_NAME)
VALUES (1,'Java EE');
INSERT INTO CATEGORY (CATEGORY_ID, CATEGORY_NAME)
VALUES (2,'.NET');
INSERT INTO CATEGORY (CATEGORY_ID, CATEGORY_NAME)
VALUES (3,'PHP');
INSERT INTO CATEGORY (CATEGORY_ID, CATEGORY_NAME)
VALUES (4,'C#');
INSERT INTO CATEGORY (CATEGORY_ID, CATEGORY_NAME)
VALUES (5,'SGBD');
INSERT INTO CATEGORY (CATEGORY_ID, CATEGORY_NAME)
VALUES (6,'Conception');
INSERT INTO CATEGORY (CATEGORY_ID, CATEGORY_NAME)
VALUES (7,'Qualité');
INSERT INTO CATEGORY (CATEGORY_ID, CATEGORY_NAME)
VALUES (8,'AMOA');
INSERT INTO CATEGORY (CATEGORY_ID, CATEGORY_NAME)
VALUES (9,'PMO');


---------------------------
--    CONCEPT    --
---------------------------
INSERT INTO CONCEPT (CONCEPT_ID, CATEGORY_ID, CONCEPT_NAME)
VALUES (1,1,'IOC');
INSERT INTO CONCEPT (CONCEPT_ID, CATEGORY_ID, CONCEPT_NAME)
VALUES (2,1,'ORM');
INSERT INTO CONCEPT (CONCEPT_ID, CATEGORY_ID, CONCEPT_NAME)
VALUES (3,2,'IOC');
INSERT INTO CONCEPT (CONCEPT_ID, CATEGORY_ID, CONCEPT_NAME)
VALUES (4,2,'ORM');
INSERT INTO CONCEPT (CONCEPT_ID, CATEGORY_ID, CONCEPT_NAME)
VALUES (5,3,'ORM');
INSERT INTO CONCEPT (CONCEPT_ID, CATEGORY_ID, CONCEPT_NAME)
VALUES (6,3,'IOC');
INSERT INTO CONCEPT (CONCEPT_ID, CATEGORY_ID, CONCEPT_NAME)
VALUES (7,4,'IOC');
INSERT INTO CONCEPT (CONCEPT_ID, CATEGORY_ID, CONCEPT_NAME)
VALUES (8,4,'ORM');
INSERT INTO CONCEPT (CONCEPT_ID, CATEGORY_ID, CONCEPT_NAME)
VALUES (9,5,'SGBD');
INSERT INTO CONCEPT (CONCEPT_ID, CATEGORY_ID, CONCEPT_NAME)
VALUES (10,1,'Tests Unitaires');
INSERT INTO CONCEPT (CONCEPT_ID, CATEGORY_ID, CONCEPT_NAME)
VALUES (11,2,'Tests Unitaires');
INSERT INTO CONCEPT (CONCEPT_ID, CATEGORY_ID, CONCEPT_NAME)
VALUES (12,6,'Conception');
INSERT INTO CONCEPT (CONCEPT_ID, CATEGORY_ID, CONCEPT_NAME)
VALUES (13,7,'Qualité');
INSERT INTO CONCEPT (CONCEPT_ID, CATEGORY_ID, CONCEPT_NAME)
VALUES (14,8,'Suivi');
INSERT INTO CONCEPT (CONCEPT_ID, CATEGORY_ID, CONCEPT_NAME)
VALUES (15,8,'Gestion');
INSERT INTO CONCEPT (CONCEPT_ID, CATEGORY_ID, CONCEPT_NAME)
VALUES (16,9,'Suivi');
INSERT INTO CONCEPT (CONCEPT_ID, CATEGORY_ID, CONCEPT_NAME)
VALUES (17,9,'Gestion');

---------------------------
--    PROFILE     --
---------------------------
INSERT INTO PROFILE (PROFILE_ID, PROFILE_TYPE)
VALUES (1,'Développeur');
INSERT INTO PROFILE (PROFILE_ID, PROFILE_TYPE)
VALUES (2,'Fonctionnel');
INSERT INTO PROFILE (PROFILE_ID, PROFILE_TYPE)
VALUES (3,'Chef de Projet');
INSERT INTO PROFILE (PROFILE_ID, PROFILE_TYPE)
VALUES (4,'Architecte Technique');
INSERT INTO PROFILE (PROFILE_ID, PROFILE_TYPE)
VALUES (5,'Architecte Fonct.');
INSERT INTO PROFILE (PROFILE_ID, PROFILE_TYPE)
VALUES (6,'Testeur');

---------------------------
--    MANAGER     --
---------------------------
INSERT INTO MANAGER (MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (1,6,'MARIE-SAINTE','Jean-Max','j.marie-sainte@novediagroup.com',102030405,'23/04/2010',7,'Julie VIGNERON');
INSERT INTO MANAGER (MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (2,2,'GERME','Kelly','k.germe@novediagroup.com',102030405,'23/04/2010',11,'Renaud MIGNE');
INSERT INTO MANAGER (MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (3,1,'BONVOISIN','Sébastien','s.bonvoisin@novediagroup.com',102030405,'23/04/2010',8,'Deborah ATTIAS');
INSERT INTO MANAGER (MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (4,4,'BRULATOUT','Eric','e.brulatout@novediagroup.com',102030405,'23/04/2010',9,'Renaud MIGNE');
INSERT INTO MANAGER (MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (5,1,'ACKERMANN','Bruno','b.ackermann@novediagroup.com',102030405,'23/04/2010',12,'Renaud MIGNE');
INSERT INTO MANAGER (MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (6,6,'DANTO','Fanny','f.danto@novedia-solutions.com',102030405,'23/04/2010',5,'Julie VIGNERON');
INSERT INTO MANAGER (MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (7,1,'DEJONGHE','Xavier','x.dejonghe@novedia-solutions.com',102030405,'23/04/2010',6,'Julie VIGNERON');

---------------------------
--    COLLABORATOR     --
---------------------------
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (1,1,5,'COLLET','Julien','j.collet@novedia-solution.com',102030405,'23/04/2010',1,'Renaud MIGNE');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (2,1,1,'MOUMBE','Elisé','e.moumbe@novedia-solution.com',102030415,'23/04/2010',2,'Julie VIGNERON');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (3,3,4,'GUILLEMAIN','Vanessa','v.guillemain@novedia-solution.com',102030425,'23/04/2010',3,'Déborah ATTIAS');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (4,3,5,'CHAUVIE','Olivier','o.chauvie@novedia-solution.com',102030435,'23/04/2010',8,'Renaud MIGNE');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (5,4,5,'BRULATOUT','Eric','e.brulatout@novedia-solution.com',102030445,'23/04/2010',7,'Julie VIGNERON');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (6,2,1,'CAUMARTIN','Régine','r.caumartin@novedia-solution.com',102030455,'23/04/2010',6,'Déborah ATTIAS');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (7,2,5,'ANGLADE','Mathias','m.anglade@novedia-solution.com',102030465,'23/04/2010',2,'Renaud MIGNE');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (8,2,6,'THAI','Shien-Haur','s.thai@novedia-solution.com',102030475,'23/04/2010',4,'Julie VIGNERON');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (9,4,5,'EYCHENNE','Patrick','p.eychenne@novedia-solution.com',102030485,'23/04/2010',6,'Déborah ATTIAS');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (10,5,6,'ROBIN','Murielle','m.robin@novedia-solution.com',102030495,'23/04/2010',5,'Renaud MIGNE');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (11,5,3,'RAVINE','Sandrine','s.ravine@novedia-solution.com',102030505,'23/04/2010',7,'Julie VIGNERON');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (12,6,6,'LOET','Sylvie','s.loet@novedia-solution.com',102030515,'23/04/2010',9,'Renaud MIGNE');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (13,1,1,'BERTON','Alexandre ','a.berton@novedia-solutions.com',102030525,'23/04/2010',1,'Renaud MIGNE');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (14,1,4,'BOUDICH','Karim','k.boudich@novedia-solutions.com',102030535,'23/04/2010',2,'Julie VIGNERON');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (15,3,2,'CHEVALIER','Isabelle','i.chevalier@novedia-solutions.com',102030545,'23/04/2010',3,'Déborah ATTIAS');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (16,5,4,'GALOT','Anne-lise','al.galot@novedia-solutions.com',102030555,'23/04/2010',8,'Renaud MIGNE');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (17,4,2,'FAUQUEMBERGUE','Eric','e.fauquembergue@novedia-solutions.com',102030565,'23/04/2010',7,'Julie VIGNERON');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (18,1,6,'GERMOND','Sébastien','s.germond@novedia-solutions.com',102030575,'23/04/2010',6,'Déborah ATTIAS');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (19,7,6,'GRELIER','Vincent','v.grelier@novedia-solutions.com',102030585,'23/04/2010',2,'Julie VIGNERON');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (20,3,1,'LE GUENNEC','Julien','j.leguennec@novedia-solutions.com',102030595,'23/04/2010',4,'Déborah ATTIAS');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (21,4,3,'ANDRIET','Louis','l.andriet@novedia-solutions.com',102030605,'23/04/2010',6,'Renaud MIGNE');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (22,3,5,'AVRIL','Thierry','t.avril@novedia-solutions.com',102030615,'23/04/2010',5,'Julie VIGNERON');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (23,1,1,'BONNEAU','Johan','j.bonneau@novedia-solutions.com',102030625,'23/04/2010',7,'Déborah ATTIAS');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (24,7,3,'BALFIN','Guillaume','g.balfin@novedia-agency.com',102030635,'23/04/2010',9,'Renaud MIGNE');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (25,3,2,'BOUADJIE','Paule','p.bouadjie@novedia-solutions.com',102030645,'23/04/2010',1,'Julie VIGNERON');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (26,4,6,'BELMQADEM','Houda','h.belmqadem@novedia-decision.com',102030655,'23/04/2010',2,'Renaud MIGNE');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (27,7,5,'HENRY','Mickaël','m.henry@novedia-solutions.com',102030665,'23/04/2010',3,'Renaud MIGNE');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (28,3,4,'DEROUICH','Amina','a.derouich@novedia-decision.com',102030675,'23/04/2010',8,'Julie VIGNERON');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (29,5,1,'DREUX','Hélène','h.dreux@novedia-solutions.com',102030685,'23/04/2010',7,'Déborah ATTIAS');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (30,6,2,'ALLIX','Erwan','e.allix@novedia-solutions.com',102030695,'23/04/2010',6,'Renaud MIGNE');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (31,2,1,'HUBERT','Jean-lou','j.hubert@novedia-solutions.com',102030705,'23/04/2010',2,'Julie VIGNERON');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (32,2,6,'PELLOT','Stéphane','s.pellot@novedia-solutions.com',102030715,'23/04/2010',4,'Renaud MIGNE');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (33,5,4,'MERLET','Sylvain','s.merlet@novedia-solutions.com',102030725,'23/04/2010',6,'Renaud MIGNE');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (34,6,1,'MAESTRACCI','Mirana','m.maestracci@novedia-solutions.com',102030735,'23/04/2010',5,'Julie VIGNERON');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (35,3,1,'DIBI','Véronique','v.dibi@novediagroup.com',102030745,'23/04/2010',3,'Déborah ATTIAS');
INSERT INTO COLLABORATOR (COLLAB_ID, MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) 
VALUES (36,5,3,'MARIE-SAINTE','Jean-Max','j.marie-sainte@novediagroup.com',102030755,'23/04/2010',3,'Déborah ATTIAS');

---------------------------
--    SKILL     --
---------------------------
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (11,22,2,5,1);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (14,42,3,1,3);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (14,53,2,5,4);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (8,37,3,2,5);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (13,57,1,5,4);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (14,62,2,3,2);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (10,45,2,4,5);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (20,18,1,3,1);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (29,26,1,3,3);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (32,3,2,2,4);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (20,42,2,1,4);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (2,46,3,1,1);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (2,5,3,5,3);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (19,20,3,3,3);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (23,20,3,2,5);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (7,21,2,2,2);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (28,56,2,5,4);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (2,5,1,3,2);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (16,41,1,2,4);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (2,32,1,4,2);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (3,25,1,3,4);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (32,1,2,5,5);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (7,48,1,2,2);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (3,37,1,1,1);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (3,34,3,2,5);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (28,58,3,4,2);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (28,38,2,4,3);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (12,27,3,4,3);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (11,47,2,1,3);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (3,35,3,4,3);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (19,21,2,4,5);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (27,35,2,4,4);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (17,12,3,4,4);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (17,10,3,2,2);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (10,26,2,2,5);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (20,18,2,5,3);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (5,21,2,1,4);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (10,48,1,5,3);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (24,46,3,4,5);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (7,34,2,1,5);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (28,40,3,2,2);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (32,9,3,3,2);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (10,40,2,4,3);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (9,50,2,2,5);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (6,4,2,2,1);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (12,20,1,5,1);
INSERT INTO SKILL (COLLAB_ID, TOOL_ID, USE_FREQUENCY, NO_USING_TIME, SCORE)
VALUES (33,59,2,1,5);

---------------------------
--    MISSION     --
---------------------------
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (1,20,'Moteur de recherche','Lille','Orange','','26/2/2008','24/12/2012');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (2,17,'Publications','Grenoble','Orange','','11/1/2005','1/1/2012');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (3,1,'Statistiques de pannes','New-York','Air France','','26/5/2006','13/5/2012');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (4,13,'Moteur de recherche','Boulogne','PSA','','25/4/2004','17/11/2009');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (5,17,'Site intranet','San Francisco','Orange','','2/6/2003','8/7/2010');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (6,6,'Recherche Et Developpement','Londres','Canal+','','10/11/2009','16/10/2012');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (7,19,'Moteur de recherche','Paris','Intent','','23/10/2004','26/5/2011');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (8,26,'Gestion des forces de vente','Grenoble','Orange','','3/2/2005','26/5/2011');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (9,25,'Site intranet','Belgique','Canal+','','8/11/2003','8/9/2010');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (10,33,'Statistiques de pannes','Belgique','Air France','','19/6/1999','2/10/2009');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (11,28,'Statistiques de ventes','San Francisco','Total','','5/12/2003','21/12/2009');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (12,6,'Base de collecte de votes','Grenoble','Air France','','8/7/2003','4/2/2011');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (13,28,'Gestion des forces de vente','Londres','Mattel','','26/11/2002','3/5/2009');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (14,32,'Site intranet','Nice','NavBoard','','13/12/1999','4/4/2009');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (15,10,'Application embarquée','Saint-Denis','Citroen','','21/9/1998','15/2/2012');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (16,34,'Statistiques de pannes','Marseille','PSA','','11/1/2003','12/7/2011');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (17,5,'Gestion des forces de vente','Londres','France Télécom','','17/8/2003','11/9/2010');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (18,6,'IHM de paramétrie','Londres','PSA','','4/5/2009','7/7/2010');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (19,15,'Application embarquée','Lyon','Citroen','','24/6/2008','12/4/2010');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (20,9,'Site intranet','Lyon','Hermès','','2/1/2000','24/12/2010');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (21,7,'Publications','Lyon','France Télécom','','3/5/2001','22/8/2009');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (22,11,'Publications','San Francisco','Total','','11/11/2003','23/3/2011');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (23,6,'Site intranet','Toulouse','Air France','','3/5/2004','26/9/2011');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (24,24,'IHM de paramétrie','Boulogne','NavBoard','','1/1/2005','7/12/2010');
INSERT INTO MISSION (MISSION_ID, COLLAB_ID, NAME, PLACE, CLIENT, NOTES, START_DATE, END_DATE)
VALUES (25,14,'Application embarquée','New-York','Air France','','20/9/2002','11/2/2011');

---------------------------
--    AUTHENTIFICATION   --
---------------------------
INSERT INTO AUTHENTIFICATION (AUTHENTIFICATION_ID, COLLAB_ID, LOGIN, PASSWORD)
VALUES (1,35,'v.dibi','v.dibi');
INSERT INTO AUTHENTIFICATION (AUTHENTIFICATION_ID, COLLAB_ID, LOGIN, PASSWORD)
VALUES (2,2,'e.moumbe','e.moumbe');
INSERT INTO AUTHENTIFICATION (AUTHENTIFICATION_ID, COLLAB_ID, LOGIN, PASSWORD)
VALUES (3,3,'v.guillemain','v.guillemain');
INSERT INTO AUTHENTIFICATION (AUTHENTIFICATION_ID, COLLAB_ID, LOGIN, PASSWORD)
VALUES (4,36,'j.ms','j.ms');

COMMIT;