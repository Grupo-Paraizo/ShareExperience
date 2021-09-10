package view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sev1.FeedFragment;
import com.example.sev1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import data.FeedExperience;

public class FeedExperienceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Object> recyclerViewItems;
    private final Context mContext;
    FeedFragment feedFragment;


    public FeedExperienceAdapter(Context context, List<Object> recyclerViewItems,FeedFragment feedFragment) {
        this.mContext = context;
        this.recyclerViewItems = recyclerViewItems;
        this.feedFragment = feedFragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feed_experience, null);
        return new MenuItemViewHolder(itemLayoutView);
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {

        MenuItemViewHolder menuItemHolder = (MenuItemViewHolder) holder;
        final FeedExperience fp = (FeedExperience) recyclerViewItems.get(position);

        menuItemHolder.names.setText(fp.getName());
        //menuItemHolder.userId.setText(fp.getId());
        menuItemHolder.description.setText(fp.getDescription());

    }


    @Override
    public int getItemCount() {
        return recyclerViewItems.size();
    }


    public class MenuItemViewHolder extends RecyclerView.ViewHolder {
        public TextView names;
        public TextView userId;
        public ImageView thumb;
        public TextView description;
        public LinearLayout lineLayout;

        MenuItemViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            names      = (TextView) itemLayoutView.findViewById(R.id.name);
            thumb       = (ImageView) itemLayoutView.findViewById(R.id.img_feed);
            //userId     = (TextView) itemLayoutView.findViewById(R.id.userUid);
            description     = (TextView) itemLayoutView.findViewById(R.id.description);
            //lineLayout = (LinearLayout) itemLayoutView.findViewById(R.id.midlelayout);

            Picasso.get().load("https://media-exp1.licdn.com/dms/image/C4D03AQHu_P-GHDZX-Q/profile-displayphoto-shrink_200_200/0/1626997550637?e=1632960000&v=beta&t=0lRQaGJTSPR5JRwkEAYh42Co7bU4NKLPn_-nKLSG2NY").into(thumb);

        }
    }
}
