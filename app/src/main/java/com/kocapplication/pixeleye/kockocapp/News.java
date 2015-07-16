package com.kocapplication.pixeleye.kockocapp;

/**
 * Created by Administrator on 2015-07-15.
 */
import java.util.ArrayList;

public class News {
    ArrayList<Integer> BoardNo_s = null;
    ArrayList<Board> Boards = null;
    Board temp;
    News(){
        Boards = new ArrayList<Board>();      // getNewsNum_s();
    }
    public void loadBoard(){
        temp = new Board();
        Boards = JsonParser.readNews(JspConn.readNews());
    }
    public ArrayList<Board> getBoards() {
        return this.Boards;
    }

}