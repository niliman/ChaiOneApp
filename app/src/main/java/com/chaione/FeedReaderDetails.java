package com.chaione;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chaione.adapter.MyRecyclerAdapter;
import com.chaione.model.Data;
import com.example.synerzip.chaioneapp.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by synerzip on 9/3/15.
 */
public class FeedReaderDetails extends Activity {

    /**
     * responsible for providing views that represent items in a data set.
     */
    private MyRecyclerAdapter adapter;

    /**
     * The RecycleView reference.
     */
    private RecyclerView recyclerView;


    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.feed_reader_details);
        recyclerView = (RecyclerView) findViewById(R.id.listView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        realm = Realm.getInstance(getApplicationContext());

        RealmResults<Data> result = realm.where(Data.class).findAll();
        List<Data> dataList = new ArrayList<Data>();

        for (int i = 0; i < result.size(); i++) {

            dataList.add(result.get(i));

        }

        adapter = new MyRecyclerAdapter(FeedReaderDetails.this, dataList);

        recyclerView.setAdapter(adapter);
    }


}
