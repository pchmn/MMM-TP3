package zobi.paulo.kalawi.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import zobi.paulo.kalawi.R;
import zobi.paulo.kalawi.model.Zamel;

public class CreateZamel extends AppCompatActivity {

    private static final String TAG = CreateZamel.class.toString();

    private EditText mName;
    private EditText mBirthDate;
    private EditText mBirthPlace;
    private Button mValidateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_mother_fucker);

        // xml elements
        mName = (EditText) findViewById(R.id.name);
        mBirthDate = (EditText) findViewById(R.id.birth_date);
        mBirthPlace = (EditText) findViewById(R.id.birth_place);
        mValidateButton = (Button) findViewById(R.id.validate_button);

        // validate button onclick
        mValidateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptValidate();
            }
        });
    }

    private void attemptValidate() {
        String name = mName.getText().toString();
        String birthDate = mBirthDate.getText().toString();
        String birthPlace = mBirthPlace.getText().toString();

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(birthDate) || TextUtils.isEmpty(birthPlace))
            Toast.makeText(this, "Formulaire non valide", Toast.LENGTH_LONG).show();
        else
            saveClient(name, birthDate, birthPlace);

    }

    private void saveClient(String name, String birthDate, String birthPlace) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMax(100);
        progressDialog.setMessage("Chargement");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        // firebase ref
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("zamel");

        // create zamel object
        Zamel zamel = new Zamel(name, birthDate, birthPlace);

        // save zamel
        myRef.push().setValue(zamel.toMap(), new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                // hide dialog
                progressDialog.dismiss();

                if(databaseError != null) {
                    Toast.makeText(CreateZamel.this, "Erreur firebase", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(CreateZamel.this, ListOfZamels.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
