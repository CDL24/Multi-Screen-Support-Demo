package demo.com.multiscreensupportdemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import fragments.FragmentList;
import utility.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Utils.isTablet(this)) {
            Toast.makeText(this, "This is Tablet", Toast.LENGTH_SHORT).show();
            bindFragment(R.id.container_left);
        } else {
            Toast.makeText(this, "This is Phone", Toast.LENGTH_SHORT).show();
            bindFragment(R.id.container);
        }


    }

    private void bindFragment(int container) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentList fragmentList = new FragmentList();
        fragmentTransaction.add(container, fragmentList, "LIST");
        fragmentTransaction.commit();
    }


}
