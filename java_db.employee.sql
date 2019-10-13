use java_db;

CREATE TABLE `employee` (
  `empno` int(11) NOT NULL AUTO_INCREMENT,
  `ename` varchar(100) DEFAULT NULL,
  `sal` int(11) DEFAULT NULL,
  `job` varchar(45) DEFAULT NULL,
  `deptno` int(11) DEFAULT NULL,
  PRIMARY KEY (`empno`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;