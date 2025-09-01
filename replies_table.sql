-- Create replies table with correct structure
CREATE TABLE IF NOT EXISTS replies (
    reply_id SERIAL PRIMARY KEY,
    message_id INTEGER NOT NULL,
    admin_id INTEGER NOT NULL,
    reply_content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (message_id) REFERENCES messages(message_id) ON DELETE CASCADE
);

-- Add status column to messages table if it doesn't exist
ALTER TABLE messages ADD COLUMN IF NOT EXISTS status VARCHAR(20) DEFAULT 'Unread';