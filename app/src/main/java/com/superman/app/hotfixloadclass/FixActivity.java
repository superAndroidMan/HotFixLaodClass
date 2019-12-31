package com.superman.app.hotfixloadclass;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.superman.app.hotfixloadclass.utils.FileUtils;
import com.superman.app.hotfixloadclass.utils.FixDexUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author superman
 * @date 2019-12-31
 */
public class FixActivity extends AppCompatActivity {


    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fix_activity_layout);
        textView = (TextView) findViewById(R.id.tv_title);
    }

    public void calculate(View view) {
        Calculator calculator = new Calculator();
        int calculate = calculator.calculate(this);
        textView.setText(String.valueOf(calculate));
    }

    public void fix(View view) {

        fixBug();

    }

    private void fixBug() {
        //1 从服务器下载dex文件 比如v1.1修复包文件（classes2.dex）
        File sourceFile = new File(Environment.getExternalStorageDirectory(), "classes2.dex");
        // 目标路径：私有目录
        //getDir("odex", Context.MODE_PRIVATE) data/user/0/包名/app_odex
        File targetFile = new File(getDir("odex",
                Context.MODE_PRIVATE).getAbsolutePath() + File.separator + "classes2.dex");
        if (targetFile.exists()) {
            targetFile.delete();
        }
        try {
            // 复制dex到私有目录
            FileUtils.copyFile(sourceFile, targetFile);
            Toast.makeText(this, "复制到私有目录完成", Toast.LENGTH_SHORT).show();
            FixDexUtils.loadFixedDex(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
