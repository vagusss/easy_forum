package forum.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import forum.dao.BoardDao;
import forum.domain.Board;
import forum.domain.Post;
import forum.service.BoardService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardDao boardDao;

    @Value("${BOARD_SHOW}")
    private String BOARD_SHOW;
//
    @Value("${ALL_BOARD}")
    private String ALL_BOARD;

    @Autowired
    RabbitTemplate template;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    public BoardServiceImpl(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Override
    public void addBoardByBoard(Board board) {
        if (board != null) {
            boardDao.addBoard(board);

            //增加板块后发消息到队列
            template.convertAndSend("BOARD.INSERT","BOARD.INSERT"+board.getBoardId());
        }
    }

    @Override
    public void deleteBoardByBoardName(String boardName) { }

    @Override
    public List<Board> listAllBoard() throws JsonProcessingException {
//        System.out.println(BOARD_SHOW);
        Jedis jedis = jedisPool.getResource();
        //先查询redis缓存
        try {
            System.out.println("查缓存");
            String json = jedis.hget(BOARD_SHOW, ALL_BOARD);
            if (json != null && json.length()>0){
                JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class,Board.class);
                List<Board> boards = mapper.readValue(json, javaType);
                return boards;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        //缓存没有,查询数据库
        List<Board> boards = boardDao.listAllBoard();

        //把结果添加到缓存
        try {
            System.out.println("写缓存");
            jedis.hset(BOARD_SHOW,ALL_BOARD,mapper.writeValueAsString(boards));
            jedis.expire(BOARD_SHOW,1800);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //返回结果
        return boards;
    }

    @Override
//    public Board listAllPostOfBoard(int boardId) {
//        return boardDao.listAllPostsOfBoard(boardId);
//    }
    public List<Post> listAllPostOfBoard(int boardId) {
        return boardDao.listAllPostsOfBoard(boardId);
    }

    @Override
    public Board intoBoardByBoardId(int boardId) {
        return boardDao.findBoardByBoardId(boardId);
    }

    @Override
    public void updatePostNum(int boardId) {
        Board board = boardDao.findBoardByBoardId(boardId);
        board.setBoardPostNum(board.getBoardPostNum() + 1);
        boardDao.updateBoardByBoard(board);
    }

    @Override
    public Board intoBoardByBoardName(String boardName) {
        return boardDao.findBoardByBoardName(boardName);
    }

    @Override
    public void updateBoardInfo(Board board) {
        boardDao.updateBoardByBoard(board);

        //修改板块后发消息到队列
        template.convertAndSend("BOARD.UPDATE","BOARD.UPDATE"+board.getBoardId());
    }

    @Override
    public void deleteBoard(int boardId) {
        boardDao.deleteBoardById(boardId);
    }
}
