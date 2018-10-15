package forum.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import forum.domain.Board;
import forum.domain.Post;
import forum.service.BoardService;
import forum.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //显示指定板块的文章
    @RequestMapping(value = "/listPosts-{boardId}")
    public String intoBoard(@PathVariable int boardId, HttpServletRequest request,
                            @RequestParam(value = "pageNum",defaultValue = "1",required = false) Integer pageNum) {

        PageHelper.startPage(pageNum,3);

        List<Post> posts = boardService.listAllPostOfBoard(boardId);
        PageInfo<Post> pageInfo = new PageInfo<>(posts,4);

        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("boardId", boardId);
        return "post/postMain";
    }
}
