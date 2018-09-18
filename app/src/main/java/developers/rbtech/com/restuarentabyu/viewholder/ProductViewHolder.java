package developers.rbtech.com.restuarentabyu.viewholder;


import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import at.blogc.android.views.ExpandableTextView;
import developers.rbtech.com.restuarentabyu.R;
import developers.rbtech.com.restuarentabyu.models.Products2;

/**
 * Created by rishad on 7/6/18.
 */

public class ProductViewHolder extends ChildViewHolder {

    private TextView productName,productDescription;
    private ImageView productImage;
    private ExpandableTextView expandableTextView;
    private Button buttonToggle;

    public ProductViewHolder(View itemView) {
        super(itemView);



        productName = (TextView) itemView.findViewById(R.id.product_name);
        productDescription=(TextView)itemView.findViewById(R.id.product_description);
        productImage=(ImageView)itemView.findViewById(R.id.productImage);
        expandableTextView = (ExpandableTextView) itemView.findViewById(R.id.expandableTextView);
        buttonToggle = (Button) itemView.findViewById(R.id.button_toggle);


    }

    public void onBind(Products2 products2, ExpandableGroup group) {
        productName.setText(products2.getName());
        productDescription.setText(products2.getProductDescription());
        productDescription.setVisibility(View.INVISIBLE);
        expandableTextView.setAnimationDuration(750L);
        expandableTextView.setInterpolator(new OvershootInterpolator());
        expandableTextView.setExpandInterpolator(new OvershootInterpolator());
        expandableTextView.setCollapseInterpolator(new OvershootInterpolator());

        buttonToggle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                buttonToggle.setText(expandableTextView.isExpanded() ? R.string.expand : R.string.collapse);
                expandableTextView.toggle();
            }
        });

        buttonToggle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                if (expandableTextView.isExpanded())
                {
                    expandableTextView.collapse();
                    buttonToggle.setText(R.string.expand);
                }
                else
                {
                    expandableTextView.expand();
                    buttonToggle.setText(R.string.collapse);
                }
            }
        });

        expandableTextView.addOnExpandListener(new ExpandableTextView.OnExpandListener()
        {
            @Override
            public void onExpand(@NonNull final ExpandableTextView view)
            {
                // Log.d(TAG, "ExpandableTextView expanded");
            }

            @Override
            public void onCollapse(@NonNull final ExpandableTextView view)
            {
                // Log.d(TAG, "ExpandableTextView collapsed");
            }
        });


      /*  try {*/
            Glide.with(productImage.getContext()).load(products2.getProductImage()).into(productImage);

      /*  }catch (Exception e){
            e.printStackTrace();
        }*/

        /*if (group.getTitle().equals("Android")) {
            productName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nexus, 0, 0, 0);
        } else if (group.getTitle().equals("iOS")) {
            productName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.iphone, 0, 0, 0);
        } else {
            productName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.window_phone, 0, 0, 0);
        }*/
    }
}
