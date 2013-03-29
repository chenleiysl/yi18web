CREATE TABLE IF NOT EXISTS `yi18_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '登录帐号',
  `password` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `name` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '姓名或昵称',
  `home` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '站内主页号',
  `image` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
  `email` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '联系电话 手机，电话等',
  `qq` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'QQ号',
  `url` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '个人主页网址',
  `sex` int(1) NOT NULL DEFAULT '1' COMMENT '性别，1：男 ;0：女',
  `birth` timestamp NULL DEFAULT NULL COMMENT '出生年月',
  `area` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '地址如 四川省-成都市',
  `signature` varchar(140) COLLATE utf8_bin DEFAULT NULL COMMENT '个性签名',
  `integral` int(11) NOT NULL DEFAULT '0' COMMENT '用户积分',
  `isemail` int(1) NOT NULL DEFAULT '0' COMMENT '是否显示邮箱 1：显示 0：不显示',
  `isphone` int(1) NOT NULL DEFAULT '0' COMMENT '是否显示联系电话 1：显示 0：不显示',
  `isqq` int(1) NOT NULL DEFAULT '0' COMMENT '是否显示qq号，1：显示，0：不显示',
  `isuse` int(1) NOT NULL DEFAULT '1' COMMENT '是否可用，如禁止帐号，1：可用，0：不可用',
  `role` int(1) NOT NULL DEFAULT '0' COMMENT '用户权限，0：普通用户，1：管理员',
  `hkey` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '钥匙，做认证',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表，实现记录用户信息，登录验证。'

