package forum.dao;

import forum.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostDao {
    /**
     * 添加文章
     */
    void addPost(Post post);

    /**
     * 通过文章 id 查找文章
     */
    Post findPostByPostId(int postId);

    /**
     * 获取所有文章
     */
    List<Post> listAllPostInfo();

    /**
     * 通过文章 id 删除文章
     */
    void deletePostById(int postId);

    /**
     * 更新文章
     */
    void updatePostByPost(Post post);
}
