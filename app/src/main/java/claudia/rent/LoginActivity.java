package claudia.rent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by ei047234 on 4/21/17.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText emailTextEdit;
    private EditText passwordTextEdit;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailTextEdit = (EditText) findViewById(R.id.editTextUserName);
        passwordTextEdit = (EditText) findViewById(R.id.editTextPassword);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                } else {

                }
            }
        };
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = emailTextEdit.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailTextEdit.setError("Required.");
            valid = false;
        } else {
            emailTextEdit.setError(null);
        }

        String password = passwordTextEdit.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordTextEdit.setError("Required.");
            valid = false;
        } else if(password.length() < 6){
            passwordTextEdit.setError("Too small, at least 6 chars.");
            valid = false;
        } else{
            passwordTextEdit.setError(null);
        }

        return valid;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void navigateToHome(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void loginButtonAction(View view) {

        if(validateForm()) {

            mAuth.signInWithEmailAndPassword(emailTextEdit.getText().toString(), passwordTextEdit.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                //TODO: show error message that the loggin failed
                            } else {
                                //TODO: navigate to the next page
                                navigateToHome();
                            }
                        }
                    });
        }
    }

    public void signupButtonAction(View view) {

        if(validateForm()) {
            mAuth.createUserWithEmailAndPassword(emailTextEdit.getText().toString(), passwordTextEdit.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                //TODO: show error message that the loggin failed
                                System.out.println("show messahe to verify the loggin failed");
                            } else {
                                //TODO: show messahe to verify the email address
                                System.out.println("show messahe to verify the email address");
                            }

                            // ...
                        }
                    });
        }
    }


    private void getCurrentUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }
    }
}
