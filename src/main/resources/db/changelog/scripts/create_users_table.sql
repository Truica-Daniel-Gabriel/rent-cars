CREATE TABLE IF NOT EXISTS users (
   id SERIAL PRIMARY KEY,
   full_name VARCHAR(50),
   email VARCHAR(50),
   password VARCHAR(255),
   user_role VARCHAR(50)
)