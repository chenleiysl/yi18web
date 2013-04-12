delimiter $$

CREATE TABLE `yi18_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id编号',
  `title` varchar(128) NOT NULL COMMENT '标题',
  `message` text COMMENT '内容',
  `count` int(11) DEFAULT NULL COMMENT '访问次数',
  `time` timestamp NULL DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='综合的新闻信息'$$

