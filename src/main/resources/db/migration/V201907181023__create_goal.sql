CREATE TABLE IF NOT EXISTS `user` (

    `goal_id` varchar (8) NOT NULL PRIMARY KEY,
    `goal_title` varchar(40),
    `goal_description` varchar (100),
    `goal_start_data` DATA ,
    `goal_deadline_data` DATA ,
    `goal_complete_data` DATA ,
    `goal_state` INT ,

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
