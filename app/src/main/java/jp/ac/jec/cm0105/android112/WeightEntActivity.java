package jp.ac.jec.cm0105.android112;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WeightEntActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weight_ent);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // OK 按钮监视器
        findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 画面に入力された体重の値を取得する
                TextView edtTextWeight = findViewById(R.id.editTextWeight);//分界面体重输入框 ID -> View
                String weightString = edtTextWeight.getText().toString(); //体重 View -> String

                if (!TextUtils.isEmpty(weightString)) {
                    // Intentに取得した体重のデータをセットする
                    Intent intentWeight = new Intent();         //创建 Intent instance
                    intentWeight.putExtra("inputWeight", weightString);   //用实例对象 接收 指定 key 对应输入框的体重值

                    // MainActivityに上のIntent情報を返す
                    setResult(RESULT_OK, intentWeight);   //确定是否返回有效信息给主界面
                    finish(); // 画面を閉じる (HeightEntActivity) 返回上一层
                }else{
                    //如果没有输入体重弹窗提示 请输入
                }


            }
        });

        Button btnCancelW=findViewById(R.id.btnCancelW);
        btnCancelW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }//onCreate end
}