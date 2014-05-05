package de.teamgamma.cansat.app.main;

import java.io.File;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import de.teamgamma.cansat.app.R;
import de.teamgamma.cansat.app.data.constantValues;
import de.teamgamma.cansat.app.dataImport.ImportFragment;
import de.teamgamma.cansat.app.fragments.HomeFragment;
import de.teamgamma.cansat.app.fragments.OptionsFragment;
import de.teamgamma.cansat.app.fragments.OptionsSearcherFragment;
import de.teamgamma.cansat.app.fragments_androidplot.RealtimeGraph;
import de.teamgamma.cansat.app.options.ChartViewOptions;
import de.teamgamma.cansat.app.options.KindOfOption;
import de.teamgamma.cansat.app.options.MapsOptions;
import de.teamgamma.cansat.app.options.Options;
import de.teamgamma.cansat.app.options.OptionsExport;
import de.teamgamma.cansat.app.sensors.Sensor;

public class MainActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private Options options = Options.getInstance();

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mSlidemanueTitels;
	private Sensor sensor = new Sensor();
	private static Fragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		for (double d = 0; d < 10; d++) {
			sensor.setValues(0, d);
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTitle = mDrawerTitle = getTitle();
		mSlidemanueTitels = getResources().getStringArray(
				R.array.slidemenu_array);
		constantValues.generateMap(mSlidemanueTitels);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// set up the drawer's list view with items and click listener
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mSlidemanueTitels));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (new File(Environment.getExternalStorageDirectory().getPath()
				+ "/teamgamma/options.txt").exists()) {
			options.readAll();
			if (savedInstanceState == null) {
				selectItem(0, false);
			}

		} else {
			// setContentView(R.layout.activity_main);
			selectItem(0, true);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action buttons
		switch (item.getItemId()) {
		case R.id.action_websearch:
			// create intent to perform web search for this planet
			Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
			intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
			// catch event that there's no activity to handle intent
			if (intent.resolveActivity(getPackageManager()) != null) {
				startActivity(intent);
			} else {
				Toast.makeText(this, R.string.app_not_available,
						Toast.LENGTH_LONG).show();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position, false);
		}
	}

	private void selectItem(int position, boolean check) {
		// update the main content by replacing fragments
		switch (position) {
		case 0:
			if (check) {
				fragment = new OptionsSearcherFragment();
				break;
			} else {
				fragment = new HomeFragment();
				break;
			}
		case 1:
			options.setOption(KindOfOption.CHARTVIEW.ordinal(),ChartViewOptions.ACTIVESENSORNAME,constantValues.names[0]);
			fragment = new RealtimeGraph();
			break;
		case 2:
			options.setOption(KindOfOption.CHARTVIEW.ordinal(),ChartViewOptions.ACTIVESENSORNAME,constantValues.names[1]);
			fragment = new RealtimeGraph();		
			break;
		case 3:
			options.setOption(KindOfOption.CHARTVIEW.ordinal(),ChartViewOptions.ACTIVESENSORNAME,constantValues.names[2]);
			fragment = new RealtimeGraph();
			break;
		case 4:
			fragment = new ImportFragment();
			break;
		case 5:
			String longitude = options.getOption(KindOfOption.MAPS.ordinal(), MapsOptions.LONGITUDE);
			String latitude = options.getOption(KindOfOption.MAPS.ordinal(), MapsOptions.LATITUDE);
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?q=" + longitude + "," + latitude)));
			break;
		case 6:
			fragment = new OptionsFragment();
			break;

		}
		Bundle args = new Bundle();
		args.putInt(OptionsFragment.ARG_SLIDEMENU_VALUES, position);
		fragment.setArguments(args);

		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		// update selected item and title, then close the drawer
		mDrawerList.setItemChecked(position, true);
		setTitle(mSlidemanueTitels[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	public static Fragment getCurrentFragment(){
		return fragment;
	}

}
