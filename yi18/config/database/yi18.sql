SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `yi18` DEFAULT CHARACTER SET utf8 ;
USE `yi18` ;

-- -----------------------------------------------------
-- Table `yi18`.`yi18_departments`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yi18`.`yi18_departments` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(64) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '科室';


-- -----------------------------------------------------
-- Table `yi18`.`yi18_directory`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yi18`.`yi18_directory` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '目录的id编号' ,
  `title` VARCHAR(64) NOT NULL COMMENT '目录的标题' ,
  `issearch` SMALLINT(6) NULL DEFAULT '1' COMMENT '是否允许搜索检索，1：允许 0：不允许。也就是标记是否该目录下的内容允许通过搜索找到' ,
  `description` VARCHAR(1024) NULL DEFAULT NULL COMMENT '描述，，目录的描述' ,
  `sequence` SMALLINT(6) NULL DEFAULT NULL COMMENT '目录的显示顺序 从0开始' ,
  `type` SMALLINT(6) NULL DEFAULT NULL COMMENT '分类 1:drug(药品)；2：symptom(病状)' ,
  `time` TIMESTAMP NULL DEFAULT NULL COMMENT '目录的添加时间' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8
COMMENT = '药品内容的目录';


-- -----------------------------------------------------
-- Table `yi18`.`yi18_disease`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yi18`.`yi18_disease` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  ` name` VARCHAR(64) NOT NULL COMMENT '疾病名称' ,
  `description` VARCHAR(1024) NULL DEFAULT NULL COMMENT '疾病描述 ' ,
  `diseaseclass` BIGINT(20) NULL DEFAULT NULL COMMENT '疾病分类' ,
  `place` BIGINT(20) NULL DEFAULT NULL COMMENT '发病部位' ,
  `departments` BIGINT(20) NULL DEFAULT NULL COMMENT '就诊科室' ,
  `infectious` SMALLINT(6) NULL DEFAULT NULL COMMENT '传 染 性 0：不传染，1传染' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '疾病信息表';


-- -----------------------------------------------------
-- Table `yi18`.`yi18_diseasedepartments`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yi18`.`yi18_diseasedepartments` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `disease` BIGINT(20) NULL DEFAULT NULL ,
  `departments` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `yi18`.`yi18_diseaseinfo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yi18`.`yi18_diseaseinfo` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '疾病信息的id编号' ,
  `drug` BIGINT(20) NOT NULL COMMENT '疾病的编号' ,
  `directory` BIGINT(20) NULL DEFAULT NULL COMMENT '疾病词条' ,
  `message` TEXT NULL DEFAULT NULL COMMENT '添加的内容' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 34
DEFAULT CHARACTER SET = utf8
COMMENT = '疾病的基本信息';


-- -----------------------------------------------------
-- Table `yi18`.`yi18_diseaseplace`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yi18`.`yi18_diseaseplace` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `disease` BIGINT(20) NULL DEFAULT NULL ,
  `place` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `yi18`.`yi18_diseasseclass`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yi18`.`yi18_diseasseclass` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '疾病分类的Id编号' ,
  `title` VARCHAR(64) NOT NULL COMMENT '疾病分类的标题' ,
  `level` SMALLINT(6) NOT NULL DEFAULT '1' COMMENT '分类的级别，0：root，1,2等' ,
  `_parentId` BIGINT(20) NULL DEFAULT NULL COMMENT '上级药品的id' ,
  `state` VARCHAR(45) NULL DEFAULT NULL ,
  `time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '添加药品分类的时间' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8
COMMENT = '疾病分类，主要用于药品分类';


-- -----------------------------------------------------
-- Table `yi18`.`yi18_drug`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yi18`.`yi18_drug` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '药品的id编号' ,
  `name` VARCHAR(64) NOT NULL COMMENT '药品名称' ,
  `alias` VARCHAR(128) NULL DEFAULT NULL COMMENT '药品的别名，对应名称的其他名称，中间可以用 /;等分开' ,
  `term` VARCHAR(1024) NULL DEFAULT NULL COMMENT '药品的词条，也就是该药品的基本信息。' ,
  `image` VARCHAR(128) NULL DEFAULT NULL COMMENT '药品的一个基本图片' ,
  `prescription` SMALLINT(6) NULL DEFAULT '0' COMMENT '药品的处方类型，1：处方药，0：非处方的药' ,
  `ingredient` SMALLINT(6) NULL DEFAULT '0' COMMENT '药品的组成成分，0：中药，1：中成药：2西药' ,
  `price` FLOAT NULL DEFAULT '0' COMMENT '用于药品的产考价格' ,
  `factory` BIGINT(20) NULL DEFAULT NULL COMMENT '生产厂家' ,
  `allow` SMALLINT(6) NULL DEFAULT NULL COMMENT '是否允许显示，，是否通过管理员审核，1：通过，0：等待，-1 不通过' ,
  `drugclass` BIGINT(20) NULL DEFAULT '0' COMMENT '药品的分类' ,
  `count` INT(11) NULL DEFAULT NULL COMMENT '访问次数' ,
  `time` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8
COMMENT = '药品库，主要包括药品的名称，描述，摘要，生产厂商等';


-- -----------------------------------------------------
-- Table `yi18`.`yi18_drugclass`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yi18`.`yi18_drugclass` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '药品分类的Id编号' ,
  `title` VARCHAR(64) NOT NULL COMMENT '药品分类的标题' ,
  `level` SMALLINT(6) NOT NULL DEFAULT '1' COMMENT '分类的级别，0：root，1,2等' ,
  `_parentId` BIGINT(20) NULL DEFAULT NULL COMMENT '上级药品的id' ,
  `state` VARCHAR(45) NULL DEFAULT NULL ,
  `time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '添加药品分类的时间' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8
COMMENT = '药品分类，主要用于药品分类';


-- -----------------------------------------------------
-- Table `yi18`.`yi18_druginfo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yi18`.`yi18_druginfo` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '药品信息的id编号' ,
  `drug` BIGINT(20) NOT NULL COMMENT '药品的编号' ,
  `directory` BIGINT(20) NULL DEFAULT NULL ,
  `message` TEXT NULL DEFAULT NULL COMMENT '添加的内容' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 34
DEFAULT CHARACTER SET = utf8
COMMENT = '药品的基本信息';


-- -----------------------------------------------------
-- Table `yi18`.`yi18_factory`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yi18`.`yi18_factory` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '厂家的标号ID' ,
  `name` VARCHAR(64) NOT NULL COMMENT '生产厂家的的名称' ,
  `description` VARCHAR(1024) NULL DEFAULT NULL COMMENT '厂家的基本描述，' ,
  `address` VARCHAR(128) NULL DEFAULT NULL COMMENT '生产厂家的地址' ,
  `phone` VARCHAR(64) NULL DEFAULT NULL COMMENT '厂家的联系电话，多个用 ， 号隔开' ,
  `url` VARCHAR(128) NULL DEFAULT NULL COMMENT '生产厂家的网站主页' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8
COMMENT = '药品的专门的生产厂家';


-- -----------------------------------------------------
-- Table `yi18`.`yi18_links`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yi18`.`yi18_links` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '友情链接的id编号' ,
  `title` VARCHAR(64) NOT NULL COMMENT '友情链接的标题' ,
  `url` VARCHAR(128) NOT NULL COMMENT '友情链接的URL链接' ,
  `focal` SMALLINT(6) NULL DEFAULT '0' COMMENT '该友情链接是否拥有焦点 0：没有，1：拥有。这个主要拥有友情链接的突出' ,
  `sequence` SMALLINT(6) NULL DEFAULT '0' COMMENT '排序设置友情链接的排序方式，从0开始' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8
COMMENT = '网站的友情链接';


-- -----------------------------------------------------
-- Table `yi18`.`yi18_login`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yi18`.`yi18_login` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `user` BIGINT(20) NOT NULL COMMENT '用户id' ,
  `session` VARCHAR(100) NOT NULL COMMENT '记录session中的id' ,
  `hkey` VARCHAR(100) NULL DEFAULT NULL COMMENT '验证码，用户自动登录的标识码' ,
  `logintime` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '登录时间' ,
  `isonline` INT(11) NOT NULL DEFAULT '1' COMMENT '是否在线 0:没有在线，1：在线' ,
  `ip` VARCHAR(100) NULL DEFAULT NULL COMMENT '登录的ip地址' ,
  `isauto` INT(11) NOT NULL DEFAULT '1' COMMENT '是否自动登录，1：记住登录状态，0：bu实现记录登录状态' ,
  PRIMARY KEY (`id`) )
ENGINE = MyISAM
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8
COMMENT = '记录用户的登录状态，如记住登录状态等功能，同时也实现登录记录';


-- -----------------------------------------------------
-- Table `yi18`.`yi18_news`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yi18`.`yi18_news` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id编号' ,
  `title` VARCHAR(128) NULL DEFAULT NULL COMMENT '标题' ,
  `author` VARCHAR(64) NULL DEFAULT NULL COMMENT '作者' ,
  `message` TEXT NULL DEFAULT NULL COMMENT '内容' ,
  `count` INT(11) NULL DEFAULT NULL COMMENT '访问次数' ,
  `allow` SMALLINT(6) NULL DEFAULT NULL COMMENT '是否允许显示，，是否通过管理员审核，1：通过，0：等待，-1 不通过' ,
  `time` TIMESTAMP NULL DEFAULT NULL COMMENT '添加时间' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8
COMMENT = '综合的新闻信息';


-- -----------------------------------------------------
-- Table `yi18`.`yi18_partner`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yi18`.`yi18_partner` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '合作伙伴的id编号' ,
  `title` VARCHAR(64) NOT NULL COMMENT '合作伙伴的标题' ,
  `logo` VARCHAR(128) NULL DEFAULT NULL COMMENT 'LOGO标志的地址' ,
  `url` VARCHAR(128) NULL DEFAULT NULL COMMENT '合作伙伴的网站' ,
  `description` VARCHAR(1024) NULL DEFAULT NULL COMMENT '合作伙伴的描述' ,
  `sequence` SMALLINT(6) NULL DEFAULT NULL COMMENT '合作伙伴的排列顺序' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COMMENT = '合作伙伴';


-- -----------------------------------------------------
-- Table `yi18`.`yi18_place`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yi18`.`yi18_place` (
  `id` BIGINT(20) NOT NULL ,
  `name` VARCHAR(64) NULL DEFAULT NULL COMMENT '名称' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '人的身体部分，主要是疾病的发病部分';


-- -----------------------------------------------------
-- Table `yi18`.`yi18_searchlog`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yi18`.`yi18_searchlog` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '搜索信息存储的记录' ,
  `keyword` VARCHAR(64) NOT NULL COMMENT '搜索的关键字' ,
  `searchcount` INT(11) NOT NULL COMMENT '搜索的次数' ,
  `resultcount` INT(11) NOT NULL COMMENT '搜索的结果总数' ,
  `updatetime` TIMESTAMP NULL DEFAULT NULL COMMENT '更新时间' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '用于信息搜索的记录信息';


-- -----------------------------------------------------
-- Table `yi18`.`yi18_symptomclass`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yi18`.`yi18_symptomclass` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '病状分类的Id编号' ,
  `title` VARCHAR(64) NOT NULL COMMENT '病状分类的标题' ,
  `level` SMALLINT(6) NOT NULL DEFAULT '1' COMMENT '分类的级别，0：root，1,2等' ,
  `_parentId` BIGINT(20) NULL DEFAULT NULL COMMENT '上级药品的id' ,
  `state` VARCHAR(45) NULL DEFAULT NULL ,
  `time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '添加药品分类的时间' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8
COMMENT = '病状分类，主要用于药品分类';


-- -----------------------------------------------------
-- Table `yi18`.`yi18_symptominfo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yi18`.`yi18_symptominfo` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '病状信息的id编号' ,
  `symptoms` BIGINT(20) NOT NULL COMMENT '病状的编号' ,
  `directory` BIGINT(20) NULL DEFAULT NULL COMMENT '病状的内容目录' ,
  `message` TEXT NULL DEFAULT NULL COMMENT '添加的内容' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 38
DEFAULT CHARACTER SET = utf8
COMMENT = '病状的基本信息';


-- -----------------------------------------------------
-- Table `yi18`.`yi18_symptoms`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yi18`.`yi18_symptoms` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(64) NOT NULL COMMENT '病状名称' ,
  `description` VARCHAR(1024) NULL DEFAULT NULL COMMENT '基本描述' ,
  `count` INT(11) NULL DEFAULT NULL COMMENT '访问次数' ,
  `symptomsclass` BIGINT(20) NULL DEFAULT NULL COMMENT '医药分类' ,
  `allow` SMALLINT(6) NULL DEFAULT NULL COMMENT '是否允许显示，，是否通过管理员审核，1：通过，0：等待，-1 不通过' ,
  `time` TIMESTAMP NULL DEFAULT NULL COMMENT '添加时间' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COMMENT = '病状信息';


-- -----------------------------------------------------
-- Table `yi18`.`yi18_user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `yi18`.`yi18_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `account` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL COMMENT '登录帐号' ,
  `password` VARCHAR(32) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL COMMENT '密码' ,
  `name` VARCHAR(10) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL COMMENT '姓名或昵称' ,
  `home` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL COMMENT '站内主页号' ,
  `image` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL COMMENT '头像' ,
  `email` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL COMMENT '邮箱' ,
  `phone` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL COMMENT '联系电话 手机，电话等' ,
  `qq` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL COMMENT 'QQ号' ,
  `url` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL COMMENT '个人主页网址' ,
  `sex` INT(1) NOT NULL DEFAULT '1' COMMENT '性别，1：男 ;0：女' ,
  `birth` TIMESTAMP NULL DEFAULT NULL COMMENT '出生年月' ,
  `area` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL COMMENT '地址如 四川省-成都市' ,
  `signature` VARCHAR(140) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL COMMENT '个性签名' ,
  `integral` INT(11) NOT NULL DEFAULT '0' COMMENT '用户积分' ,
  `isemail` INT(1) NOT NULL DEFAULT '0' COMMENT '是否显示邮箱 1：显示 0：不显示' ,
  `isphone` INT(1) NOT NULL DEFAULT '0' COMMENT '是否显示联系电话 1：显示 0：不显示' ,
  `isqq` INT(1) NOT NULL DEFAULT '0' COMMENT '是否显示qq号，1：显示，0：不显示' ,
  `isuse` INT(1) NOT NULL DEFAULT '1' COMMENT '是否可用，如禁止帐号，1：可用，0：不可用' ,
  `role` INT(1) NOT NULL DEFAULT '0' COMMENT '用户权限，0：普通用户，1：管理员' ,
  `hkey` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL COMMENT '钥匙，做认证' ,
  `time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '用户表，实现记录用户信息，登录验证。';

USE `yi18` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
