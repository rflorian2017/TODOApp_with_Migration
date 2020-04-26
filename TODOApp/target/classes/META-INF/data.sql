INSERT INTO `users` (`user_id`,`password`,`username`) VALUES (1,'456','John');
INSERT INTO `users` (`user_id`,`password`,`username`) VALUES (2,'123','Maria');

INSERT INTO `task` (`task_id`,`created_at`,`description`,`in_progress`,`project_project_id`,`user_user_id`) VALUES (1,'2020-04-26 09:55:07.365000','A simple to do',FALSE,NULL,1);
INSERT INTO `task` (`task_id`,`created_at`,`description`,`in_progress`,`project_project_id`,`user_user_id`) VALUES (2,'2020-04-26 09:57:03.197000','another 1',TRUE,NULL,1);
INSERT INTO `task` (`task_id`,`created_at`,`description`,`in_progress`,`project_project_id`,`user_user_id`) VALUES (3,'2020-04-26 09:57:06.413000','...',FALSE,NULL,1);
INSERT INTO `task` (`task_id`,`created_at`,`description`,`in_progress`,`project_project_id`,`user_user_id`) VALUES (4,'2020-04-26 09:57:09.014000','potatoes',FALSE,NULL,1);

INSERT INTO `task` (`task_id`,`created_at`,`description`,`in_progress`,`project_project_id`,`user_user_id`) VALUES (5,'2020-04-26 09:57:09.014000','Open Intellij',FALSE,NULL,NULL);
INSERT INTO `task` (`task_id`,`created_at`,`description`,`in_progress`,`project_project_id`,`user_user_id`) VALUES (6,'2020-04-26 09:57:09.014000','Write code',FALSE,NULL,NULL);
