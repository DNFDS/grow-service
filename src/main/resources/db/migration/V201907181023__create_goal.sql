CREATE TABLE IF NOT EXISTS `goal` (

    `goal_id` varchar (8) NOT NULL PRIMARY KEY,
    `user_id` varchar (8),
    `goal_title` varchar(40),
    `goal_description` varchar (100),
    `goal_start_date` DATE ,
    `goal_deadline_date` DATE ,
    `goal_completed_date` DATE ,
    `goal_state` INT

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
