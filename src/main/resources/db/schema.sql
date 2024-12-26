CREATE TABLE IF NOT EXISTS config_data (
   id INTEGER PRIMARY KEY AUTOINCREMENT,
   name TEXT NOT NULL UNIQUE,
   data TEXT,
   create_time DATETIME,
   update_time DATETIME
);
