package Library.Dto.java.VisualizeItems;

import java.util.Date;

public class BorrowedItemsVisualize {
    private int id;
    private String title;
    private Date BorrowDate;
    private Date DueDate;

    public BorrowedItemsVisualize(int id,String title, Date borrowDate, Date dueDate) {
        this.id = id;
        this.title = title;
        BorrowDate = borrowDate;
        DueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBorrowDate() {
        return BorrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        BorrowDate = borrowDate;
    }

    public Date getDueDate() {
        return DueDate;
    }

    public void setDueDate(Date dueDate) {
        DueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
