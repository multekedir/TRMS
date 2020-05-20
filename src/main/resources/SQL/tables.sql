DROP USER TRMS CASCADE;

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

create table ROLE
(
    ID        NUMBER generated as identity,
    ROLE_NAME VARCHAR2(20) not null
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

create table EMPLOYEES
(
    ID         NUMBER generated as identity,
    FIRST_NAME VARCHAR2(40) not null,
    LAST_NAME  VARCHAR2(40) not null,
    DEPARTMENT NUMBER default null,
    ROLE       NUMBER default null,
    USERNAME   VARCHAR2(40) not null,
    PASSWORD   VARCHAR2(60) not null,
    constraint DEPARTMENT
        foreign key (DEPARTMENT) references DEPARTMENTS
            on delete set null,
    constraint ROLE_ID
        foreign key (ROLE) references ROLE
            on delete set null
)
/

create unique index EMPLOYEES_ID_UINDEX
    on EMPLOYEES (ID)
/

create unique index EMPLOYEES_USERNAME_UINDEX
    on EMPLOYEES (USERNAME)
/

alter table EMPLOYEES
    add constraint EMPLOYEES_PK
        primary key (ID)
/

create table DEP_MANAGER
(
    DEPARTMENT NUMBER,
    MANAGER    NUMBER,
    ID         NUMBER generated as identity,
    constraint DEP_MANAGER_DEPARTMENTS_ID_FK
        foreign key (DEPARTMENT) references DEPARTMENTS,
    constraint DEP_MANAGER_EMPLOYEES_ID_FK
        foreign key (MANAGER) references EMPLOYEES
)
/

create unique index DEP_MANAGER_ID_UINDEX
    on DEP_MANAGER (ID)
/

alter table DEP_MANAGER
    add constraint DEP_MANAGER_PK
        primary key (ID)
/

create table ROLE_SUPER
(
    ID         NUMBER generated as identity,
    ROLE       NUMBER,
    SUPERVISOR NUMBER,
    constraint ROLE_SUPER_EMPLOYEES_ID_FK
        foreign key (SUPERVISOR) references EMPLOYEES,
    constraint ROLE_SUPER_ROLE_ID_FK
        foreign key (ROLE) references ROLE
)
/

create unique index ROLE_SUPER_ID_UINDEX
    on ROLE_SUPER (ID)
/

alter table ROLE_SUPER
    add constraint ROLE_SUPER_PK
        primary key (ID)
/

create table FORMS
(
    ID           NUMBER generated as identity,
    SUBMITTED_BY NUMBER                    not null,
    SUBMITTED_ON DATE default CURRENT_DATE not null,
    AMOUNT       NUMBER(6, 2)              not null,
    WAITING_FOR  VARCHAR2(20),
    DESCRIPTION  NUMBER,
    constraint FORMS_EMPLOYEES_ID_FK
        foreign key (SUBMITTED_BY) references EMPLOYEES
            on delete cascade
)
/

create unique index FORMS_ID_UINDEX
    on FORMS (ID)
/

