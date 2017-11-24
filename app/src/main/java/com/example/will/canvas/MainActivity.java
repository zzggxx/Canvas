package com.example.will.canvas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.pieview)
//    PieView mPieview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//
//        ArrayList<PieData> pieDatas = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            pieDatas.add(new PieData("Will_" + i, i * 10));
//        }
//
//        mPieview.setmPieDatas(pieDatas);
    }

//    @OnClick(R.id.pieview)
//    public void onClick() {
//    }
}
