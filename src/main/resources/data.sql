delete from tweet_log;
delete from twitter_application;
insert into twitter_application VALUES
  (1, 'dev', '@dev_bot_cyan', 'https://twitter.com/dev_bot_cyan', 'かいはつぼっとちゃん', 'DRrJp1VWExASN64BVgknupI3T', 'NgWxrk0cWk1VJVbYV0SQFmVk8ahdzKSqphxfGsHbIdutEBiMgp', '938316768862527488-maea5I06YkBkRBbmHdoUIHUmV0zNb9c', 'jyoxx4WX82OFxmQeRIv46NjHZTxEM0OBnBnEFE2mjdt9d', true, NOW() , NOW()),
  (2, 'prod', '@prod_bot_cyan', 'https://twitter.com/prod_bot_cyan', 'ほんばんぼっとちゃん', 'awkheaBr6XCiadpeJZMLttBkf', 'UfvVPsmZDFDHUaq8Jb3TEaPytG6XNIYfLLKQOfSNd877h5R6jg', '947152326552313856-Qdwh3YS907tY4sYHPGbMwXyWTMN8R18', 'oDpEW5Oa9Mb0bmcLtSgFGOGxVgXf4leJf6mrO6amsJyUE', true, NOW() , NOW())
;
