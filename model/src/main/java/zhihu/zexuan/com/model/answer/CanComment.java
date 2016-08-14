package zhihu.zexuan.com.model.answer;

/**
 * Created by zexuan on 2016/8/14.
 */
public class CanComment {
    private boolean status;

    private String reason;

    public void setStatus(boolean status){
        this.status = status;
    }
    public boolean getStatus(){
        return this.status;
    }
    public void setReason(String reason){
        this.reason = reason;
    }
    public String getReason(){
        return this.reason;
    }
}
