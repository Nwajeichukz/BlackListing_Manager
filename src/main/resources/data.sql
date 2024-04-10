--INSERT INTO role (name) VALUES('ROLE_USER_ADMIN');
-- Insert the role if it does not already exist
INSERT INTO role (name)
VALUES ('ROLE_USER_ADMIN')
ON CONFLICT (name) DO NOTHING;


-- Insert a user only if the email does not already exist
INSERT INTO users (email, role_fk)
SELECT 'nwajeigoddowell@gmail.com', id
FROM role
WHERE name = 'ROLE_USER_ADMIN'
AND NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'nwajeigoddowell@gmail.com'
);

