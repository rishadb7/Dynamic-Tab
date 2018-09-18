package developers.rbtech.com.restuarentabyu.models;

import android.annotation.SuppressLint;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import java.util.List;

/**
 * Created by rishad on 7/6/18.
 */

@SuppressLint("ParcelCreator")
public class CategoryItem extends ExpandableGroup<Products2> {

        public CategoryItem(String title, List<Products2> items) {
        super(title, items);
        }
}
