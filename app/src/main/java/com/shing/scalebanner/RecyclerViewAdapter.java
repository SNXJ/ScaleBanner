package com.shing.scalebanner;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Name: RecyclerViewAdapter
 * Author: SNXJ
 * Date: 2017-12-19
 * Description:描述：
 */
public class RecyclerViewAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {
    public RecyclerViewAdapter(@Nullable List<Integer> data) {
        super(R.layout.view_card_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        helper.getView(R.id.imageView).setBackgroundResource(item);

    }

}
