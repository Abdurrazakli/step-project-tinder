package DAO;

import Models.Message;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface MessageWrapper {
        String getAllMessages = "SELECT * FROM messages m\n" +
                "WHERE \"from\" = #{loggedUser} AND \"to\"=#{chatFriend} OR\n" +
                "\"from\" = #{chatFriend} AND \"to\"= #{loggedUser}\n" +
                "ORDER BY date";

        @Select(getAllMessages)
        @Results(value = {
                @Result(property = "messageID",column = "id"),
                @Result(property = "from",column = "from"),
                @Result(property = "to",column = "to"),
                @Result(property = "date",column = "date",javaType = java.time.LocalDateTime.class,jdbcType = JdbcType.TIMESTAMP,typeHandler = org.apache.ibatis.type.LocalDateTimeTypeHandler.class)
        })
        List<Message> getAllMessageOfUser(String loggedUser, String chatFriend);
}
