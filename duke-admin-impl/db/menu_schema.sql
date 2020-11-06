/*
 Navicat Premium Data Transfer

 Source Server         : dukehu
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : dukehu.top:3306
 Source Schema         : duke_auth

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 22/10/2020 11:01:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`
(
    `id`          varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '主键',
    `code`        varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '菜单编码',
    `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
    `icon`        varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '菜单图标',
    `parent_id`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '父级菜单id',
    `level_no`    int(1)                                                  NOT NULL COMMENT '菜单级次',
    `is_leaf`     int(1)                                                  NOT NULL COMMENT '当前菜单是否叶子节点',
    `url`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '路径',
    `service_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单归属服务',
    `menu_order`  int(11)                                                 NOT NULL COMMENT '菜单显示顺序',
    `params`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单参数',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
    `is_deleted`  int(1)                                                  NOT NULL COMMENT '是否删除',
    `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
    `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
    `update_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '最后修改人',
    `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
