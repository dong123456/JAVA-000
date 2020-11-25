/*
 Navicat MySQL Data Transfer

 Source Server         : 127.0.0.1
 Source Server Version : 50641
 Source Host           : localhost
 Source Database       : shop

 Target Server Version : 50641
 File Encoding         : utf-8

 Date: 11/25/2020 14:14:04 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `goods_name` varchar(255) NOT NULL COMMENT '商品名',
  `goods_type` tinyint(4) NOT NULL DEFAULT '99' COMMENT '商品类型',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1正常状态 -1已下架',
  `goods_price` decimal(10,2) NOT NULL COMMENT '商品价格',
  `goods_photo` varchar(255) NOT NULL COMMENT '商品首图',
  `goods_description` varchar(500) NOT NULL COMMENT '商品简单描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `quality` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '商品库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
--  Table structure for `goods_detail`
-- ----------------------------
DROP TABLE IF EXISTS `goods_detail`;
CREATE TABLE `goods_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品详情id',
  `goods_id` bigint(20) NOT NULL COMMENT '商品id',
  `vendor_id` int(11) NOT NULL DEFAULT '0' COMMENT '供应商id',
  `vendor_price` decimal(10,2) NOT NULL COMMENT '供应商价格',
  `goods_detail` text NOT NULL COMMENT '商品详细介绍',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品详情表';

-- ----------------------------
--  Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `order_amount` decimal(10,2) NOT NULL COMMENT '订单金额',
  `shipping_amount` decimal(10,2) NOT NULL COMMENT '运费',
  `pay_amount` decimal(10,2) NOT NULL COMMENT '支付金额',
  `pay_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '支付时间',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '订单状态 1下单未付款  2 已支付 -1已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `inx_order_num` (`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
--  Table structure for `orders_detail`
-- ----------------------------
DROP TABLE IF EXISTS `orders_detail`;
CREATE TABLE `orders_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品详情id',
  `order_id` bigint(20) NOT NULL COMMENT '商品id',
  `shipping_user_name` varchar(50) NOT NULL DEFAULT '0' COMMENT '收货用户名',
  `shipping_province` smallint(6) NOT NULL COMMENT '收货地址省份id',
  `shipping_city` smallint(6) NOT NULL COMMENT '收货地址城市id',
  `shipping_district` smallint(6) NOT NULL COMMENT '收货地址区id',
  `shipping_adress` varchar(255) NOT NULL COMMENT '收货地址',
  `shipping_no` varchar(50) NOT NULL COMMENT '快递单号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情表';

-- ----------------------------
--  Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT 'MD5后的用户密码',
  `head_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `id_card` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '用户状态 1正常 -1已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

SET FOREIGN_KEY_CHECKS = 1;
