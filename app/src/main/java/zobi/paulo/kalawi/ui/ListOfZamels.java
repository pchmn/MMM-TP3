package zobi.paulo.kalawi.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import zobi.paulo.kalawi.R;
import zobi.paulo.kalawi.model.Zamel;
import zobi.paulo.kalawi.ui.adapter.ZamelAdapter;

public class ListOfZamels extends AppCompatActivity {

    private static final String TAG = ListOfZamels.class.toString();

    // xml elements
    private RecyclerView mRecyclerZamels;
    private FloatingActionButton mFab;
    // firebase
    private ZamelAdapter mZamelAdapter;
    private List<Zamel> mZamelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_zamels);

        // xml elements
        mRecyclerZamels = (RecyclerView) findViewById(R.id.recycler_zamels);
        mFab = (FloatingActionButton) findViewById(R.id.fab);

        // fab onclick
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListOfZamels.this, CreateZamel.class);
                startActivity(intent);
            }
        });

        // zamel list
        mZamelList = new ArrayList<>();
        // adapter
        mZamelAdapter = new ZamelAdapter(mZamelList);
        mRecyclerZamels.setAdapter(mZamelAdapter);
        mRecyclerZamels.setLayoutManager(new LinearLayoutManager(this));
        // get zamels
        getZamelFromFirebase();
    }

    private void getZamelFromFirebase() {
        // firebase ref
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("zamel");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mZamelList = new ArrayList<>();

                for (DataSnapshot zamelSnapshot: dataSnapshot.getChildren()) {
                    Zamel zamel = zamelSnapshot.getValue(Zamel.class);
                    zamel.setId(zamelSnapshot.getKey());

                    mZamelList.add(zamel);

                    Log.e(TAG, zamel.getName());
                }

                // update adapter
                mZamelAdapter.updateZamelList(mZamelList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "loadZamel:onCancelled : " + databaseError.toString());
            }
        });
    }
}
