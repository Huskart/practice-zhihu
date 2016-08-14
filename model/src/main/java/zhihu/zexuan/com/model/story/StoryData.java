package zhihu.zexuan.com.model.story;

import java.util.List;

/**
 * Created by zexuan on 2016/8/12.
 */
public class StoryData {
    private Paging paging;

    private List<Data> data ;

    private boolean refresh;

    public void setPaging(Paging paging){
        this.paging = paging;
    }
    public Paging getPaging(){
        return this.paging;
    }
    public void setData(List<Data> data){
        this.data = data;
    }
    public List<Data> getData(){
        return this.data;
    }
    public void setRefresh(boolean refresh){
        this.refresh = refresh;
    }
    public boolean getRefresh(){
        return this.refresh;
    }

}
