--for role
INSERT IGNORE INTO role VALUES (1, 'USER');
INSERT IGNORE INTO role VALUES (2, 'ADMIN');

--for currency
INSERT IGNORE INTO currency VALUES (1, 'UAH');
INSERT IGNORE INTO currency VALUES (2, 'USD');
INSERT IGNORE INTO currency VALUES (3, 'EUR');

--for type operation
INSERT IGNORE INTO type_operation VALUES (1, 'Income');
INSERT IGNORE INTO type_operation VALUES (2, 'Expense');
INSERT IGNORE INTO type_operation VALUES (3, 'Transfer');

--for group categories
INSERT IGNORE INTO group_category VALUES (1, 'Products');
INSERT IGNORE INTO group_category VALUES (2, 'Repair');
INSERT IGNORE INTO group_category VALUES (3, 'Car');
INSERT IGNORE INTO group_category VALUES (4, 'Transfer from a card ');
INSERT IGNORE INTO group_category VALUES (5, 'Enrollment on the card  ');








--for category
INSERT IGNORE INTO category VALUES (1, 'Milk products', 1);
INSERT IGNORE INTO category VALUES (2, 'Spare parts for the car', 3);
INSERT IGNORE INTO category VALUES (3, 'Car wash', 3);
INSERT IGNORE INTO category VALUES (4, 'Wallpaper', 2);
INSERT IGNORE INTO category VALUES (5, 'To a bank card', 4);
INSERT IGNORE INTO category VALUES (6, 'To mobile phone', 4);
INSERT IGNORE INTO category VALUES (7, 'Salary', 5);
INSERT IGNORE INTO category VALUES (8, 'Card replenishment', 5);

--for user
INSERT IGNORE INTO users VALUES (1, 'Andrey', 'Kotsenko', '068 980 34 56', 'pass', 2);
INSERT IGNORE INTO users VALUES (2, 'Rick', 'Grock', '068 345 34 12', 'passw', 1);
INSERT IGNORE INTO users VALUES (3, 'Morty', 'Flan', '068 234 34 36', 'passwo', 1);
INSERT IGNORE INTO users VALUES (4, 'Kata', 'Morgana', '096 534 44 88', 'passwor', 1);


--for account
INSERT IGNORE INTO account VALUES (1, '4145 6845 8923 3256', 3456, 1, 3);
INSERT IGNORE INTO account VALUES (2, '4145 6845 8923 3200', 1458, 2, 1);
INSERT IGNORE INTO account VALUES (3, '4178 6845 8123 3256', 4878, 3, 2);
INSERT IGNORE INTO account VALUES (4, '4115 6825 8223 3776', 575, 4, 2);
INSERT IGNORE INTO account VALUES (5, '4115 4444 8223 3766', 5575, 1, 1);
INSERT IGNORE INTO account VALUES (6, '4115 6825 8223 1111', 875, 2, 2);


--for operation
INSERT IGNORE INTO operation VALUES (1, '2021-07-15 11:11:34', ' wheel for car ', 300, 2, 2, 2, null);
INSERT IGNORE INTO operation VALUES (2, '2021-07-16 14:26:32', ' wheel for car ', 300, 2, 2, 1, null);
INSERT IGNORE INTO operation VALUES (3, '2021-07-19 19:21:34', ' wheel for car ', 300, 2, 2, 3, null);
INSERT IGNORE INTO operation VALUES (4, '2021-07-12 23:01:04', ' Milk ',  50, 2, 1, 3, null);
INSERT IGNORE INTO operation VALUES (5, '2021-07-21 19:21:24', ' Card replenishment ',  100, 3, 8, null, 4);
INSERT IGNORE INTO operation VALUES (6, '2021-07-20 14:21:34', 'salary from company',  500, 1, 7, null, 1);
INSERT IGNORE INTO operation VALUES (7, '2021-07-13 12:21:34', 'salary from company',  1500, 1, 7, null, 6);
INSERT IGNORE INTO operation VALUES (8, '2021-07-11 04:21:34', 'Card replenishment',  500, 3, 8, 5, null);
INSERT IGNORE INTO operation VALUES (9, '2021-07-07 14:11:34', 'Card replenishment',  1500, 3, 8, 5, null);
INSERT IGNORE INTO operation VALUES (10, '2021-07-23 18:21:34', 'To a bank card',  500, 3, 5, 5, 6);
INSERT IGNORE INTO operation VALUES (11, '2021-07-20 10:21:34', 'To a bank card',  500, 3, 5, 1, 4);
INSERT IGNORE INTO operation VALUES (12, '2021-07-03 19:21:34', 'To a bank card',  1500, 3, 5, 3, 4);
INSERT IGNORE INTO operation VALUES (13, '2021-07-27 20:21:34', 'replenishment mobile phone',  50, 2, 4, 1, null);
INSERT IGNORE INTO operation VALUES (14, '2021-07-09 13:21:34', 'washing my car',  500, 2, 3, 1, null);
INSERT IGNORE INTO operation VALUES (15, '2021-07-17 16:33:34', 'To a bank card',  500, 3, 5, 5, 6);
INSERT IGNORE INTO operation VALUES (16, '2021-07-25 18:34:11', 'salary from company',  1000, 1, 7, null, 5);