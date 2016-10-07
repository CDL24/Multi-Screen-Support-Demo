package demo.com.multiscreensupportdemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

import fragments.FragmentDetail;
import modals.Info;

/**
 * Created by C.limbachiya on 10/4/2016.
 */

public class DetailListActivity extends AppCompatActivity {

    int position;
    ArrayList<Info> listInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras().getBundle("DATA_BUNDLE");
        listInfo = extras.getParcelableArrayList("DETAIL_OBJECT");
        position  = extras.getInt("POSITION", 0);

        bindFragment();
    }

    private void bindFragment() {

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("DETAIL_OBJECT", listInfo);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentDetail fragmentList = new FragmentDetail();
        fragmentList.setArguments(bundle);
        fragmentTransaction.add(R.id.container, fragmentList, "DETAIL");
        fragmentTransaction.commit();
    }
}
