creat table car(id real, name text primary key, model text, cost text );
creat table human(id real, name text primary key, age integer, driver license boolean, car_id text references car (id));  