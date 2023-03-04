--------------------------------------------------------------------------------
-- 테이블명은 소문자만 사용
--------------------------------------------------------------------------------

INSERT INTO test_post(created_date, modified_date, title, content, author) VALUES(NOW(), NOW(), 'init title', 'init_content', 'init_author');

INSERT INTO test_mybatis(created_date, modified_date, title, content, author) VALUES(NOW(), NOW(), 'init title', 'init_content', 'init_author');
