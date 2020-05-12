DROP USER bicycle_shop_app CASCADE;

-- CREATE USER TRMS
--     IDENTIFIED BY p4ssw0rd
--     DEFAULT TABLESPACE users
--     TEMPORARY TABLESPACE temp
--     QUOTA 10 M ON users;
--
-- GRANT connect to TRMS;
-- GRANT resource to TRMS;
-- GRANT create session TO TRMS;
-- GRANT create table TO TRMS;
-- GRANT create view TO TRMS;


conn TRMS/p4ssw0rd;


create table DEPARTMENTS
(
    ID   NUMBER generated as identity,
    NAME VARCHAR2(40) not null
)
/

create table EMPLOYEES
(
    ID         NUMBER generated as identity,
    USER_NAME  VARCHAR2(45) not null,
    FIRST_NAME VARCHAR2(40) not null,
    LAST_NAME  VARCHAR2(40) not null,
    PASSWORD   VARCHAR2(80) not null,
    DEPARTMENT NUMBER       not null,
    ROLE       VARCHAR2(20)
)
/

create table DEPT_MANAGER
(
    ID NUMBER generated as identity
        constraint DEPARTMENT_ID
            references TRMS.DEPARTMENTS
                on delete cascade
        constraint MANAGER_ID
            references TRMS.EMPLOYEES
                on delete set null
)
/

-- Alter

create unique index DEPARTMENTS_ID_UINDEX
    on DEPARTMENTS (ID)
/

create unique index DEPARTMENTS_NAME_UINDEX
    on DEPARTMENTS (NAME)
/

alter table DEPARTMENTS
    add constraint DEPARTMENTS_PK
        primary key (ID)
/


create unique index EMPLOYEES_ID_UINDEX
    on EMPLOYEES (ID)
/

create unique index EMPLOYEES_USER_NAME_UINDEX
    on EMPLOYEES (USER_NAME)
/

alter table EMPLOYEES
    add constraint EMPLOYEES_PK
        primary key (ID)
/

alter table EMPLOYEES
    add constraint department
        foreign key (ID) references DEPARTMENTS
            on delete set null
/

alter table EMPLOYEES
    add constraint supervisor_id
        foreign key (ID) references EMPLOYEES
/


create unique index DEPT_MANAGER_ID_UINDEX
    on TRMS.DEPT_MANAGER (ID)
/

alter table DEPT_MANAGER
    add constraint DEPT_MANAGER_PK
        primary key (ID)
/


