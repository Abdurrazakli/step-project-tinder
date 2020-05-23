CREATE TABLE User ( id VARCHAR(36) ,
                      username VARCHAR(25) NOT NULL ,
                      password VARCHAR(35) NOT NULL ,
                      gender VARCHAR(1),
                      imageURL VARCHAR(50) NOT NULL ,
                      PRIMARY KEY (id)
                    );

CREATE TABLE Messages (
                          id varchar(36),
                          `from`  varchar(36) NOT NULL ,
                          `to`    varchar(36) NOT NULL ,
                          date    timestamp DEFAULT now(),
                          message VARCHAR(260) NOT NULL ,
                          FOREIGN KEY (`from`) REFERENCES user(id),
                          FOREIGN KEY (`to`) REFERENCES user(id)
                      );

CREATE TABLE Liked (  user   varchar(36),
                       toUser varchar(36),
                       isLiked  bool NOT NULL ,
                       FOREIGN KEY (user) REFERENCES user(id),
                       FOREIGN KEY (toUser) REFERENCES user(id),
                       PRIMARY KEY (user,toUser)
                   );
