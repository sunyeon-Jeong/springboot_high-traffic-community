use `high-traffic community`;

CREATE TABLE `user`(
	`id` int NOT NULL AUTO_INCREMENT,
    `username` varchar(50) NOT NULL,
    `password` varchar(100) NOT NULL,
    `nick_name` varchar(50) NOT NULL,
    `is_admin` tinyint NOT NULL DEFAULT '0',
    `is_with_draw` tinyint NOT NULL,
    `status` varchar(50) DEFAULT NULL,
    `create_time` datetime DEFAULT NULL,
    `modify_time` datetime DEFAULT NULL,
    
	PRIMARY KEY(`id`)
);

CREATE TABLE `tag`(
	`id` int NOT NULL AUTO_INCREMENT,
    `tag` varchar(50) NOT NULL,
    `url` varchar(50) NOT NULL,
    
    PRIMARY KEY(`id`)
);

CREATE TABLE `category`(
	`id` int NOT NULL AUTO_INCREMENT,
    `category` varchar(50) NOT NULL,
    
    PRIMARY KEY(`id`)
);

CREATE TABLE `post`(
	`id` int NOT NULL AUTO_INCREMENT,
    `title` varchar(50) NOT NULL,
    `is_admin` varchar(50) NOT NULL,
    `contents` varchar(500) NOT NULL,
    `views` int NOT NULL,
    `create_time` datetime NOT NULL,
    `modify_time` datetime DEFAULT NULL,
    `user_id` int NOT NULL,
    `category_id` int NOT NULL,
    
    PRIMARY KEY(`id`),
    KEY `FK_1` (`user_id`),
    KEY `FK_2` (`category_id`),
    CONSTRAINT `FK_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    CONSTRAINT `FK_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
);

CREATE TABLE `post_tag`(
	`id` int NOT NULL AUTO_INCREMENT,
    `post_id` int NOT NULL,
    `tag_id` int NOT NULL,
    
    PRIMARY KEY(`id`),
    KEY `FK_1` (`post_id`),
    CONSTRAINT `FK_3` FOREIGN KEY `FK_1` (`post_id`) REFERENCES `post` (`id`),
    KEY `FK_2` (`tag_id`),
    CONSTRAINT `FK_4` FOREIGN KEY `FK_2` (`tag_id`) REFERENCES `tag` (`id`)
);

CREATE TABLE `file`(
	`id` int NOT NULL AUTO_INCREMENT,
	`path` varchar(100) NOT NULL,
    `file` varchar(50) NOT NULL,
    `extension` varchar(50) NOT NULL,
	`post_id` int NOT NULL,
    
    PRIMARY KEY(`id`),
    KEY `FK_1` (`post_id`),
    CONSTRAINT `FK_6` FOREIGN KEY `FK_1` (`post_id`) REFERENCES `post` (`id`)
);

CREATE TABLE `comment`(
	`id` int NOT NULL AUTO_INCREMENT,
    `contents` varchar(300) NOT NULL,
    `post_id` int NOT NULL,
    `sub_comment_id` int NOT NULL,
    
    PRIMARY KEY(`id`),
    KEY `FK_1` (`post_id`),
    CONSTRAINT `FK_5` FOREIGN KEY `FK_1` (`post_id`) REFERENCES `post` (`id`)
);