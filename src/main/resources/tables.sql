
create table submittedform
(
    -- column_name, data_type, modifiers/constraints
    day_of_submisson   date,
    time_of_submission time,
    user_location      varchar2(30),
    cost_of_event      number(10),
    grading_format     foreign key,
    typeEvent          varchar2(30),
    justification      varchar2(60),
    approval           num(10),
    submitted by

);

create table employee{
first_name varchar2(30)
last_name varchar2(30)
}