package com.zexuan.zhihu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QuestionActivity extends AppCompatActivity {

    @Bind(R.id.question_toolbar)
    Toolbar questionToolbar;
    @Bind(R.id.topic_horizontal_ll)
    LinearLayout topicHorizontalLl;
    @Bind(R.id.topic_horizontal_sv)
    HorizontalScrollView topicHorizontalSv;

    private String[] topic = new String[]{"生活", "旅行", "旅游", "旅行分享", "一个人的生活"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        questionToolbar.setBackgroundColor(getResources().getColor(R.color.primary_blue));
        questionToolbar.setTitle("共 1864 个回答");
        questionToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(questionToolbar);

        questionToolbar.setNavigationIcon(getDrawable(R.drawable.ic_arrow_back));
        questionToolbar.setOnMenuItemClickListener(onMenuItemClick);

        for (String s : topic) {
            TextView textView = new TextView(this);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            params.leftMargin = 14;
            params.rightMargin = 14;
            textView.setLayoutParams(params);
            // 设置字体大小
            textView.setText(s);
            textView.setTextSize(14);
            textView.setBackground(getResources().getDrawable(R.drawable.rec_btn_background, getTheme()));
            textView.setTextColor(getResources().getColor(R.color.primary_blue));
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(30, 16, 30, 16);//left, top, right, bottom
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            topicHorizontalLl.addView(textView);
        }
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_social_share:
                    msg += "action_social_share";
                    break;
                case R.id.action_settings:
                    msg += "Click setting";
                    break;
            }

            if (!msg.equals("")) {
                Toast.makeText(QuestionActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_question, menu);
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
}
