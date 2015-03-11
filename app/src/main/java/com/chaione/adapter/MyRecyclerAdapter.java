package com.chaione.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.toolbox.ImageLoader;
import com.chaione.model.Data;
import com.chaione.FeedListRowHolder;
import com.chaione.util.RequestHandler;
import com.example.synerzip.chaioneapp.R;


import java.util.List;

/**
 * This is a custom list adapter class which provides data to list view.
 * Created by niliman on 1/19/2015.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<FeedListRowHolder> {

    /**
     * List of Data Model Class varibles;
     */
    private List<Data> feedItemList;

    /**
     * Context mContext.
     */
    private Context mContext;

    /**
     * Image Loader object.
     */
    private ImageLoader imageLoader;

    /**
     * My Recycle View Adapter class Constructor
     *
     * @param context
     * @param feedItemList list of Data Model class variables.
     */
    public MyRecyclerAdapter(Context context, List<Data> feedItemList) {

        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    /**
     * OnCreateViewHolder is called whenever a new instance of View Holder class must be instantiated.
     *
     * @param viewGroup special view that contains other views.
     */
    @Override
    public FeedListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        /**
         * Creates a new view.
         */
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        FeedListRowHolder viewHolder = new FeedListRowHolder(v);
        return viewHolder;
    }

    /**
     * This method will move the Cursor to the correct position.
     * @param feedListRowHolder {@inheritDoc}
     * @param i                 {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(FeedListRowHolder feedListRowHolder, int i) {

        Data dataObject = feedItemList.get(i);
        if (imageLoader == null)

            imageLoader = RequestHandler.getInstance(mContext).getImageLoader();


        /**
         *Set the URL of the image that should be loaded into this view, and
         *specify the ImageLoader that will be used to make the request.
         */
        feedListRowHolder.avatarImg.setImageUrl(dataObject.getUser().getAvatar_image().getUrl(), imageLoader);
        feedListRowHolder.title.setText(dataObject.getUser().getName());
        feedListRowHolder.description.setText(dataObject.getText());


    }

    /**
     * Counts the occurrences of items in ArrayList.
     */
    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }
}
