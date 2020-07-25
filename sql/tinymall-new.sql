DROP TABLE IF EXISTS `tinymall_template`;

CREATE TABLE `tinymall_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '模板名称',
  `spec_num` int(11) DEFAULT '0' COMMENT '规格数量',
  `para_num` int(11) DEFAULT '0' COMMENT '参数数量',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='模板表';

/*Data for the table `tb_template` */

insert  into `tinymall_template`(`id`,`name`,`spec_num`,`para_num`,`create_time`,`update_time`,`deleted`)
values (1,'手机',NULL,NULL,NOW(),NOW(),0),(2,'电视',NULL,NULL,NOW(),NOW(),0),(3,'服装',0,0,NOW(),NOW(),0);

DROP TABLE IF EXISTS `tinymall_spec`;

CREATE TABLE `tinymall_spec` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `options` varchar(2000) DEFAULT NULL COMMENT '规格选项',
  `seq` int(11) DEFAULT NULL COMMENT '排序',
  `template_id` int(11) DEFAULT NULL COMMENT '模板ID',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='规格表';

/*Data for the table `tb_spec` */

insert  into `tinymall_spec`(`name`,`options`,`seq`,`template_id`,`create_time`,`update_time`,`deleted`)
values ('尺码','165,170,175',NULL,3,NOW(),NOW(),0),('颜色','红色,蓝色,黑色,槟色,白色,金色,银色,灰色.紫色',NULL,1,NOW(),NOW(),0),
('版本','8GB+128GB,6GB+64GB,6GB+128GB,4GB+64GB,4GB+32GB',NULL,1,NOW(),NOW(),0),
('尺寸','70寸,80寸,43寸,49寸,55寸,46寸',NULL,2,NOW(),NOW(),0);

DROP TABLE IF EXISTS `tinymall_para`;

CREATE TABLE `tinymall_para` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `options` varchar(2000) DEFAULT NULL COMMENT '选项',
  `seq` int(11) DEFAULT NULL COMMENT '排序',
  `template_id` int(11) DEFAULT NULL COMMENT '模板ID',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '参数表';

/*Data for the table `tb_para` */

insert  into `tinymall_para`(`name`,`options`,`seq`,`template_id`,`create_time`,`update_time`,`deleted`)
values ('出厂年份','2001,2002,2004,2005',1,1,NOW(),NOW(),0),('版本','10,20,30',11,1,NOW(),NOW(),0);