package zhihu.zexuan.com.model.story;

/**
 * Created by zexuan on 2016/8/12.
 */
public class Paging {

    private boolean is_end;

    private String next;

    private String previous;

    public void setIs_end(boolean is_end){
        this.is_end = is_end;
    }
    public boolean getIs_end(){
        return this.is_end;
    }
    public void setNext(String next){
        this.next = next;
    }
    public String getNext(){
        return this.next;
    }
    public void setPrevious(String previous){
        this.previous = previous;
    }
    public String getPrevious(){
        return this.previous;
    }

}
