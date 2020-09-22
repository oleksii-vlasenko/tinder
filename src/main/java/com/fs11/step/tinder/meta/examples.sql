SELECT *
FROM users u
         FULL OUTER JOIN authentications a ON (a."user" = u.id);

SELECT *
FROM authentications a
         LEFT OUTER JOIN users u ON (u.id = a."user");

SELECT *
FROM authentications a
         RIGHT OUTER JOIN users u ON (u.id = a."user");

SELECT *
FROM authentications a
         JOIN users u ON (u.id = a."user"); -- inner

-- SELECT sum(r) as sum
-- from history h;

-- select avg(r)
-- from history h;

-- select u_id, count(*)
-- from history h
-- group by u_id;
--
-- select * from users u
--                   join (select u_id, count(*)
--                         from history
--                         group by u_id) cnt on (cnt.u_id = u.id);
--
-- CREATE VIEW groupped AS
-- select u_id, count(*)
-- from history
-- group by u_id;
--
-- select * from users u
--                   join groupped g on (g.u_id = u.id);
