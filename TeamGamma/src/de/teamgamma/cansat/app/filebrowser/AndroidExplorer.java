package de.teamgamma.cansat.app.filebrowser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.teamgamma.cansat.app.R;
import de.teamgamma.cansat.app.options.Options;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AndroidExplorer extends ListActivity {
	private Options options = Options.getInstance();
	private List<String> item = null;
	private List<String> path = null;
	private String root = "/";
	private TextView myPath;
	private String dirPath;
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browser);
		myPath = (TextView) findViewById(R.id.path);
		getDir(root);
	}

	private void getDir(String dirPath)
	{
		this.dirPath = dirPath;
		myPath.setText("Location: " + dirPath);
		item = new ArrayList<String>();
		path = new ArrayList<String>();
		File f = new File(dirPath);
		File[] files = f.listFiles();
		if (!dirPath.equals(root))
		{
			item.add(root);
			path.add(root);
			item.add("../");
			path.add(f.getParent());
		}
		for (int i = 0; i < files.length; i++)
		{
			File file = files[i];
			path.add(file.getPath());
			if (file.isDirectory())
				item.add(file.getName() + "/");
			else
				item.add(file.getName());
		}
		ArrayAdapter<String> fileList =
		new ArrayAdapter<String>(this, R.layout.row, item);
		setListAdapter(fileList);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		File file = new File(path.get(position));
		final String fileName = file.getName();
		if (file.isDirectory())
		{
			if (file.canRead())
				getDir(path.get(position));
			else
			{
				new AlertDialog.Builder(this)
				.setTitle("[" + fileName + "] folder can't be read!")
				.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				}).show();
			}
		}

		else
		{
			new AlertDialog.Builder(this)
			.setTitle("[" + file.getName() + "]")
			.setPositiveButton("OK",
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					String myPath = dirPath + "/" + fileName;
					boolean [] browsButton = options.getBrowsButtons();
					for(int i = 0; i<browsButton.length;i++){
						if(browsButton[i]){
							switch(i){
							case 0:	options.setTempOptionsPath(myPath);break;
							case 1: options.setTempValueExportPath(myPath);break;
							case 2: options.setTempValueStoragePath(myPath);break;
							}
						}
					}
					
					options.setTempOptionsPath(myPath);
					Intent intent = new Intent();
					setResult(RESULT_OK, intent);
					finish();
				}
			}).show();

		}
	}
}