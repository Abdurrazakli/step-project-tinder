package org.adilEfqan.tinder.Services;

import org.adilEfqan.tinder.DAO.MessageWrapper;
import org.adilEfqan.tinder.Models.Message;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ChatService {
    private final SqlSession session;
    private MessageWrapper mapper;
    public ChatService(SqlSession session) {
        this.session=session;
        mapper = session.getMapper(MessageWrapper.class);
    }

    public List<Message> getMessages(String loggedUser,String chatFriend) {
        return mapper.getAllMessageOfUser(loggedUser,chatFriend);
    }

    public void insertMessage(Message newMessage) {
        mapper.insert(newMessage);
        session.commit();
    }
}
