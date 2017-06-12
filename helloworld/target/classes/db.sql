CREATE TABLE user (
  id binary(16) NOT NULL,
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  username char(100) NOT NULL,

  PRIMARY KEY (id),
  UNIQUE KEY user_username (username)
);

ALTER TABLE user ADD user_sig_fk binary(16);

CREATE TABLE IF NOT EXISTS user_signature (
  id binary(16) NOT NULL,
  signature blob NOT NULL,
  file_type char(5),
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,

  PRIMARY KEY (id)
);

ALTER TABLE user ADD CONSTRAINT user_signature_fk FOREIGN KEY (user_sig_fk) REFERENCES user_signature (id) ON DELETE CASCADE;