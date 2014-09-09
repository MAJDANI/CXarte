@echo off
cls

SET host="localhost"
SET rootuser="root"
SET rootpassword="password"
SET dbname="talent_map"
SET dbuser="talentmapuser"
SET dbuserpassword="talentmapuser"

cd db
FOR %%f IN (*) DO mysql -h %host% -u %rootuser% -p%rootpassword% %dbname% < %%f

cd ..\tables
FOR %%f IN (*) DO mysql -h %host% -u %dbuser% -p%dbuserpassword% %dbname% < %%f

cd ..\views
FOR %%f IN (*) DO mysql -h %host% -u %dbuser% -p%dbuserpassword% %dbname% < %%f

cd ..\indexes
FOR %%f IN (*) DO mysql -h %host% -u %dbuser% -p%dbuserpassword% %dbname% < %%f

cd ..\constraints\foreign_key
FOR %%f IN (*) DO mysql -h %host% -u %dbuser% -p%dbuserpassword% %dbname% < %%f

cd ..\..\datas
FOR %%f IN (*) DO mysql -h %host% -u %dbuser% -p%dbuserpassword% %dbname% < %%f

cd ..