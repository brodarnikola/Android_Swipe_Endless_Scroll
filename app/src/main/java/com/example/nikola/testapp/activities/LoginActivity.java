package com.example.nikola.testapp.activities;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nikola.testapp.R;
import com.example.nikola.testapp.Settings.Constants;
import com.example.nikola.testapp.Settings.MyApplication;
import com.example.nikola.testapp.Settings.Settings;
import com.example.nikola.testapp.Settings.Util;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;


public class LoginActivity extends AppCompatActivity {


    private MyApplication myApp;
    private Context context;

    private ImageView logo;

    private RelativeLayout rlLogin;

    private TextView tvGoogleLogin, tvNormalUser, tvNormalUserLogout;
    private ImageView ivAvatar;

    private GoogleSignInClient mGoogleSignInClient;

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myApp = MyApplication.getInstance();
        context = this;

        // fingerprint authentication successfully
        /*Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("message");
        if(message.equals("")) {
        } */


        logo = (ImageView) findViewById(R.id.loading_logo);
        if (myApp.get(Constants.screenWidth) != null)
            logo.setImageBitmap(Util.scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.splash_logo), (float) ((int) myApp.get(Constants.screenWidth) * 0.6), false));

        float topMargin = 0.0f;  // top margin is -25% of screen

        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            Display display = getWindowManager().getDefaultDisplay();
            int width = display.getWidth();
            int height = display.getHeight();
            topMargin = (int) (height * 0.25);  // top margin is -25% of screen
        } else {
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;
            topMargin = (int) (height * 0.25);  // top margin is -25% of screen
        }

        logo.animate().translationX(0).translationY(-topMargin).setDuration(1000).start();

        rlLogin = (RelativeLayout) findViewById(R.id.rlLogin);
        AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
        animation1.setStartOffset(1000);
        animation1.setDuration(1500);
        animation1.start();
        rlLogin.startAnimation(animation1);
        rlLogin.setAlpha(1.0f);

        ivAvatar = (ImageView) findViewById(R.id.ivAvatar);
        if (!Settings.getGoogleProfilePicture(context).equals("0")) {

            Glide.with(context)
                    .load(Settings.getGoogleProfilePicture(context))
                    .placeholder(R.drawable.avatar_menu)
                    .into(ivAvatar);

        }

        if( !Settings.getGoogleUsername(context).equals("") ) {

            tvNormalUserLogout = (TextView) findViewById(R.id.tvNormalUserLogout);
            tvNormalUserLogout.setVisibility(View.VISIBLE);
            tvNormalUserLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signOut();
                    revokeAccess();
                }
            });
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        tvGoogleLogin = (TextView) findViewById(R.id.tvGoogleLogin);
        tvGoogleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        tvNormalUser = (TextView) findViewById(R.id.tvNormalUser);
        tvNormalUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Main2Activity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }

    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("google login error", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }


    private void updateUI(@Nullable GoogleSignInAccount acct) {

        if (acct != null) {

            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            Toast.makeText(context, "uspješno smo logirali usera", Toast.LENGTH_SHORT).show();

            Settings.setGoogleUsername(context, personName);
            if (personPhoto != null) {
                Settings.setGoogleProfilePicture(context, personPhoto.toString());

                Glide.with(context)
                        .load(personPhoto)
                        .placeholder(R.drawable.avatar_menu)
                        .into(ivAvatar);
            }

            Log.d("User data", "Person name: " + personName + " .. Person email: " + personEmail);
        } else {


            Toast.makeText(context, "nismo uspjeli", Toast.LENGTH_SHORT).show();
            Log.d("nismo uspjeli", "nismo uspjeli dobiti podatke");
        }
    }


}
