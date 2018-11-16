package in.appnow.ypo.android.ui.create_new_bottom_sheet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import in.appnow.ypo.android.R;

/**
 * Created by sonu on 13:25, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class CreateNewAdapter extends BaseAdapter {
    private Context context;
    private List<CreateNewModel> createNewModelList;
    private LayoutInflater layoutInflater;

    public CreateNewAdapter(Context context, List<CreateNewModel> createNewModelList) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.createNewModelList = createNewModelList;
    }

    @Override
    public int getCount() {
        return createNewModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return createNewModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CreateNewViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.create_new_custom_row_layout, viewGroup, false);
            holder = new CreateNewViewHolder(context, view);
            view.setTag(holder);
        } else {
            holder = (CreateNewViewHolder) view.getTag();
        }
        holder.bindData(createNewModelList.get(i));
        return view;
    }
}
