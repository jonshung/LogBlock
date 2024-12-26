INSERT INTO Profile (userEmail, displayName, biographyDesc, profileImage, privLevel) VALUES
('chocoCaro@mail.com', 'chocoCaro', 'This is chocoCaro.', 'default', 0),
('henryclauses@mail.com', 'Henry Clauss', 'This is Henry Clauss.', 'default', 0),
('davidjohn@mail.com', 'David John', 'This is David John.', 'default', 0),
('olivermia@mail.com', 'Oliver Mia', 'This is Oliver Mia.', 'default', 0),
('hendrickkale@mail.com', 'Hendrick J. Kale', 'This is Hendrick J. Kale.', 'default', 0),
('jackma@mail.com', 'Jack Ma', 'This is Jack Ma.', 'default', 0),
('studywithme@mail.com', 'Study With Me | Layla', 'Come and study with me.', 'default', 0),
('benfoster@mail.com', 'Ben Foster', 'This is Ben Foster.', 'default', 0),
('codeladamme@mail.com', 'code là đam mê', 'Không dành cho người không đam mê code :3.', 'default', 0);

INSERT INTO Posting (originalAuthor, postCaption) VALUES
(1, 'My Solution in Python for Problem 50 on Leetcode.'),
(2, 'This is my first program in C++. Can someone tell me if it is optimal or not?');

INSERT INTO Commenting (postID, commentAuthor, commentCaption) VALUES
(1, 1, 'Wow! Your Solution is excellent. Love it, bro.'),
(1, 2, 'Could you elaborate on how you arrive at this solution? I appreciate your efforts!');