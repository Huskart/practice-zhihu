package com.zexuan.zhihu;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zexuan.zhihu.adapter.StoryListAdapter;
import com.zexuan.zhihu.present.StoryPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import zhihu.zexuan.com.logical.IStoryListView;
import zhihu.zexuan.com.model.story.Data;


public class MainActivity extends AppCompatActivity implements IStoryListView {


    //    @Bind(R.id.get_story_btn)
//    Button getStoryBtn;
//    @Bind(R.id.excerpt_tv)
//    TextView excerptTv;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @Bind(R.id.recycle_list)
    RecyclerView recycleList;
    @Bind(R.id.story_toolbar)
    Toolbar toolbar;
    int count = 0;


    private List<Data> storyList = new ArrayList<>();
    private StoryListAdapter storyListAdapter;
    private StoryPresenter mStoryPresent = new StoryPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    LinearLayoutManager mLayoutManager;
    int lastVisibleItem;
    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_alarm:
                    msg += "alarm";
                    break;
                case R.id.action_search:
                    msg += "search";
                    break;
                case R.id.action_settings:
                    msg += "Click setting";
                    break;
            }

            if (!msg.equals("")) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };

    private void initView() {
        toolbar.setBackgroundColor(getResources().getColor(R.color.primary_blue));
        toolbar.setTitle("首页");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_drawer_home));
        toolbar.setOnMenuItemClickListener(onMenuItemClick);

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeContainer.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Map<String, String> map = new HashMap<>();
                map.put("screenSize", "");
                map.put("afterId", count + "");
                count = mStoryPresent.loadStoryFromServer(map);
                Log.e("zexuan","count is " + count);
            }
        });
        final ImageLoader imageLoader = ImageLoader.getInstance();
        mLayoutManager = new LinearLayoutManager(this);
        recycleList.setLayoutManager(new LinearLayoutManager(this));
        recycleList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState){
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
                        &&( lastVisibleItem + 1 == storyListAdapter.getItemCount() - 3 ||  lastVisibleItem + 1 == storyListAdapter.getItemCount())) {
                    setSwipeLayoutStatus(true);
                    Map<String, String> map = new HashMap<>();
                    map.put("screenSize", "");
                    map.put("afterId", count + "");
                    count = mStoryPresent.loadStoryFromServer(map);
                    Log.e("zexuan" , "count is " + count);
                }
            }
        });
        recycleList.setHasFixedSize(true);
        recycleList.setLayoutManager(mLayoutManager);
        recycleList.setItemAnimator(new DefaultItemAnimator());

        storyList = mStoryPresent.loadCacheStoryDataFromLocal();
        if (storyList.size() == 0) {
            setSwipeLayoutStatus(true);
            swipeContainer.post(new Runnable() {
                @Override
                public void run() {
                    swipeContainer.setRefreshing(true);
                }
            });
            Map<String, String> map = new HashMap<>();
            map.put("screenSize", "");
            map.put("afterId", count + "");
            count = mStoryPresent.loadStoryFromServer(map);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void askQuestion() {

    }

    @Override
    public void writeDraft() {

    }

    @Override
    public void handleLoadException() {
        Toast.makeText(MainActivity.this, "网络异常，加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadDataToList(List data) {
        storyList.addAll(data);
        if (storyListAdapter == null) {
            storyListAdapter = new StoryListAdapter(MainActivity.this, storyList);
            recycleList.setAdapter(storyListAdapter);
        } else {
            storyListAdapter.setDatas(storyList);
            storyListAdapter.notifyDataSetChanged();
            recycleList.invalidate();
        }
    }

    @Override
    public void setSwipeLayoutStatus(final boolean status) {
        swipeContainer.setRefreshing(status);
        swipeContainer.post(new Runnable() {
            @Override
            public void run() {
                swipeContainer.setRefreshing(status);
            }
        });
    }

}
