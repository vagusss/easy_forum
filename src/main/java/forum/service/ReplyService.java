package forum.service;

import forum.domain.Reply;

import java.util.List;


public interface ReplyService {
    /**
     * 添加回复
     */
    void addReply(Reply reply);

    /**
     * 获取指定文章的回复
     */
    List<Reply> listReplyByPostId(int postId);

    /**
     * 删除回复
     */
    void deleteReply(int replyId);
}
