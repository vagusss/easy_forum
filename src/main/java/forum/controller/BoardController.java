package forum.controller;

import forum.domain.Board;
import forum.service.BoardService;
import forum.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
    public String intoBoard(@PathVariable int boardId, HttpServletRequest request) {
        Board board = boardService.listAllPostOfBoard(boardId);
        request.setAttribute("board", board);
        request.setAttribute("boardId", boardId);
        return "post/postMain";
    }
}
