package com.example.alnumlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.alnumlist.adapter.PhotosAdapter;
import com.example.alnumlist.database.photo.PhotoDataSource;
import com.example.alnumlist.models.Album_Details_Model;

import java.util.List;

import retrofit2.http.Url;

public class Photo extends AppCompatActivity implements PhotosAdapter.addListener {
    private RecyclerView photosRecyclerView;
    private PhotosAdapter photosAdapter;
    private List<Album_Details_Model> models;
    String url;
    View view;
    ImageView fullScreenImageView;
    View rootView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        photosRecyclerView =findViewById(R.id.photosRecyclerView);
        fullScreenImageView = findViewById(R.id.full_size_profile_picture);
        String id = (getIntent().getStringExtra("id"));
        rootView = findViewById(R.id.root);
        Log.d("retrofit", "onResume: " + id);
        models = PhotoDataSource.getInstance().getAlbumPhotos(Integer.valueOf(id));
        photosAdapter = new PhotosAdapter(models , this , this);
        photosRecyclerView.setAdapter(photosAdapter);
        photosRecyclerView.setLayoutManager(new GridLayoutManager(this , 2));

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("retrofit", "onResume: ");
    }

    @Override
    public void animate(View view , String url2) {
        Log.d("retrofit", "animate: " + view.getId());
        url = url2;
        enlargeProfilePicture(view);
    }

    private void enlargeProfilePicture(View view) {
        // view is the ImageView holding the image inside one ListView item
        Glide.with(this)
                .load(url)
                .into(fullScreenImageView);
        Animation scale = new ScaleAnimation(0f, 1f, 0f, 1f);
        scale.setDuration(1000);
        scale.setInterpolator(new LinearInterpolator());

        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        view.getGlobalVisibleRect(startBounds);
        rootView.getGlobalVisibleRect(finalBounds, globalOffset);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        Animation translate
                = new TranslateAnimation(Animation.ABSOLUTE, startBounds.left + view.getWidth()/2,
                Animation.ABSOLUTE, finalBounds.left,
                Animation.ABSOLUTE, startBounds.top - view.getHeight()/2,
                Animation.ABSOLUTE, finalBounds.top);
        translate.setInterpolator(new LinearInterpolator());
        translate.setDuration(1000);
        // Animation set to join both scaling and moving
        AnimationSet animSet = new AnimationSet(true);
        animSet.setFillEnabled(true);
        animSet.addAnimation(scale);
        animSet.addAnimation(translate);
        // Launching animation set
        animSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                fullScreenImageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        fullScreenImageView.startAnimation(animSet);
    }

}
