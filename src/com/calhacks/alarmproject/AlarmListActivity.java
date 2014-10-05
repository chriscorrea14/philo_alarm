package com.calhacks.alarmproject;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * An activity representing a list of Alarms. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link AlarmDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link AlarmListFragment} and the item details (if present) is a
 * {@link AlarmDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link AlarmListFragment.Callbacks} interface to listen for item selections.
 */
public class AlarmListActivity extends FragmentActivity implements
		AlarmListFragment.Callbacks {
	
	static ArrayList<Alarm> allAlarms = new ArrayList<Alarm>();
	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;
	
	public static void addAlarm(int hour,int min,boolean[] checked){
	   
	   
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_list);

		if (findViewById(R.id.alarm_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((AlarmListFragment) getSupportFragmentManager().findFragmentById(
					R.id.alarm_list)).setActivateOnItemClick(true);
		}
		boolean[] trues={true,true,true,true,true,true,true};
		allAlarms.add(new Alarm(7,15,trues));
		allAlarms.add(new Alarm(8,15,trues));
		allAlarms.add(new Alarm(9,15,trues));
		// TODO: If exposing deep links into your app, handle intents here.
	}

	/**
	 * Callback method from {@link AlarmListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(AlarmDetailFragment.ARG_ITEM_ID, id);
			AlarmDetailFragment fragment = new AlarmDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.alarm_detail_container, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, AlarmDetailActivity.class);
			detailIntent.putExtra(AlarmDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.layout.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {	
			case R.id.action_edit:
					AddAlarmFragment aaf = new AddAlarmFragment();
					aaf.show(getFragmentManager(),"aaf");
				break;
			default:
				break;
		}
		return true;
	}
}
class Alarm implements Serializable{
	int hour=0;
	int min=0;
	boolean[] checked;
	boolean isOn=true;

	public Alarm(int hour, int min, boolean[] checked){
		this.hour=hour;
		this.min=min;
		this.checked=checked;
	}
}
