package org.adilEfqan.tinder.DAO;

import org.adilEfqan.tinder.Models.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface MessageWrapper {
        String getAllMessages = "SELECT * FROM messages\n" +
                "WHERE \"from\" = #{loggedUser} AND \"to\"=#{chatFriend} OR\n" +
                "\"from\" = #{chatFriend} AND \"to\"= #{loggedUser} \n" +
                "ORDER BY DATE";

        String insert = "INSERT INTO messages (\"from\", \"to\", MESSAGE)" +
                " VALUES(#{From}, #{to}, #{message})";

        @Select(getAllMessages)
        @Results(value = {
                @Result(property = "messageID",column = "id"),
                @Result(property = "From",column = "from"),
                @Result(property = "to",column = "to"),
                @Result(property = "message", column = "message"),
                @Result(property = "date",column = "date",javaType = java.time.LocalDateTime.class,jdbcType = JdbcType.TIMESTAMP,typeHandler = org.apache.ibatis.type.LocalDateTimeTypeHandler.class)
        })
        List<Message> getAllMessageOfUser(@Param("loggedUser")String loggedUser, @Param("chatFriend") String chatFriend);


        @Select("SELECT * FROM \"messages\"")
        @Results(value = {
                @Result(property = "messageID",column = "id"),
                @Result(property = "from",column = "from"),
                @Result(property = "to",column = "to"),
                @Result(property = "date",column = "date",javaType = java.time.LocalDateTime.class,jdbcType = JdbcType.TIMESTAMP,typeHandler = org.apache.ibatis.type.LocalDateTimeTypeHandler.class)
        })
        List<Message> getAll();

        @Insert(insert)
        @Options(useGeneratedKeys = true, keyProperty = "messageID")
        void insert(Message newMessage);
}
