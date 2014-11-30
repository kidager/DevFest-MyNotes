package tn.devfest.mynotes;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class addNote extends ActionBarActivity{

	Button confirm;
	Button cancel;
	EditText ed1;
	EditText ed2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.addnote);
		 
		 confirm = (Button) findViewById(R.id.btn1);
		 cancel = (Button) findViewById(R.id.btn2);
		 ed1 = (EditText) findViewById(R.id.editText1);
		 ed2 = (EditText) findViewById(R.id.editText2);
		
		 
		 
		 cancel.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		 
		 
		 confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String title = ed1.getText().toString();
				String content = ed2.getText().toString();
				
				
				
			}
		});
		 
		 
		 
		 
	}

}
