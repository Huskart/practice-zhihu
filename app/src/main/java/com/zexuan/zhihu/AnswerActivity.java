package com.zexuan.zhihu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zexuan.zhihu.views.IAnswerView;

import butterknife.ButterKnife;

public class AnswerActivity extends AppCompatActivity implements IAnswerView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        ButterKnife.bind(this);

        initView();
    }

    private void initView(){

    }

    @Override
    public void handleAuthor() {

    }

    @Override
    public void shareAnswer() {

    }

    @Override
    public void handleToolbarEvent() {

    }
}
