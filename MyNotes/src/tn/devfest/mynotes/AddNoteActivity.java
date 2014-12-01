package tn.devfest.mynotes;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

import tn.devfest.dao.MediaDAO;
import tn.devfest.dao.NoteDAO;
import tn.devfest.entities.MediaEntity;
import tn.devfest.entities.NoteEntity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoteActivity extends ActionBarActivity {

  private static final int CAMERA_CODE = 0;
  private static final int RQS_RECORDING = 1;

  private EditText addTitle;
  private EditText addContent;
  private NoteDAO nDAO;
  private MediaDAO mDAO;

  private NoteEntity note;
  private MediaEntity media;
  
  private Bundle extras;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_addnote);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setBackgroundDrawable(
        new ColorDrawable(Color.parseColor("#3f51b5")));
    getSupportActionBar().setTitle("Add new note");
    
    nDAO = new NoteDAO(getApplicationContext());
    mDAO = new MediaDAO(getApplicationContext());
    note = new NoteEntity();
    media = new MediaEntity();

    addTitle = (EditText) findViewById(R.id.note_add_title);
    addContent = (EditText) findViewById(R.id.note_add_content);
    
    extras = getIntent().getExtras();

    if (extras != null && getIntent().getSerializableExtra("note") != null) {
      note = (NoteEntity) getIntent().getSerializableExtra("note");
      addTitle.setText(note.getTitle());
      addContent.setText(note.getContent());
    }    

  }
  
  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return super.onSupportNavigateUp();
  }

  public void imageClick(View v) {
    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    intent.putExtra(MediaStore.EXTRA_OUTPUT,
        Uri.fromFile(getFile(getApplicationContext())));
    startActivityForResult(intent, CAMERA_CODE);
  }

  public void soundClick(View v) {
    Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
    startActivityForResult(intent, RQS_RECORDING);
  }

  public void cancelClick(View v) {
    // Simulate onBackPressed :p
    onBackPressed();
  }

  private File getFile(Context context) {
    final File path = new File(Environment.getExternalStorageDirectory(),
        context.getPackageName());
    if (!path.exists()) {
      path.mkdir();
    }
    return new File(path, new Date().getTime() + ".jpg");
  }

  public void confirmClick(View v) {
    try {
      String title = addTitle.getText().toString();
      String content = addContent.getText().toString();
      if (title.length() < 2 || content.length() < 2) {
        Toast.makeText(getApplicationContext(), "Please put something",
            Toast.LENGTH_LONG).show();
        return;
      }
      long currentCategory = 1;
      note.setTitle(title);
      note.setContent(content);
      note.setCategoryId(currentCategory);
      note.setTimestamp(new Timestamp(new Date().getTime()));
      media.setNoteId(note.getId());

      if (extras != null && extras.getSerializable("note") != null) {
        nDAO.update(note);
      } else {
        nDAO.create(note);
      }
      mDAO.create(media);

      nDAO.close();
      mDAO.close();
      Toast.makeText(getApplicationContext(), "Note added successfully",
          Toast.LENGTH_LONG).show();
    } catch (Exception e) {
      Toast.makeText(getApplicationContext(),
          "Note was not added! There was an internal error", Toast.LENGTH_LONG)
          .show();
    }
    finish();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    try {
      switch (requestCode) {
      case CAMERA_CODE:
        File file = getFile(this);
        try {
          // Bitmap captureBmp = Media.getBitmap(getContentResolver(),
          // Uri.fromFile(file));
          media.setPath(file.getAbsolutePath());
        } catch (Exception e) {
          e.printStackTrace();
        }
        break;
      case RQS_RECORDING:
        media.setPath(data.getDataString());
        // Toast.makeText(getApplicationContext(), "Saved: " +
        // savedUri.getPath(),
        // Toast.LENGTH_LONG).show();
        break;
      default:
        break;
      }
    } catch (Exception e) {
      Toast.makeText(getApplicationContext(), "Error getting the media!",
          Toast.LENGTH_LONG).show();
    }
  }
}
