package DAO;

import Models.LikeDislike;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;


public interface LikeMapper {
    String insertToLike = "INSERT INTO public.liked (\"user\", touser, isliked) VALUES (#{currentUserID}, #{toUserID}, #{isLiked})";

    @Insert(insertToLike)
    //@Options(useGeneratedKeys = true, keyProperty = "userID")
    void insertToLike(LikeDislike ld);
}
