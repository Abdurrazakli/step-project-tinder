package DAO;

import Models.User;
import org.apache.ibatis.annotations.*;

import java.util.Collection;
import java.util.UUID;


public interface UserMapper{
    String getAll = "SELECT * FROM USER";
    String getById = "SELECT * FROM USER WHERE ID = #{id}";
    String deleteById = "DELETE from USER WHERE ID = #{id}";
    String insert = "INSERT INTO \"user\" (USERNAME, PASSWORD, GENDER, IMAGEURL ) VALUES (#{username}, #{password}, #{gender}, #{imageURL})";
    String update = "UPDATE USER SET USERNAME = #{username}, PASSWORD = #{password}, GENDER = #{gender}, IMAGEURL = #{imageURL} WHERE ID = #{id}";


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
            @Result(property = "userID",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "gender",column = "gender"),
            @Result(property = "imageURL",column = "imageURL")
    })
    User getById(int id);

    @Update(update)
    void update(User student);

    @Delete(deleteById)
    void deleteByID(UUID id);

    @Insert(insert)
    @Options(useGeneratedKeys = true, keyProperty = "userID")
    void insert(User user);
}
