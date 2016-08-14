package zhihu.zexuan.com.model.story;

/**
 * Created by zexuan on 2016/8/12.
 */
public class Target {
    private Question question;

    private String title;

    private boolean is_copyable;

    private CanComment can_comment;

    private Author author;

    private String url;

    private String comment_permission;

    private String excerpt;

    private int updated_time;

    private int comment_count;

    private int created_time;

    private int voteup_count;

    private String image_url;

    private String type;

    private int id;

    private int thanks_count;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setQuestion(Question question){
        this.question = question;
    }
    public Question getQuestion(){
        return this.question;
    }
    public void setIs_copyable(boolean is_copyable){
        this.is_copyable = is_copyable;
    }
    public boolean getIs_copyable(){
        return this.is_copyable;
    }
    public void setCan_comment(CanComment can_comment){
        this.can_comment = can_comment;
    }
    public CanComment getCan_comment(){
        return this.can_comment;
    }
    public void setAuthor(Author author){
        this.author = author;
    }
    public Author getAuthor(){
        return this.author;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setComment_permission(String comment_permission){
        this.comment_permission = comment_permission;
    }
    public String getComment_permission(){
        return this.comment_permission;
    }
    public void setExcerpt(String excerpt){
        this.excerpt = excerpt;
    }
    public String getExcerpt(){
        return this.excerpt;
    }
    public void setUpdated_time(int updated_time){
        this.updated_time = updated_time;
    }
    public int getUpdated_time(){
        return this.updated_time;
    }
    public void setComment_count(int comment_count){
        this.comment_count = comment_count;
    }
    public int getComment_count(){
        return this.comment_count;
    }
    public void setCreated_time(int created_time){
        this.created_time = created_time;
    }
    public int getCreated_time(){
        return this.created_time;
    }
    public void setVoteup_count(int voteup_count){
        this.voteup_count = voteup_count;
    }
    public int getVoteup_count(){
        return this.voteup_count;
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
    public void setThanks_count(int thanks_count){
        this.thanks_count = thanks_count;
    }
    public int getThanks_count(){
        return this.thanks_count;
    }
}
