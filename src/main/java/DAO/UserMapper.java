package DAO;

import Models.Gender;
import Models.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Collection;
import java.util.UUID;


public interface UserMapper{
    String getAll = "SELECT * FROM \"user\"";
    String getById = "SELECT * FROM \"user\" WHERE ID = #{id}";
    String deleteById = "DELETE from \"user\" WHERE ID = #{id}";
    String insert = "INSERT INTO \"user\" (USERNAME, PASSWORD, GENDER, IMAGEURL ) VALUES (#{username}, #{password}, #{gender}, #{imageURL})";
    String update = "UPDATE \"user\" SET USERNAME = #{username}, PASSWORD = #{password}, GENDER = #{gender}, IMAGEURL = #{imageURL} WHERE ID = #{id}";
    String getBY = "SELECT * FROM \"user\" WHERE USERNAME=#{username}";
    String getUserOneByOne = "SELECT id, username, gender,imageURL, password FROM \"user\" u\n" +
            "LEFT OUTER JOIN Liked l ON l.toUser != u.id\n" +
            "WHERE 'bc1103d1-110c-4372-b5d6-8e4d9b2f7900'=l.\"user\" AND u.id !='bc1103d1-110c-4372-b5d6-8e4d9b2f7900' limit 1";
    @Select(getAll)
    @Results(value = {
            @Result(property = "userID",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "gender",column = "gender"),
            @Result(property = "imageURL",column = "imageURL")
    })
    Collection<User> getAll();

    @Select(getById)
    @Results(value = {
            @Result(property = "userID",column = "id",javaType = UUID.class,id = true),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "gender",column = "gender",javaType = Gender.class),
            @Result(property = "imageURL",column = "imageURL")
    })
    User getById(UUID id);

    @Update(update)
    void update(User student);

    @Delete(deleteById)
    void deleteByID(UUID id);

    @Insert(insert)
    @Options(useGeneratedKeys = true, keyProperty = "userID")
    void insert(User user);

    @Select(getBY)
    @Results(value = {
            @Result(property = "userID",column = "id",javaType = String.class,jdbcType = JdbcType.VARCHAR),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "gender",column = "gender",javaType = Gender.class),
            @Result(property = "imageURL",column = "imageURL")
    })
    User getBy(String username);


    @Select(getUserOneByOne)
    @Results(value = {
            @Result(property = "userID",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "gender",column = "gender"),
            @Result(property = "imageURL",column = "imageURL")
    })
    User getUserOneByOne(String id);
}
