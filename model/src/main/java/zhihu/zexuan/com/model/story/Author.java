package zhihu.zexuan.com.model.story;

import java.util.List;

/**
 * Created by zexuan on 2016/8/12.
 */
public class Author {
    private List<Badge> badge;

    private boolean is_followed;

    private boolean is_following;

    private String name;

    private String url;

    private int gender;

    private String user_type;

    private String headline;

    private String avatar_url;

    private String type;

    private String id;

    public void setBadge(List<Badge> badge) {
        this.badge = badge;
    }

    public List<Badge> getBadge() {
        return this.badge;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getGender() {
        return this.gender;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_type() {
        return this.user_type;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getHeadline() {
        return this.headline;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getAvatar_url() {
        return this.avatar_url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public boolean is_followed() {
        return is_followed;
    }

    public void setIs_followed(boolean is_followed) {
        this.is_followed = is_followed;
    }

    public boolean is_following() {
        return is_following;
    }

    public void setIs_following(boolean is_following) {
        this.is_following = is_following;
    }
}
