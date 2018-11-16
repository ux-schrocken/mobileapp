package in.appnow.ypo.android.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.SwitchCompat;
import android.widget.Button;
import android.widget.TextView;

public class VectorUtils {
    /**
     * sets compound drawables programmatically, as it is not supported in apis below 21
     *
     * @param view
     * @param context
     * @param left
     * @param right
     * @param top
     * @param bottom
     */
    public static void setVectorCompoundDrawable(TextView view, Context context, int left, int top, int right, int bottom, int color) {

        try {

            if (context != null) {
                Drawable drawableLeft = null;
                Drawable drawableRight = null;
                Drawable drawableTop = null;
                Drawable drawableBottom = null;

                if (left > 0) {
                    drawableLeft = context.getResources().getDrawable(left);
                }
                if (right > 0) {
                    drawableRight = context.getResources().getDrawable(right);
                }
                if (top > 0) {
                    drawableTop = context.getResources().getDrawable(top);
                }
                if (bottom > 0) {
                    drawableBottom = context.getResources().getDrawable(bottom);
                }

                view.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);

                if (color > 0) {
                    for (Drawable drawable : view.getCompoundDrawables()) {
                        if (drawable != null) {
                            drawable.setColorFilter(new PorterDuffColorFilter(context.getResources().getColor(color), PorterDuff.Mode.SRC_IN));
                        }
                    }
                }
            }

        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * sets compound drawables programmatically, as it is not supported in apis below 21
     *
     * @param button
     * @param context
     * @param left
     * @param right
     * @param top
     * @param bottom
     */
    public static void setVectorCompoundDrawable(Button button, Context context, int left, int top, int right, int bottom, int color) {

        try {

            if (context != null) {
                Drawable drawableLeft = null;
                Drawable drawableRight = null;
                Drawable drawableTop = null;
                Drawable drawableBottom = null;

                if (left > 0) {
                    drawableLeft = context.getResources().getDrawable(left);
                }
                if (right > 0) {
                    drawableRight = context.getResources().getDrawable(right);
                }
                if (top > 0) {
                    drawableTop = context.getResources().getDrawable(top);
                }
                if (bottom > 0) {
                    drawableBottom = context.getResources().getDrawable(bottom);
                }
                if (color > 0) {
                } else {

                }
                button.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
                if (color > 0) {
                    for (Drawable drawable : button.getCompoundDrawables()) {
                        if (drawable != null) {
                            drawable.setColorFilter(new PorterDuffColorFilter(context.getResources().getColor(color), PorterDuff.Mode.SRC_IN));
                        }
                    }
                }
            }

        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param switchCompat
     * @param context
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @param color
     */
    public static void setVectorCompoundDrawable(SwitchCompat switchCompat, Context context, int left, int top, int right, int bottom, int color) {

        try {

            if (context != null) {
                Drawable drawableLeft = null;
                Drawable drawableRight = null;
                Drawable drawableTop = null;
                Drawable drawableBottom = null;

                if (left > 0) {
                    drawableLeft = context.getResources().getDrawable(left);
                }
                if (right > 0) {
                    drawableRight = context.getResources().getDrawable(right);
                }
                if (top > 0) {
                    drawableTop = context.getResources().getDrawable(top);
                }
                if (bottom > 0) {
                    drawableBottom = context.getResources().getDrawable(bottom);
                }
                if (color > 0) {
                } else {

                }
                switchCompat.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
                if (color > 0) {
                    for (Drawable drawable : switchCompat.getCompoundDrawables()) {
                        if (drawable != null) {
                            drawable.setColorFilter(new PorterDuffColorFilter(context.getResources().getColor(color), PorterDuff.Mode.SRC_IN));
                        }
                    }
                }
            }

        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

}
