package com.example.snooze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	private MediaPlayer mMediaPlayer;

	private AudioManager mAudioManager;

	boolean playing = false;

	private View decorView;


	private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
		@Override
		public void onAudioFocusChange(int focusChange) {
			if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
					focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
				// The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
				// short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
				// our app is allowed to continue playing sound but at a lower volume. We'll treat
				// both cases the same way because our app is playing short sound files.

				// Pause playback and reset player to the start of the file. That way, we can
				// play the word from the beginning when we resume playback.
				mMediaPlayer.pause();
				mMediaPlayer.seekTo(0);
			} else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
				// The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
				mMediaPlayer.start();
			} else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
				// The AUDIOFOCUS_LOSS case means we've lost audio focus and
				// Stop playback and clean up resources
				releaseMediaPlayer();
			}
		}
	};


	private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
		@Override
		public void onCompletion(MediaPlayer mediaPlayer) {
			// Now that the sound file has finished playing, release the media player resources.
			mMediaPlayer.start();
		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		decorView = getWindow().getDecorView();
		decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
			@Override
			public void onSystemUiVisibilityChange(int visibility) {
				if(visibility == 0) {
					decorView.setSystemUiVisibility(hideBars());
				}
			}
		});


		ImageView sittingPos = findViewById(R.id.sitting_pos_button);
		sittingPos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "worked", Toast.LENGTH_SHORT).show();
			}
		});

		ImageView layingPos = findViewById(R.id.laying_pos_button);
		layingPos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "worked", Toast.LENGTH_SHORT).show();
			}
		});

		ImageView zeroPos = findViewById(R.id.zerogravity_pos_button3);
		zeroPos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "worked", Toast.LENGTH_SHORT).show();
			}
		});

		ImageView stopButton = findViewById(R.id.stop);
		stopButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "worked", Toast.LENGTH_SHORT).show();
			}
		});

		ImageView savePosButton = findViewById(R.id.save_pos);
		savePosButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "worked", Toast.LENGTH_SHORT).show();
			}
		});

		ImageView posButton1 = findViewById(R.id.open_pos1);
		posButton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "worked", Toast.LENGTH_SHORT).show();
			}
		});

		ImageView posButton2 = findViewById(R.id.open_pos2);
		posButton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "worked", Toast.LENGTH_SHORT).show();
			}
		});

		ImageView posButton3 = findViewById(R.id.open_pos3);
		posButton3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "worked", Toast.LENGTH_SHORT).show();
			}
		});


		ImageView openButton2 = findViewById(R.id.open2);
		openButton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "worked", Toast.LENGTH_SHORT).show();
			}
		});


		ImageView saveButton2 = findViewById(R.id.save_position2);
		saveButton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "worked", Toast.LENGTH_SHORT).show();
			}
		});




		final ArrayList<Topic> topics = new ArrayList<>();
		topics.add(new Topic("Rain", R.drawable.rain, R.raw.rain));
		topics.add(new Topic("Stream", R.drawable.stream, R.raw.stream));
		topics.add(new Topic("Birds", R.drawable.birds, R.raw.birds));
		topics.add(new Topic("Jungle", R.drawable.jungle, R.raw.jungle));
		topics.add(new Topic("Waves", R.drawable.waves_beach, R.raw.waves_beach));

		TopicAdapter adapter = new TopicAdapter(MainActivity.this, topics);
		ListView meditationList = findViewById(R.id.meditation_list);
		meditationList.setAdapter(adapter);


		mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		// Set a click listener to play the audio when the list item is clicked on
		meditationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

				if(playing) {

				// Release the media player if it currently exists because we are about to
				// play a different sound file
				releaseMediaPlayer();
				}
				else {

					// Release the media player if it currently exists because we are about to
					// play a different sound file
					releaseMediaPlayer();

					// Get the {@link Word} object at the given position the user clicked on
					Topic topic = topics.get(position);

					int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
							AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

					// Start the audio file
					if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

						mMediaPlayer = MediaPlayer.create(MainActivity.this, topic.getAudioResourceId());

						// Start the audio file
						mMediaPlayer.start();

						// Setup a listener on the media player, so that we can stop and release the
						// media player once the sound has finished playing.
						mMediaPlayer.setOnCompletionListener(mCompletionListener);
					}
				}
				playing = !playing;

			}
		});
	}

	@Override
	protected void onStop() {
		super.onStop();
		// When the activity is stopped, release the media player resources because we won't
		// be playing any more sounds.
		releaseMediaPlayer();
	}

	private void releaseMediaPlayer() {
		// If the media player is not null, then it may be currently playing a sound.
		if (mMediaPlayer != null) {
			// Regardless of the current state of the media player, release its resources
			// because we no longer need it.
			mMediaPlayer.release();

			// Set the media player back to null. For our code, we've decided that
			// setting the media player to null is an easy way to tell that the media player
			// is not configured to play an audio file at the moment.
			mMediaPlayer = null;

			// Regardless of whether or not we were granted audio focus, abandon it. This also
			// unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
			mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
		}
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			decorView.setSystemUiVisibility(hideBars());
		}
	}

	private int hideBars() {
		return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
				| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_FULLSCREEN
				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
	}
}
