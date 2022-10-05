package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private CircleImageView image;
    private TextView lbl_about, lbl_fullname, lbl_email, lbl_homepage;
    private Button btn_visit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        lbl_about = findViewById(R.id.label_about);
        lbl_fullname = findViewById(R.id.label_fullname);
        lbl_email = findViewById(R.id.label_email);
        lbl_homepage = findViewById(R.id.label_homepage);
        btn_visit = findViewById(R.id.button_homepage);
        image = findViewById(R.id.image_profile);

        Bundle bundle = getIntent().getExtras();
        String labelAbout = bundle.getString("about");
        String labelFullname = bundle.getString("fullname");
        String labelEmail = bundle.getString("email");
        final String labelHomepage = bundle.getString("homepage");
        String uri = bundle.getString("image");

        image.setImageURI(Uri.parse(uri));
        lbl_about.setText(labelAbout);
        lbl_fullname.setText(labelFullname);
        lbl_email.setText(labelEmail);
        lbl_homepage.setText(labelHomepage);

        btn_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoURL(labelHomepage);
            }
        });

    }

    private void gotoURL(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}
