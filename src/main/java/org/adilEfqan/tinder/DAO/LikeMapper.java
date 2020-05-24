package org.adilEfqan.tinder.DAO;

import org.adilEfqan.tinder.Models.*;
import org.apache.ibatis.annotations.Insert;


public interface LikeMapper {
    String insertToLike = "INSERT INTO public.liked (\"user\", touser, isliked) VALUES (#{currentUserID}, #{toUserID}, #{isLiked})";

    @Insert(insertToLike)
    //@Options(useGeneratedKeys = true, keyProperty = "userID")
    void insertToLike(LikeDislike ld);
}
