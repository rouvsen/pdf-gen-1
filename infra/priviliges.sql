-- CREATE USER demo_user IDENTIFIED BY "secret";
-- ALTER USER demo_user DEFAULT TABLESPACE USERS;
-- GRANT CREATE SESSION TO demo_user;
-- GRANT CREATE TABLE, CREATE SEQUENCE, CREATE VIEW, CREATE PROCEDURE TO demo_user;
-- If requires => ALTER USER demo_user QUOTA UNLIMITED ON USERS;

-- connect via SYS:
docker exec -it db_pdf_gen_1 sqlplus sys/oracle@XE as sysdba

-- generate User:
CREATE USER demo_user IDENTIFIED BY secret;
GRANT CONNECT, RESOURCE TO demo_user;
GRANT CREATE SESSION TO demo_user;
GRANT UNLIMITED TABLESPACE TO demo_user;
EXIT;