package zhihu.zexuan.com.model.story;

/**
 * Created by zexuan on 2016/8/12.
 */
public class Actors {
    private String name;

    private String url;

    private String excerpt;

    private String introduction;

    private String avatar_url;

    private String type;

    private String id;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setExcerpt(String excerpt){
        this.excerpt = excerpt;
    }
    public String getExcerpt(){
        return this.excerpt;
    }
    public void setIntroduction(String introduction){
        this.introduction = introduction;
    }
    public String getIntroduction(){
        return this.introduction;
    }
    public void setAvatar_url(String avatar_url){
        this.avatar_url = avatar_url;
    }
    public String getAvatar_url(){
        return this.avatar_url;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
}
