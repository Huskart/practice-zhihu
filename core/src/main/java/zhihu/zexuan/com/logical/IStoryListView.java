package zhihu.zexuan.com.logical;


import java.util.List;

import zhihu.zexuan.com.model.story.Data;

/**
 * Created by Zexuan on 2016/4/8.
 */
public interface IStoryListView<T> {
    void askQuestion();

    void writeDraft();

    void handleLoadException();

    void loadDataToList(List<Data> data);

    void setSwipeLayoutStatus(boolean status);

}
