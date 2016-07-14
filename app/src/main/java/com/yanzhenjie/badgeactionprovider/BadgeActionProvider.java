/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yanzhenjie.badgeactionprovider;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class BadgeActionProvider extends ActionProvider {

    private ImageView mIvIcon;
    private TextView mTvBadge;

    private int clickWhat;
    private OnClickListener onClickListener;

    /**
     * Creates a new instance.
     *
     * @param context Context for accessing resources.
     */
    public BadgeActionProvider(Context context) {
        super(context);
    }

    @Override
    public View onCreateActionView() {
        int size = getContext().getResources().getDimensionPixelSize(android.support.design.R.dimen.abc_action_bar_default_height_material);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(size, size);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.menu_badge_provider, null, false);
        view.setLayoutParams(layoutParams);
        mIvIcon = (ImageView) view.findViewById(R.id.iv_icon);
        mTvBadge = (TextView) view.findViewById(R.id.tv_badge);
        view.setOnClickListener(onViewClickListener);
        return view;
    }

    private View.OnClickListener onViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onClickListener != null)
                onClickListener.onClick(clickWhat);
        }
    };

    /**
     * 设置点击监听。
     *
     * @param what            what。
     * @param onClickListener listener。
     */
    public void setOnClickListener(int what, OnClickListener onClickListener) {
        this.clickWhat = what;
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int what);
    }

    /**
     * 设置图标。
     *
     * @param icon drawable 或者mipmap中的id。
     */
    public void setIcon(@DrawableRes int icon) {
        mIvIcon.setImageResource(icon);
    }

    /**
     * 设置显示的数字。
     *
     * @param i 数字。
     */
    public void setBadge(int i) {
        mTvBadge.setText(Integer.toString(i));
    }

    /**
     * 设置文字。
     *
     * @param i string.xml中的id。
     */
    public void setTextInt(@StringRes int i) {
        mTvBadge.setText(i);
    }

    /**
     * 设置显示的文字。
     *
     * @param i 文字。
     */
    public void setText(CharSequence i) {
        mTvBadge.setText(i);
    }
}