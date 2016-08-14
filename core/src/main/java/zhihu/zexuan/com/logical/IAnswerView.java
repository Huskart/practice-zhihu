package zhihu.zexuan.com.logical;

/**
 * Created by zexuan on 2016/8/14.
 */
public interface IAnswerView<T> {

    //加载作者布局
    void handleAuthor();

    void shareAnswer();

    void handleToolbarEvent();

}
