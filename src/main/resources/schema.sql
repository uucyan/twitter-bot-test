create table if not exists test (
  id int primary key auto_increment,
  name varchar(255),
  created_at timestamp not null default current_timestamp,
  updated_at timestamp not null default current_timestamp on update current_timestamp
);

delete from test;
insert into test VALUES
  ( 1 , 'bot1', NOW() , NOW()),
  ( 2 , 'bot2', NOW() , NOW()),
  ( 3 , 'bot3', NOW() , NOW())
;
