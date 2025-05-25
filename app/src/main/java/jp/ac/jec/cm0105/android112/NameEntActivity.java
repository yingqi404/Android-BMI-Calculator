package jp.ac.jec.cm0105.android112;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomappbar.BottomAppBar;

public class NameEntActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_name_ent);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //ok 按钮 监视器👇
        Button btnOK = findViewById(R.id.btnOk);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView edtLastName = findViewById(R.id.edtLastName);  //姓输入框 ID -> View
                String lastName = edtLastName.getText().toString();     //姓 View -> String

                TextView edtFirstName = findViewById(R.id.edtFirstName);//名输入框 ID -> View
                String firstName = edtFirstName.getText().toString();   //名 View -> String

                //判断输入是否为空
                if (!TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(firstName)) {
                    //向mainActivity界面发送信息
                    Intent returnIntent = getIntent();
                    returnIntent.putExtra("InputName", lastName+"　"+firstName);
                    setResult(RESULT_OK,returnIntent);
                    finish();
                } else {
                    //弹窗提示 输入姓名

                }
            }
        });

        Button btnCancel = findViewById(R.id.btnCancelN);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }//onCreate end
}