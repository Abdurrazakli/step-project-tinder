package DAO;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;


public interface LikeMapper {
    String insertToLike = "INSERT INTO public.liked (\"user\", touser, isliked) VALUES (#{userID}, #{toUserID}, #{isLiked})";

    @Insert(insertToLike)
    @Options(useGeneratedKeys = true, keyProperty = "userID")
    public void insertToLike(String userID,String toUser,boolean isLiked);
}
