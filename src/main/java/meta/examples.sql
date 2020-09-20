select *
from history h
         full outer join users u on (u.id = h.u_id);

select *
from history h
         left outer join users u on (u.id = h.u_id);

select *
from history h
         right outer join users u on (u.id = h.u_id);

select *
from history h
         join users u on (u.id = h.u_id); -- inner

select sum(r) as sum
from history h;

select avg(r)
from history h;

select u_id, count(*)
from history h
group by u_id;

select * from users u
                  join (select u_id, count(*)
                        from history
                        group by u_id) cnt on (cnt.u_id = u.id);

CREATE VIEW groupped AS
select u_id, count(*)
from history
group by u_id;

select * from users u
                  join groupped g on (g.u_id = u.id);
