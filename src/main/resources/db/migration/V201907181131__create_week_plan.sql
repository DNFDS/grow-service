CREATE TABLE IF NOT EXISTS `week_plan` (

    `weekPlanTd` varchar (8) NOT NULL PRIMARY KEY,
    `goalTd` varchar (8),
    `weekPlanTitle` varchar(40),
    `weekPlanDescription` varchar (100),
    `weekPlanNumber` INT ,
    `weekPlanState` INT ,

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
