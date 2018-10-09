package forum.controller;


import forum.domain.Board;
import forum.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private BoardService boardService;


    @RequestMapping("/main")
    public String mainPage(HttpServletRequest request) {
        List<Board> boards = boardService.listAllBoard();
        request.setAttribute("board", boards);
        return "mainPage";
    }
}
