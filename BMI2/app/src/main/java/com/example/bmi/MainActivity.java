package com.example.bmi;

import static com.example.bmi.R.id.tvAdvice;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etHeight = findViewById(R.id.etHeight);
        EditText etWeight = findViewById(R.id.etWeight);
        Button btnCalculate = findViewById(R.id.btnCalculate);
        TextView tvResult = findViewById(R.id.tvResult);
        TextView tvAdvice;
        tvAdvice = findViewById(R.id.tvAdvice);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heightStr = etHeight.getText().toString();
                String weightStr = etWeight.getText().toString();

                if (heightStr.isEmpty() || weightStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "请输入身高和体重", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double height = Double.parseDouble(heightStr);
                    double weight = Double.parseDouble(weightStr);

                    if (height <= 0 || weight <= 0) {
                        Toast.makeText(MainActivity.this, "身高体重必须大于0", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // 计算BMI
                    double bmi = weight / (height * height);

                    // 显示结果
                    String result = String.format("您的BMI值: %.2f\n身体状态: ", bmi);
                    String advice;

                    if (bmi < 18.5) {
                        result += "体重过轻";
                        advice = "建议：增加营养摄入，适当增加体重";
                    } else if (bmi < 24) {
                        result += "正常范围";
                        advice = "建议：保持良好生活习惯";
                    } else if (bmi < 27) {
                        result += "过重";
                        advice = "建议：适当控制饮食，增加运动";
                    } else if (bmi < 30) {
                        result += "轻度肥胖";
                        advice = "建议：控制饮食，加强锻炼";
                    } else if (bmi < 35) {
                        result += "中度肥胖";
                        advice = "建议：制定减肥计划，咨询营养师";
                    } else {
                        result += "重度肥胖";
                        advice = "建议：立即就医，制定科学减肥方案";
                    }

                    tvResult.setText(result);
                    tvAdvice.setText(advice);

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "请输入有效的数字", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}