/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50610
Source Host           : 127.0.0.1:3306
Source Database       : yi18

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2013-04-09 09:47:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `yi18_directory`
-- ----------------------------
DROP TABLE IF EXISTS `yi18_directory`;
CREATE TABLE `yi18_directory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '目录的id编号',
  `title` varchar(64) NOT NULL COMMENT '目录的标题',
  `issearch` smallint(6) DEFAULT '1' COMMENT '是否允许搜索检索，1：允许 0：不允许。也就是标记是否该目录下的内容允许通过搜索找到',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述，，目录的描述',
  `sequence` smallint(6) DEFAULT NULL COMMENT '目录的显示顺序 从0开始',
  `time` timestamp NULL DEFAULT NULL COMMENT '目录的添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='药品内容的目录';

-- ----------------------------
-- Records of yi18_directory
-- ----------------------------
INSERT INTO `yi18_directory` VALUES ('1', '功能主治', '0', '主要是编写该药品的主要医治功能主要是编写该药品的主要医治功能主要是编写该药品的主要医治功能主要是编写该药品的主要医治功能主要是编写该药品的主要医治功能主要是编写该药品的主要医治功能', '1', '2013-03-28 13:13:51');
INSERT INTO `yi18_directory` VALUES ('2', '是的', '1', '是的', '0', '2013-03-28 13:47:11');
INSERT INTO `yi18_directory` VALUES ('3', '药品简介', '1', '药品的基本信息', '1', '2013-03-28 13:52:32');

-- ----------------------------
-- Table structure for `yi18_drug`
-- ----------------------------
DROP TABLE IF EXISTS `yi18_drug`;
CREATE TABLE `yi18_drug` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '药品的id编号',
  `name` varchar(64) NOT NULL COMMENT '药品名称',
  `alias` varchar(128) DEFAULT NULL COMMENT '药品的别名，对应名称的其他名称，中间可以用 /;等分开',
  `term` varchar(1024) DEFAULT NULL COMMENT '药品的词条，也就是该药品的基本信息。',
  `image` varchar(128) DEFAULT NULL COMMENT '药品的一个基本图片',
  `prescription` smallint(6) DEFAULT '0' COMMENT '药品的处方类型，1：处方药，0：非处方的药',
  `ingredient` smallint(6) DEFAULT '0' COMMENT '药品的组成成分，0：中药，1：中成药：2西药',
  `price` float DEFAULT '0' COMMENT '用于药品的产考价格',
  `factory` bigint(20) DEFAULT NULL COMMENT '生产厂家',
  `allow` smallint(6) DEFAULT NULL COMMENT '是否允许显示，，是否通过管理员审核，1：通过，0：等待，-1 不通过',
  `drugclass` bigint(20) DEFAULT '0' COMMENT '药品的分类',
  `time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='药品库，主要包括药品的名称，描述，摘要，生产厂商等';

-- ----------------------------
-- Records of yi18_drug
-- ----------------------------
INSERT INTO `yi18_drug` VALUES ('1', '999', '999感冒灵颗粒', '松岛枫11', 'http://localhost:8080/yi18/common/temp/20130402114213_153.png', '0', '1', '0', '2', '1', '5', '2013-03-29 13:20:34');
INSERT INTO `yi18_drug` VALUES ('2', '的说法', '松岛枫', '', null, '0', '1', '0', '0', '0', '0', '2013-03-29 15:06:27');
INSERT INTO `yi18_drug` VALUES ('3', '999感冒灵颗粒', '999感冒灵颗粒', '偶见皮疹、荨麻疹、药热及粒细胞减少；可见困倦、嗜睡、口渴、虚弱感；长期大量用药会导致肝肾功能异常。', null, '0', '1', '0', '0', '0', '0', '2013-03-29 15:07:47');
INSERT INTO `yi18_drug` VALUES ('4', '999感冒灵颗粒', '999感冒灵颗粒', '阿斯蒂芬松岛枫士大夫', 'http://localhost:8080/yi18/common/temp/20130329151307_703.jpg', '0', '1', '0', '0', '0', '0', '2013-03-29 15:13:08');
INSERT INTO `yi18_drug` VALUES ('5', '撒地方撒地方', '阿斯蒂芬发送到', '', 'http://localhost:8080/yi18/common/temp/20130329151815_978.jpg', '0', '1', '0', '0', '0', '0', '2013-03-29 15:18:16');
INSERT INTO `yi18_drug` VALUES ('6', '地方', '四谛法', '的说法个', 'http://localhost:8080/yi18/common/temp/20130401102352_702.', '0', '1', '0', '0', '0', '0', '2013-04-01 10:23:52');
INSERT INTO `yi18_drug` VALUES ('7', '111', '1111', '111', 'http://localhost:8080/yi18/common/temp/20130401102559_799.', '0', '1', '0', '0', '0', '0', '2013-04-01 10:25:59');
INSERT INTO `yi18_drug` VALUES ('8', '222', '2222', '222', 'http://localhost:8080/yi18/common/temp/20130401102828_543.', '0', '1', '0', '1', '0', '0', '2013-04-01 10:28:29');
INSERT INTO `yi18_drug` VALUES ('9', '333', '3333', '33333', null, '0', '1', '0', '0', '0', '0', '2013-04-01 10:31:21');
INSERT INTO `yi18_drug` VALUES ('10', '121212', '', '', null, '0', '1', '0', '2', '0', '0', '2013-04-01 10:37:57');

-- ----------------------------
-- Table structure for `yi18_drugclass`
-- ----------------------------
DROP TABLE IF EXISTS `yi18_drugclass`;
CREATE TABLE `yi18_drugclass` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '药品分类的Id编号',
  `title` varchar(64) NOT NULL COMMENT '药品分类的标题',
  `level` smallint(6) NOT NULL DEFAULT '1' COMMENT '分类的级别，0：root，1,2等',
  `_parentId` bigint(20) DEFAULT NULL COMMENT '上级药品的id',
  `state` varchar(45) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '添加药品分类的时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='药品分类，主要用于药品分类';

-- ----------------------------
-- Records of yi18_drugclass
-- ----------------------------
INSERT INTO `yi18_drugclass` VALUES ('1', '感冒药', '1', '0', 'closed', '2013-03-28 15:40:46');
INSERT INTO `yi18_drugclass` VALUES ('2', '999', '2', '1', null, '2013-03-28 15:18:12');
INSERT INTO `yi18_drugclass` VALUES ('3', '7777', '2', '1', null, '2013-03-28 15:32:20');
INSERT INTO `yi18_drugclass` VALUES ('4', '信息1', '1', '0', 'closed', '2013-03-28 16:12:32');
INSERT INTO `yi18_drugclass` VALUES ('5', '四四', '2', '1', null, '2013-03-28 16:11:14');

-- ----------------------------
-- Table structure for `yi18_druginfo`
-- ----------------------------
DROP TABLE IF EXISTS `yi18_druginfo`;
CREATE TABLE `yi18_druginfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '药品信息的id编号',
  `drug` bigint(20) NOT NULL COMMENT '药品的编号',
  `directory` bigint(20) DEFAULT NULL,
  `message` text COMMENT '添加的内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='药品的基本信息';

-- ----------------------------
-- Records of yi18_druginfo
-- ----------------------------
INSERT INTO `yi18_druginfo` VALUES ('4', '1', '3', 'asdfssdf');
INSERT INTO `yi18_druginfo` VALUES ('5', '1', '2', '<strong>HTML内容sadf sadf sdf <br />\r\n</strong>');
INSERT INTO `yi18_druginfo` VALUES ('6', '1', '1', '<strong>HTML内容sdaf sdf <br />\r\n</strong>');
INSERT INTO `yi18_druginfo` VALUES ('7', '2', '3', '');
INSERT INTO `yi18_druginfo` VALUES ('8', '2', '2', '');
INSERT INTO `yi18_druginfo` VALUES ('9', '2', '1', '');
INSERT INTO `yi18_druginfo` VALUES ('10', '3', '3', '<div class=\"para\">\r\n	1.·忌烟，酒及辛辣，生冷，<a target=\"_blank\" href=\"http://baike.baidu.com/view/2309305.htm\">油腻食物</a>。\r\n</div>\r\n<div class=\"para\">\r\n	2.不宜在服药期间同时服用滋补性中药\r\n</div>\r\n<div class=\"para\">\r\n	3. 高血压、心脏病、肝病、肾病等慢性病严重者应在医师指导下服用。\r\n</div>\r\n<div class=\"para\">\r\n	4.本品含对乙氨基酚，马来酸氨苯那敏，咖啡因。服用本品期间不得饮酒或含有酒精的饮料；不能同时服用与本品成分相似的其它抗感冒药；肝，肾功能不全者慎用；膀胱颈梗阻，<a target=\"_blank\" href=\"http://baike.baidu.com/view/61884.htm\">甲状腺功能亢进</a>，青光眼，高血压和前列腺肥大者慎用；孕妇及哺乳期间女子慎用；服药期间不得驾驶机，车，船，从事高空作业，机械作业及操作精密仪器。\r\n</div>\r\n<div class=\"para\">\r\n	5.脾胃虚寒，症见腹痛，喜暖，泄泻者慎用。\r\n</div>\r\n<div class=\"para\">\r\n	6.糖尿病患者、消化道溃疡患者、膀胱颈梗阻、幽门十二指肠梗阻、<a target=\"_blank\" href=\"http://baike.baidu.com/view/124576.htm\">甲状腺机能亢进</a>、青光眼以及前列腺肥大等患者慎用。\r\n</div>\r\n<div class=\"para\">\r\n	7.儿童，年老体弱者应在医师指导下使用。\r\n</div>\r\n<div class=\"para\">\r\n	8.服药3天后症状无改善，或症状加重，或出现新的严重症状如胸闷，心悸等应立即停药，并去医院就诊。\r\n</div>\r\n<div class=\"para\">\r\n	9.对本药过敏者禁用，过敏体质者慎用。\r\n</div>\r\n<div class=\"para\">\r\n	10. 本品性状发生改变时禁止使用。\r\n</div>\r\n<div class=\"para\">\r\n	11.儿童必须在成人监护下使用。\r\n</div>\r\n<div class=\"para\">\r\n	12.请将本品放在儿童不能接触的地方。\r\n</div>\r\n<div class=\"para\">\r\n	13.如正在使用其他药品，使用本品前请咨询医师或药师。\r\n</div>');
INSERT INTO `yi18_druginfo` VALUES ('11', '3', '2', '<div class=\"para\">\r\n	1.·忌烟，酒及辛辣，生冷，<a target=\"_blank\" href=\"http://baike.baidu.com/view/2309305.htm\">油腻食物</a>。\r\n</div>\r\n<div class=\"para\">\r\n	2.不宜在服药期间同时服用滋补性中药\r\n</div>\r\n<div class=\"para\">\r\n	3. 高血压、心脏病、肝病、肾病等慢性病严重者应在医师指导下服用。\r\n</div>\r\n<div class=\"para\">\r\n	4.本品含对乙氨基酚，马来酸氨苯那敏，咖啡因。服用本品期间不得饮酒或含有酒精的饮料；不能同时服用与本品成分相似的其它抗感冒药；肝，肾功能不全者慎用；膀胱颈梗阻，<a target=\"_blank\" href=\"http://baike.baidu.com/view/61884.htm\">甲状腺功能亢进</a>，青光眼，高血压和前列腺肥大者慎用；孕妇及哺乳期间女子慎用；服药期间不得驾驶机，车，船，从事高空作业，机械作业及操作精密仪器。\r\n</div>\r\n<div class=\"para\">\r\n	5.脾胃虚寒，症见腹痛，喜暖，泄泻者慎用。\r\n</div>\r\n<div class=\"para\">\r\n	6.糖尿病患者、消化道溃疡患者、膀胱颈梗阻、幽门十二指肠梗阻、<a target=\"_blank\" href=\"http://baike.baidu.com/view/124576.htm\">甲状腺机能亢进</a>、青光眼以及前列腺肥大等患者慎用。\r\n</div>\r\n<div class=\"para\">\r\n	7.儿童，年老体弱者应在医师指导下使用。\r\n</div>\r\n<div class=\"para\">\r\n	8.服药3天后症状无改善，或症状加重，或出现新的严重症状如胸闷，心悸等应立即停药，并去医院就诊。\r\n</div>\r\n<div class=\"para\">\r\n	9.对本药过敏者禁用，过敏体质者慎用。\r\n</div>\r\n<div class=\"para\">\r\n	10. 本品性状发生改变时禁止使用。\r\n</div>\r\n<div class=\"para\">\r\n	11.儿童必须在成人监护下使用。\r\n</div>\r\n<div class=\"para\">\r\n	12.请将本品放在儿童不能接触的地方。\r\n</div>\r\n<div class=\"para\">\r\n	13.如正在使用其他药品，使用本品前请咨询医师或药师。\r\n</div>');
INSERT INTO `yi18_druginfo` VALUES ('12', '3', '1', '<div class=\"para\">\r\n	1.·忌烟，酒及辛辣，生冷，<a target=\"_blank\" href=\"http://baike.baidu.com/view/2309305.htm\">油腻食物</a>。\r\n</div>\r\n<div class=\"para\">\r\n	2.不宜在服药期间同时服用滋补性中药\r\n</div>\r\n<div class=\"para\">\r\n	3. 高血压、心脏病、肝病、肾病等慢性病严重者应在医师指导下服用。\r\n</div>\r\n<div class=\"para\">\r\n	4.本品含对乙氨基酚，马来酸氨苯那敏，咖啡因。服用本品期间不得饮酒或含有酒精的饮料；不能同时服用与本品成分相似的其它抗感冒药；肝，肾功能不全者慎用；膀胱颈梗阻，<a target=\"_blank\" href=\"http://baike.baidu.com/view/61884.htm\">甲状腺功能亢进</a>，青光眼，高血压和前列腺肥大者慎用；孕妇及哺乳期间女子慎用；服药期间不得驾驶机，车，船，从事高空作业，机械作业及操作精密仪器。\r\n</div>\r\n<div class=\"para\">\r\n	5.脾胃虚寒，症见腹痛，喜暖，泄泻者慎用。\r\n</div>\r\n<div class=\"para\">\r\n	6.糖尿病患者、消化道溃疡患者、膀胱颈梗阻、幽门十二指肠梗阻、<a target=\"_blank\" href=\"http://baike.baidu.com/view/124576.htm\">甲状腺机能亢进</a>、青光眼以及前列腺肥大等患者慎用。\r\n</div>\r\n<div class=\"para\">\r\n	7.儿童，年老体弱者应在医师指导下使用。\r\n</div>\r\n<div class=\"para\">\r\n	8.服药3天后症状无改善，或症状加重，或出现新的严重症状如胸闷，心悸等应立即停药，并去医院就诊。\r\n</div>\r\n<div class=\"para\">\r\n	9.对本药过敏者禁用，过敏体质者慎用。\r\n</div>\r\n<div class=\"para\">\r\n	10. 本品性状发生改变时禁止使用。\r\n</div>\r\n<div class=\"para\">\r\n	11.儿童必须在成人监护下使用。\r\n</div>\r\n<div class=\"para\">\r\n	12.请将本品放在儿童不能接触的地方。\r\n</div>\r\n<div class=\"para\">\r\n	13.如正在使用其他药品，使用本品前请咨询医师或药师。\r\n</div>');
INSERT INTO `yi18_druginfo` VALUES ('13', '4', '3', '阿斯蒂芬松岛枫撒地方撒地方<br />');
INSERT INTO `yi18_druginfo` VALUES ('14', '4', '2', '阿斯蒂芬发送到发送到飞');
INSERT INTO `yi18_druginfo` VALUES ('15', '4', '1', '阿斯蒂芬撒旦发送到发送到');
INSERT INTO `yi18_druginfo` VALUES ('16', '5', '3', '');
INSERT INTO `yi18_druginfo` VALUES ('17', '5', '2', '');
INSERT INTO `yi18_druginfo` VALUES ('18', '5', '1', '');
INSERT INTO `yi18_druginfo` VALUES ('19', '6', '3', '四谛法<br />');
INSERT INTO `yi18_druginfo` VALUES ('20', '6', '2', '四谛法');
INSERT INTO `yi18_druginfo` VALUES ('21', '6', '1', '四谛法<br />');
INSERT INTO `yi18_druginfo` VALUES ('22', '7', '3', '1111');
INSERT INTO `yi18_druginfo` VALUES ('23', '7', '2', '1111');
INSERT INTO `yi18_druginfo` VALUES ('24', '7', '1', '111111111111');
INSERT INTO `yi18_druginfo` VALUES ('25', '8', '3', '');
INSERT INTO `yi18_druginfo` VALUES ('26', '8', '2', '');
INSERT INTO `yi18_druginfo` VALUES ('27', '8', '1', '');
INSERT INTO `yi18_druginfo` VALUES ('28', '9', '3', '');
INSERT INTO `yi18_druginfo` VALUES ('29', '9', '2', '');
INSERT INTO `yi18_druginfo` VALUES ('30', '9', '1', '');
INSERT INTO `yi18_druginfo` VALUES ('31', '10', '3', '');
INSERT INTO `yi18_druginfo` VALUES ('32', '10', '2', '');
INSERT INTO `yi18_druginfo` VALUES ('33', '10', '1', '');

-- ----------------------------
-- Table structure for `yi18_factory`
-- ----------------------------
DROP TABLE IF EXISTS `yi18_factory`;
CREATE TABLE `yi18_factory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '厂家的标号ID',
  `name` varchar(64) NOT NULL COMMENT '生产厂家的的名称',
  `description` varchar(1024) DEFAULT NULL COMMENT '厂家的基本描述，',
  `address` varchar(128) DEFAULT NULL COMMENT '生产厂家的地址',
  `phone` varchar(64) DEFAULT NULL COMMENT '厂家的联系电话，多个用 ， 号隔开',
  `url` varchar(128) DEFAULT NULL COMMENT '生产厂家的网站主页',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='药品的专门的生产厂家';

-- ----------------------------
-- Records of yi18_factory
-- ----------------------------
INSERT INTO `yi18_factory` VALUES ('1', '士大夫', '1111sadf sd sdfa sdfsdf', '', '1980334484', 'http://weibo.com/ajaxlogin.php?framelogin=1&callback=parent.sinaSSOController.feedBackUrlCallBack');
INSERT INTO `yi18_factory` VALUES ('2', '医药', '', '', '', '');

-- ----------------------------
-- Table structure for `yi18_links`
-- ----------------------------
DROP TABLE IF EXISTS `yi18_links`;
CREATE TABLE `yi18_links` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '友情链接的id编号',
  `title` varchar(64) NOT NULL COMMENT '友情链接的标题',
  `url` varchar(128) NOT NULL COMMENT '友情链接的URL链接',
  `focal` smallint(6) DEFAULT '0' COMMENT '该友情链接是否拥有焦点 0：没有，1：拥有。这个主要拥有友情链接的突出',
  `sequence` smallint(6) DEFAULT '0' COMMENT '排序设置友情链接的排序方式，从0开始',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='网站的友情链接';

-- ----------------------------
-- Records of yi18_links
-- ----------------------------
INSERT INTO `yi18_links` VALUES ('1', '寻医问药', 'http://www.xywy.com/', '0', '1');
INSERT INTO `yi18_links` VALUES ('2', '818医药网', 'http://www.818.com/', '1', '2');
INSERT INTO `yi18_links` VALUES ('3', '新浪健康', 'http://health.sina.com.cn/', '0', '4');
INSERT INTO `yi18_links` VALUES ('4', '医药吧', 'http://yi18.cloudfoundry.com/', '1', '8');
INSERT INTO `yi18_links` VALUES ('5', '家庭医生在线', 'http://www.familydoctor.com.cn/', '0', '3');
INSERT INTO `yi18_links` VALUES ('6', 'yi18', 'http://www.yi18.cn', '1', '0');

-- ----------------------------
-- Table structure for `yi18_login`
-- ----------------------------
DROP TABLE IF EXISTS `yi18_login`;
CREATE TABLE `yi18_login` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user` bigint(20) NOT NULL COMMENT '用户id',
  `session` varchar(100) NOT NULL COMMENT '记录session中的id',
  `hkey` varchar(100) DEFAULT NULL COMMENT '验证码，用户自动登录的标识码',
  `logintime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '登录时间',
  `isonline` int(11) NOT NULL DEFAULT '1' COMMENT '是否在线 0:没有在线，1：在线',
  `ip` varchar(100) DEFAULT NULL COMMENT '登录的ip地址',
  `isauto` int(11) NOT NULL DEFAULT '1' COMMENT '是否自动登录，1：记住登录状态，0：bu实现记录登录状态',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='记录用户的登录状态，如记住登录状态等功能，同时也实现登录记录';

-- ----------------------------
-- Records of yi18_login
-- ----------------------------

-- ----------------------------
-- Table structure for `yi18_partner`
-- ----------------------------
DROP TABLE IF EXISTS `yi18_partner`;
CREATE TABLE `yi18_partner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '合作伙伴的id编号',
  `title` varchar(64) NOT NULL COMMENT '合作伙伴的标题',
  `logo` varchar(128) DEFAULT NULL COMMENT 'LOGO标志的地址',
  `url` varchar(128) DEFAULT NULL COMMENT '合作伙伴的网站',
  `description` varchar(1024) DEFAULT NULL COMMENT '合作伙伴的描述',
  `sequence` smallint(6) DEFAULT NULL COMMENT '合作伙伴的排列顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='合作伙伴';

-- ----------------------------
-- Records of yi18_partner
-- ----------------------------
INSERT INTO `yi18_partner` VALUES ('1', '得到1', null, 'http://www.yi18.cn', '撒旦1', '2');

-- ----------------------------
-- Table structure for `yi18_searchlog`
-- ----------------------------
DROP TABLE IF EXISTS `yi18_searchlog`;
CREATE TABLE `yi18_searchlog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '搜索信息存储的记录',
  `keyword` varchar(64) NOT NULL COMMENT '搜索的关键字',
  `searchcount` int(11) NOT NULL COMMENT '搜索的次数',
  `resultcount` int(11) NOT NULL COMMENT '搜索的结果总数',
  `updatetime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于信息搜索的记录信息';

-- ----------------------------
-- Records of yi18_searchlog
-- ----------------------------

-- ----------------------------
-- Table structure for `yi18_user`
-- ----------------------------
DROP TABLE IF EXISTS `yi18_user`;
CREATE TABLE `yi18_user` (
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表，实现记录用户信息，登录验证。';

-- ----------------------------
-- Records of yi18_user
-- ----------------------------
INSERT INTO `yi18_user` VALUES ('1', '397713572@qq.com', 'd89267ba6e888426c8f798a04f2fb874', 'mysns', 'mysns', 'avatar.gif', null, null, null, null, '1', null, '四川', null, '0', '0', '0', '0', '1', '0', null, '2013-03-25 12:21:27');
