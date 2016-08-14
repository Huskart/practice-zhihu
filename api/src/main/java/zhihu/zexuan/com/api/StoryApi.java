package zhihu.zexuan.com.api;


import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;
import zhihu.zexuan.com.model.answer.AnswerData;
import zhihu.zexuan.com.model.story.Question;
import zhihu.zexuan.com.model.story.StoryData;

/**
 * Created by Zexuan on 2016/4/7.
 */
public interface StoryApi {

    @GET("/topstory?resolution=1920x1080")
    Observable<StoryData> getStoryData(@Query("after_id") String after_id);

    @GET("{questionUrl}")
    Observable<Question> getQuestionData(@Path("questionUrl") String questionUrl);

    @GET("{/answers}")
    Observable<AnswerData> getAnswer(@Query("id") String answerId);

}
