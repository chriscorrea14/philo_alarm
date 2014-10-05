package com.calhacks.alarmproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;
import android.widget.ToggleButton;

public class AddAlarmFragment extends DialogFragment{
	static ToggleButton sunday, monday, tuesday, wednesday, thursday, friday, saturday;
	static boolean[] checked = new boolean[7];
	static TimePicker time;
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_add_alarm,null);
        
        time = (TimePicker)v.findViewById(R.id.time);
        time.setCurrentHour(8);
        time.setCurrentMinute(0);
        
        monday=(ToggleButton)v.findViewById(R.id.Monday);
        sunday=(ToggleButton)v.findViewById(R.id.Sunday);
        tuesday=(ToggleButton)v.findViewById(R.id.Tuesday);
        wednesday=(ToggleButton)v.findViewById(R.id.Wednesday);
        thursday=(ToggleButton)v.findViewById(R.id.Thursday);
        friday=(ToggleButton)v.findViewById(R.id.Friday);
        saturday=(ToggleButton)v.findViewById(R.id.Saturday);
        
        builder.setView(v);
        builder
               .setPositiveButton("Add", new DialogInterface.OnClickListener() {
            	   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
            		   checked[0]=sunday.isChecked();
            		   checked[1]=monday.isChecked();
            		   checked[2]=tuesday.isChecked();
            		   checked[3]=wednesday.isChecked();
            		   checked[4]=thursday.isChecked();
            		   checked[5]=friday.isChecked();
            		   checked[6]=saturday.isChecked();
            		   AlarmListActivity.allAlarms.add(new Alarm(time.getCurrentHour(),time.getCurrentMinute(),checked));
            		   AlarmListFragment.AA.notifyDataSetChanged();
                   }
               })
               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
	}
}