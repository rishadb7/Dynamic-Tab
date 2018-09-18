package developers.rbtech.com.restuarentabyu.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import developers.rbtech.com.restuarentabyu.fragment.ProductFragment;
import developers.rbtech.com.restuarentabyu.models.Category;

/**
 * Created by rishad on 5/6/18.
 */

public class MyFragmentPageAdapter extends FragmentPagerAdapter {

    public static int pos = 0;

    private List<Fragment> myFragments;
    private List<Category> categories;
    private Context context;

    public MyFragmentPageAdapter(Context c, FragmentManager fragmentManager, List<Fragment> myFrags, List<Category> categories) {
        super(fragmentManager);
        myFragments = myFrags;
        this.categories = categories;
        this.context = c;
    }

    @Override
    public Fragment getItem(int position) {

        return myFragments.get(position);

    }

    @Override
    public int getCount() {

        return myFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        setPos(position);
        return categories.get(position).getCategoryName();
    }

    public static int getPos() {
        return pos;
    }

    public void add(Class<Fragment> c, String title, Bundle b) {
        myFragments.add(Fragment.instantiate(context,c.getName(),b));
       // categories.add(title);
    }

    public static void setPos(int pos) {
        MyFragmentPageAdapter.pos = pos;
    }
}
