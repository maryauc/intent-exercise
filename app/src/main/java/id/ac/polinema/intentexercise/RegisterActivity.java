package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    private ImageView ivImageView;
   // private TextInputLayout tilLayout_fullname, tilLayout_email, tilLayout_password, tilLayout_confirm_password, tilLayout_homepage, tilLayout_about;
    private TextInputEditText tietText_fullname, tietText_Email, tietText_password, tietText_confirm_password, tietText_homepage, tietText_about;
    private Button btn_ok;
    private CircleImageView avatar, changeAvatar;
    private static final int GALLERY_REQUEST_CODE = 1;
    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private Bitmap bitmap;
    private Uri imgUri = null;
    private boolean change_img = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ivImageView = findViewById(R.id.imageView);
        tietText_fullname = findViewById(R.id.text_fullname);
        tietText_Email = findViewById(R.id.text_email);
        tietText_password = findViewById(R.id.text_password);
        tietText_confirm_password = findViewById(R.id.text_confirm_password);
        tietText_homepage = findViewById(R.id.text_homepage);
        tietText_about = findViewById(R.id.text_about);
        btn_ok = findViewById(R.id.button_ok);
        avatar = findViewById(R.id.image_profile);

        ivImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), GALLERY_REQUEST_CODE);
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(RegisterActivity.this, ProfileActivity.class);

                String fullname = tietText_fullname.getText().toString();
                String email = tietText_Email.getText().toString();
                String password = tietText_password.getText().toString();
                String confirm_password = tietText_confirm_password.getText().toString();
                String homepage = tietText_homepage.getText().toString();
                String about = tietText_about.getText().toString();

                if (!change_img){
                    Toast.makeText(RegisterActivity.this, "Image must be change", Toast.LENGTH_SHORT).show();
                } else if (fullname.isEmpty()){
                    tietText_fullname.setError("Fullname required");
                } else if (email.isEmpty()) {
                    tietText_Email.setError("Email required");
                } else if (password.isEmpty()) {
                    tietText_password.setError("Password required");
                } else if (confirm_password.isEmpty()) {
                    tietText_confirm_password.setError("Confirm Password required");
                } else if (homepage.isEmpty()) {
                    tietText_homepage.setError("Homepage required");
                } else if (about.isEmpty()) {
                    tietText_about.setError("About required");
                } else if (!password.equals(confirm_password)) {
                    Toast.makeText(RegisterActivity.this, "Confirm password is not correct", Toast.LENGTH_SHORT).show();
                } else {
                    String image = imgUri.toString();
                    pindah.putExtra("image", image);
                    pindah.putExtra("fullname", fullname);
                    pindah.putExtra("email", email);
                    pindah.putExtra("password", password);
                    pindah.putExtra("confirm_password", confirm_password);
                    pindah.putExtra("homepage", homepage);
                    pindah.putExtra("about", about);
                    startActivity(pindah);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Cancel input image", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (requestCode == GALLERY_REQUEST_CODE){
                if (data != null){
                    try {
                        change_img = true;
                        imgUri = data.getData();
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imgUri);
                        avatar.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, e.getMessage());
                    }
                }
            }
        }
    }
}
