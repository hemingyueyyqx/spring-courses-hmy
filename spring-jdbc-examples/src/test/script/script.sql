explain
select * from address a where  a.user_id='1283955919788601344';

explain
select * from address a join user u on a.user_id = u.id where a.id='1292717272796475392';

explain
select * from user u join address a on u.id = a.user_id where a.user_id='1283955919788601344';
explain
select u.id as user_id,u.name,count(a.user_id) as count from user u  left join address a
on u.id=a.user_id
group by u.id
order by count;

select u.name,count(a.user_id) as count from user u join address a
on u.id=a.user_id
group by u.id
order by count;