-- Task 1
-- Write SQL statements in this file to create the brewery database and 
-- populate the database with the given SQL files

SET GLOBAL local_infile=true;

CREATE DATABASE brewery;

USE brewery;

SOURCE C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/beers.sql;
SOURCE C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/breweries.sql;
SOURCE C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/categories.sql;
SOURCE C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/geocodes.sql;
SOURCE C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/styles.sql;