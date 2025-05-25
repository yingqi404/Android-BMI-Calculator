package jp.ac.jec.cm0105.android112;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HeightEntActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_height_ent);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // OK 按钮监视器
        findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 画面に入力された身長の値を取得する
                TextView edtText = findViewById(R.id.edtText);//身高输入框 ID -> View
                String value = edtText.getText().toString(); //身高 View -> String(160.0)

                // Intentに取得した身長のデータをセットする
                Intent intent = new Intent();         //创建 Intent instance
                intent.putExtra("inputHeight", value);   //用实例对象 接收 指定 key 对应输入框的身高值

                // MainActivityに上のIntent情報を返す
                setResult(RESULT_OK, intent);   //确定是否返回有效信息给主界面
                finish(); // 画面を閉じる (HeightEntActivity) 返回上一层
            }
        });

        findViewById(R.id.btnCancelH).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        }//onCreat end
}