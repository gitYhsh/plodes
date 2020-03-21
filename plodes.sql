/*
Navicat MariaDB Data Transfer

Source Server         : mdb_204_3308
Source Server Version : 100313
Source Host           : 192.168.8.204:3308
Source Database       : godly

Target Server Type    : MariaDB
Target Server Version : 100313
File Encoding         : 65001

Date: 2020-03-21 09:56:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('xlcxxScheduler', 'TASK_null', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------
INSERT INTO `qrtz_fired_triggers` VALUES ('xlcxxScheduler', 'DESKTOP-9B1Q9MI15847555670031584755566995', 'TASK_null', 'DEFAULT', 'DESKTOP-9B1Q9MI1584755567003', '1584755610124', '1584755620000', '5', 'ACQUIRED', null, null, '0', '0');

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('xlcxxScheduler', 'TASK_null', 'DEFAULT', null, 'com.xlcxx.config.quartz.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720022636F6D2E786C6378782E636F6E6669672E71756172747A2E646F6D61696E2E4A6F62058D52AC1093A3040200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C000673746174757371007E00097870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000170FACA2BF97874000E302F3130202A202A202A202A203F70740004746573747400043131313174000FE6B58BE8AF95E5AE9AE697B6E599A8740001307800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('xlcxxScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('xlcxxScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('xlcxxScheduler', 'DESKTOP-9B1Q9MI1584755567003', '1584755599917', '15000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('xlcxxScheduler', 'TASK_null', 'DEFAULT', 'TASK_null', 'DEFAULT', null, '1584755620000', '1584755610000', '5', 'ACQUIRED', 'CRON', '1584755518000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720022636F6D2E786C6378782E636F6E6669672E71756172747A2E646F6D61696E2E4A6F62058D52AC1093A3040200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C000673746174757371007E0009787074000A51756172747A546573747372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000170FACAEADE7874000E302F3130202A202A202A202A203F70740004746573747400043131313174000FE6B58BE8AF95E5AE9AE697B6E599A8740001307800);

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `parent_id` bigint(20) NOT NULL COMMENT '上级部门ID',
  `dept_name` varchar(100) NOT NULL COMMENT '部门名称',
  `dept_status` varchar(255) NOT NULL DEFAULT '' COMMENT '是不是基础部门 0 否 1是 基础部门不能删除',
  `dept_type` varchar(255) NOT NULL DEFAULT '' COMMENT '是不是作为一级部门显示 0否，1是',
  `dept_del` varchar(255) NOT NULL DEFAULT '' COMMENT '部门是否删除了 0 否 1是',
  `dept_director` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '部门主管id',
  `dept_creattime` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8 COMMENT='部门';

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES ('1', '0', '新里程(山东)教育有限公司', '1', '0', '0', 0x2C, '2019-11-09 09:08:18');
INSERT INTO `t_dept` VALUES ('2', '1', '默认部门(同步钉钉)', '1', '0', '0', 0x313532383435323035392D313132393931363032, '2019-11-06 16:56:00');
INSERT INTO `t_dept` VALUES ('74', '1', '总经办', '0', '1', '0', '', '2019-11-23 13:30:13');
INSERT INTO `t_dept` VALUES ('75', '1', '职能管理部门', '0', '0', '0', null, '2019-11-23 13:30:14');
INSERT INTO `t_dept` VALUES ('76', '1', '技术开发', '0', '1', '0', 0x3039303936393436303131383934313531383436, '2019-11-23 13:30:19');
INSERT INTO `t_dept` VALUES ('77', '1', '教学教务部门', '0', '1', '0', '', '2019-11-23 13:30:20');
INSERT INTO `t_dept` VALUES ('78', '1', 'PPC运营事业部', '0', '1', '0', '', '2019-11-23 13:30:22');
INSERT INTO `t_dept` VALUES ('79', '1', '平台运行中心', '0', '1', '0', '', '2019-11-23 13:30:23');
INSERT INTO `t_dept` VALUES ('80', '1', '新媒体运营中心', '0', '1', '0', '', '2019-11-23 13:30:24');
INSERT INTO `t_dept` VALUES ('81', '1', '市场运营中心', '0', '1', '0', '', '2019-11-05 14:49:05');
INSERT INTO `t_dept` VALUES ('82', '81', '聚学U', '0', '0', '1', '', '2019-11-05 14:53:44');
INSERT INTO `t_dept` VALUES ('83', '1', '技术开发部', '0', '0', '0', null, '2019-11-09 17:12:09');
INSERT INTO `t_dept` VALUES ('84', '1', '测试部门', '0', '0', '1', null, '2019-11-12 15:16:12');
INSERT INTO `t_dept` VALUES ('85', '83', '技术开发一部', '0', '0', '0', null, '2019-11-12 15:17:12');
INSERT INTO `t_dept` VALUES ('86', '81', '运营中心1', '0', '0', '0', null, '2019-11-12 15:36:12');
INSERT INTO `t_dept` VALUES ('87', '1', '测试', '0', '0', '1', null, '2019-11-12 15:54:12');
INSERT INTO `t_dept` VALUES ('88', '1', '111', '0', '0', '1', null, '2019-11-12 15:55:12');
INSERT INTO `t_dept` VALUES ('89', '1', '技术测试11', '0', '1', '0', null, '2019-11-29 10:40:29');
INSERT INTO `t_dept` VALUES ('90', '75', '1213', '0', '0', '0', null, '2019-11-29 11:29:29');
INSERT INTO `t_dept` VALUES ('91', '1', '技术1', '0', '0', '0', null, '2019-12-26 14:07:26');

-- ----------------------------
-- Table structure for t_job
-- ----------------------------
DROP TABLE IF EXISTS `t_job`;
CREATE TABLE `t_job` (
  `JOB_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `BEAN_NAME` varchar(100) NOT NULL COMMENT 'spring bean名称',
  `METHOD_NAME` varchar(100) NOT NULL COMMENT '方法名',
  `PARAMS` varchar(200) DEFAULT NULL COMMENT '参数',
  `CRON_EXPRESSION` varchar(100) NOT NULL COMMENT 'cron表达式',
  `STATUS` char(2) NOT NULL COMMENT '任务状态  0：正常  1：暂停',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`JOB_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_job
-- ----------------------------
INSERT INTO `t_job` VALUES ('2', 'testTask', 'test', '1111', '0/10 * * * * ?', '0', '测试定时器', '2019-11-12 17:06:23');

-- ----------------------------
-- Table structure for t_job_log
-- ----------------------------
DROP TABLE IF EXISTS `t_job_log`;
CREATE TABLE `t_job_log` (
  `LOG_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `JOB_ID` bigint(20) NOT NULL COMMENT '任务id',
  `BEAN_NAME` varchar(100) NOT NULL COMMENT 'spring bean名称',
  `METHOD_NAME` varchar(100) NOT NULL COMMENT '方法名',
  `PARAMS` varchar(200) DEFAULT NULL COMMENT '参数',
  `STATUS` char(2) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `ERROR` text DEFAULT NULL COMMENT '失败信息',
  `TIMES` decimal(11,0) DEFAULT NULL COMMENT '耗时(单位：毫秒)',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=42284 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `USERNAME` varchar(50) DEFAULT '' COMMENT '操作用户或者ip',
  `OPERATION` text DEFAULT NULL COMMENT '操作内容',
  `TIME` decimal(11,0) DEFAULT NULL COMMENT '耗时',
  `METHOD` text DEFAULT NULL COMMENT '操作方法',
  `PARAMS` text DEFAULT NULL COMMENT '方法参数',
  `IP` varchar(64) DEFAULT NULL COMMENT '操作者IP',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `LOCATION` varchar(25) DEFAULT '' COMMENT '操作地点',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=78061 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `MENU_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单/按钮ID',
  `PARENT_ID` bigint(20) NOT NULL COMMENT '上级菜单ID',
  `MENU_NAME` varchar(50) NOT NULL COMMENT '菜单/按钮名称',
  `URL` varchar(100) DEFAULT NULL COMMENT '菜单URL',
  `PERMS` text DEFAULT NULL COMMENT '权限标识',
  `ICON` varchar(50) DEFAULT NULL COMMENT '图标',
  `TYPE` char(2) NOT NULL COMMENT '类型 0菜单 1按钮',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=238 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '0', '系统设置', 'layout/index', '', 'fa  fa-sitemap', '0', '2019-11-30 09:52:44', '2018-12-30 09:23:56');
INSERT INTO `t_menu` VALUES ('2', '1', '通讯录管理', 'system/connect', '', 'fa fa-2x fa-maxcdn', '0', '2019-11-30 09:53:44', null);
INSERT INTO `t_menu` VALUES ('3', '0', '首页', 'layout/index', null, 'fa fa-home', '0', '2019-11-01 09:13:12', null);
INSERT INTO `t_menu` VALUES ('5', '1', '菜单管理', 'system/menu', '', 'fa fa-2x fa-sliders', '0', '2019-11-30 09:54:01', '2018-04-25 09:01:30');
INSERT INTO `t_menu` VALUES ('27', '1', '基础设置', 'system/basicconfig', null, 'fa fa-database', '0', '2019-11-30 10:27:31', null);
INSERT INTO `t_menu` VALUES ('28', '1', '定时任务', 'system/timetask', null, 'fa fa-file-audio-o', '0', '2019-11-30 10:29:14', null);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `ROLE_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `ROLE_NAME` varchar(100) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('5', '管理员');
INSERT INTO `t_role` VALUES ('6', '主管');
INSERT INTO `t_role` VALUES ('7', '普通员工');
INSERT INTO `t_role` VALUES ('22', '总经理');
INSERT INTO `t_role` VALUES ('24', '技术部');
INSERT INTO `t_role` VALUES ('25', '杨洪升');
INSERT INTO `t_role` VALUES ('26', '设计');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色ID',
  `MENU_ID` bigint(20) NOT NULL COMMENT '菜单/按钮ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('5', '4');
INSERT INTO `t_role_menu` VALUES ('5', '6');
INSERT INTO `t_role_menu` VALUES ('5', '7');
INSERT INTO `t_role_menu` VALUES ('5', '8');
INSERT INTO `t_role_menu` VALUES ('5', '9');
INSERT INTO `t_role_menu` VALUES ('5', '10');
INSERT INTO `t_role_menu` VALUES ('5', '11');
INSERT INTO `t_role_menu` VALUES ('5', '30');
INSERT INTO `t_role_menu` VALUES ('5', '12');
INSERT INTO `t_role_menu` VALUES ('5', '13');
INSERT INTO `t_role_menu` VALUES ('5', '14');
INSERT INTO `t_role_menu` VALUES ('5', '15');
INSERT INTO `t_role_menu` VALUES ('5', '16');
INSERT INTO `t_role_menu` VALUES ('5', '17');
INSERT INTO `t_role_menu` VALUES ('5', '18');
INSERT INTO `t_role_menu` VALUES ('5', '19');
INSERT INTO `t_role_menu` VALUES ('5', '20');
INSERT INTO `t_role_menu` VALUES ('5', '21');
INSERT INTO `t_role_menu` VALUES ('5', '22');
INSERT INTO `t_role_menu` VALUES ('5', '23');
INSERT INTO `t_role_menu` VALUES ('5', '24');
INSERT INTO `t_role_menu` VALUES ('5', '25');
INSERT INTO `t_role_menu` VALUES ('5', '26');
INSERT INTO `t_role_menu` VALUES ('5', '1');
INSERT INTO `t_role_menu` VALUES ('5', '31');
INSERT INTO `t_role_menu` VALUES ('5', '2');
INSERT INTO `t_role_menu` VALUES ('5', '5');
INSERT INTO `t_role_menu` VALUES ('5', '27');
INSERT INTO `t_role_menu` VALUES ('5', '28');
INSERT INTO `t_role_menu` VALUES ('5', '32');
INSERT INTO `t_role_menu` VALUES ('5', '33');
INSERT INTO `t_role_menu` VALUES ('5', '34');
INSERT INTO `t_role_menu` VALUES ('5', '35');
INSERT INTO `t_role_menu` VALUES ('5', '3');
INSERT INTO `t_role_menu` VALUES ('5', '38');
INSERT INTO `t_role_menu` VALUES ('5', '40');
INSERT INTO `t_role_menu` VALUES ('5', '41');
INSERT INTO `t_role_menu` VALUES ('25', '3');
INSERT INTO `t_role_menu` VALUES ('25', '4');
INSERT INTO `t_role_menu` VALUES ('25', '11');
INSERT INTO `t_role_menu` VALUES ('25', '12');
INSERT INTO `t_role_menu` VALUES ('25', '23');
INSERT INTO `t_role_menu` VALUES ('25', '33');
INSERT INTO `t_role_menu` VALUES ('25', '17');
INSERT INTO `t_role_menu` VALUES ('25', '32');
INSERT INTO `t_role_menu` VALUES ('25', '34');
INSERT INTO `t_role_menu` VALUES ('25', '1');
INSERT INTO `t_role_menu` VALUES ('25', '6');
INSERT INTO `t_role_menu` VALUES ('25', '7');
INSERT INTO `t_role_menu` VALUES ('25', '8');
INSERT INTO `t_role_menu` VALUES ('25', '9');
INSERT INTO `t_role_menu` VALUES ('25', '10');
INSERT INTO `t_role_menu` VALUES ('25', '38');
INSERT INTO `t_role_menu` VALUES ('25', '39');
INSERT INTO `t_role_menu` VALUES ('25', '2');
INSERT INTO `t_role_menu` VALUES ('25', '5');
INSERT INTO `t_role_menu` VALUES ('25', '13');
INSERT INTO `t_role_menu` VALUES ('25', '14');
INSERT INTO `t_role_menu` VALUES ('25', '15');
INSERT INTO `t_role_menu` VALUES ('25', '16');
INSERT INTO `t_role_menu` VALUES ('25', '41');
INSERT INTO `t_role_menu` VALUES ('25', '42');
INSERT INTO `t_role_menu` VALUES ('25', '43');
INSERT INTO `t_role_menu` VALUES ('25', '40');
INSERT INTO `t_role_menu` VALUES ('25', '18');
INSERT INTO `t_role_menu` VALUES ('25', '19');
INSERT INTO `t_role_menu` VALUES ('25', '20');
INSERT INTO `t_role_menu` VALUES ('25', '44');
INSERT INTO `t_role_menu` VALUES ('25', '21');
INSERT INTO `t_role_menu` VALUES ('25', '22');
INSERT INTO `t_role_menu` VALUES ('25', '24');
INSERT INTO `t_role_menu` VALUES ('5', '44');
INSERT INTO `t_role_menu` VALUES ('5', '42');
INSERT INTO `t_role_menu` VALUES ('5', '43');
INSERT INTO `t_role_menu` VALUES ('25', '47');
INSERT INTO `t_role_menu` VALUES ('5', '48');
INSERT INTO `t_role_menu` VALUES ('5', '47');
INSERT INTO `t_role_menu` VALUES ('25', '29');
INSERT INTO `t_role_menu` VALUES ('5', '45');
INSERT INTO `t_role_menu` VALUES ('5', '46');
INSERT INTO `t_role_menu` VALUES ('5', '37');
INSERT INTO `t_role_menu` VALUES ('5', '36');
INSERT INTO `t_role_menu` VALUES ('5', '39');
INSERT INTO `t_role_menu` VALUES ('25', '45');
INSERT INTO `t_role_menu` VALUES ('25', '46');
INSERT INTO `t_role_menu` VALUES ('5', '29');
INSERT INTO `t_role_menu` VALUES ('25', '25');
INSERT INTO `t_role_menu` VALUES ('25', '26');
INSERT INTO `t_role_menu` VALUES ('25', '35');
INSERT INTO `t_role_menu` VALUES ('25', '48');
INSERT INTO `t_role_menu` VALUES ('25', '37');
INSERT INTO `t_role_menu` VALUES ('25', '36');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` varchar(50) NOT NULL COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT '' COMMENT '昵称',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `status` char(1) NOT NULL COMMENT '状态 1锁定 0有效',
  PRIMARY KEY (`user_id`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('0101695108880048494', '0101695108880048494', '$2a$10$pkcQ/AtqgDomOSBm6z//mO8SavhxrHDyRs8GztByjQSbhuzbpwI92', '柴坤', '74', '1');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `USER_ID` varchar(50) NOT NULL COMMENT '用户ID',
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1526092607-1594655110', '5');
