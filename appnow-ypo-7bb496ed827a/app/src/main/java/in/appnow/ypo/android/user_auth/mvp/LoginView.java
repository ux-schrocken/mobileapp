package in.appnow.ypo.android.user_auth.mvp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.appnow.ypo.android.R;
import in.appnow.ypo.android.utils.ImageUtils;
import in.appnow.ypo.android.utils.StringUtils;
import in.appnow.ypo.android.utils.ToastUtils;

/**
 * Created by sonu on 18:13, 18/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
public class LoginView extends FrameLayout {
    @BindView(R.id.login_note_label)
    TextView noteLabel;
    @BindView(R.id.login_sign_in_button)
    Button loginButton;
    @BindView(R.id.login_app_logo)
    ImageView appLogo;
    @BindView(R.id.login_email_input)
    EditText email;
    @BindView(R.id.login_password_input)
    EditText password;

    public LoginView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.activity_login, this);
        ButterKnife.bind(this, this);
        ImageUtils.setDrawableImage(context, appLogo, R.drawable.icon_ypo);
        setPrivacyPolicySpannable(context);
    }

    public void onLoginButtonClick(OnClickListener onClickListener) {
        loginButton.setOnClickListener(onClickListener);
    }

    public String getUserId(){
        if (!email.getText().toString().equalsIgnoreCase("")){
            StringUtils.USER_ID = email.getText().toString().trim();
        }else{
            StringUtils.USER_ID = "4";
        }

        return StringUtils.USER_ID;
    }

    public boolean isValid(){
        boolean isValid = false;
        if (email.getText().toString().equalsIgnoreCase("")){
            ToastUtils.shortToast("Enter ID");
        }else if (password.getText().toString().equalsIgnoreCase("")){
            ToastUtils.shortToast("Enter Password");
        }else if (!password.getText().toString().equalsIgnoreCase("ypo")){
            ToastUtils.shortToast("Invalid Password");
        }else{
            StringUtils.USER_ID = email.getText().toString().trim();
            isValid = true;
        }
        return isValid;
    }

    private void setPrivacyPolicySpannable(Context context) {

        SpannableStringBuilder spanTxt = new SpannableStringBuilder(
                "  Your details are secure 256-bit encryption enabled.");
        Drawable d = getResources().getDrawable(R.drawable.ic_lock_outline_black_24dp); //drawable image
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);
        spanTxt.setSpan(span, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanTxt.append(" Know more");
        spanTxt.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
            }
        }, spanTxt.length() - "Know more".length(), spanTxt.length(), 0);
        noteLabel.setMovementMethod(LinkMovementMethod.getInstance());
        noteLabel.setText(spanTxt, TextView.BufferType.SPANNABLE);
    }
}
