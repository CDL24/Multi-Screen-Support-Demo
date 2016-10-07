package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import adapter.DetailRecyclerAdapter;
import demo.com.multiscreensupportdemo.R;
import interfaces.RecyclerItemClickListener;
import modals.Info;
import utility.Utils;

/**
 * Created by C.limbachiya on 10/4/2016.
 */

public class FragmentDetail extends Fragment {

    private RecyclerView mRecyclerView;
    DetailRecyclerAdapter adapter;
    ArrayList<Info> listInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detailview, null);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initControls(view);

        initClassObjects();
    }

    private void initClassObjects() {
        listInfo = new ArrayList<>();
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
                        if (Utils.isTablet(getActivity())) {
                            //Add Detail Fragment

                        } else {
                            //Open Detail Activity
                        }
                    }
                })
        );
    }

    //Load data from server
    private void bindListData() {
        //Bundle extras  = getArguments().getInt("POSITION", 0);
        //Bundle extras = getActivity().getIntent().getExtras();
        listInfo = getArguments().getParcelableArrayList("DETAIL_OBJECT");
        if(listInfo != null)
        {
            if (null != listInfo && listInfo.size() > 0) {
                adapter = new DetailRecyclerAdapter(getActivity(), listInfo);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setAdapter(adapter);
            }
        }
    }

    //init UI controls
    private void initControls(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_detail);
    }
}