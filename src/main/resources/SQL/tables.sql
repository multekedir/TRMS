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

create table EMPLOYEES
(
    ID         NUMBER generated as identity,
    USER_NAME  VARCHAR2(45) not null,
    FIRST_NAME VARCHAR2(40) not null,
    LAST_NAME  VARCHAR2(40) not null,
    PASSWORD   VARCHAR2(80) not null,
    DEPARTMENT NUMBER       not null,
    ROLE       NUMBER,
    constraint DEPARTMENT
        foreign key (DEPARTMENT) references DEPARTMENTS
            on delete set null
)
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

create table DEPT_MANAGER
(
    ID            NUMBER generated as identity,
    DEPARTMENT_ID NUMBER,
    MANAGER_ID    NUMBER,
    constraint DEPARTMENT_ID
        foreign key (DEPARTMENT_ID) references DEPARTMENTS
            on delete cascade,
    constraint MANAGER_ID
        foreign key (MANAGER_ID) references EMPLOYEES
            on delete set null
)
/

create unique index DEPT_MANAGER_ID_UINDEX
    on DEPT_MANAGER (ID)
/

alter table DEPT_MANAGER
    add constraint DEPT_MANAGER_PK
        primary key (ID)
/

create table ROLE
(
    ID         NUMBER generated as identity,
    ROLE_NAME  VARCHAR2(20) not null,
    SUPERVISOR NUMBER       not null,
    constraint ROLE_EMPLOYEES_ID_FK
        foreign key (SUPERVISOR) references EMPLOYEES
)
/

create unique index ROLE_ID_UINDEX
    on ROLE (ID)
/

create unique index ROLE_ROLE_NAME_UINDEX
    on ROLE (ROLE_NAME)
/

alter table ROLE
    add constraint ROLE_PK
        primary key (ID)
/

alter table EMPLOYEES
    add constraint ROLE_ID
        foreign key (ROLE) references TRMS.ROLE
            on delete set null
/


