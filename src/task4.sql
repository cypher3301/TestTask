-- Postgresql
select Day, round(coalesce(onlyCenceledStatuses, 0) / totalNumberStatuses, 2) as "Cancellation Rate"
from
     (select request_at as Day, cast(count(*) as numeric) as totalNumberStatuses
      from trips
          join users u on u.users_id = trips.client_id
      where banned = 'No'
      group by request_at) as allStatus
left join
    (select request_at as Day2, cast(count(*) as numeric) as onlyCenceledStatuses
    from trips
        join users u on u.users_id = trips.client_id
    where banned = 'No' and status = 'cancelled_by_client'
    group by request_at) as onlyCenceled
        ON Day2 = Day;
--
-- сначала из под запросов извлекаем дни и статусы, в одном под запросе агрегация под дням на все статусы во втором под запросе
-- агрегация на отклоненные статусы, забраненные пользователи не учитываются, после идет левое соеденение


--
-- create database davadev;
-- create table Trips(
--     id int primary key,
--     status varchar,
--     Client_Id int,
--     Driver_Id int,
--     Request_at date
-- );
-- create table Users(
--     Users_Id int primary key ,
--     Banned varchar,
--     Role varchar
-- );
-- alter table Trips add foreign key (Client_Id) references Users(Users_Id);
-- insert into users(users_id, banned, role)
-- VALUES (1, 'No', 'client'),
--        (2, 'Yes', 'client'),
--        (3, 'No', 'client'),
--        (4, 'No', 'client'),
--        (10, 'No', 'driver'),
--        (11, 'No', 'driver'),
--        (12, 'No', 'driver'),
--        (13, 'No', 'driver');
-- alter table trips
--     add column City_Id int;
-- insert into trips(id, client_id, driver_id, City_Id, status, request_at)
-- VALUES (1, 1, 10, 1, 'completed', '2013-10-01'),
--        (2, 2, 11, 1, 'cancelled_by_client', '2013-10-01'),
--        (3, 3, 12, 6, 'completed', '2013-10-01'),
--        (4, 4, 13, 6, 'cancelled_by_client', '2013-10-01'),
--        (5, 1, 10, 1, 'completed', '2013-10-02'),
--        (6, 2, 11, 6, 'completed', '2013-10-02'),
--        (7, 3, 12, 6, 'completed', '2013-10-02'),
--        (8, 2, 12, 12, 'completed', '2013-10-03'),
--        (9, 3, 10, 12, 'completed', '2013-10-03'),
--        (10, 4, 13, 12, 'cancelled_by_client', '2013-10-03');