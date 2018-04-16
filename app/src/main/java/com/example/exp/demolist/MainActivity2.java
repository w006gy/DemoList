package com.example.exp.demolist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private  ListView list;
    public boolean clickable=false;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Intent intent=new Intent(MainActivity2.this,MainActivity3.class);
        //startActivity(intent);

        /**
         * Only logged in users should access this activity
         */
        session = new SessionManager(getApplicationContext());
        if (!session.isLoggedIn()) {
                logout();

        }

        /**
         * If the user just registered an account from Register.class,
         * the parcelable should be retrieved
         */
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            // Retrieve the parcelable
            Feedback feedback = bundle.getParcelable("feedback");
            // Get the from the object

        }

        clickable=true;
        setContentView(R.layout.activity_main);
        //ListView listView=findViewById(R.id.lv_app);
        //绑定XML中的ListView，作为Item的容器
        list = (ListView) findViewById(R.id.lv_app);

        //生成动态数组，并且转载数据
        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();

        String[] strings={"Button Toast","Button Intent","GetColor"};
        //String[] strings={"Life Cycle","UserName","Layout","Button Toast","Button Intent","Button StartActivity","Image Button","EditText","RadioButtons","listView","GetColor","Gradient Background","Implicit Intent"
        //,"Weather App Design","","ListView","ListViewCustomAdapter","AudioRecorder","","DataBase","Fragment One","WebView","ServiceDemo","Service","Fingerprint","AppPrivateDirectory","Assets Folder","Intent Extras"};
        for(int i=0;i<strings.length;i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("ItemTitle", strings[i]);

            mylist.add(map);
        }
        Log.i("msg", strings.length+" items");
            /*
             map = new HashMap<String, String>();
            map.put("ItemTitle", "Button Intent");
            mylist.add(map);
             map = new HashMap<String, String>();
            map.put("ItemTitle", "GetColor");
            mylist.add(map);
            */
        //生成适配器，数组===》ListItem
        SimpleAdapter mSchedule = new SimpleAdapter(this, //没什么解释
                mylist,//数据来源
                R.layout.my_listitem,//ListItem的XML实现

                //动态数组与ListItem对应的子项
                new String[] {"ItemTitle"},

                //ListItem的XML文件里面的TextView ID
                new int[] {R.id.tv_title});
        //添加并且显示
        list.setAdapter(mSchedule);
        init();
    }

    public void init(){
    list.setOnItemClickListener(new MyOnItemClickListener());}


    private class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {

            //String p = parent.getItemAtPosition(position).toString();
            /*
            HashMap<String, String> map= (HashMap<String, String>)parent.getItemAtPosition(position);

            Toast.makeText(getApplicationContext(), map.get("ItemTitle"), Toast.LENGTH_SHORT).show();
            Log.i("click", position+"");
            */
            if(clickable==false) return;
            switch (position){
                //case 3:
                case 0:
                    Intent intent=new Intent(MainActivity2.this,ThirdActivity.class);
                    startActivity(intent);

                    break;
                //case 4:
                case 1:
                    Intent intent1=new Intent(MainActivity2.this,FourthActivity.class);
                    startActivity(intent1);
                    break;
                //case 10:
                case 2:
                    Intent intent2=new Intent(MainActivity2.this,TenthActivity.class);
                    startActivity(intent2);
                    break;
                default:
                    Log.i("clicked", "position: "+position);
                    break;


            }

        }

    }


    public void btnLogout(View view) {
        logout();
    }

    public void logout() {
        // Updating the session
        session.setLogin(false);
        // Redirect the user to the login activity
        startActivity(new Intent(this, Login.class));
        finish();
    }


}
