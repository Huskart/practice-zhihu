package com.zexuan.zhihu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zexuan.zhihu.adapter.StoryListAdapter;
import com.zexuan.zhihu.present.StoryPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import zhihu.zexuan.com.logical.IStoryListView;
import zhihu.zexuan.com.model.story.Data;

/**
 * Created by zexuan on 2016/8/22.
 */
public class MainFragment extends Fragment implements IStoryListView {

    private static final String TAG = MainFragment.class.getSimpleName();

    private View mView;
    private SwipeRefreshLayout mSwipeContainer;
    private RecyclerView mRecycleView;

    private int mLastVisibleItemPosition = 20;
    private StoryPresenter mStoryPresent = new StoryPresenter(this);
    private StoryListAdapter storyListAdapter;
    private List<Data> storyList = new ArrayList<>();
    private int count = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(getActivity());
        this.mView = inflater.inflate(R.layout.fragment_main, container, false);
        initView();
        return this.mView;
    }

    @SuppressLint("CutPasteId")
    @Override
    public void initView() {
        this.mSwipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        this.mRecycleView = (RecyclerView) findViewById(R.id.recycle_list);
        this.mSwipeContainer.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        this.mSwipeContainer.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        this.mSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Map<String, String> map = new HashMap<>();
                map.put("screenSize", "");
                map.put("afterId", count + "");
                count = mStoryPresent.loadStoryFromServer(map);
            }
        });
        initRecycleData();
    }

    private void initRecycleData() {
        final ImageLoader imageLoader = ImageLoader.getInstance();
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        this.mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                mLastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
                mLastVisibleItemPosition  = mLayoutManager.findLastCompletelyVisibleItemPosition();

                Log.i(TAG, "mLastVisibleItemPosition is " + mLastVisibleItemPosition);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        //正在滑动
                        imageLoader.pause();
                        break;
                    case RecyclerView.SCROLL_STATE_IDLE:
                        //滑动停止
                        imageLoader.resume();
                        break;
                }
                if (storyListAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE
                        && (mLastVisibleItemPosition + 1 == storyListAdapter.getItemCount() - 3 || mLastVisibleItemPosition + 1 == storyListAdapter.getItemCount())) {
                    setSwipeLayoutStatus(true);
                    Map<String, String> map = new HashMap<>();
                    map.put("screenSize", "");
                    map.put("afterId", count + "");
                    count = mStoryPresent.loadStoryFromServer(map);
                }
            }
        });
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        storyList = mStoryPresent.loadCacheStoryDataFromLocal();
        if (storyList.size() == 0) {
            setSwipeLayoutStatus(true);
            mSwipeContainer.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeContainer.setRefreshing(true);
                }
            });
            Map<String, String> map = new HashMap<>();
            map.put("screenSize", "");
            map.put("afterId", count + "");
            count = mStoryPresent.loadStoryFromServer(map);
        }
    }

    private View findViewById(int id) {
        return this.mView.findViewById(id);
    }

    @Override
    public void onDestroy() {
        ButterKnife.unbind(getActivity());
        super.onDestroy();
    }

    @Override
    public void askQuestion() {

    }

    @Override
    public void writeDraft() {

    }

    @Override
    public void handleLoadException() {
        Toast.makeText(getActivity(), "网络异常，加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSwipeLayoutStatus(final boolean status) {
        mSwipeContainer.setRefreshing(status);
        mSwipeContainer.post(new Runnable() {
            @Override
            public void run() {
                mSwipeContainer.setRefreshing(status);
            }
        });
    }

    @Override
    public void loadDataToList(List data) {
        storyList.addAll(data);
        if (storyListAdapter == null) {
            storyListAdapter = new StoryListAdapter(getActivity(), storyList);
            mRecycleView.setAdapter(storyListAdapter);
        } else {
            storyListAdapter.setDatas(storyList);
            storyListAdapter.notifyDataSetChanged();
            mRecycleView.invalidate();
        }
    }
}
