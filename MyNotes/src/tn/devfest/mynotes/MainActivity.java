package tn.devfest.mynotes;


import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;


public class MainActivity extends ActionBarActivity {

	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    ActionBarDrawerToggle toogle ;
    Toolbar toolbar ;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //reference to XML toolbar

        
        //drawer configuration 
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
       
        
        toogle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_drawer_close){
        	@Override
        	public void onDrawerOpened(View drawerView) {
        		
        		 FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
                 fab.hide();
                 
        		super.onDrawerOpened(drawerView);
        	}
        	
        	
        	@Override
        	public void onDrawerClosed(View drawerView) {
        		 FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
                 fab.show();
        		super.onDrawerClosed(drawerView);
        	}
        };
        
        
        
        mDrawerLayout.setDrawerListener(toogle);
        

        
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        
      
        findViewById(R.id.fab).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				 Toast.makeText(MainActivity.this, "Clicked pink Floating Action Button", Toast.LENGTH_SHORT).show();

				
			}
		});
 
    
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		toogle.syncState();
	}
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	
    	if (toogle.onOptionsItemSelected(item)) {
		      return true;
		    }
    	
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
