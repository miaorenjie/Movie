package com.example.miaojie.ptest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.miaojie.ptest.bean.MovieInfo;
import com.example.miaojie.ptest.R;

/**
 * Created by miaojie on 2017/3/20.
 */

public class DetailedActivity extends Activity {
    public ImageView DetailedImg;
    public Toolbar DetailedName;
    public TextView Name;
    public ImageView CoverImg;
    public TextView Director;
    public TextView Protagonist;
    public TextView Introduce;
    private MovieInfo movieInfo;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_movie_info);
        initView();
        movieInfo= (MovieInfo) getIntent().getSerializableExtra("MovieInfo");
        setInfo();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailedActivity.this, ChoosSeatActivity.class);
                intent.putExtra("MovieName", movieInfo.getName());
                startActivity(intent);
            }
        });
    }
    private void setInfo()
    {
        DetailedImg.setImageResource(movieInfo.getDetailedCover());
        DetailedName.setTitle(movieInfo.getName());
        Name.setText(movieInfo.getName());
        CoverImg.setImageResource(movieInfo.getCoverId());
        Director.setText(movieInfo.getDirector());
        Introduce.setText(movieInfo.getIntroduce());
        Protagonist.setText(movieInfo.getProtagonist());
    }

    private void initView()
    {
        button= (Button) findViewById(R.id.Movie_Item_Buy);
        DetailedImg= (ImageView) findViewById(R.id.Detailed_Img);
        DetailedName= (Toolbar) findViewById(R.id.detailed_name);
        Introduce= (TextView) findViewById(R.id.detailed_Introduce);
        Director= (TextView) findViewById(R.id.Movie_Item_Director);
        CoverImg= (ImageView) findViewById(R.id.Movie_Item_Img);
        Protagonist= (TextView) findViewById(R.id.Movie_Item_Protagonist);
        Name= (TextView) findViewById(R.id.Movie_Item_Name);
    }
}
