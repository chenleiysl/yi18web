

DROP TABLE IF EXISTS `yi18_departments`;

CREATE TABLE `yi18_departments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='科室,主要拥有疾病的分类';



INSERT INTO `yi18_departments` VALUES (7,' 中医科'),(9,' 耳鼻喉科'),(4,' 肿瘤科'),(12,'乳腺外科'),(33,'产科'),(3,'传染科'),(1,'体检保健科'),(34,'儿科'),(22,'内分泌科'),(39,'内科'),(26,'呼吸内科'),(29,'外科'),(32,'妇科'),(5,'心理咨询'),(17,'心胸外科'),(18,'心血管内科'),(35,'急诊科'),(28,'性病科'),(14,'泌尿外科'),(25,'消化内科'),(10,'烧伤科'),(27,'生殖健康'),(31,'男科'),(8,'皮肤科'),(24,'神经内科'),(23,'神经外科'),(6,'精神病科'),(2,'老年科'),(13,'肛肠外科'),(16,'肝胆外科'),(21,'肾内科'),(15,'胃肠外科'),(19,'血液科'),(11,'血管外科'),(20,'风湿科');


DROP TABLE IF EXISTS `yi18_directory`;

CREATE TABLE `yi18_directory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '目录的id编号',
  `title` varchar(64) NOT NULL COMMENT '目录的标题',
  `issearch` smallint(6) DEFAULT '1' COMMENT '是否允许搜索检索，1：允许 0：不允许。也就是标记是否该目录下的

内容允许通过搜索找到',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述，，目录的描述',
  `sequence` smallint(6) DEFAULT NULL COMMENT '目录的显示顺序 从0开始',
  `type` smallint(6) DEFAULT NULL COMMENT '分类 1:drug(药品)；2：symptom(病状)；3：disease（疾病）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='药品内容的目录';



INSERT INTO `yi18_directory` VALUES (1,'详细说明书',1,'',0,1),(2,'功能主治',1,'',1,1),(3,'适应症',1,'',2,1),(4,'药物成分',1,'',3,1),(5,'用法用量',1,'',4,1),(6,'性状标准',1,'',5,1),(7,'药物相互作用',1,'',6,1),(8,'注意事项',1,'',7,1),(9,'禁忌症',1,'',8,1),(10,'不良反应',1,'',9,1),(11,'生产企业',1,'',10,1),(12,'病因',1,'',1,2),(13,'症状诊断',1,'',2,2),(14,'预防保健',1,'',3,2),(15,'相关疾病',1,'',4,2),(16,'症状体现',1,'',1,3),(17,'病理病因',1,'',2,3),(18,'预防保健',1,'',3,3),(19,'疾病治疗',1,'',4,3),(20,'病例解析',1,'',5,3);


DROP TABLE IF EXISTS `yi18_disease`;

CREATE TABLE `yi18_disease` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '疾病名称',
  `description` varchar(1024) DEFAULT NULL COMMENT '疾病描述 ',
  `diseaseclass` bigint(20) DEFAULT NULL COMMENT '疾病分类',
  `infectious` smallint(6) DEFAULT NULL COMMENT '传 染 性 0：不传染，1传染',
  `allow` smallint(6) DEFAULT NULL COMMENT '是否允许显示，，是否通过管理员审核，1：通过，0：等待，-1 不通过

',
  `time` timestamp NULL DEFAULT NULL COMMENT '添加时间',
  `count` int(11) DEFAULT NULL COMMENT '阅读次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='疾病信息表';




DROP TABLE IF EXISTS `yi18_diseaseclass`;

CREATE TABLE `yi18_diseaseclass` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '疾病分类的Id编号',
  `title` varchar(64) NOT NULL COMMENT '疾病分类的标题',
  `level` smallint(6) NOT NULL DEFAULT '1' COMMENT '分类的级别，0：root，1,2等',
  `_parentId` bigint(20) DEFAULT NULL COMMENT '上级药品的id',
  `state` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='疾病分类，主要用于疾病的标准分类';


INSERT INTO `yi18_diseaseclass` VALUES (1,'传染病与寄生虫病',1,0,'closed'),(2,'神经系与感觉器官疾病',1,0,'closed'),(3,'循环系疾病',1,0,'closed'),(4,'呼吸系疾病',1,0,'closed'),(5,'消化系疾病',1,0,'closed'),(6,'泌尿生殖系疾病',1,0,'closed'),(7,'妊娠、分娩及产后合并症',1,0,'closed'),(8,'妊娠综合症',2,7,NULL);


DROP TABLE IF EXISTS `yi18_diseasedepartments`;

CREATE TABLE `yi18_diseasedepartments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `disease` bigint(20) DEFAULT NULL COMMENT '疾病的id',
  `departments` bigint(20) DEFAULT NULL COMMENT '科室的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='疾病对应科室，一个科室对应多个疾病，一个疾病对应多个科室。';






DROP TABLE IF EXISTS `yi18_diseaseinfo`;

CREATE TABLE `yi18_diseaseinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '疾病信息的id编号',
  `disease` bigint(20) NOT NULL COMMENT '疾病的编号',
  `directory` bigint(20) DEFAULT NULL COMMENT '疾病词条',
  `message` text COMMENT '添加的内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='疾病的基本信息';



DROP TABLE IF EXISTS `yi18_diseaseplace`;

CREATE TABLE `yi18_diseaseplace` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `disease` bigint(20) DEFAULT NULL COMMENT '疾病的id',
  `place` bigint(20) DEFAULT NULL COMMENT '身体部位的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='主要用于疾病和身体部位的对应，一个疾病对应多个身体部位，一个身体部位对应对个疾病';



DROP TABLE IF EXISTS `yi18_drug`;

CREATE TABLE `yi18_drug` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '药品的id编号',
  `name` varchar(64) NOT NULL COMMENT '药品名称',
  `alias` varchar(128) DEFAULT NULL COMMENT '药品的别名，对应名称的其他名称，中间可以用  /等分开',
  `term` varchar(1024) DEFAULT NULL COMMENT '药品的词条，也就是该药品的基本信息。',
  `image` varchar(128) DEFAULT NULL COMMENT '药品的一个基本图片',
  `prescription` smallint(6) DEFAULT '0' COMMENT '药品的处方类型，1：处方药，0：非处方的药',
  `ingredient` smallint(6) DEFAULT '0' COMMENT '药品的组成成分，0：中药，1：中成药：2西药',
  `price` float DEFAULT '0' COMMENT '用于药品的产考价格',
  `factory` bigint(20) DEFAULT NULL COMMENT '生产厂家',
  `allow` smallint(6) DEFAULT NULL COMMENT '是否允许显示，，是否通过管理员审核，1：通过，0：等待，-1 不通过',
  `drugclass` bigint(20) DEFAULT '0' COMMENT '药品的分类',
  `count` int(11) DEFAULT NULL COMMENT '访问次数',
  `time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='药品库，主要包括药品的名称，描述，摘要，生产厂商等';


DROP TABLE IF EXISTS `yi18_drugclass`;

CREATE TABLE `yi18_drugclass` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '药品分类的Id编号',
  `title` varchar(64) NOT NULL COMMENT '药品分类的标题',
  `level` smallint(6) NOT NULL DEFAULT '1' COMMENT '分类的级别，0：root，1,2等',
  `_parentId` bigint(20) DEFAULT NULL COMMENT '上级药品的id',
  `state` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='药品分类，主要用于药品分类';



INSERT INTO `yi18_drugclass` VALUES (1,'泌尿分泌',1,0,'closed'),(2,'皮肤呼吸',1,0,'closed'),(3,'五官肠胃',1,0,'closed'),(4,'心脑血管',1,0,'closed'),(5,'肿瘤癌症',1,0,'closed'),(6,'抗生免疫',1,0,'closed'),(7,'精神神经',1,0,'closed'),(8,'保健养生',1,0,'closed'),(9,'减肥美容',1,0,'closed'),(10,'儿科用药',1,0,'closed'),(11,'男科用药',1,0,'closed'),(12,'妇科用药',1,0,'closed'),(13,'感冒发热',1,0,'closed'),(14,'家庭常备',1,0,'closed'),(15,'感冒',2,13,NULL),(16,'防暑降温',2,14,NULL);



DROP TABLE IF EXISTS `yi18_druginfo`;

CREATE TABLE `yi18_druginfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '药品信息的id编号',
  `drug` bigint(20) NOT NULL COMMENT '药品的编号',
  `directory` bigint(20) DEFAULT NULL,
  `message` text COMMENT '添加的内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='药品的基本信息';


DROP TABLE IF EXISTS `yi18_factory`;

CREATE TABLE `yi18_factory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '厂家的标号ID',
  `name` varchar(64) NOT NULL COMMENT '生产厂家的的名称',
  `description` varchar(1024) DEFAULT NULL COMMENT '厂家的基本描述，',
  `address` varchar(128) DEFAULT NULL COMMENT '生产厂家的地址',
  `phone` varchar(64) DEFAULT NULL COMMENT '厂家的联系电话，多个用 ， 号隔开',
  `url` varchar(128) DEFAULT NULL COMMENT '生产厂家的网站主页',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='药品的专门的生产厂家';



INSERT INTO `yi18_factory` VALUES (1,'其它生产厂商','医药吧处理药品不明确的生产厂商','四川省-成都市-成华区','13880334484','http://www.yi18.cn');



DROP TABLE IF EXISTS `yi18_links`;

CREATE TABLE `yi18_links` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '友情链接的id编号',
  `title` varchar(64) NOT NULL COMMENT '友情链接的标题',
  `url` varchar(128) NOT NULL COMMENT '友情链接的URL链接',
  `focal` smallint(6) DEFAULT '0' COMMENT '该友情链接是否拥有焦点 0：没有，1：拥有。这个主要拥有友情链接的突出',
  `sequence` smallint(6) DEFAULT '0' COMMENT '排序设置友情链接的排序方式，从0开始',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网站的友情链接';


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
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='记录用户的登录状态，如记住登录状态等功能，同时也实现登录记录';



DROP TABLE IF EXISTS `yi18_lore`;

CREATE TABLE `yi18_lore` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id编号',
  `title` varchar(128) NOT NULL COMMENT '标题',
  `author` varchar(64) DEFAULT NULL COMMENT '作者',
  `message` text COMMENT '内容',
  `count` int(11) DEFAULT NULL COMMENT '访问次数',
  `allow` int(11) DEFAULT NULL COMMENT '是否允许显示，，是否通过管理员审核，1：通过，0：等待，-1 不通过',
  `loreclass` bigint(20) DEFAULT NULL COMMENT '健康知识分类',
  `time` timestamp NULL DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='健康知识';


DROP TABLE IF EXISTS `yi18_loreclass`;

CREATE TABLE `yi18_loreclass` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='健康知识分类';



INSERT INTO `yi18_loreclass` VALUES (1,'老人健康',NULL),(2,'婴儿健康',NULL),(3,'减肥美容',NULL),(4,'男性保健',NULL),(5,'女性保健',NULL),(6,'孕妇保健',NULL);


DROP TABLE IF EXISTS `yi18_news`;

CREATE TABLE `yi18_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id编号',
  `title` varchar(128) NOT NULL COMMENT '标题',
  `author` varchar(64) DEFAULT NULL COMMENT '作者',
  `message` text COMMENT '内容',
  `count` int(11) DEFAULT NULL COMMENT '访问次数',
  `allow` int(11) DEFAULT NULL COMMENT '是否允许显示，，是否通过管理员审核，1：通过，0：等待，-1 不通过',
  `time` timestamp NULL DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='综合的新闻信息';


DROP TABLE IF EXISTS `yi18_partner`;

CREATE TABLE `yi18_partner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '合作伙伴的id编号',
  `title` varchar(64) NOT NULL COMMENT '合作伙伴的标题',
  `logo` varchar(128) DEFAULT NULL COMMENT 'LOGO标志的地址',
  `url` varchar(128) DEFAULT NULL COMMENT '合作伙伴的网站',
  `description` varchar(1024) DEFAULT NULL COMMENT '合作伙伴的描述',
  `sequence` smallint(6) DEFAULT NULL COMMENT '合作伙伴的排列顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合作伙伴';



DROP TABLE IF EXISTS `yi18_place`;

CREATE TABLE `yi18_place` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='人的身体部分，主要是疾病的发病部分';


INSERT INTO `yi18_place` VALUES (1,'头部'),(2,'四肢'),(3,'颈部'),(4,'胸部'),(5,'背部'),(6,'腹部'),(7,'腰部'),(8,'盆腔'),(9,'臀部'),(10,'会阴部'),(11,'生殖部位'),(12,'血液血管'),(13,'皮肤'),(14,'毛发'),(15,'心里'),(16,'骨'),(17,'全身'),(18,'其他');


DROP TABLE IF EXISTS `yi18_searchlog`;

CREATE TABLE `yi18_searchlog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '搜索信息存储的记录',
  `keyword` varchar(64) NOT NULL COMMENT '搜索的关键字',
  `searchcount` int(11) NOT NULL COMMENT '搜索的次数',
  `resultcount` int(11) NOT NULL COMMENT '搜索的结果总数',
  `updatetime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于信息搜索的记录信息';





DROP TABLE IF EXISTS `yi18_symptomclass`;

CREATE TABLE `yi18_symptomclass` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '病状分类的Id编号',
  `title` varchar(64) NOT NULL COMMENT '病状分类的标题',
  `level` smallint(6) NOT NULL DEFAULT '1' COMMENT '分类的级别，0：root，1,2等',
  `_parentId` bigint(20) DEFAULT NULL COMMENT '上级药品的id',
  `state` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='病状分类，主要用于药品分类';


INSERT INTO `yi18_symptomclass` VALUES (1,'呼吸系统',1,0,'closed'),(2,'消化系统',1,0,'closed'),(3,'心血血管',1,0,'closed'),(4,'肿瘤癌症',1,0,'closed'),(5,'男性生理',1,0,'closed'),(6,'女性生理',1,0,'closed'),(7,'孩子老人',1,0,'closed'),(8,'孩子矮小',2,7,NULL);


DROP TABLE IF EXISTS `yi18_symptominfo`;

CREATE TABLE `yi18_symptominfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '病状信息的id编号',
  `symptoms` bigint(20) NOT NULL COMMENT '病状的编号',
  `directory` bigint(20) DEFAULT NULL COMMENT '病状的内容目录',
  `message` text COMMENT '添加的内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='病状的基本信息';


DROP TABLE IF EXISTS `yi18_symptoms`;

CREATE TABLE `yi18_symptoms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '病状名称',
  `description` varchar(1024) DEFAULT NULL COMMENT '基本描述',
  `count` int(11) DEFAULT NULL COMMENT '访问次数',
  `symptomsclass` bigint(20) DEFAULT NULL COMMENT '医药分类',
  `allow` smallint(6) DEFAULT NULL COMMENT '是否允许显示，，是否通过管理员审核，1：通过，0：等待，-1 不通过

',
  `time` timestamp NULL DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='病状信息';





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
  `sex` int(1) NOT NULL DEFAULT '1' COMMENT '性别，1：男  0：女',
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表，实现记录用户信息，登录验证。';

