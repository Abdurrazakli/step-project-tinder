CREATE TYPE gender_t AS ENUM('Male','Female');
CREATE TABLE "user" ( id uuid DEFAULT uuid_generate_v4(),
                      username VARCHAR(25) NOT NULL ,
                      password VARCHAR(35) NOT NULL ,
                      gender VARCHAR(1),
                      imageURL VARCHAR(50) NOT NULL ,
                      PRIMARY KEY (id)
                    );

CREATE TABLE Messages (
                          "from"  uuid         NOT NULL ,
                          "to"    uuid         NOT NULL ,
                          date    timestamp DEFAULT now(),
                          message VARCHAR(260) NOT NULL ,
                          FOREIGN KEY ("from") REFERENCES "user"(id),
                          FOREIGN KEY ("to") REFERENCES "user"(id)
                      );

CREATE TABLE Liked (  "user"   uuid,
                       fromUser uuid,
                       isLiked  bool DEFAULT NULL,
                       FOREIGN KEY ("user") REFERENCES "user"(id),
                       FOREIGN KEY (fromUser) REFERENCES "user"(id)
                   );
