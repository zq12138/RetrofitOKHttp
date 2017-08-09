package com.example.admin.retrofitokhttp.dialog;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.retrofitokhttp.R;

/**
 * Created by zq on 2017/8/7.
 */

public class ETongDaiDialog extends AppCompatDialog {

    EmptyOnClickListener leftListener;
    EmptyOnClickListener rightListener;
    private EtdDoubleListener doubleListener;
    private EtdSingleListener singleListener;
    private TextView TitleView;
    private TextView contentView;
    private Button leftButton;
    private Button rightButton;
    private Object tag;
    private View line;

    private Button.OnClickListener clicked = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.leftBtn:
                    // 第一个按钮
                    if (null != doubleListener) {
                        doubleListener.OnLeftButtonClicked(ETongDaiDialog.this);
                    }
                    if (singleListener != null) {
                        singleListener.OnLeftButtonClicked(ETongDaiDialog.this);
                    }
                    if (leftListener != null) {
                        leftListener.OnClick(ETongDaiDialog.this);
                    }
                    break;
                case R.id.rightBtn:
                    if (null != doubleListener) {
                        doubleListener.OnRightButtonClicked(ETongDaiDialog.this);
                    }
                    if (rightListener != null) {
                        rightListener.OnClick(ETongDaiDialog.this);
                    }
                    break;
            }
        }
    };

    public ETongDaiDialog(Context context) {
        super(context, R.style.DialogTheme);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = (int) (getContext().getResources().getDisplayMetrics().widthPixels * 0.85);
        this.setCancelable(false);
        init();
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public TextView getTitleView() {
        TitleView.setVisibility(View.VISIBLE);
        return TitleView;
    }

    public TextView getContentView() {

        return contentView;
    }

    public Button getLeftButton() {
        leftButton.setVisibility(View.VISIBLE);
        return leftButton;
    }

    public ETongDaiDialog setLeftButton(String content) {
        getLeftButton().setText(content);
        return this;
    }

    public Button getRightButton() {
        line.setVisibility(View.VISIBLE);
        rightButton.setVisibility(View.VISIBLE);
        return rightButton;
    }

    public ETongDaiDialog setRightButton(String content) {
        getRightButton().setText(content);
        return this;
    }

    private void init() {

        setContentView(R.layout.etongdanewdialog);
        TitleView = (TextView) findViewById(R.id.title);
        contentView = (TextView) findViewById(R.id.content);
        line = findViewById(R.id.vertical_line);
        leftButton = (Button) findViewById(R.id.leftBtn);
        rightButton = (Button) findViewById(R.id.rightBtn);
        leftButton.setOnClickListener(clicked);
        rightButton.setOnClickListener(clicked);
        this.setCanceledOnTouchOutside(false);
    }

    public ETongDaiDialog buildSingle(String content, String button, EtdSingleListener listener) {
        getLeftButton().setText(button);
        getContentView().setText(content);
        singleListener = listener;
        return this;
    }

    public ETongDaiDialog buildSingle(String content, String title, String button, EtdSingleListener listener) {
        getTitleView().setText(title);
        return buildSingle(content, button, listener);
    }

    public ETongDaiDialog buildSingle(String content, EtdSingleListener listener) {
        return buildSingle(content, "提示", "确定", listener);

    }

    public ETongDaiDialog buildDouble(String content, String leftButton, String rightButton, EtdDoubleListener listener) {
        getContentView().setText(content);
        getLeftButton().setText(leftButton);
        getRightButton().setText(rightButton);
        doubleListener = listener;
        return this;
    }

    public ETongDaiDialog setContent(String content) {
        getContentView().setText(content);
        return this;
    }

    public ETongDaiDialog setLeftClickListener(EmptyOnClickListener listener) {
        getLeftButton();
        leftListener = listener;
        return this;
    }

    public ETongDaiDialog setRightClickListener(EmptyOnClickListener listener) {
        getRightButton();
        rightListener = listener;
        return this;
    }

    public ETongDaiDialog setTitle(String title) {
        getTitleView().setText(title);
        return this;
    }

    public ETongDaiDialog setDoubleListener(EtdDoubleListener listener) {
        doubleListener = listener;
        return this;
    }

    public ETongDaiDialog setSingleListener(EtdSingleListener listener) {
        singleListener = listener;
        return this;
    }

    public ETongDaiDialog setCancelableChain(boolean flag) {
        super.setCancelable(flag);
        return this;
    }

    public ETongDaiDialog setCanceledOnTouchOutsideChain(boolean cancel) {
        super.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public ETongDaiDialog buildDouble(String content, EtdDoubleListener listener) {
        return buildDouble(content, "确定", "取消", listener);
    }

    public ETongDaiDialog buildDouble(String content, String title, String leftButton, String rightButton, EtdDoubleListener listener) {
        getTitleView().setText(title);
        return buildDouble(content, leftButton, rightButton, listener);
    }

    public ETongDaiDialog showChain() {
        super.show();
        return this;
    }

    public interface EtdDoubleListener extends EtdSingleListener {
        void OnRightButtonClicked(ETongDaiDialog dialog);
    }

    public interface EtdSingleListener {
        void OnLeftButtonClicked(ETongDaiDialog dialog);
    }

    public static class EmptyETongDaiDialogListener implements EtdDoubleListener {
        @Override
        public void OnLeftButtonClicked(ETongDaiDialog dialog) {
            dialog.dismiss();
        }

        @Override
        public void OnRightButtonClicked(ETongDaiDialog dialog) {
            dialog.dismiss();
        }
    }

    public static class EmptyOnClickListener {
        public void OnClick(ETongDaiDialog dialog) {
            dialog.dismiss();
        }
    }
}
