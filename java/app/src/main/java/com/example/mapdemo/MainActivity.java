/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.mapdemo;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static com.example.mapdemo.R.id.button_compare;

/**
 * The main activity of the API library demo gallery.
 * <p>
 * The main layout lists the demonstrated features, with buttons to launch them.
 */
public final class MainActivity extends AppCompatActivity
    /*创建点击事件*/

        implements AdapterView.OnItemClickListener{

            Button button1;
            Button button2;
            Button button3;

    /**
     * A custom array adapter that shows a {@link FeatureView} containing details about the demo.
     */
    private static class CustomArrayAdapter extends ArrayAdapter<DemoDetails> {

        /**
         * @param demos An array containing the details of the demos to be displayed.
         */
        /*自定义了一个数组适配器，其中包含，context和展示页面的代码*/
        public CustomArrayAdapter(Context context, DemoDetails[] demos) {
            super(context, R.layout.feature, R.id.title, demos);
        }

        /*创建获得主页面视图*/
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            /*创建获得视图对象*/
            FeatureView featureView;
            /*使用convertView主要是为了缓存试图View，用以增加ListView的item view加载效率。
            有经验的Android开发者通常知道在Adapter的getView中，先判断convertView是否为空null，如果非空，
            则直接再次对convertView复用，否则才创建新的View
             */
            if (convertView instanceof FeatureView) {
                featureView = (FeatureView) convertView;
            } else {
                /*getContext()：这个是View类中提供的方法，在继承了View的类中才可以调用，返回的是当前View运行在哪个Activity Context中。*/
                featureView = new FeatureView(getContext());
            }
            /*getItem(position)：返回的是点击位置的信息，其值是int*/
            DemoDetails demo = getItem(position);
            /*getTitle()取得的值就是上述 android:label="@string/titlebar_text" 的值*/
            featureView.setTitleId(demo.titleId);
            featureView.setDescriptionId(demo.descriptionId);

            Resources resources = getContext().getResources();
            String title = resources.getString(demo.titleId);
            String description = resources.getString(demo.descriptionId);
            featureView.setContentDescription(title + ". " + description);

            return featureView;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ListView list = (ListView) findViewById(R.id.list);
        ListAdapter adapter = new CustomArrayAdapter(this, DemoDetailsList.DEMOS);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
        list.setEmptyView(findViewById(R.id.empty));


        button1=findViewById(R.id.button_mylocation);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(com.example.mapdemo.MainActivity.this,MyLocationDemoActivity.class);
                startActivity(a);
            }
        });

        button2=findViewById(R.id.button_myroute);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(com.example.mapdemo.MainActivity.this,PolylineDemoActivity.class);
                startActivity(b);
            }
        });

        button3=findViewById(button_compare);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(com.example.mapdemo.MainActivity.this,CompareDemo.class);
                c.putExtra("extra_data","Hello SecondActivity");
                startActivity(c);
            }
        });

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DemoDetails demo = (DemoDetails) parent.getAdapter().getItem(position);
        startActivity(new Intent(this, demo.activityClass));
    }
}
