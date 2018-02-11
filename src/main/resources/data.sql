delete from tweet_log;
delete from twitter_application;
insert into twitter_application VALUES
  (1, 'dev', '@dev_bot_cyan', 'https://twitter.com/dev_bot_cyan', 'かいはつぼっとちゃん', 'DRrJp1VWExASN64BVgknupI3T', 'NgWxrk0cWk1VJVbYV0SQFmVk8ahdzKSqphxfGsHbIdutEBiMgp', '938316768862527488-KcwZafQ7FD8r1eMACFoNyNcztCN5Xuj', 'VvHiFm5kcNodCDgR3BGrYDumF3UH7Rvxsd7sSs1mx77ce', true, NOW() , NOW()),
  (2, 'prod', '@prod_bot_cyan', 'https://twitter.com/prod_bot_cyan', 'ほんばんぼっとちゃん', '6HdkRW8TLZIE8GfiSUPsXyfiJ', 'IU4XSpqxonp1KdTnOUwuBNJlZkyr2gZd8Y6e3MkDw7n3QPSZS4', '947152326552313856-D9TjrvGLAdpMhGGfA3whycZkNNJtsQt', 'upx1tkTjnI209Z0viMslP7IXhsuoQY1jp5d0KFz3f8A3A', true, NOW() , NOW())
;
