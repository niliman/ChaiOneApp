package com.chaione;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.chaione.adapter.MyRecyclerAdapter;
import com.chaione.model.Data;
import com.chaione.util.ConnectionDetector;
import com.chaione.util.RequestHandler;
import com.example.synerzip.chaioneapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * This activity just shows the most recent posts from public timeline.
 */
public class FeedReader extends Activity implements SwipeRefreshLayout.OnRefreshListener {

    /*
    * The RequestQueue class reference.
    */
    private RequestQueue requestQueue;

    /**
     * JsonObject Response URL
     */
    private String URL = "https://alpha-api.app.net/stream/0/posts/stream/global";

    /**
     * Specify a URL and get a JSON object in response.
     */
    private JsonObjectRequest stringRequest;

    /**
     * List of Data Model Class varibles.
     */
    private List<Data> dataArrayList = new ArrayList<Data>();

    /**
     * The RecycleView reference.
     */
    private RecyclerView recyclerView;

    /**
     * SwipeRefreshLayout reference
     */
    private SwipeRefreshLayout swipeView;

    /**
     * responsible for providing views that represent items in a data set.
     */
    private MyRecyclerAdapter adapter;

    /**
     * isInternetPresent represent boolean type corresponds to the primitive value false.
     */
    private boolean isInternetPresent = false;

    /**
     * ConnectionDetector object.
     */
    private ConnectionDetector conn;

    /**
     * The Realm realm.
     */

    private Realm realm;

    /**
     * The Button button next.
     */
    private Button btnNext;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState This is the same Bundle object that was used to save state data in the onSaveInstanceState( ).
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.listView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeView = (SwipeRefreshLayout) findViewById(R.id.swipe);
        btnNext = (Button) findViewById(R.id.btnNext);
        swipeView.setOnRefreshListener(this);

        // Realm.deleteRealmFile(this);
        realm = Realm.getInstance(this);
        // Instantiate the ConnectionDetector.
        conn = new ConnectionDetector(getApplicationContext());

        isInternetPresent = conn.isConnectingToInternet();
        // checks if Internet Connection is Present or not.
        if (isInternetPresent) {

            fetchTimeLinePosts();
            // Adding request to request queue

            /**
             * create an Instance of a RequestQueue.
             */
            requestQueue = RequestHandler.getInstance(this.getApplicationContext()).getRequestQueue();
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        } else {

            //  realm.beginTransaction();
            RealmResults<Data> resultDataMain = realm.allObjects(Data.class);

            List<Data> dataList = new ArrayList<Data>();
            for (int i = 0; i < resultDataMain.size(); i++) {
                realm.beginTransaction();
                dataList.add(resultDataMain.get(i));
                realm.commitTransaction();
            }
            //realm.commitTransaction();
            adapter = new MyRecyclerAdapter(FeedReader.this, dataList);

            recyclerView.setAdapter(adapter);
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FeedReader.this, FeedReaderDetails.class);
                startActivity(i);

            }
        });

    }

    /**
     * A Method to make a Request for retrieving a response body at a given URL.
     */
    private void fetchTimeLinePosts() {

        stringRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                /**
                 * @throws org.json.JSONException If  problem with the JSON API occurred.
                 */

                try {
                    // Parsing json array response
                    // response will be a json array

                    JSONArray dataArray = response.getJSONArray("data");

                    for (int i = 0; i < dataArray.length(); i++) {
                        // Parsing json object response
                        // response will be a json object
                        realm.beginTransaction();
                        JSONObject numberObj = dataArray.getJSONObject(i);
                        Data dataMain = realm.createObjectFromJson(Data.class, numberObj);
                        dataArrayList.add(0, dataMain);
                        adapter = new MyRecyclerAdapter(FeedReader.this, dataArrayList);
                        recyclerView.setAdapter(adapter);
                        realm.commitTransaction();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * The SwipeRefreshLayout will notify the listener each and every time the gesture is completed again;
     * the listener is responsible for correctly determining when to actually
     * initiate a refresh of its content. If the listener determines there should
     * not be a refresh, it must call setRefreshing(false) to cancel any visual
     * indication of a refresh.
     */
    @Override
    public void onRefresh() {

        requestQueue = Volley.newRequestQueue(this);
        if (adapter != null) {
            realm.beginTransaction();
            realm.where(Data.class).findAll().clear();
            realm.commitTransaction();
            dataArrayList.clear();
            fetchTimeLinePosts();
        } else {
            fetchTimeLinePosts();
        }
        // Adding request to request queue
        RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                swipeView.setRefreshing(false);

            }
        }, 3000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}
