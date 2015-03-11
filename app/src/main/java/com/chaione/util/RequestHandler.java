package com.chaione.util;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * This is a singleton class where we initialize all the volley core objects.
 * Created by niliman on 1/15/2015.
 */
public class RequestHandler extends Application {
    /**
     * Debug Tag for use logging debug output to LogCat
     */
    public static final String TAG = "VolleyPatterns";

    private RequestQueue requestQueue;
    ImageLoader imageLoader;
    private static RequestHandler sInstance;
    private Context mContext;


    RequestHandler() {

    }

    public static synchronized RequestHandler getInstance(Context context) {
        if (null == sInstance) {
            sInstance = new RequestHandler();
        }
        sInstance.mContext = context;
        return sInstance;

    }

    /*
   *A Method to Get a RequestQueue.
   * @return requestQueue Class Object.
   */
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mContext);
        }
        return requestQueue;
    }

    public <Data> void addToRequestQueue(Request<Data> req, String tag) {

        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (imageLoader == null) {
            imageLoader = new ImageLoader(this.requestQueue,
                    new LruBitmapCache());
        }
        return this.imageLoader;
    }

    /*
   * A method to Add the request to the RequestQueue.
   * @param req request.
   */
    public <Data> void addToRequestQueue(Request<Data> req) {

        req.setTag(TAG);
        getRequestQueue().add(req);
    }


}
