package com.example.imagechoosedemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.imagechoosedemo.adapters.ImageFloderAdapter;
import com.example.imagechoosedemo.utils.FileTraversal;
import com.example.imagechoosedemo.utils.ImageUtils;

public class ImageFloderActivity extends Activity implements OnItemClickListener{

	ListView listView;
	ImageUtils util;
	ImageFloderAdapter listAdapter;
	List<FileTraversal> locallist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_image_floder);
		listView=(ListView) findViewById(R.id.listView1);
		util=new ImageUtils(this);
		locallist=util.LocalImgFileList();
		List<HashMap<String, String>> listdata=new ArrayList<HashMap<String,String>>();
		Bitmap bitmap[] = null;
		if (locallist!=null) {
			bitmap=new Bitmap[locallist.size()];
			for (int i = 0; i < locallist.size(); i++) {
				HashMap<String, String> map=new HashMap<String, String>();
				map.put("filecount", locallist.get(i).filecontent.size()+"уе");
				map.put("imgpath", locallist.get(i).filecontent.get(0)==null?null:(locallist.get(i).filecontent.get(0)));
				map.put("filename", locallist.get(i).filename);
				listdata.add(map);
			}
		}
		listAdapter=new ImageFloderAdapter(this, listdata);
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(this);
		
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent=new Intent(this,ImageChooseActivity.class);
		Bundle bundle=new Bundle();
		bundle.putParcelable("data", locallist.get(arg2));
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
}

