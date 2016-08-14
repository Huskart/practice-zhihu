package zhihu.zexuan.com.model.story;

/**
 * Created by zexuan on 2016/8/12.
 */
public class Question {
    private boolean is_following;

    private String title;

    private String url;

    private Author author;

    private String type;

    private int answer_count;

    private int id;

    public int getAnswer_count() {
        return answer_count;
    }

    public void setAnswer_count(int answer_count) {
        this.answer_count = answer_count;
    }

    public void setIs_following(boolean is_following){
        this.is_following = is_following;
    }
    public boolean getIs_following(){
        return this.is_following;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setAuthor(Author author){
        this.author = author;
    }
    public Author getAuthor(){
        return this.author;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
}
