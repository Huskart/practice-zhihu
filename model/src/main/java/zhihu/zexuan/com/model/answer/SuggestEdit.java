package zhihu.zexuan.com.model.answer;

/**
 * Created by zexuan on 2016/8/14.
 */
public class SuggestEdit {
    private boolean status;

    private String url;

    private String reason;

    private String tip;

    private String title;

    public void setStatus(boolean status){
        this.status = status;
    }
    public boolean getStatus(){
        return this.status;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setReason(String reason){
        this.reason = reason;
    }
    public String getReason(){
        return this.reason;
    }
    public void setTip(String tip){
        this.tip = tip;
    }
    public String getTip(){
        return this.tip;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }

}
