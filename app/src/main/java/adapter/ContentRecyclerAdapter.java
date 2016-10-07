package adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import demo.com.multiscreensupportdemo.R;
import modals.Feed;

/**
 * Created by C.limbachiya on 1/11/2016.
 */
public class ContentRecyclerAdapter extends RecyclerView.Adapter<ContentRecyclerAdapter.CustomViewHolder> {

    private ArrayList<Feed> feedItemList;
    private Context mContext;

    private OnItemClickListener mListener;

    public ContentRecyclerAdapter(Context context, ArrayList<Feed> listTitles) {
        this.feedItemList = listTitles;
        this.mContext = context;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    /*public ContentRecyclerAdapter(Context context, List<String> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }*/

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_list_item, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, final int position) {
        final Feed feedItem = feedItemList.get(position);

        //Setting text view title
        customViewHolder.textTitle.setText(feedItem.getHeading());
    }
    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitle;

        public CustomViewHolder(View view) {
            super(view);
            this.textTitle = (TextView) view.findViewById(R.id.text_heading);
        }
    }
}