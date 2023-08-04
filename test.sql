-- 1. Вывести имена всех пассажиров, которые ни разу не пользовались услугами авиакомпаний

-- 2. Вывести количество пассажиров, которые летели из Воронежа в Москву или обратно.   
  SELECT COUNT(*) AS количество_пассажиров FROM  Passenger 
JOIN Pass_in_trip ON Pass_in_trip.passenger=Passenger.id
JOIN Trip ON Trip.id=Pass_in_trip.trip 
WHERE  (town_from='Воронеж' AND  town_to ='Москва') OR  (town_from='Москва' AND  town_to ='Воронеж')

-- 3. Вывести имена всех пассажиров и количество перелетов. (примерно 1 минуту )  
  SELECT name, COUNT(*) AS 'количество перелетов' FROM  Passenger
  GROUP BY name 
-- 4. Вывести рейтинг авиакомпаний по количеству совершенных рейсов, которые совершили более 5 рейсов.
  SELECT name, COUNT(*) as count
FROM Company 
JOIN Trip on Trip.company  = Company.id 
GROUP BY name
HAVING COUNT(*) > 5
-- 5.	Вывести топ 3 самых продолжительных рейсов, совершенных в мае 2020.
SELECT  * AS flight_time
FROM Trip
WHERE YEAR(time_out)=2020  AND  MONTH(time_out) = 5
ORDER BY TIMEDIFF(time_in, time_out) DESC 
LIMIT 3
-- 6.	Вывести имена всех пар пассажиров, летевших вместе на одном рейсе два или более раз и количество таких совместных рейсов.

SELECT name1, name2, COUNT(trip1) AS 'количество таких совместных рейсов'
FROM (SELECT Passenger.id AS passenger1, name AS name1, trip AS trip1 FROM Passenger 
    INNER JOIN Pass_in_trip ON Passenger.id = Pass_in_trip.passenger 
    GROUP BY Passenger.id, name, trip) AS UP1 
    INNER JOIN (
        SELECT Passenger.id AS passenger2, name AS name2, trip AS trip2 
        FROM Passenger 
        INNER JOIN Pass_in_trip ON Passenger.id = Pass_in_trip.passenger
        GROUP BY Passenger.id, name, trip) AS UP2 
    ON UP1.trip1 = UP2.trip2
WHERE (passenger1<passenger2) GROUP BY name1, name2 
HAVING (COUNT(trip1)>1);
