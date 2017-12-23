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

create table if not exists twitter_keys (
  id int primary key auto_increment,
  bot_type varchar(255),
  consumer_key varchar(255),
  consumer_secret varchar(255),
  access_token varchar(255),
  access_token_secret varchar(255),
  created_at timestamp not null default current_timestamp,
  updated_at timestamp not null default current_timestamp on update current_timestamp
);

delete from twitter_keys;
insert into twitter_keys VALUES
  (1, 'test', 'DRrJp1VWExASN64BVgknupI3T', 'NgWxrk0cWk1VJVbYV0SQFmVk8ahdzKSqphxfGsHbIdutEBiMgp', '938316768862527488-KcwZafQ7FD8r1eMACFoNyNcztCN5Xuj', 'VvHiFm5kcNodCDgR3BGrYDumF3UH7Rvxsd7sSs1mx77ce', NOW() , NOW())
;

create table if not exists twitter_application (
  id int primary key auto_increment,
  bot_type varchar(255),
  twitter_id varchar(255),
  twitter_url varchar(255),
  twitter_name varchar(255),
  consumer_key varchar(255) not null,
  consumer_secret varchar(255) not null,
  access_token varchar(255) not null,
  access_token_secret varchar(255) not null,
  is_execute boolean default false,
  created_at timestamp not null default current_timestamp,
  updated_at timestamp not null default current_timestamp on update current_timestamp
);

create table if not exists tweet_log (
  id int primary key auto_increment,
  twitter_application_id int(11) NOT NULL,
  tweet varchar(255),
  is_duplicate_error boolean,
  created_at timestamp not null default current_timestamp,
  updated_at timestamp not null default current_timestamp on update current_timestamp,
  foreign key (twitter_application_id) references twitter_application(id)
);

delete from tweet_log;
delete from twitter_application;
insert into twitter_application VALUES
  (1, 'test', '@gbp_bot_test', 'https://twitter.com/gbp_bot_test', 'ぼっとてすとちゃん', 'DRrJp1VWExASN64BVgknupI3T', 'NgWxrk0cWk1VJVbYV0SQFmVk8ahdzKSqphxfGsHbIdutEBiMgp', '938316768862527488-KcwZafQ7FD8r1eMACFoNyNcztCN5Xuj', 'VvHiFm5kcNodCDgR3BGrYDumF3UH7Rvxsd7sSs1mx77ce', true, NOW() , NOW())
;
