-- 会議室
INSERT INTO meeting_room (room_name) VALUES ('新木場');
INSERT INTO meeting_room (room_name) VALUES ('辰巳');
INSERT INTO meeting_room (room_name) VALUES ('豊洲');
INSERT INTO meeting_room (room_name) VALUES ('月島');
INSERT INTO meeting_room (room_name) VALUES ('新富町');
INSERT INTO meeting_room (room_name) VALUES ('銀座一丁目');
INSERT INTO meeting_room (room_name) VALUES ('有楽町');

-- room_id=1 (新木場)の予約可能日
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, 1);
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE + 1, 1);
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE - 1, 1);

-- room_id=2 (辰巳)の予約可能日
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, 2);
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE + 1, 2);
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE - 1, 2);

-- room_id=3 (豊洲)の予約可能日
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, 3);
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE + 1, 3);
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE - 1, 3);

-- room_id=4 (月島)の予約可能日
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, 4);
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE + 1, 4);
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE - 1, 4);

-- room_id=5 (新富町)の予約可能日
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, 5);
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE + 1, 5);
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE - 1, 5);

-- room_id=6 (銀座一丁目)の予約可能日
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, 6);
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE + 1, 6);
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE - 1, 6);

-- room_id=7 (有楽町)の予約可能日
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, 7);
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE + 1, 7);
INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE - 1, 7);


-- ダミーユーザー(password = demo)
INSERT INTO usr (user_id, first_name, last_name, password, role_name) VALUES ('taro-yamada', '太郎', '山田', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', 'USER');

-- 認証確認用テストユーザー(password = demo)
INSERT INTO usr (user_id, first_name, last_name, password, role_name) VALUES ('aaaa', 'Aaa', 'Aaa', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', 'USER');
INSERT INTO usr (user_id, first_name, last_name, password, role_name) VALUES ('bbbb', 'Bbb', 'Bbb', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', 'USER');
INSERT INTO usr (user_id, first_name, last_name, password, role_name) VALUES ('cccc', 'Ccc', 'Ccc', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', 'ADMIN');
