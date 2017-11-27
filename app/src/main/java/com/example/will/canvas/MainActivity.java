package com.example.will.canvas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.will.canvas.checkview.CheckView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CheckView checkView;

    //    @BindView(R.id.pieview)
//    PieView mPieview;
//
//    @BindView(R.id.check)
//    Button check;
//    @BindView(R.id.uncheck)
//    Button uncheck;
//    @BindView(R.id.checkview)
//    CheckView checkview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);

        findViewById(R.id.check).setOnClickListener(this);
        findViewById(R.id.uncheck).setOnClickListener(this);
        checkView = (CheckView) findViewById(R.id.checkview);

//
//        ArrayList<PieData> pieDatas = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            pieDatas.add(new PieData("Will_" + i, i * 10));
//        }
//
//        mPieview.setmPieDatas(pieDatas);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.check:
                checkView.check();
                break;
            case R.id.uncheck:
                checkView.unCheck();
                break;
            default:
                break;
        }
    }
}
