package forum.dao;

import forum.domain.Reply;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReplyDao {
    /**
     * 添加回复
     */
    void addReply(Reply reply);

    /**
     * 指定文章的回复
     */
    List<Reply> listReplyByPostId(int postId);

    /**
     * 删除回复
     */
    void deleteReplyById(int postId);

    /**
     * 通过回复 id 查找回复
     */
    Reply findReplyByReplyId(int replyId);
}
