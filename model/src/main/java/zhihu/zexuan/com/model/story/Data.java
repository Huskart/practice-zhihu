package zhihu.zexuan.com.model.story;

import java.util.List;

/**
 * Created by zexuan on 2016/8/12.
 */
public class Data {
    private Target target;

    private String action_text;

    private int updated_time;

    private String verb;

    private List<Actors> actors ;

    private String type;

    private String id;

    public void setTarget(Target target){
        this.target = target;
    }
    public Target getTarget(){
        return this.target;
    }
    public void setAction_text(String action_text){
        this.action_text = action_text;
    }
    public String getAction_text(){
        return this.action_text;
    }
    public void setUpdated_time(int updated_time){
        this.updated_time = updated_time;
    }
    public int getUpdated_time(){
        return this.updated_time;
    }
    public void setVerb(String verb){
        this.verb = verb;
    }
    public String getVerb(){
        return this.verb;
    }
    public void setActors(List<Actors> actors){
        this.actors = actors;
    }
    public List<Actors> getActors(){
        return this.actors;
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
