CREATE TABLE IF NOT EXISTS config_data (
   id INTEGER PRIMARY KEY AUTOINCREMENT,
   name TEXT NOT NULL UNIQUE,
   data TEXT,
   create_time DATETIME,
   update_time DATETIME
);

CREATE TABLE IF NOT EXISTS feature_directory (
   id INTEGER PRIMARY KEY AUTOINCREMENT,
   name TEXT NOT NULL UNIQUE,
   path TEXT,
   status int default 1,
   create_time DATETIME,
   update_time DATETIME
);

REPLACE INTO feature_directory (id, name, path, status, create_time, update_time) VALUES (1, 'JSON 格式化工具', 'tool/json-format', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
REPLACE INTO feature_directory (id, name, path, status, create_time, update_time) VALUES (2, 'IP查询工具', 'tool/ip-query', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
REPLACE INTO feature_directory (id, name, path, status, create_time, update_time) VALUES (3, '像素网格', 'pixel/index', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
