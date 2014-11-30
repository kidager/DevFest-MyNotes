package tn.devfest.catogories;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
  private String categories[] = new String[] {};

  public ScreenSlidePagerAdapter(FragmentManager fm) {
    super(fm);
  }

  public void setElements(List<String> list) {
    this.categories = list.toArray(new String[] {});
  }

  @Override
  public Fragment getItem(int position) {
    return new ScreenSlidePageFragment();
  }

  @Override
  public int getCount() {
    return categories.length;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return categories[position];
  }
}
