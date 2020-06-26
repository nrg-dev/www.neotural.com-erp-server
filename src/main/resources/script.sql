ALTER TABLE production_issue modify COLUMN cardImageBase64 MEDIUMTEXT ;
ALTER TABLE production_issue modify COLUMN ISSUE_STATUS VARCHAR (100) ;
ALTER TABLE production_issue modify COLUMN ISSUE_NOTES MEDIUMTEXT ;

INSERT INTO user (USER_ID,USER_NAME, USER_PASSWORD, USER_TYPE,USER_STATUS,USER_EMAILID,USER_DEPARTMENT )
VALUES (1,"alex", "alex@123", "maker/authorizer",'Active','alexubalton@neotural.com','IT');

INSERT INTO user (USER_ID,USER_NAME, USER_PASSWORD, USER_TYPE,USER_STATUS,USER_EMAILID,USER_DEPARTMENT )
VALUES (2,"duser1", "duser1", "maker",'Active','duser1@neotural.com','IT');

INSERT INTO user (USER_ID,USER_NAME, USER_PASSWORD, USER_TYPE,USER_STATUS,USER_EMAILID,USER_DEPARTMENT )
VALUES (3,"buser1", "buser1", "maker",'Active','buser1@neotural.com','IT');


