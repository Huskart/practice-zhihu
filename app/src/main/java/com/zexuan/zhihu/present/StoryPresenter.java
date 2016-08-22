package com.zexuan.zhihu.present;

import android.util.Log;

import com.nostra13.universalimageloader.utils.L;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zexuan.zhihu.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import zhihu.zexuan.com.api.StoryApi;
import zhihu.zexuan.com.logical.IStoryListView;
import zhihu.zexuan.com.model.story.Data;
import zhihu.zexuan.com.model.story.StoryData;

/**
 * Created by Zexuan on 2016/4/8.
 * Present is the bridge between model and view
 */
public class StoryPresenter {

    private IStoryListView storyListView;

    public StoryPresenter(IStoryListView storyListView) {
        this.storyListView = storyListView;
    }

    public List<Data> loadCacheStoryDataFromLocal() {
        return new ArrayList<>();
    }

    public int loadStoryFromServer(Map<String, String> param) {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Authorization", Constants.AUTHORIZATION).build();
                return chain.proceed(request);
            }
        };
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.interceptors().add(interceptor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.zhihu.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        StoryApi storyApi = retrofit.create(StoryApi.class);
        storyApi.getStoryData(param.get("afterId"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StoryData>() {
                    @Override
                    public void onCompleted() {
                        storyListView.setSwipeLayoutStatus(false);
//                        swipeContainer.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("MainActivity", e.toString());
                    }

                    @Override
                    public void onNext(StoryData storyData) {
                        Log.i("zexuan" , storyData.getData().size() + " size --- paging " + storyData.getPaging().getPrevious() + " " + storyData.getPaging().getNext());
                        List<Data> list = storyData.getData();
                        storyListView.loadDataToList(list); //把数据加载到UI
                    }
                });

        int afterId = Integer.parseInt(param.get("afterId")) ;
        if(afterId == 0){
            return 20;
        }else {
            return afterId * 2;
        }
    }


}
