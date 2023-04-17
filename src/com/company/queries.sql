CREATE TABLE `users` (
                         `user_id` int(11) NOT NULL,
                         `user_name` VARCHAR(20) NOT NULL,
                         `email` VARCHAR(20) NOT NULL,
                         `candidate_score` DECIMAL(4,2) NOT NULL
);

INSERT INTO `users` (`user_id`, `user_name`, `email`, `candidate_score`) VALUES (1, 'Clive', 'clivemaina@gmail.com', 98.00), (2, 'Mary', 'mary@gmail.com', 66.66), (3, 'Doe', 'doe@gmail.com', 38.66);