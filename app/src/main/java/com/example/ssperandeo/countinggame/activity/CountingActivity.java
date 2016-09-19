package com.example.ssperandeo.countinggame.activity;

import android.support.v4.app.Fragment;
import com.example.ssperandeo.countinggame.fragment.NumberGridFragment;

public class CountingActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new NumberGridFragment();
    }

}
