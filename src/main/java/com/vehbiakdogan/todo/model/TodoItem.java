package com.vehbiakdogan.todo.model;

import javax.persistence.*;

@Entity
@Table(name = "todo_items")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemId;


    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "desctription", nullable = false)
    private String desctription;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "todo_id", nullable = false)
    private Long todoId;

    @Column(name = "attached_todo_id", nullable = true)
    private Long attachedId;


    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;


    @ManyToMany(mappedBy = "todo_items", fetch = FetchType.LAZY)


    public long getItemId() {
        return itemId;
    }


    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesctription() {
        return desctription;
    }

    public void setDesctription(String desctription) {
        this.desctription = desctription;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getTodoId() {
        return todoId;
    }

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
    }

    public Long getAttachedId() {
        return attachedId;
    }

    public void setAttachedId(Long attachedId) {
        this.attachedId = attachedId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
