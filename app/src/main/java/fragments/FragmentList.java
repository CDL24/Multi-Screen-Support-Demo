package fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.ContentRecyclerAdapter;
import demo.com.multiscreensupportdemo.DetailListActivity;
import demo.com.multiscreensupportdemo.MainActivity;
import demo.com.multiscreensupportdemo.R;
import interfaces.RecyclerItemClickListener;
import modals.Feed;
import modals.Info;
import utility.Utils;

/**
 * Created by C.limbachiya on 10/4/2016.
 */

public class FragmentList extends Fragment {

    private ArrayList<Feed> listTitles;
    private RecyclerView mRecyclerView;
    ContentRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listview, null);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initControls(view);

        initClassObjects();
    }

    private void initClassObjects() {
        listTitles = new ArrayList<>();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindListData();

        registerEvent();
    }

    private void registerEvent() {
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Toast.makeText(getActivity(), "position : "+position, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getActivity(), "Title : "+listInfo.get(position).getInfoList().size(), Toast.LENGTH_SHORT).show();
                        if (Utils.isTablet(getActivity())) {
                            //Add Detail Fragment
                            Bundle bundle = new Bundle();
                            bundle.putParcelableArrayList("DETAIL_OBJECT", (ArrayList<? extends Parcelable>) listTitles.get(position).getInfoList());
                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                            FragmentDetail fragmentList = new FragmentDetail();
                            fragmentList.setArguments(bundle);
                            fragmentTransaction.replace(R.id.container_right, fragmentList, "DETAIL");
                            fragmentTransaction.commit();

                        } else {
                            //Open Detail Activity
                            Bundle bundle = new Bundle();
                            bundle.putParcelableArrayList("DETAIL_OBJECT", (ArrayList<? extends Parcelable>) listTitles.get(position).getInfoList());

                            Intent intent = new Intent(getActivity(), DetailListActivity.class);
                            intent.putExtra("DATA_BUNDLE", bundle);
                            startActivity(intent);
                        }
                    }
                })
        );
    }

    //Load data from server
    private void bindListData() {

        listTitles.clear();

        int i = 0;
        for (Feed feed : Utils.loadFeeds(getActivity())) {
            listTitles.add(feed);
        }

        Toast.makeText(getActivity(), "listTitles.size() : "+listTitles.size(), Toast.LENGTH_SHORT).show();
        if (null != listTitles && listTitles.size() > 0) {
            adapter = new ContentRecyclerAdapter(getActivity(), listTitles);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.setAdapter(adapter);
        }
    }

    //init UI controls
    private void initControls(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
    }
}