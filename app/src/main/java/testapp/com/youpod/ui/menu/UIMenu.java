package testapp.com.youpod.ui.menu;

import android.app.Activity;

/**
 * Created by Jeremy on 4/19/2016.
 */
public abstract class UIMenu
{
    private final int resId;
    protected Activity parentAct;

    public int getResId(){return resId;}

    public UIMenu(int resId)
    {
        this.resId = resId;
    }

    public void didShow(Activity activity)
    {
        this.parentAct = activity;
        onShow();
    }

    protected abstract void onShow();
}
