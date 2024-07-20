CREATE TABLE `tb_shop` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `identifier` varchar(100) NOT NULL,
  `status` varchar(10) NOT NULL,
  `date_shop` datetime,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

create table `tb_shop_item` (
	`id` bigint not null auto_increment,
	`product_identifier` varchar(100) not null,
	`amount` int not null,
	`price` decimal(7,2) not null,
	`shop_id` bigint not null,

	primary key (`id`)
) engine=InnoDB default charset=utf8;

CREATE TABLE `tb_product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_identifier` varchar(100) NOT NULL,
  `amount` int NOT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

alter table `tb_shop_item` add constraint fk_tb_shop_item_shop
foreign key (`shop_id`) references `tb_shop` (`id`);