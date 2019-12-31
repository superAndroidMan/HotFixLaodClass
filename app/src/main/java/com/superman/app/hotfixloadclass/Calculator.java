package com.superman.app.hotfixloadclass;

import android.content.Context;
import android.widget.Toast;

public class Calculator {
    public int calculate(Context context){
        int a = 666;
        int b = 1;
        Toast.makeText(context, "calculate >>> " + a / b, Toast.LENGTH_SHORT).show();
        return a / b;
    }
}
