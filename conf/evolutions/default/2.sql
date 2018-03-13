# --- Sample dataset

# --- !Ups
INSERT INTO myqq.client (id, name, apikey, description, version, created, modified) VALUES (1, 'Browser', '764235bd-38d9-4fae-a172-07dea659657d', '浏览器通用的客户端API Key。', 1, '2018-03-13 09:57:53.240000', '2018-03-13 09:58:12.560000');
INSERT INTO myqq.client (id, name, apikey, description, version, created, modified) VALUES (2, 'Android', '0116ce83-63c4-44c1-b7d5-3307a2702011', '安卓手机通用的客户端API Key。', 1, '2018-03-13 10:53:01.951000', '2018-03-13 10:53:09.098000');

# --- !Downs

DELETE FROM myqq.client;
