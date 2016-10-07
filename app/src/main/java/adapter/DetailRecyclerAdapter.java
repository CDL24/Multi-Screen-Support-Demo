package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import demo.com.multiscreensupportdemo.R;
import modals.Info;

/**
 * Created by C.limbachiya on 1/11/2016.
 */
public class DetailRecyclerAdapter extends RecyclerView.Adapter<DetailRecyclerAdapter.CustomViewHolder> {

    //private ArrayList<Feed> feedItemList;
    private ArrayList<Info> feedItemList;
    private Context mContext;

    private OnItemClickListener mListener;

    public DetailRecyclerAdapter(Context context, ArrayList<Info> infoList) {
        this.feedItemList = infoList;
        this.mContext = context;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feed_item, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, final int position) {
        final Info feedItem = feedItemList.get(position);

        //Setting text view title
        customViewHolder.textTitle.setText(feedItem.getTitle());
        customViewHolder.textCaption.setText(feedItem.getCaption());
        Glide.with(mContext).load(feedItem.getImageUrl()).into(customViewHolder.imgView);
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitle;
        private TextView textCaption;
        private ImageView imgView;

        public CustomViewHolder(View view) {
            super(view);
            this.textTitle = (TextView) view.findViewById(R.id.titleTxt);
            this.textCaption = (TextView) view.findViewById(R.id.captionTxt);
            this.imgView = (ImageView) view.findViewById(R.id.imageView);
        }
    }
}