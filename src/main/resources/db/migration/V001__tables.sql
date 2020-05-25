CREATE TABLE "user" ( id varchar DEFAULT uuid_generate_v4(),
                      username VARCHAR(25) NOT NULL ,
                      password VARCHAR(35) NOT NULL ,
                      gender VARCHAR(1) ,
                      imageURL VARCHAR(400) NOT NULL ,
                      lastLogin timestamp without time zone DEFAULT now(),
                      PRIMARY KEY (id)
                    );

CREATE TABLE Messages (
                          id varchar DEFAULT uuid_generate_v4(),
                          "from"  varchar NOT NULL ,
                          "to"    varchar NOT NULL ,
                          date    timestamp without time zone DEFAULT now(),
                          message VARCHAR(260) NOT NULL ,
                          FOREIGN KEY ("from") REFERENCES "user"(id),
                          FOREIGN KEY ("to") REFERENCES "user"(id)
                      );

CREATE TABLE Liked (  "user"   varchar,
                       toUser varchar,
                       isLiked  bool NOT NULL ,
                       FOREIGN KEY ("user") REFERENCES "user"(id),
                       FOREIGN KEY (toUser) REFERENCES "user"(id),
                       PRIMARY KEY ("user",toUser)
                   );
