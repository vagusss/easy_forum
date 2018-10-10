package forum.controller;

import forum.domain.Post;
import forum.domain.Reply;
import forum.service.BoardService;
import forum.service.PostService;
import forum.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;


@Controller
@RequestMapping(value = "/post")
public class PostController {
    private final PostService postService;
    private final BoardService boardService;
    private final ReplyService replyService;

    @Autowired
    public PostController(PostService postService, BoardService boardService, ReplyService replyService) {
        this.postService = postService;
        this.boardService = boardService;
        this.replyService = replyService;
    }

    //添加帖子
    @RequestMapping(value = "/addPost")
    public String addPost(Post post) {
        if (post != null) {
            Post newPost = post;
            Timestamp createLoginTime = new Timestamp(System.currentTimeMillis());
            newPost.setPostCreateTime(createLoginTime);
            newPost.setPostUpdateTime(createLoginTime);

            postService.addPostByPost(newPost);
            boardService.updatePostNum(newPost.getPostBoardId());

            return "redirect:postContent-" + post.getPostId();
        }
        return "error";
    }

    //查看帖子
    @RequestMapping(value = "postContent-{postId}")
    public String intoPost(@PathVariable int postId, HttpServletRequest request) {
        System.out.println(postId);
        Post post = postService.listPostContent(postId);
        List<Reply> replies = replyService.listReplyByPostId(postId);

        if (post == null) {
            return "/error";
        }
        // 帖子有回复则添加回复信息
        if (replies != null) {
            request.setAttribute("replies", replies);
        }

        request.setAttribute("post", post);
        return "post/postContent";
    }
}
