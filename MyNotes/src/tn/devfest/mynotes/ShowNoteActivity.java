package tn.devfest.mynotes;

import tn.devfest.entities.NoteEntity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ShowNoteActivity extends ActionBarActivity {

  TextView title;
  TextView contenu;
  NoteEntity note;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.shownote);
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setBackgroundDrawable(
        new ColorDrawable(Color.parseColor("#01579b")));
    
    title = (TextView) findViewById(R.id.titleview);
    contenu = (TextView) findViewById(R.id.contenuview);

    Bundle extras;
    extras = getIntent().getExtras();

    if (extras != null && getIntent().getSerializableExtra("MyClass") != null) {
      note = (NoteEntity)getIntent().getSerializableExtra("MyClass");
      title.setText(note.getTitle());
      contenu.setText(note.getContent());
    }else {
      Toast.makeText(getApplicationContext(), "No note to show!", Toast.LENGTH_LONG).show();
      finish();
    }
  }
  
  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return super.onSupportNavigateUp();
  }
  
  public void shareThis(View v) {
    Intent sendIntent = new Intent();
    sendIntent.setAction(Intent.ACTION_SEND);
    sendIntent.putExtra(Intent.EXTRA_TEXT, note.getTitle() + "\n\n" + note.getContent());
    sendIntent.setType("text/plain");
    startActivity(sendIntent);
  }

}
