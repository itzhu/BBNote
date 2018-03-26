package me.itzhu.common.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.itzhu.common.R;
import me.itzhu.common.util.S;

/**
 * Created by admin on 2016/6/23.
 */
public abstract class CommonUIActivity extends BaseActivity {

    private LinearLayout layout_content;
    private TextView tv_title;
    private ImageView iv_icon;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView();
    }

    public void initContentView() {
        ViewGroup content = (ViewGroup) findViewById(android.R.id.content);
        content.removeAllViews();
        View view = LayoutInflater.from(this).inflate(R.layout.activity_baseui, null);
        layout_content = (LinearLayout) view.findViewById(R.id.layout_content);
        initToolbar(view);
        content.addView(view);
    }

    @Override
    public void setContentView(View contentView) {
        layout_content.addView(contentView);
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, layout_content, true);
    }

    /**
     * 设置toolbar
     *
     * @param view
     */
    private void initToolbar(View view) {
        //toolbar
        toolbar = view.findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionbar = getSupportActionBar();
            actionbar.setDisplayShowTitleEnabled(false);
            //默认图标不显示
            actionbar.setDisplayShowHomeEnabled(false);
            actionbar.setDisplayHomeAsUpEnabled(false);
            //setHomeIcon(R.drawable.aa_icon_back);
            initToolbarContentView();
            showBack();
        }
    }

    /**
     * toolbar maybe null
     */
    public Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * 设置toolbar里面的视图
     */
    public void initToolbarContentView() {
        if (toolbar != null) {
            LayoutInflater.from(this).inflate(R.layout.toolbar_content_common, toolbar, true);
            iv_icon = toolbar.findViewById(R.id.iv_icon);
            tv_title = toolbar.findViewById(R.id.tv_title);
        }
    }

    public void setTitle(String title) {
        if (tv_title != null) {
            tv_title.setText(title);
        }
    }

    /**
     * 显示返回按钮
     * 默认显示
     */
    public void showBack() {
        if (iv_icon != null) {
            iv_icon.setImageResource(R.drawable.ic_expand_more_black_36dp);
            S.INSTANCE._c(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            }, iv_icon);
        }
    }

    /**
     * 隐藏返回按钮
     */
    public void hiddenBack() {
        if (iv_icon != null) {
            iv_icon.setVisibility(View.GONE);
        }
    }

    public TextView getTitleView() {
        return tv_title;
    }

    public ImageView getNavicationView() {
        return iv_icon;
    }

}
