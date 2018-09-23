/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Mart_
 */
public class Post {
    
    private int id;
    private String type;
    private int parentId;
    private Date date;
    private String content;
    private int userId;
    private int threadId;

    public Post(String type, int parentId, Date date, String content, int userId, int threadId) {
        this.type = type;
        this.parentId = parentId;
        this.date = date;
        this.content = content;
        this.userId = userId;
        this.threadId = threadId;
    }
    
    
    
}
