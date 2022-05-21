/*
 Navicat Premium Data Transfer

 Source Server         : newLinked
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : katydid_online_edu

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 21/05/2022 17:13:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for acl_permission
-- ----------------------------
DROP TABLE IF EXISTS `acl_permission`;
CREATE TABLE `acl_permission`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '编号',
  `pid` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '所属上级',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名称',
  `type` tinyint(0) NOT NULL DEFAULT 0 COMMENT '类型(1:菜单,2:按钮)',
  `permission_value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限值',
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '访问路径',
  `component` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件路径',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '状态(0:禁止,1:正常)',
  `is_deleted` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_pid`(`pid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of acl_permission
-- ----------------------------
INSERT INTO `acl_permission` VALUES ('1', '0', '全部数据', 0, '', '', '', '', NULL, 0, '2019-11-15 17:13:06', '2019-11-15 17:13:06');
INSERT INTO `acl_permission` VALUES ('1195268474480156673', '1', '权限管理', 1, NULL, '/acl', 'Layout', 'eye', NULL, 0, '2019-11-15 17:13:06', '2019-11-18 13:54:25');
INSERT INTO `acl_permission` VALUES ('1195268616021139457', '1195268474480156673', '用户管理', 1, NULL, 'user/list', '/acl/user/list', NULL, NULL, 0, '2019-11-15 17:13:40', '2019-11-18 13:53:12');
INSERT INTO `acl_permission` VALUES ('1195268788138598401', '1195268474480156673', '角色管理', 1, NULL, 'role/list', '/acl/role/list', NULL, NULL, 0, '2019-11-15 17:14:21', '2019-11-15 17:14:21');
INSERT INTO `acl_permission` VALUES ('1195268893830864898', '1195268474480156673', '菜单管理', 1, NULL, 'menu/list', '/acl/menu/list', NULL, NULL, 0, '2019-11-15 17:14:46', '2019-11-15 17:14:46');
INSERT INTO `acl_permission` VALUES ('1195269143060602882', '1195268616021139457', '查看', 2, 'user.list', '', '', NULL, NULL, 0, '2019-11-15 17:15:45', '2019-11-17 21:57:16');
INSERT INTO `acl_permission` VALUES ('1195269295926206466', '1195268616021139457', '添加', 2, 'user.add', 'user/add', '/acl/user/form', NULL, NULL, 0, '2019-11-15 17:16:22', '2019-11-15 17:16:22');
INSERT INTO `acl_permission` VALUES ('1195269473479483394', '1195268616021139457', '修改', 2, 'user.update', 'user/update/:id', '/acl/user/form', NULL, NULL, 0, '2019-11-15 17:17:04', '2019-11-15 17:17:04');
INSERT INTO `acl_permission` VALUES ('1195269547269873666', '1195268616021139457', '删除', 2, 'user.remove', '', '', NULL, NULL, 0, '2019-11-15 17:17:22', '2019-11-15 17:17:22');
INSERT INTO `acl_permission` VALUES ('1195269821262782465', '1195268788138598401', '修改', 2, 'role.update', 'role/update/:id', '/acl/role/form', NULL, NULL, 0, '2019-11-15 17:18:27', '2019-11-15 17:19:53');
INSERT INTO `acl_permission` VALUES ('1195269903542444034', '1195268788138598401', '查看', 2, 'role.list', '', '', NULL, NULL, 0, '2019-11-15 17:18:47', '2019-11-15 17:18:47');
INSERT INTO `acl_permission` VALUES ('1195270037005197313', '1195268788138598401', '添加', 2, 'role.add', 'role/add', '/acl/role/form', NULL, NULL, 0, '2019-11-15 17:19:19', '2019-11-18 11:05:42');
INSERT INTO `acl_permission` VALUES ('1195270442602782721', '1195268788138598401', '删除', 2, 'role.remove', '', '', NULL, NULL, 0, '2019-11-15 17:20:55', '2019-11-15 17:20:55');
INSERT INTO `acl_permission` VALUES ('1195270621548568578', '1195268788138598401', '角色权限', 2, 'role.acl', 'role/distribution/:id', '/acl/role/roleForm', NULL, NULL, 0, '2019-11-15 17:21:38', '2019-11-15 17:21:38');
INSERT INTO `acl_permission` VALUES ('1195270744097742849', '1195268893830864898', '查看', 2, 'permission.list', '', '', NULL, NULL, 0, '2019-11-15 17:22:07', '2019-11-15 17:22:07');
INSERT INTO `acl_permission` VALUES ('1195270810560684034', '1195268893830864898', '添加', 2, 'permission.add', '', '', NULL, NULL, 0, '2019-11-15 17:22:23', '2019-11-15 17:22:23');
INSERT INTO `acl_permission` VALUES ('1195270862100291586', '1195268893830864898', '修改', 2, 'permission.update', '', '', NULL, NULL, 0, '2019-11-15 17:22:35', '2019-11-15 17:22:35');
INSERT INTO `acl_permission` VALUES ('1195270887933009922', '1195268893830864898', '删除', 2, 'permission.remove', '', '', NULL, NULL, 0, '2019-11-15 17:22:41', '2019-11-15 17:22:41');
INSERT INTO `acl_permission` VALUES ('1195349439240048642', '1', '讲师管理', 1, '', '/teacher', 'Layout', 'peoples', NULL, 0, '2019-11-15 22:34:49', '2019-11-15 22:34:49');
INSERT INTO `acl_permission` VALUES ('1195349699995734017', '1195349439240048642', '讲师列表', 1, NULL, 'table', '/edu/teacher/list', NULL, NULL, 0, '2019-11-15 22:35:52', '2019-11-15 22:35:52');
INSERT INTO `acl_permission` VALUES ('1195349810561781761', '1195349439240048642', '添加讲师', 1, NULL, 'save', '/edu/teacher/save', NULL, NULL, 0, '2019-11-15 22:36:18', '2019-11-15 22:36:18');
INSERT INTO `acl_permission` VALUES ('1195349876252971010', '1195349810561781761', '添加', 2, 'teacher.add', '', '', NULL, NULL, 0, '2019-11-15 22:36:34', '2019-11-15 22:36:34');
INSERT INTO `acl_permission` VALUES ('1195349979797753857', '1195349699995734017', '查看', 2, 'teacher.list', '', '', NULL, NULL, 0, '2019-11-15 22:36:58', '2019-11-15 22:36:58');
INSERT INTO `acl_permission` VALUES ('1195350117270261762', '1195349699995734017', '修改', 2, 'teacher.update', 'edit/:id', '/edu/teacher/save', NULL, NULL, 0, '2019-11-15 22:37:31', '2019-11-15 22:37:31');
INSERT INTO `acl_permission` VALUES ('1195350188359520258', '1195349699995734017', '删除', 2, 'teacher.remove', '', '', NULL, NULL, 0, '2019-11-15 22:37:48', '2019-11-15 22:37:48');
INSERT INTO `acl_permission` VALUES ('1195350299365969922', '1', '课程分类', 1, NULL, '/subject', 'Layout', 'nested', NULL, 0, '2019-11-15 22:38:15', '2019-11-15 22:38:15');
INSERT INTO `acl_permission` VALUES ('1195350397751758850', '1195350299365969922', '课程分类列表', 1, NULL, 'list', '/edu/subject/list', NULL, NULL, 0, '2019-11-15 22:38:38', '2019-11-15 22:38:38');
INSERT INTO `acl_permission` VALUES ('1195350500512206850', '1195350299365969922', '导入课程分类', 1, NULL, 'save', '/edu/subject/save', NULL, NULL, 0, '2019-11-15 22:39:03', '2019-11-15 22:39:03');
INSERT INTO `acl_permission` VALUES ('1195350612172967938', '1195350397751758850', '查看', 2, 'subject.list', '', '', NULL, NULL, 0, '2019-11-15 22:39:29', '2019-11-15 22:39:29');
INSERT INTO `acl_permission` VALUES ('1195350687590748161', '1195350500512206850', '导入', 2, 'subject.import', '', '', NULL, NULL, 0, '2019-11-15 22:39:47', '2019-11-15 22:39:47');
INSERT INTO `acl_permission` VALUES ('1195350831744782337', '1', '课程管理', 1, NULL, '/course', 'Layout', 'clipboard', NULL, 0, '2019-11-15 22:40:21', '2019-11-15 22:40:21');
INSERT INTO `acl_permission` VALUES ('1195350919074385921', '1195350831744782337', '课程列表', 1, NULL, 'list', '/edu/course/list', NULL, NULL, 0, '2019-11-15 22:40:42', '2019-11-15 22:40:42');
INSERT INTO `acl_permission` VALUES ('1195351020463296513', '1195350831744782337', '发布课程', 1, NULL, 'info', '/edu/course/info', NULL, NULL, 0, '2019-11-15 22:41:06', '2019-11-15 22:41:06');
INSERT INTO `acl_permission` VALUES ('1195351159672246274', '1195350919074385921', '完成发布', 2, 'course.publish', 'publish/:id', '/edu/course/publish', NULL, NULL, 0, '2019-11-15 22:41:40', '2019-11-15 22:44:01');
INSERT INTO `acl_permission` VALUES ('1195351326706208770', '1195350919074385921', '编辑课程', 2, 'course.update', 'info/:id', '/edu/course/info', NULL, NULL, 0, '2019-11-15 22:42:19', '2019-11-15 22:42:19');
INSERT INTO `acl_permission` VALUES ('1195351566221938690', '1195350919074385921', '编辑课程大纲', 2, 'chapter.update', 'chapter/:id', '/edu/course/chapter', NULL, NULL, 0, '2019-11-15 22:43:17', '2019-11-15 22:43:17');
INSERT INTO `acl_permission` VALUES ('1195351862889254913', '1', '统计分析', 1, NULL, '/statistic', 'Layout', 'list', NULL, 0, '2019-11-15 22:44:27', '2022-04-23 12:47:11');
INSERT INTO `acl_permission` VALUES ('1195351968841568257', '1195351862889254913', '生成统计', 1, NULL, 'create', '/edu/statistic/create', NULL, NULL, 0, '2019-11-15 22:44:53', '2022-04-23 13:24:21');
INSERT INTO `acl_permission` VALUES ('1195352054917074946', '1195351862889254913', '统计图表', 1, NULL, 'show', '/edu/statistic/show', NULL, NULL, 0, '2019-11-15 22:45:13', '2022-04-23 13:24:16');
INSERT INTO `acl_permission` VALUES ('1195352127734386690', '1195352054917074946', '查看', 2, 'daily.list', '', '', NULL, NULL, 0, '2019-11-15 22:45:30', '2019-11-15 22:45:30');
INSERT INTO `acl_permission` VALUES ('1195352215768633346', '1195351968841568257', '生成', 2, 'daily.add', '', '', NULL, NULL, 0, '2019-11-15 22:45:51', '2019-11-15 22:45:51');
INSERT INTO `acl_permission` VALUES ('1196301740985311234', '1195268616021139457', '分配角色', 2, 'user.assgin', 'user/role/:id', '/acl/user/roleForm', NULL, NULL, 0, '2019-11-18 13:38:56', '2019-11-18 13:38:56');

-- ----------------------------
-- Table structure for acl_role
-- ----------------------------
DROP TABLE IF EXISTS `acl_role`;
CREATE TABLE `acl_role`  (
  `id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '角色id',
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `is_deleted` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of acl_role
-- ----------------------------
INSERT INTO `acl_role` VALUES ('1', '超级管理员', NULL, NULL, 0, '2022-11-11 13:09:32', '2022-08-21 11:59:37');
INSERT INTO `acl_role` VALUES ('1517740085666082817', '普通管理员', NULL, NULL, 0, '2022-04-23 13:40:09', '2022-04-23 13:43:09');
INSERT INTO `acl_role` VALUES ('1517740417183870977', '讲师管理员', NULL, NULL, 0, '2022-04-23 13:41:28', '2022-04-23 13:41:28');
INSERT INTO `acl_role` VALUES ('1517740531642232833', '课程管理员', NULL, NULL, 0, '2022-04-23 13:41:56', '2022-04-23 13:42:01');

-- ----------------------------
-- Table structure for acl_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `acl_role_permission`;
CREATE TABLE `acl_role_permission`  (
  `id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `role_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `permission_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `is_deleted` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE,
  INDEX `idx_permission_id`(`permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of acl_role_permission
-- ----------------------------
INSERT INTO `acl_role_permission` VALUES ('1517739174541619202', '1', '1', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619203', '1', '1195268474480156673', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619204', '1', '1195268616021139457', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619205', '1', '1195269143060602882', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619206', '1', '1195269295926206466', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619207', '1', '1195269473479483394', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619208', '1', '1195269547269873666', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619209', '1', '1196301740985311234', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619210', '1', '1195268788138598401', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619211', '1', '1195269821262782465', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619212', '1', '1195269903542444034', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619213', '1', '1195270037005197313', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619214', '1', '1195270442602782721', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619215', '1', '1195270621548568578', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619216', '1', '1195268893830864898', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619217', '1', '1195270744097742849', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619218', '1', '1195270810560684034', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619219', '1', '1195270862100291586', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619220', '1', '1195270887933009922', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619221', '1', '1195349439240048642', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619222', '1', '1195349699995734017', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619223', '1', '1195349979797753857', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619224', '1', '1195350117270261762', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619225', '1', '1195350188359520258', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619226', '1', '1195349810561781761', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619227', '1', '1195349876252971010', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619228', '1', '1195350299365969922', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619229', '1', '1195350397751758850', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619230', '1', '1195350612172967938', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619231', '1', '1195350500512206850', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174541619232', '1', '1195350687590748161', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174797471746', '1', '1195350831744782337', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174797471747', '1', '1195350919074385921', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174797471748', '1', '1195351159672246274', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174797471749', '1', '1195351326706208770', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174797471750', '1', '1195351566221938690', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174797471751', '1', '1195351020463296513', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174797471752', '1', '1195351862889254913', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174797471753', '1', '1195351968841568257', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174797471754', '1', '1195352215768633346', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174797471755', '1', '1195352054917074946', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517739174797471756', '1', '1195352127734386690', 0, '2022-04-23 13:36:32', '2022-04-23 13:36:32');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803778', '1517740085666082817', '1', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803779', '1517740085666082817', '1195349439240048642', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803780', '1517740085666082817', '1195349699995734017', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803781', '1517740085666082817', '1195349979797753857', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803782', '1517740085666082817', '1195350117270261762', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803783', '1517740085666082817', '1195350188359520258', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803784', '1517740085666082817', '1195349810561781761', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803785', '1517740085666082817', '1195349876252971010', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803786', '1517740085666082817', '1195350299365969922', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803787', '1517740085666082817', '1195350397751758850', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803788', '1517740085666082817', '1195350612172967938', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803789', '1517740085666082817', '1195350500512206850', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803790', '1517740085666082817', '1195350687590748161', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803791', '1517740085666082817', '1195350831744782337', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803792', '1517740085666082817', '1195350919074385921', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803793', '1517740085666082817', '1195351159672246274', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803794', '1517740085666082817', '1195351326706208770', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803795', '1517740085666082817', '1195351566221938690', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803796', '1517740085666082817', '1195351020463296513', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803797', '1517740085666082817', '1195351862889254913', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803798', '1517740085666082817', '1195351968841568257', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803799', '1517740085666082817', '1195352215768633346', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803800', '1517740085666082817', '1195352054917074946', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740336233803801', '1517740085666082817', '1195352127734386690', 0, '2022-04-23 13:41:09', '2022-04-23 13:41:09');
INSERT INTO `acl_role_permission` VALUES ('1517740489334288386', '1517740417183870977', '1', 0, '2022-04-23 13:41:46', '2022-04-23 13:41:46');
INSERT INTO `acl_role_permission` VALUES ('1517740489334288387', '1517740417183870977', '1195349439240048642', 0, '2022-04-23 13:41:46', '2022-04-23 13:41:46');
INSERT INTO `acl_role_permission` VALUES ('1517740489334288388', '1517740417183870977', '1195349699995734017', 0, '2022-04-23 13:41:46', '2022-04-23 13:41:46');
INSERT INTO `acl_role_permission` VALUES ('1517740489334288389', '1517740417183870977', '1195349979797753857', 0, '2022-04-23 13:41:46', '2022-04-23 13:41:46');
INSERT INTO `acl_role_permission` VALUES ('1517740489334288390', '1517740417183870977', '1195350117270261762', 0, '2022-04-23 13:41:46', '2022-04-23 13:41:46');
INSERT INTO `acl_role_permission` VALUES ('1517740489334288391', '1517740417183870977', '1195350188359520258', 0, '2022-04-23 13:41:46', '2022-04-23 13:41:46');
INSERT INTO `acl_role_permission` VALUES ('1517740489334288392', '1517740417183870977', '1195349810561781761', 0, '2022-04-23 13:41:46', '2022-04-23 13:41:46');
INSERT INTO `acl_role_permission` VALUES ('1517740489334288393', '1517740417183870977', '1195349876252971010', 0, '2022-04-23 13:41:46', '2022-04-23 13:41:46');
INSERT INTO `acl_role_permission` VALUES ('1517740590379266049', '1517740531642232833', '1', 0, '2022-04-23 13:42:10', '2022-04-23 13:42:10');
INSERT INTO `acl_role_permission` VALUES ('1517740590379266050', '1517740531642232833', '1195350831744782337', 0, '2022-04-23 13:42:10', '2022-04-23 13:42:10');
INSERT INTO `acl_role_permission` VALUES ('1517740590379266051', '1517740531642232833', '1195350919074385921', 0, '2022-04-23 13:42:10', '2022-04-23 13:42:10');
INSERT INTO `acl_role_permission` VALUES ('1517740590379266052', '1517740531642232833', '1195351159672246274', 0, '2022-04-23 13:42:10', '2022-04-23 13:42:10');
INSERT INTO `acl_role_permission` VALUES ('1517740590379266053', '1517740531642232833', '1195351326706208770', 0, '2022-04-23 13:42:10', '2022-04-23 13:42:10');
INSERT INTO `acl_role_permission` VALUES ('1517740590379266054', '1517740531642232833', '1195351566221938690', 0, '2022-04-23 13:42:10', '2022-04-23 13:42:10');
INSERT INTO `acl_role_permission` VALUES ('1517740590379266055', '1517740531642232833', '1195351020463296513', 0, '2022-04-23 13:42:10', '2022-04-23 13:42:10');

-- ----------------------------
-- Table structure for acl_user
-- ----------------------------
DROP TABLE IF EXISTS `acl_user`;
CREATE TABLE `acl_user`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '会员id',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '微信openid',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '密码',
  `nick_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `token` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户签名',
  `is_deleted` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of acl_user
-- ----------------------------
INSERT INTO `acl_user` VALUES ('1', 'admin', '7b14a3d34930e4d67cddfa53588dbd82', 'admin', '', NULL, 0, '2019-11-01 10:39:47', '2019-11-01 10:39:47');
INSERT INTO `acl_user` VALUES ('1517739984512053250', 'funThree', 'c28edaf4df92764868f8b8ed7d524228', '有趣的Three', NULL, NULL, 0, '2022-04-23 13:39:45', '2022-04-23 13:39:45');

-- ----------------------------
-- Table structure for acl_user_role
-- ----------------------------
DROP TABLE IF EXISTS `acl_user_role`;
CREATE TABLE `acl_user_role`  (
  `id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '主键id',
  `role_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '角色id',
  `user_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '用户id',
  `is_deleted` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of acl_user_role
-- ----------------------------
INSERT INTO `acl_user_role` VALUES ('1', '1', '1', 0, '2022-04-19 23:17:34', '2022-04-19 23:17:36');
INSERT INTO `acl_user_role` VALUES ('1517741167779737601', '1517740085666082817', '1517739984512053250', 0, '2022-04-23 13:44:27', '2022-04-23 13:44:27');

-- ----------------------------
-- Table structure for crm_banner
-- ----------------------------
DROP TABLE IF EXISTS `crm_banner`;
CREATE TABLE `crm_banner`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'ID',
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '标题',
  `image_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '图片地址',
  `link_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '链接地址',
  `sort` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
  `is_deleted` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`title`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '首页banner表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crm_banner
-- ----------------------------
INSERT INTO `crm_banner` VALUES ('1515880048228233217', 'banner1', 'https://katydid-online.oss-cn-beijing.aliyuncs.com/banner.jpg', '#', 0, 0, '2022-04-18 10:29:02', '2022-04-18 10:29:02');

-- ----------------------------
-- Table structure for edu_chapter
-- ----------------------------
DROP TABLE IF EXISTS `edu_chapter`;
CREATE TABLE `edu_chapter`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '章节ID',
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '章节名称',
  `sort` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '显示排序',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_chapter
-- ----------------------------
INSERT INTO `edu_chapter` VALUES ('1510979189828710402', '1510979153950633986', '一、', 0, '2022-04-04 21:54:46', '2022-04-04 21:54:46');
INSERT INTO `edu_chapter` VALUES ('1510984514082402306', '1510984492876001282', '一、', 0, '2022-04-04 22:15:56', '2022-04-04 22:18:04');
INSERT INTO `edu_chapter` VALUES ('1510984530284998658', '1510984492876001282', '二、', 0, '2022-04-04 22:15:59', '2022-04-04 22:15:59');
INSERT INTO `edu_chapter` VALUES ('1517749148919480322', '1517749117747412994', '一', 0, '2022-04-23 14:16:10', '2022-04-23 14:16:10');

-- ----------------------------
-- Table structure for edu_comment
-- ----------------------------
DROP TABLE IF EXISTS `edu_comment`;
CREATE TABLE `edu_comment`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '讲师ID',
  `course_id` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '课程id',
  `teacher_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '讲师id',
  `member_id` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '会员id',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '会员昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '会员头像',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论内容',
  `is_deleted` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE,
  INDEX `idx_teacher_id`(`teacher_id`) USING BTREE,
  INDEX `idx_member_id`(`member_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评论' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for edu_course
-- ----------------------------
DROP TABLE IF EXISTS `edu_course`;
CREATE TABLE `edu_course`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程ID',
  `teacher_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程讲师ID',
  `subject_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程专业ID',
  `subject_parent_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程标题',
  `price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '课程销售价格，设置为0则可免费观看',
  `lesson_num` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '总课时',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程封面图片路径',
  `buy_count` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '销售数量',
  `view_count` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '浏览数量',
  `version` bigint(0) UNSIGNED NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Draft' COMMENT '课程状态 Draft未发布  Normal已发布',
  `is_deleted` tinyint(0) NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_title`(`title`) USING BTREE,
  INDEX `idx_subject_id`(`subject_id`) USING BTREE,
  INDEX `idx_teacher_id`(`teacher_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_course
-- ----------------------------
INSERT INTO `edu_course` VALUES ('1510979153950633986', '1497147163438710785', '1504431406426607619', '1504431406359498754', 'JVAV', 0.00, 10, 'https://katydid-online.oss-cn-beijing.aliyuncs.com/2022-04/5daf1aa5-f4a9-46ec-aeaa-74944d08c5451442295581911.jpg', 0, 0, 1, 'Normal', 0, '2022-04-04 21:54:38', '2022-04-18 11:57:39');
INSERT INTO `edu_course` VALUES ('1510984492876001282', '1497147163438710785', '1507012117910220802', '1504660301377634305', '在线教育', 0.00, 20, 'https://katydid-online.oss-cn-beijing.aliyuncs.com/2022-04/cf10a845-7ef0-495e-a8bc-2c378260243d12ef0fe1bd966d8fabd1b40964dbd63a.jpg', 0, 0, 1, 'Normal', 0, '2022-04-04 22:15:51', '2022-04-04 22:15:51');
INSERT INTO `edu_course` VALUES ('1517748975032025090', '1501756736371900417', '1507012117973135362', '1507012117910220803', '测试课程', 0.00, 1, 'https://katydid-online.oss-cn-beijing.aliyuncs.com/2022-04/5eeab487-16a4-44f4-a544-c09a22aa154ddefault.gif', 0, 0, 1, 'Normal', 0, '2022-04-23 14:15:29', '2022-04-23 14:15:34');
INSERT INTO `edu_course` VALUES ('1517749117747412994', '1501756736371900417', '1507012117973135362', '1507012117910220803', '测试课程2', 0.00, 1, 'https://katydid-online.oss-cn-beijing.aliyuncs.com/2022-04/cd409a8f-e574-4794-86d2-8a8900ce190fdefault.gif', 0, 0, 1, 'Normal', 0, '2022-04-23 14:16:03', '2022-04-23 14:16:22');
INSERT INTO `edu_course` VALUES ('1517749433045827586', '1501756736371900417', '1507012117973135362', '1507012117910220803', '测试信息3', 0.00, 2, 'https://katydid-online.oss-cn-beijing.aliyuncs.com/2022-04/06a0d23c-1b45-44ac-8b84-e7582dc5bb7bcourse2.jpg', 0, 0, 1, 'Normal', 0, '2022-04-23 14:17:18', '2022-04-23 14:17:23');
INSERT INTO `edu_course` VALUES ('1517749646246494210', '1501756736371900417', '1507012117973135362', '1507012117910220803', '测试课程4', 0.00, 1, 'https://katydid-online.oss-cn-beijing.aliyuncs.com/2022-04/ba07433f-afc7-4c76-b81d-4dc1a582f008021ded8d063fea1e8e892650972aa8fd.jpg', 0, 0, 1, 'Normal', 0, '2022-04-23 14:18:09', '2022-04-23 14:18:25');
INSERT INTO `edu_course` VALUES ('1517749912295391233', '1516437670283108354', '1504658967869980674', '1504658928598712321', '充数课程', 0.00, 12, 'https://katydid-online.oss-cn-beijing.aliyuncs.com/2022-04/1ebd2a49-c92a-42c0-920d-cc9ed74b92f8default.gif', 0, 0, 1, 'Normal', 0, '2022-04-23 14:19:12', '2022-04-23 14:19:17');
INSERT INTO `edu_course` VALUES ('1517750039756095490', '1516437670283108354', '1504658967869980674', '1504658928598712321', '充数课程2', 0.00, 1, 'https://katydid-online.oss-cn-beijing.aliyuncs.com/defaultcover.jpg', 0, 0, 1, 'Normal', 0, '2022-04-23 14:19:43', '2022-04-23 14:19:47');

-- ----------------------------
-- Table structure for edu_course_description
-- ----------------------------
DROP TABLE IF EXISTS `edu_course_description`;
CREATE TABLE `edu_course_description`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程ID',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '课程简介',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程简介' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu_course_description
-- ----------------------------
INSERT INTO `edu_course_description` VALUES ('1510979153950633986', 'JAVA', '2022-04-04 21:54:38', '2022-04-18 11:56:46');
INSERT INTO `edu_course_description` VALUES ('1510984492876001282', '这里面包罗万象', '2022-04-04 22:15:51', '2022-04-04 22:15:51');
INSERT INTO `edu_course_description` VALUES ('1517748975032025090', '1', '2022-04-23 14:15:29', '2022-04-23 14:15:29');
INSERT INTO `edu_course_description` VALUES ('1517749117747412994', '2', '2022-04-23 14:16:03', '2022-04-23 14:16:03');
INSERT INTO `edu_course_description` VALUES ('1517749433045827586', '3', '2022-04-23 14:17:18', '2022-04-23 14:17:18');
INSERT INTO `edu_course_description` VALUES ('1517749646246494210', '4', '2022-04-23 14:18:09', '2022-04-23 14:18:21');
INSERT INTO `edu_course_description` VALUES ('1517749912295391233', '1', '2022-04-23 14:19:12', '2022-04-23 14:19:12');
INSERT INTO `edu_course_description` VALUES ('1517750039756095490', 'n/a', '2022-04-23 14:19:43', '2022-04-23 14:19:43');

-- ----------------------------
-- Table structure for edu_subject
-- ----------------------------
DROP TABLE IF EXISTS `edu_subject`;
CREATE TABLE `edu_subject`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程类别ID',
  `title` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类别名称',
  `parent_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '父ID',
  `sort` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序字段',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  INDEX `idx_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程科目' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_subject
-- ----------------------------
INSERT INTO `edu_subject` VALUES ('1504431406179143682', '前端', '0', 0, '2022-03-17 20:16:13', '2022-03-17 20:16:13');
INSERT INTO `edu_subject` VALUES ('1504431406225281026', 'css', '1504431406179143682', 0, '2022-03-17 20:16:13', '2022-03-17 20:16:13');
INSERT INTO `edu_subject` VALUES ('1504431406292389889', 'html', '1504431406179143682', 0, '2022-03-17 20:16:13', '2022-03-17 20:16:13');
INSERT INTO `edu_subject` VALUES ('1504431406292389890', 'vue', '1504431406179143682', 0, '2022-03-17 20:16:13', '2022-03-17 20:16:13');
INSERT INTO `edu_subject` VALUES ('1504431406359498754', '后端', '0', 0, '2022-03-17 20:16:13', '2022-03-17 20:16:13');
INSERT INTO `edu_subject` VALUES ('1504431406426607618', 'c++', '1504431406359498754', 0, '2022-03-17 20:16:13', '2022-03-17 20:16:13');
INSERT INTO `edu_subject` VALUES ('1504431406426607619', 'java', '1504431406359498754', 0, '2022-03-17 20:16:13', '2022-03-17 20:16:13');
INSERT INTO `edu_subject` VALUES ('1504658928598712321', '大数据', '0', 0, '2022-03-18 11:20:18', '2022-03-18 11:20:18');
INSERT INTO `edu_subject` VALUES ('1504658967869980674', 'hadoop', '1504658928598712321', 0, '2022-03-18 11:20:28', '2022-03-18 11:20:28');
INSERT INTO `edu_subject` VALUES ('1504660301377634305', '教育', '0', 0, '2022-03-18 11:25:46', '2022-03-18 11:25:46');
INSERT INTO `edu_subject` VALUES ('1507012117587259394', '英语', '1504660301377634305', 0, '2022-03-24 23:11:02', '2022-03-24 23:11:02');
INSERT INTO `edu_subject` VALUES ('1507012117713088513', '高数', '1504660301377634305', 0, '2022-03-24 23:11:02', '2022-03-24 23:11:02');
INSERT INTO `edu_subject` VALUES ('1507012117847306242', '政治', '1504660301377634305', 0, '2022-03-24 23:11:02', '2022-03-24 23:11:02');
INSERT INTO `edu_subject` VALUES ('1507012117910220802', '18+', '1504660301377634305', 0, '2022-03-24 23:11:03', '2022-03-24 23:11:03');
INSERT INTO `edu_subject` VALUES ('1507012117910220803', 'Test', '0', 0, '2022-03-24 23:11:03', '2022-03-24 23:11:03');
INSERT INTO `edu_subject` VALUES ('1507012117973135362', 'Test', '1507012117910220803', 0, '2022-03-24 23:11:03', '2022-03-24 23:11:03');

-- ----------------------------
-- Table structure for edu_teacher
-- ----------------------------
DROP TABLE IF EXISTS `edu_teacher`;
CREATE TABLE `edu_teacher`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '讲师ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '讲师姓名',
  `intro` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '讲师简介',
  `career` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '讲师资历,一句话说明讲师',
  `level` int(0) UNSIGNED NOT NULL COMMENT '头衔 1高级讲师 2首席讲师',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '讲师头像',
  `sort` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
  `is_deleted` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '讲师' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu_teacher
-- ----------------------------
INSERT INTO `edu_teacher` VALUES ('1497147163438710785', '蝈蝈', '暂无', '专业大专码农', 2, 'https://katydid-online.oss-cn-beijing.aliyuncs.com/2022-04/7f1caeaf-2545-42be-993f-b30d001121aefile.png', 0, 0, '2022-02-25 17:51:14', '2022-04-23 14:11:20');
INSERT INTO `edu_teacher` VALUES ('1501756736371900417', '张戈', '无名教练，但技术一流', '健身教练', 2, 'https://katydid-online.oss-cn-beijing.aliyuncs.com/2022-04/5e1f3dde-8b85-41ed-91ed-94a1382a4d82file.png', 0, 0, '2022-03-10 11:08:02', '2022-04-23 14:12:24');
INSERT INTO `edu_teacher` VALUES ('1501757281706917890', '一', '社交牛逼症患者，就是有…有点没素质', '社交名流', 2, 'https://katydid-online.oss-cn-beijing.aliyuncs.com/2022-04/7035f662-8b5b-41b4-978c-376558ff4956file.png', 0, 0, '2022-03-10 11:10:12', '2022-04-23 14:11:45');
INSERT INTO `edu_teacher` VALUES ('1501940038563287041', '生', '一两年前是老大爷，但是随着增长，时光倒流，变成小伙子了，能教会你年轻之术', '和蔼可亲老大爷', 2, 'https://katydid-online.oss-cn-beijing.aliyuncs.com/2022-04/e65ad2d3-b30c-40c3-810b-6eccf7e5b85afile.png', 1, 0, '2022-03-10 23:16:25', '2022-04-23 14:11:03');
INSERT INTO `edu_teacher` VALUES ('1515892436713312257', '123', '213', '213', 1, 'https://katydid-online.oss-cn-beijing.aliyuncs.com/default.gif', 0, 1, '2022-04-18 11:18:15', '2022-04-18 11:18:15');
INSERT INTO `edu_teacher` VALUES ('1515901936597962753', '123213123', '123123', '123213', 1, 'https://katydid-online.oss-cn-beijing.aliyuncs.com/default.gif', 0, 1, '2022-04-18 11:56:00', '2022-04-18 11:56:00');
INSERT INTO `edu_teacher` VALUES ('1516437670283108354', '旺', '众多讲师之中，唯一拥有对象的人，会交给你闷声发大财的秘诀!', '情场拿破仑', 2, 'https://katydid-online.oss-cn-beijing.aliyuncs.com/default.gif', 2, 0, '2022-04-19 23:24:49', '2022-04-19 23:24:49');

-- ----------------------------
-- Table structure for edu_video
-- ----------------------------
DROP TABLE IF EXISTS `edu_video`;
CREATE TABLE `edu_video`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '视频ID',
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程ID',
  `chapter_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '章节ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '节点名称',
  `video_source_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '云端视频资源',
  `video_original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '原始文件名称',
  `sort` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序字段',
  `play_count` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '播放次数',
  `is_free` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否可以试听：0收费 1免费',
  `duration` float NOT NULL DEFAULT 0 COMMENT '视频时长（秒）',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Empty' COMMENT 'Empty未上传  Transcoding转码中  Normal正常',
  `size` bigint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '视频源文件大小（字节）',
  `version` bigint(0) UNSIGNED NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE,
  INDEX `idx_chapter_id`(`chapter_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程视频' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_video
-- ----------------------------
INSERT INTO `edu_video` VALUES ('1510979291049848834', '1510979153950633986', '1510979189828710402', '1.1', 'f6c10923d9d94771978a0a6f07bab1dd', 'test.mp4', 0, 0, 0, 0, 'Empty', 0, 1, '2022-04-04 21:55:10', '2022-04-04 21:55:10');
INSERT INTO `edu_video` VALUES ('1510979332535709697', '1510979153950633986', '1510979189828710402', '1.2', '6b65768916454cbe892d954b621a6581', 'test.mp4', 0, 0, 0, 0, 'Empty', 0, 1, '2022-04-04 21:55:20', '2022-04-04 21:55:20');
INSERT INTO `edu_video` VALUES ('1513494303760961537', '1510979153950633986', '1510979189828710402', '1.3', '0c20b33eb47c48ad9c1f946778301265', '200M.mp4', 0, 0, 1, 0, 'Empty', 0, 1, '2022-04-11 20:28:56', '2022-04-11 20:28:56');
INSERT INTO `edu_video` VALUES ('1517749175284875266', '1517749117747412994', '1517749148919480322', '1.1', '', '', 0, 0, 0, 0, 'Empty', 0, 1, '2022-04-23 14:16:16', '2022-04-23 14:16:16');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `order_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '订单号',
  `course_id` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '课程id',
  `course_title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程名称',
  `course_cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程封面',
  `teacher_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '讲师名称',
  `member_id` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '会员id',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '会员昵称',
  `email` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '会员邮箱',
  `total_fee` decimal(10, 2) NULL DEFAULT 0.01 COMMENT '订单金额（分）',
  `pay_type` tinyint(0) NULL DEFAULT NULL COMMENT '支付类型（1：微信 2：支付宝）',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '订单状态（0：未支付 1：已支付）',
  `is_deleted` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_order_no`(`order_no`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE,
  INDEX `idx_member_id`(`member_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1513491024750563331', '1513491024750563330', '1510984492876001282', '在线教育', 'https://katydid-online.oss-cn-beijing.aliyuncs.com/2022-04/cf10a845-7ef0-495e-a8bc-2c378260243d12ef0fe1bd966d8fabd1b40964dbd63a.jpg', '蝈蝈', '1510258677494276098', 'Katydid', '2710488700@qq.com', 0.01, 1, 1, 0, '2022-04-11 20:15:54', '2022-04-11 20:16:13');
INSERT INTO `t_order` VALUES ('1513875457500463106', '1513875457500463105', '1510979153950633986', 'JVAV', 'https://katydid-online.oss-cn-beijing.aliyuncs.com/2022-04/5daf1aa5-f4a9-46ec-aeaa-74944d08c5451442295581911.jpg', '蝈蝈', '1510258677494276098', 'Katydid', '2710488700@qq.com', 0.01, 1, 0, 0, '2022-04-12 21:43:30', '2022-04-12 21:43:30');

-- ----------------------------
-- Table structure for t_pay_log
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_log`;
CREATE TABLE `t_pay_log`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `order_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '订单号',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `total_fee` decimal(10, 2) NULL DEFAULT 0.01 COMMENT '支付金额（分）',
  `transaction_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '交易流水号',
  `trade_state` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '交易状态',
  `pay_type` tinyint(0) NOT NULL DEFAULT 0 COMMENT '支付类型（1：微信 2：支付宝）',
  `attr` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '其他属性',
  `is_deleted` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_order_no`(`order_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '支付日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_pay_log
-- ----------------------------
INSERT INTO `t_pay_log` VALUES ('1513491101820899329', '1513491024750563330', '2022-04-11 20:16:13', 0.01, '4200001314202204117581197639', 'SUCCESS', 1, '{\"transaction_id\":\"4200001314202204117581197639\",\"nonce_str\":\"02KXXTh9BGxI98Rx\",\"trade_state\":\"SUCCESS\",\"bank_type\":\"OTHERS\",\"openid\":\"oHwsHuFUKC96zGhGk5TOUPzf1CUI\",\"sign\":\"FBFC688615A354BF057D523E38F3ABC5\",\"return_msg\":\"OK\",\"fee_type\":\"CNY\",\"mch_id\":\"1558950191\",\"cash_fee\":\"1\",\"out_trade_no\":\"1513491024750563330\",\"cash_fee_type\":\"CNY\",\"appid\":\"wx74862e0dfcf69954\",\"total_fee\":\"1\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"result_code\":\"SUCCESS\",\"attach\":\"\",\"time_end\":\"20220411201611\",\"is_subscribe\":\"N\",\"return_code\":\"SUCCESS\"}', 0, '2022-04-11 20:16:13', '2022-04-11 20:16:13');

-- ----------------------------
-- Table structure for t_statistics
-- ----------------------------
DROP TABLE IF EXISTS `t_statistics`;
CREATE TABLE `t_statistics`  (
  `id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `date_calculated` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '统计日期',
  `register_num` int(0) NOT NULL DEFAULT 0 COMMENT '注册人数',
  `login_num` int(0) NOT NULL DEFAULT 0 COMMENT '登录人数',
  `video_view_num` int(0) NOT NULL DEFAULT 0 COMMENT '每日播放视频数',
  `course_num` int(0) NOT NULL DEFAULT 0 COMMENT '每日新增课程数',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `statistics_day`(`date_calculated`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '网站统计日数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_statistics
-- ----------------------------
INSERT INTO `t_statistics` VALUES ('1514089164004306945', '2022-04-03', 1, 23, 16, 6, '2022-04-13 11:52:42', '2022-04-13 11:52:42');
INSERT INTO `t_statistics` VALUES ('1514215427507388418', '2022-04-02', 2, 75, 69, 0, '2022-04-13 20:14:25', '2022-04-13 20:14:25');
INSERT INTO `t_statistics` VALUES ('1514217455050719233', '2022-04-01', 0, 10, 42, 6, '2022-04-13 20:22:29', '2022-04-13 20:22:29');
INSERT INTO `t_statistics` VALUES ('1514217513099886594', '2022-04-04', 0, 57, 81, 0, '2022-04-13 20:22:43', '2022-04-13 20:22:43');
INSERT INTO `t_statistics` VALUES ('1515893252731920386', '2022-04-18', 0, 28, 5, 7, '2022-04-18 11:21:30', '2022-04-18 11:21:30');
INSERT INTO `t_statistics` VALUES ('1515902512593330178', '2022-04-11', 0, 75, 64, 1, '2022-04-18 11:58:18', '2022-04-18 11:58:18');

-- ----------------------------
-- Table structure for ucenter_member
-- ----------------------------
DROP TABLE IF EXISTS `ucenter_member`;
CREATE TABLE `ucenter_member`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '会员id',
  `openid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '微信openid',
  `email` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '邮箱',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(0) UNSIGNED NULL DEFAULT NULL COMMENT '性别 1 女，2 男',
  `age` tinyint(0) UNSIGNED NULL DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `sign` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户签名',
  `is_disabled` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用 1（true）已禁用，  0（false）未禁用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '会员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ucenter_member
-- ----------------------------
INSERT INTO `ucenter_member` VALUES ('1510258677494276098', NULL, '2710488700@qq.com', '3c15e60f4d37e416c4cf6251cb7b0b37', 'Katydid', NULL, NULL, 'https://katydid-online.oss-cn-beijing.aliyuncs.com/default.gif', NULL, 0, 0, '2022-04-02 22:11:43', '2022-04-02 22:11:43');
INSERT INTO `ucenter_member` VALUES ('1510539876129263618', 'o3_SC59RLcGhUVvIn7hzSuV8IhdA', '', NULL, 'Crookery', NULL, NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/ica1myzreCc4Yvzbho9T2lib8cY68Eib2dFpxB31mpXwHx4bM1E7xvETeetphjiaFU27QFiackTGv2ZZcolOVRIB3xg/132', NULL, 0, 0, '2022-04-03 16:49:06', '2022-04-03 16:49:06');
INSERT INTO `ucenter_member` VALUES ('1515896650638254082', NULL, '729341043@qq.com', '6acc235910202f18a72e46de0bb8f383', 'nick', NULL, NULL, 'https://katydid-online.oss-cn-beijing.aliyuncs.com/default.gif', NULL, 0, 0, '2022-04-18 11:35:00', '2022-04-18 11:35:00');
INSERT INTO `ucenter_member` VALUES ('1515901059321520130', NULL, '2047817538@qq.com', '3c15e60f4d37e416c4cf6251cb7b0b37', 'nickname', NULL, NULL, 'https://katydid-online.oss-cn-beijing.aliyuncs.com/default.gif', NULL, 0, 0, '2022-04-18 11:52:31', '2022-04-18 11:52:31');

SET FOREIGN_KEY_CHECKS = 1;
