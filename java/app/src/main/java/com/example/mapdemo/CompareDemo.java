package com.example.mapdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.android.gms.maps.SupportMapFragment;

public class CompareDemo extends AppCompatActivity {
    public static void main(String[] args) {

        ArrayList<String> a = CompareCus(100);

        System.out.println(a);

    }
    private static void swap(ArrayList<Double> similarity, int j, int i) {
        // TODO Auto-generated method stub

    }
    public static ArrayList<String>  CompareCus(int num) {
        HashMap<String, Double> hashMap = new HashMap<String, Double>();
        ArrayList<String> nameList = null;
        ArrayList<Double> similarity = null;
        ArrayList<String>	listj=null;
        ArrayList<String> list1 = toArrayByInputStreamReader1("F:\\Inn.txt");
        System.out.println("List1**********"+list1);
        for(int j=0;j<=num;j++) {

            listj = toArrayByInputStreamReader1("F:\\friend"+j+".txt");
            System.out.println("Listj**********"+listj);

            int sum=0;
            for(int i=0;i<(list1.size())/2;i++) {

                String a = list1.get(i);
                System.out.println("a*****************"+a);
                Double a1 =Double.parseDouble(a);
                String b = listj.get(i);
                Double b1 = Double.parseDouble(b);
                String c = list1.get(i+1);
                Double c1 = Double.parseDouble(c);
                String d = listj.get(i+1);
                Double d1 = Double.parseDouble(d);

                if((a1/b1)==1&&(c1/d1)==1) {
                    sum=sum+1;
                }

            }

            similarity.add(j, (double) (sum/1440));
            hashMap.put("friend0",similarity.get(0));
            hashMap.put("friend1",similarity.get(1));
            hashMap.put("friend2",similarity.get(2));
            int n = similarity.size();
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n - x - 1; y++) {
                    if (similarity.get(y) > similarity.get(y+1)) {
                        swap(similarity, y, y + 1);
                    }
                }
            }
        }


        if(hashMap.get("friend0")==similarity.get(0)) {
            nameList.add(0,"friend0");
        }else if(hashMap.get("friend0")==similarity.get(1)) {
            nameList.add(1,"friend0");
        }else {
            nameList.add(2,"friend0");
        }

        if(hashMap.get("friend1")==similarity.get(0)) {
            nameList.add(0,"friend1");
        }else if(hashMap.get("friend1")==similarity.get(1)) {
            nameList.add(1,"friend1");
        }else {
            nameList.add(2,"friend1");
        }

        if(hashMap.get("friend2")==similarity.get(0)) {
            nameList.add(0,"friend2");
        }else if(hashMap.get("friend2")==similarity.get(1)) {
            nameList.add(1,"friend2");
        }else {
            nameList.add(2,"friend2");
        }


        return nameList;
    }
    public static ArrayList<String> toArrayByInputStreamReader1(String name) {
        // 使用ArrayList来存储每行读取到的字符串
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            File file = new File(name);
            InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bf = new BufferedReader(inputReader);
            // 按行读取字符串
//			String str;
//			while ((str = bf.readLine()) != null) {
//
//				arrayList.add(str+"\n");
//			}
            String line;
            StringBuilder text = new StringBuilder();
            while ((line = bf.readLine()) != null) {
                text.append(line);
                // text.append("\n");
                arrayList.add(text.toString());
            }

            bf.close();
            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("arrayList************"+arrayList);
        return arrayList;
    }

    EditText show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_demo);
        show=(EditText)findViewById(R.id.custom1);


      if(savedInstanceState==null){
          show.setText("输出");
      }else{
          show.setText("输出1");
      }
    }


}
