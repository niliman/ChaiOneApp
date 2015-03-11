package com.chaione;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.synerzip.chaioneapp.R;

/**
 * FeedListRowHolder describes an item view and metadata about its place within the RecyclerView.
 * Created by niliman on 1/19/2015.
 */
public class FeedListRowHolder extends RecyclerView.ViewHolder {


    /**
     * NetworkImageView that will display the image.
     */
    public NetworkImageView avatarImg;
    /**
     * TextView that will display the text.
     */
    public TextView title, description;

    public FeedListRowHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.txtTitle);
        avatarImg = (NetworkImageView) itemView.findViewById(R.id.avatarImageView);
        description = (TextView) itemView.findViewById(R.id.txtDescription);
    }
}
