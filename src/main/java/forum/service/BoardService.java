package forum.service;

import forum.domain.Board;
import forum.domain.Post;

import java.util.List;

public interface BoardService {
//添加板块

    void addBoardByBoard(Board board);

//通过板块名删除板块

    void deleteBoardByBoardName(String boardName);

//获取所有板块

    List<Board> listAllBoard();

//获取指定板块的文章

//    Board listAllPostOfBoard(int boardId);
List<Post> listAllPostOfBoard(int boardId);

//通过 id 获取板块

    Board intoBoardByBoardId(int boardId);

//通过板块名称获取板块

    Board intoBoardByBoardName(String boardName);

//更新板块文章数量

    void updatePostNum(int boardId);

//更新板块信息

    void updateBoardInfo(Board board);

//删除板块

    void deleteBoard(int boardId);
}
