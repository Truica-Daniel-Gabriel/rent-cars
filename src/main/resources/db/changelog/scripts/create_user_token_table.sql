CREATE TABLE IF NOT EXIST user_tokens {
   id SERIAL PRIMARY KEY,
   user_id INTEGER(8),
   account_status VARCHAR(10),
   created_at TIMESTAMP,
   account_token VARCHAR(255)
}