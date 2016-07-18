package com.gkibuh.kisimupdate;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class About extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		TextView about = (TextView)findViewById(R.id.abt);
		about.setText("-----------------------------------------------\n KiSimUpdate  Copyright (C) 2014 kibuh Guy Pascal, Jiomekong Azanzi Fidèl\nThis program comes with ABSOLUTELY NO WARRANTY; for details see the fsf.org. This is free software, and you are welcome to redistribute it under certain conditions; see fsf.org for details. The author is not responsible for the bad use of it.\nPut this one in the begining of all source code\n-----------------------------------------------\nThis program will update your sim card by adding some number\nbehind, at the end or between your contacts phone number.\nCopyright (C) 2014  kibuh Guy Pascal, Jiomekong Azanzi Fidèl\nThis program is free software: you can redistribute it and/or modify\nit under the terms of the GNU General Public License as published by\nthe Free Software Foundation, either version 3 of the License, or\nany later version.\n\nThis program is distributed in the hope that it will be useful,\nbut WITHOUT ANY WARRANTY; without even the implied warranty of\nMERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See th\nGNU General Public License for more details\n\nYou should have received a copy of the GNU General Public License\nalong with this program.  If not, see <http://www.gnu.org/licenses/>.");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about, menu);
		return true;
	}

}
