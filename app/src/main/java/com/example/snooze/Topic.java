/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.snooze;


/**
 * {@link Topic} represents a Topic that the user can choose.
 */

public class Topic {

    /** Default translation for the Topic */
    private String mTopicName;

    /** Audio resource ID for the Topic */
    private int mAudioResourceId;

    /** Image resource ID for the Topic */
    private int mImageResourceId;


    /** Create a new Topic object */


    public Topic(String TopicName, int imageResourceId, int audioResourceId) {
        mTopicName = TopicName;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Get the getTopicName.
     */

    public String getTopicName() {
        return mTopicName;
    }

    /**
     * Return the image resource ID of the word.
     */

    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Return the audio resource ID of the word.
     */

    public int getAudioResourceId() {
        return mAudioResourceId;
    }

}