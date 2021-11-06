package homework1;

public class Book {
    private String title;
    private Integer pageNum;
    private String type;

    public Book(Integer pageNum, String type) {
        this("默认标题",pageNum,type);
    }

    public Book(String title, Integer pageNum, String type) {
        this.title = title;
        this.pageNum = pageNum;
        this.type = type;
    }

    public void detail(){
        if (pageNum < 200){
            System.err.println("页数不能少于200页!");
        }else{
            System.out.printf("标题：%s  页数：%d  种类：%s\n",title,pageNum,type);
        }

    }
}
