CREATE TABLE IF NOT EXISTS `week_plan` (

    `week_plan_id` varchar (8) NOT NULL PRIMARY KEY,
    `goal_id` varchar (8),
    `week_plan_title` varchar(40),
    `week_plan_description` varchar (100),
    `week_plan_number` INT ,
    `week_plan_state` INT

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
