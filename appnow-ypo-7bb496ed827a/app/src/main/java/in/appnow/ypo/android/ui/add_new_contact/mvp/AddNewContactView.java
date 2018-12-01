package in.appnow.ypo.android.ui.add_new_contact.mvp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.appnow.ypo.android.R;
import in.appnow.ypo.android.utils.TextUtils;

import static in.appnow.ypo.android.utils.StringUtils.USER_ID;

/**
 * Created by sonu on 16:22, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class AddNewContactView extends FrameLayout {
    @BindView(R.id.add_contact_enter_member_identifier_input)
    EditText memberIdentifierInput;
    @BindView(R.id.add_contact_request_access_button)
    Button addContactButton;
    @BindView(R.id.add_contact_toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_left_action_button)
    Button cancelButtonClick;
    @BindView(R.id.toolbar_right_action_button)
    Button clearButtonClick;

    public AddNewContactView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.activity_add_new_contact, this);
        ButterKnife.bind(this, this);
        memberInputListener();
        cancelButtonClick.setText("Cancel");
        clearButtonClick.setText("Clear");
    }

    public void onAddContactButtonClick(OnClickListener onClickListener) {
        addContactButton.setOnClickListener(onClickListener);
    }

    public void onCancelButtonClick(OnClickListener onClickListener) {
        cancelButtonClick.setOnClickListener(onClickListener);
    }

    public void onClearButtonClick(OnClickListener onClickListener) {
        clearButtonClick.setOnClickListener(onClickListener);
    }

    public void clearInput() {
        memberIdentifierInput.setText("");
        clearButtonClick.setEnabled(false);
    }

    public void memberInputListener() {
        memberIdentifierInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = TextUtils.getText(memberIdentifierInput);
                if (!android.text.TextUtils.isEmpty(text) && text.length() > 0) {
                    clearButtonClick.setEnabled(true);
                } else {
                    clearButtonClick.setEnabled(false);
                }
            }
        });
    }

    public boolean validateMemberIdentifier() {
        String identifier = TextUtils.getText(memberIdentifierInput);
        if (android.text.TextUtils.isEmpty(identifier)) {
            memberIdentifierInput.setError("Please enter identifier.");
            return false;
        } else if (android.text.TextUtils.equals(identifier, USER_ID)) {
            memberIdentifierInput.setError("Please enter valid Member Identifier.");
            return false;
        } else
            return true;
    }
}

