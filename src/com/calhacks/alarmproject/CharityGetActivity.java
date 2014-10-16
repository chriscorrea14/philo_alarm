package com.example.test_style;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.*;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class CharityGetActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_charity_get);
		Parse.initialize(this, "eV4O9AT3QUXeA8X4B9WfR9xgBxofxffIJswWRIKh", "Hfho8IcAnHDCWWKMgGga7rF0xBQbZ3Bp8MB2ooa7");
		ParseQuery<ParseObject> charityquery = ParseQuery.getQuery("charitylistobject");
    	final HashMap charity_hash = new HashMap();
		charityquery.getInBackground("4w8etecKhx", new GetCallback<ParseObject>(){
			@Override
			public void done(ParseObject object, ParseException e) {
		    if (e == null) {
		    	System.out.println("HELLO");
		    	List<HashMap> list_of_jsons = object.getList("list");
		    	for (HashMap hm : list_of_jsons){
		    		String title = (String) hm.get("name");
		    		String info = (String) hm.get("info");
			    	charity_hash.put(title, info);
		    	}
				System.out.println(charity_hash);
				Bundle b =new Bundle();
				b.putSerializable("parceobj", charity_hash);
				charityListFragment myFrag = new charityListFragment();
				myFrag.setArguments(b);
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.id.container, myFrag);
				transaction.addToBackStack(null);
				transaction.commit();
		    } else {
		    }
		  }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.charity_get, menu);
		return true;
	}
}
