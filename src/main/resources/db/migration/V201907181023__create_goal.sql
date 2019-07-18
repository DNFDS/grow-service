CREATE TABLE IF NOT EXISTS `goal` (

    `goalId` varchar (8) NOT NULL PRIMARY KEY,
    `userId` varchar (8),
    `goalTitle` varchar(40),
    `goalDescription` varchar (100),
    `goalStartData` DATE ,
    `goalDeadlineData` DATE ,
    `goalCompleteData` DATE ,
    `goalState` INT

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
