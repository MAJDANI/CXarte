BEGIN
  FOR i IN 1..1 LOOP
  insert into MANAGER (MANAGER_ID, PROFILE_ID, LAST_NAME, FIRST_NAME, EMAIL, PHONE, EMPLOYMENT_DATE, EXPERIENCE, BUSINESS_ENGINEER) values (i,i,'Marie-Sainte','Jean-Max','j.marie-sainte@novediagroup.com',0102030405,'23/04/2012',8,'test');
  END LOOP;
END;