package in.appnow.ypo.android.ui.create_new_bottom_sheet;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.appnow.ypo.android.R;
import in.appnow.ypo.android.utils.VectorUtils;

/**
 * Created by sonu on 13:26, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class CreateNewViewHolder {
    @BindView(R.id.create_new_row_label)
    TextView rowLabel;

    private Context context;

    public CreateNewViewHolder(Context context, View view) {
        this.context = context;
        ButterKnife.bind(this, view);
    }

    public void bindData(CreateNewModel model) {
        rowLabel.setText(model.getTitle());
        VectorUtils.setVectorCompoundDrawable(rowLabel, context, model.getIcon(), 0, 0, 0, 0);
    }
}
