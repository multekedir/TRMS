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
    ROLE       NUMBER,
    SUPERVISOR NUMBER,
    constraint DEPARTMENT
        foreign key (DEPARTMENT) references DEPARTMENTS
            on delete set null,
    constraint ROLE_ID
        foreign key (ROLE) references ROLE
            on delete set null,
    constraint SUPERVISOR_ID
        foreign key (SUPERVISOR) references EMPLOYEES
)
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

create table TRMS.ROLE
(
    ID        NUMBER generated as identity,
    ROLE_NAME VARCHAR2(20) not null
)
/


-- Alter

create unique index DEPARTMENTS_ID_UINDEX
    on TRMS.DEPARTMENTS (ID)
/

create unique index DEPARTMENTS_NAME_UINDEX
    on TRMS.DEPARTMENTS (NAME)
/

alter table DEPARTMENTS
    add constraint DEPARTMENTS_PK
        primary key (ID)
/

create unique index TRMS.EMPLOYEES_ID_UINDEX
    on TRMS.EMPLOYEES (ID)
/

create unique index TRMS.EMPLOYEES_USER_NAME_UINDEX
    on TRMS.EMPLOYEES (USER_NAME)
/

alter table EMPLOYEES
    add constraint EMPLOYEES_PK
        primary key (ID)
/


create unique index DEPT_MANAGER_ID_UINDEX
    on DEPT_MANAGER (ID)
/

alter table DEPT_MANAGER
    add constraint DEPT_MANAGER_PK
        primary key (ID)
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

