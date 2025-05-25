package jp.ac.jec.cm0105.android112;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    double BMI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //按下 name按钮 监视器👇
        Button nameEntBtn = findViewById(R.id.btnInputName);//name 按钮 ID -> View
        nameEntBtn.setOnClickListener(new View.OnClickListener() {//name 按钮 监视器
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NameEntActivity.class);//创建Intent媒介的instance(参1:当前页面，参2:跳转后界面)
                startActivityForResult(intent, 12);//先生：REQUEST_???_NAME  启动想跳转的界面(参1:Intent对象,参2请求码:***)
            }
        });

        // 身長 button 监视器👇
        findViewById(R.id.btnInputHigh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HeightEntActivity.class);
                startActivityForResult(intent, 34);
            }
        });

        // 体重 button 监视器👇
        findViewById(R.id.btnInputWeight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WeightEntActivity.class);
                startActivityForResult(intent, 56);
            }
        });

        //轻量数据储存
        TextView txtResult = findViewById(R.id.txtResult);    //主页显示结果区域 ID-> View
        SharedPreferences sharedPreferences = getSharedPreferences("android112", Context.MODE_PRIVATE);
        String message=sharedPreferences.getString("MODE","notfound");
        if(!message.isEmpty()){
            txtResult.setText(message);
        }

        //轻量数据储存
        TextView txtNow = findViewById(R.id.txtNow);    //主页显示结果区域 ID-> View
        SharedPreferences sharedPreferencesNow = getSharedPreferences("android112", Context.MODE_PRIVATE);
        String nowMessage=sharedPreferencesNow.getString("MODE1","notfoundTime");
        if(!nowMessage.isEmpty()){
            txtNow.setText(nowMessage);
        }


        //判断 按钮 监视器
        findViewById(R.id.btnCalculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double highInt;
                double weightInt;

                TextView txtResult = findViewById(R.id.txtResult);    //主页显示结果区域 ID-> View
                TextView txtNow = findViewById(R.id.txtNow);    //主页显示结果区域 ID-> View


                TextView nameView = findViewById(R.id.txtName);//name ID-> View
                String nameString = nameView.getText().toString();   //姓名 view --> String

                TextView highView = findViewById(R.id.txtHigh);//身高 ID-> View
                String highString = highView.getText().toString();   //身高 view --> String

                TextView weightView = findViewById(R.id.txtWeight);//体重 ID-> View
                String weightString = weightView.getText().toString();   //体重 view --> String

                //判断主页三项用户信息是否为空 来决定 是否发生计算👇
                if (!nameString.isEmpty() && !highString.isEmpty() && !weightString.isEmpty()) {

                    highInt = Double.parseDouble(highString);     //身高 String --> double型，获取按钮数字
                    weightInt = Double.parseDouble(weightString);     //体重 String --> double型，获取按钮数字

                    //BMI= 体重kg/(身高m)^2
                    BMI = weightInt / (highInt * highInt);  //double型数据进行计算 -> double型
                    BMI*=10000;   //double型等比扩大截取想要的精确的整数部分
//                    int BMIInt=(int)BMI;  //强制类型转换，在保证整数精度的前提下 double -> int,因为double型不可以转为String型
//                    String BMISting =Integer.toString(BMIInt);

                    ////日本肥満学会の判定基準(成人)
                    //                     指標 判定
                    //                     18.5 未満 低体重(痩せ型)
                    //                     18.5~25 未満 普通体重
                    //                     25~30 未満 肥満(1 度)
                    //                     30~35 未満 肥満(2 度)
                    //                     35~40 未満 肥満(3 度)
                    //                     40 以上 肥満(4 度)
                    if (BMI < 18.5) {
                        txtResult.setText("低体重(痩せ型)"); //String --> view型(textView)
                    } else if (BMI >= 18.5 && BMI < 25) {
                        txtResult.setText("普通体重"); //String --> view型(textView)
                    } else if (BMI >= 25 && BMI < 30) {
                        txtResult.setText("肥満(1 度)"); //String --> view型(textView)
                    } else if (BMI >= 30 && BMI < 35) {
                        txtResult.setText("肥満(2 度)"); //String --> view型(textView)
                    } else if (BMI >= 35 && BMI < 40) {
                        txtResult.setText("肥満(3 度)"); //String --> view型(textView)
                    } else {
                        txtResult.setText("肥満(4 度)"); //String --> view型(textView)
                    }
                }
                else{
                }



                //计算结果 的 轻量数据保存
                SharedPreferences sp = getSharedPreferences("android112", Context.MODE_PRIVATE);//获取SharedPreferences对象
                SharedPreferences.Editor edtr=sp.edit();//获取Editor对象的引用

                edtr.putString("MODE",txtResult.getText().toString());//把获取的值放入文件
                edtr.commit();     //edtr.commit()；提交数据。


                //储存时间👇
                Date currentDate=new Date();   //创建 时间 实例对象

                SimpleDateFormat esayDate=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");  //创建 规定时间的格式 的 实例对型
                String StringTime=esayDate.format(currentDate);//把 当前时间 的 实例对象 按规定格式 -> String

                txtNow.setText(StringTime);  //String规定格式的当前时间 --> textView蓝色区域

                //时间 的 轻量数据储存
                SharedPreferences dateInstance = getSharedPreferences("android112", Context.MODE_PRIVATE);
                SharedPreferences.Editor date=dateInstance.edit();

                date.putString("MODE1", txtNow.getText().toString());  //提取String型实例对象中的信息
                date.commit();     //提交提取出的信息

            }

        });


    }//onCreate end


    // 受け取る作業... onActivityResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("MainActivityTest", "RequestCode: " + requestCode);
        Log.d("MainActivityTest", "ResultCode: " + resultCode);

        // nameの場合
        if (requestCode == 12 && resultCode == RESULT_OK) {
            String messageName = data.getStringExtra("InputName");
            TextView txtName = findViewById(R.id.txtName);
            txtName.setText(messageName); // 画面に "160.0"を表示する
            Log.d("android112", messageName);
        }

        // 身長の場合
        if (requestCode == 34 && resultCode == RESULT_OK) {
            String messageHeight = data.getStringExtra("inputHeight"); // ex) "160.0"
            TextView txtHigh = findViewById(R.id.txtHigh);
            txtHigh.setText(messageHeight); // 画面に "160.0"を表示する
            Log.d("android112", messageHeight);
        }

        //  体重の場合
        if (requestCode == 56 && resultCode == RESULT_OK) {
            String messageWeight = data.getStringExtra("inputWeight");
            TextView txtWeight = findViewById(R.id.txtWeight);
            txtWeight.setText(messageWeight);
            Log.d("android112", messageWeight);
        }

    }
}
