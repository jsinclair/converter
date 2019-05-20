DROP TABLE IF EXISTS session_actions;
DROP TABLE IF EXISTS user_sessions;
DROP TABLE IF EXISTS users;

CREATE TABLE users(id SERIAL PRIMARY KEY, username VARCHAR(100), password VARCHAR(100));
CREATE TABLE user_sessions(id SERIAL PRIMARY KEY, user_id INTEGER REFERENCES users(id), session_id VARCHAR(100), created_date TIMESTAMP, last_modified_date TIMESTAMP);
CREATE TABLE session_actions(id SERIAL PRIMARY KEY, user_session_id INTEGER REFERENCES user_sessions(id), action VARCHAR(100), created_date TIMESTAMP, last_modified_date TIMESTAMP);
