package Services;

import DAO.MessageWrapper;
import Models.Message;
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
    }
}
