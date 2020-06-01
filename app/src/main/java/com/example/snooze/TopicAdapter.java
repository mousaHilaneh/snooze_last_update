package com.example.snooze;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;


/**
 *  TopicAdapter is an ArrayAdapter that provides the layout for each list item
 * based on a data source, which is a list of Topic objects.
 */
public class TopicAdapter extends ArrayAdapter<Topic>  {


    TopicAdapter(Context context, ArrayList<Topic> topics) {
        super(context, 0, topics);

    }
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the Topic object located at this position in the list
        Topic currentTopic = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID name_text_view.
        TextView topicNameTextView = listItemView.findViewById(R.id.topic_name_text_view);
        // Get the Topic name from the current Topic object and set this text on
        // the Topic TextView.
        assert currentTopic != null;
        topicNameTextView.setText(currentTopic.getTopicName());

        // Find the ImageView in the list_item.xml layout with the ID image.
        ImageView topicImageView = listItemView.findViewById(R.id.topic_image);
        // Check if an image is provided for this word or not
            // If an image is available, display the provided image based on the resource ID
        topicImageView.setImageResource(currentTopic.getImageResourceId());
            // Make sure the view is visible
        topicImageView.setVisibility(View.VISIBLE);

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}