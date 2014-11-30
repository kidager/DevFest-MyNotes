package tn.devfest.mynotes;

import tn.devfest.catogories.ScreenSlidePagerAdapter;
import tn.devfest.dao.CategoryDAO;
import tn.devfest.entities.CategoryEntity;
import tn.devfest.menu.MenuAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

public class MainActivity extends ActionBarActivity implements
    OnItemClickListener {
  private DrawerLayout mDrawerLayout;
  private ListView mDrawerList;
  private ActionBarDrawerToggle toogle;
  private ViewPager mPager;
  private ScreenSlidePagerAdapter mPagerAdapter;
  private ShareActionProvider mShareActionProvider;
  private CategoryDAO cDAO;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setBackgroundDrawable(
        new ColorDrawable(Color.parseColor("#3f51b5")));

    // pager adapter
    cDAO = new CategoryDAO(getApplicationContext());
    mPager = (ViewPager) findViewById(R.id.pager);
    mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
    mPagerAdapter.setElements(cDAO.getAllAsString());
    mPager.setAdapter(mPagerAdapter);
    cDAO.close();

    // add the left menu
    // drawer configuration
    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    mDrawerList = (ListView) findViewById(R.id.left_drawer);
    mDrawerList.setOnItemClickListener(this);
    MenuAdapter adapter = new MenuAdapter(this);
    mDrawerList.setAdapter(adapter);
    toogle = new ActionBarDrawerToggle(this, mDrawerLayout,
        R.string.drawer_open, R.string.drawer_drawer_close) {
      @Override
      public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.hide();
      }

      @Override
      public void onDrawerClosed(View drawerView) {
        super.onDrawerClosed(drawerView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.show();
      }
    };
    mDrawerLayout.setDrawerListener(toogle);
  }

  public void actionButtonClick(View v) {
    Intent i = new Intent(MainActivity.this, AddNoteActivity.class);
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    startActivity(i);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    MenuItem searchItem = menu.findItem(R.id.action_search);
    SearchView searchView = (SearchView) MenuItemCompat
        .getActionView(searchItem);
    MenuItem shareItem = menu.findItem(R.id.action_share);
    mShareActionProvider = (ShareActionProvider) MenuItemCompat
        .getActionProvider(shareItem);
    mShareActionProvider.setShareIntent(getDefaultIntent());
    return true;
  }

  private Intent getDefaultIntent() {
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.setType("image/*");
    return intent;
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    toogle.syncState();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (toogle.onOptionsItemSelected(item)) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position,
      long id) {
    switch (position) {
    case 1:
      actionButtonClick(findViewById(R.id.fab));
      break;
    case 2:
    case 3:
      Toast.makeText(
          getApplicationContext(),
          Math.random() < 0.5 ? "Not yet available, we didn't had enough time"
              : "Well, we should have had more time :p", Toast.LENGTH_LONG)
          .show();
      break;

    case 5:
      // Add cat
      AlertDialog.Builder alert = new AlertDialog.Builder(this);

      alert.setTitle("Add category");
      alert.setMessage("How do you want to name it ?");
      final EditText input = new EditText(this);
      alert.setView(input);
      alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
          String name = input.getText().toString();
          CategoryDAO cDAO = new CategoryDAO(getApplicationContext());
          CategoryEntity cat = new CategoryEntity(name, "");
          cDAO.create(cat);
          Toast.makeText(getApplicationContext(),
              "Category " + name + " created.", Toast.LENGTH_LONG).show();
          mPagerAdapter.notifyDataSetChanged();
          cDAO.close();
        }
      });
      alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
          dialog.dismiss();
        }
      });
      alert.show();
      break;
    case 6:
      // rename cat
      break;
    case 7:
      // Del Cat

    default:
      break;
    }
  }
}
