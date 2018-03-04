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
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table if not exists tweet_log (
  id int primary key auto_increment,
  twitter_application_id int(11) NOT NULL,
  tweet varchar(255),
  error_message text,
  created_at timestamp not null default current_timestamp,
  updated_at timestamp not null default current_timestamp on update current_timestamp,
  foreign key (twitter_application_id) references twitter_application(id)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
