-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.27-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema gestionjoyerias
--

CREATE DATABASE IF NOT EXISTS gestionjoyerias;
USE gestionjoyerias;

--
-- Definition of table `facturas`
--

DROP TABLE IF EXISTS `facturas`;
CREATE TABLE `facturas` (
  `COD_FACTURA` int(10) unsigned NOT NULL,
  `FECHA_FACTURACION` datetime NOT NULL,
  PRIMARY KEY  (`COD_FACTURA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `facturas`
--

/*!40000 ALTER TABLE `facturas` DISABLE KEYS */;
INSERT INTO `facturas` (`COD_FACTURA`,`FECHA_FACTURACION`) VALUES 
 (1,'2007-09-06 00:00:00'),
 (2,'2008-02-10 00:00:00'),
 (3,'2008-02-10 00:00:00'),
 (4,'2008-02-10 00:00:00'),
 (5,'2007-09-06 00:00:00'),
 (6,'2008-04-27 00:00:00'),
 (7,'2008-04-27 00:00:00');
/*!40000 ALTER TABLE `facturas` ENABLE KEYS */;


--
-- Definition of table `joyerias`
--

DROP TABLE IF EXISTS `joyerias`;
CREATE TABLE `joyerias` (
  `COD_JOYERIA` int(10) unsigned NOT NULL,
  `DESCRIPCION` varchar(45) NOT NULL,
  `TELEFONO` varchar(9) NOT NULL,
  PRIMARY KEY  (`COD_JOYERIA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `joyerias`
--

/*!40000 ALTER TABLE `joyerias` DISABLE KEYS */;
INSERT INTO `joyerias` (`COD_JOYERIA`,`DESCRIPCION`,`TELEFONO`) VALUES 
 (1,'Aguamarina','952259300'),
 (2,'Joyeria Aguamarina','952259300'),
 (3,'Prueba','3'),
 (4,'hh','');
/*!40000 ALTER TABLE `joyerias` ENABLE KEYS */;


--
-- Definition of table `reparaciones`
--

DROP TABLE IF EXISTS `reparaciones`;
CREATE TABLE `reparaciones` (
  `COD_REPARACION` int(10) unsigned NOT NULL auto_increment,
  `COD_SOBRE` varchar(45) default NULL,
  `REPARACION` varchar(45) default NULL,
  `PRECIO` float default NULL,
  `NOMBRE_CLIENTE` varchar(45) default NULL,
  `COD_JOYERIA_FK` int(10) unsigned NOT NULL,
  `FECHA_ENTRADA` datetime default NULL,
  `FECHA_SALIDA` datetime default NULL,
  `REPARADO` tinyint(1) default NULL,
  `FACTURADO` tinyint(1) default NULL,
  `COD_FACTURA_FK` int(10) unsigned default NULL,
  `ARTICULO` varchar(45) default NULL,
  `ENTREGADO` tinyint(3) unsigned default NULL,
  `PRESUPUESTO` tinyint(1) unsigned default NULL,
  PRIMARY KEY  (`COD_REPARACION`),
  KEY `COD_JOYERIA_FK` (`COD_JOYERIA_FK`),
  CONSTRAINT `COD_JOYERIA_FK` FOREIGN KEY (`COD_JOYERIA_FK`) REFERENCES `joyerias` (`COD_JOYERIA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reparaciones`
--

/*!40000 ALTER TABLE `reparaciones` DISABLE KEYS */;
INSERT INTO `reparaciones` (`COD_REPARACION`,`COD_SOBRE`,`REPARACION`,`PRECIO`,`NOMBRE_CLIENTE`,`COD_JOYERIA_FK`,`FECHA_ENTRADA`,`FECHA_SALIDA`,`REPARADO`,`FACTURADO`,`COD_FACTURA_FK`,`ARTICULO`,`ENTREGADO`,`PRESUPUESTO`) VALUES 
 (2,'2','hlkj',12,'Daniel',1,'2007-09-08 00:00:00','2007-09-13 00:00:00',1,1,4,'prueba',1,0),
 (4,'1','as',12,'Daniel',1,'2007-09-06 00:00:00','2007-10-18 00:00:00',1,1,6,'articulo',1,0),
 (5,'2','as',12,'Daniel',1,'2007-09-06 00:00:00','2007-09-06 00:00:00',1,0,NULL,'articulo',0,0),
 (7,'33','as',12,'Daniel',1,'2007-09-06 00:00:00','2007-10-08 00:00:00',1,1,7,'articulo',1,0),
 (8,'5','reparacion',12.25,'Daniel',1,'2007-09-06 00:00:00',NULL,0,0,NULL,'articulo de prueba',0,0),
 (9,'6','asjhgjghj',12,'Danielggg',1,'2007-09-06 00:00:00',NULL,0,0,NULL,'articulo',0,0),
 (10,'5','as',12,'Daniel',1,'2007-09-06 00:00:00','2007-09-06 00:00:00',1,0,NULL,'articulo',1,0),
 (11,'8','as',12,'Daniel',1,'2007-09-06 00:00:00','2007-09-06 00:00:00',1,0,NULL,'articulo',1,0),
 (12,'9','as',12,'Daniel',1,'2007-09-06 00:00:00','2007-09-06 00:00:00',1,0,NULL,'articulo',1,0),
 (13,'10','as',12,'Daniel',1,'2007-09-06 00:00:00','2007-09-06 00:00:00',1,0,NULL,'articulo',1,0);
INSERT INTO `reparaciones` (`COD_REPARACION`,`COD_SOBRE`,`REPARACION`,`PRECIO`,`NOMBRE_CLIENTE`,`COD_JOYERIA_FK`,`FECHA_ENTRADA`,`FECHA_SALIDA`,`REPARADO`,`FACTURADO`,`COD_FACTURA_FK`,`ARTICULO`,`ENTREGADO`,`PRESUPUESTO`) VALUES 
 (14,'11','as',12,'Daniel',1,'2007-09-06 00:00:00','2007-09-06 00:00:00',1,0,NULL,'articulo',1,0),
 (25,'14','as',12,'Daniel',1,'2007-09-06 00:00:00','2007-09-06 00:00:00',1,0,NULL,'articulo',1,0),
 (26,'66','as',12,'Daniel',1,'2007-09-06 00:00:00','2007-09-06 00:00:00',1,0,NULL,'articulo',1,0),
 (27,'56668','parado.',0,'Cliente de prueba 1',1,'2008-04-25 00:00:00',NULL,0,0,NULL,'Articulo de prueba 1',0,1);
/*!40000 ALTER TABLE `reparaciones` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
