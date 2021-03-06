package discovery.contentful.activities;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import butterknife.Bind;
import butterknife.ButterKnife;
import discovery.contentful.R;
import discovery.contentful.ui.PagerSlidingTabStrip;

abstract class AbsTabsActivity extends CFFragmentActivity {
  @Bind(R.id.tabs) PagerSlidingTabStrip tabStrip;
  @Bind(R.id.view_pager) ViewPager viewPager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Allow extending classes to initialize at this point
    initOnCreate(savedInstanceState);

    setContentView(R.layout.activity_tabs);

    // Inject views
    ButterKnife.bind(this);

    // Title
    setTitle(getTitleForActivity());

    // Initialize ViewPager & PagerSlidingTabStrip view
    viewPager.setAdapter(getAdapter());
    tabStrip.setViewPager(viewPager);

    tabStrip.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
      @Override public void onPageSelected(int position) {
        onTabSelected(position);
      }
    });
  }

  /**
   * Perform any initialization prior to calling any other code
   * in {@link #onCreate(android.os.Bundle)}.
   *
   * @param savedInstanceState {@code savedInstanceState} argument of the {@code onCreate} method.
   */
  protected abstract void initOnCreate(Bundle savedInstanceState);

  /**
   * Gets the title to be set for this {@code Activity}.
   *
   * @return String representing the title.
   */
  protected abstract String getTitleForActivity();

  /**
   * Gets a {@code PagerAdapter} instance to be used with {@code ViewPager}
   * and {@code PagerSlidingTabStrip} view.
   * Extending classes should <b>not</b> re-create the {@code PagerAdapter} each time
   * this method is called, instead should keep it as a class member and only initialize
   * it once.
   *
   * @return {@code PagerAdapter} instance.
   */
  protected abstract PagerAdapter getAdapter();

  /**
   * Callback method which gets called every time the user changes tab, either by using
   * the {@code PagerSlidingTabStrip} view directly, or by using the {@code ViewPager}.
   *
   * @param position Integer representing the selected tab index.
   */
  @SuppressWarnings("EmptyMethod")
  protected void onTabSelected(int position) {
    // Do nothing
  }
}
