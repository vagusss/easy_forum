package forum.service;

import forum.domain.Post;

import java.util.List;


public interface PostService {
    /**
     * 添加文章
     */
    void addPostByPost(Post post);

    /**
     * 获取文章内容
     */
    Post listPostContent(int postId);

    /**
     * 获取所有文章
     */
    List<Post> listAllPost();

    /**
     * 删除文章
     */
    void deletePost(int postId);
}
