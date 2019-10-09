create database midTermProject;
use midTermProject;

drop table midTerm;
create table if not exists midTerm
(
	 `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `gender`  varchar(10) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(40)DEFAULT NULL,
  `country` varchar(25)DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `userName` varchar(25)DEFAULT NULL,
  `passWord` varchar(25)DEFAULT NULL,
  `monthlydiscemail` varchar(11) null,
  `category` varchar(25) DEFAULT NULL,
  `weeklypromotionemail` varchar(25) default null,
  `typeprofile` varchar(40) default null,
  
    constraint pk_midTerm primary key clustered (id)

)ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
;

LOCK TABLES `midTerm` WRITE;
INSERT INTO `midTerm` VALUES (1,'Nihan','Ni','',111-111-2222,'333 dtreet','','Nihan@google.com','johnny','12345678','','','','');
UNLOCK TABLES;

select * from midTerm;

GRANT ALL PRIVILEGES ON database_name.* TO 'root'@'localhost';
