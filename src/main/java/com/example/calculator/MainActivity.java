package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
  private MyViewModel myViewModel;//数据模型
    private TextView myTextView;
    private TextView textView;
    private Button mBtn0;private Button mBtn1;private Button mBtn2;
    private Button mBtn3;private Button mBtn4;private Button mBtn5;
    private Button mBtn6;private Button mBtn7;private Button mBtn8;private Button mBtn9;
    private Button mBtnPo; private Button mBtnAdd; private Button mBtnDe;private Button mBtnMu;
    private Button mBtnDi;private Button mBtnCl; private Button mBtnCal; private Button mBtnBa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTextView = findViewById(R.id.myTextView); textView = findViewById(R.id.textView2);
        mBtn0 = findViewById(R.id.button0); mBtn1 = findViewById(R.id.button1); mBtn2 = findViewById(R.id.button2);
        mBtn3 = findViewById(R.id.button3); mBtn4 = findViewById(R.id.button4); mBtn5 = findViewById(R.id.button5);
        mBtn6 = findViewById(R.id.button6); mBtn7 = findViewById(R.id.button7); mBtn8 = findViewById(R.id.button8);
        mBtn9 = findViewById(R.id.button9); mBtnPo = findViewById(R.id.buttonPo); mBtnAdd = findViewById(R.id.buttonAdd);
        mBtnDe = findViewById(R.id.buttonDe); mBtnMu = findViewById(R.id.buttonMu);mBtnDi = findViewById(R.id.buttonDi);
        mBtnCl = findViewById(R.id.buttonCl);mBtnCal = findViewById(R.id.buttonCal);mBtnBa = findViewById(R.id.buttonBa);
        myViewModel = new ViewModelProvider( this, (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);
        myViewModel.getMainNum().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {//监听mainNum数据发生改变
                myTextView.setText(myViewModel.getMainNum().getValue());//让mTextView显示mainNum的值
                if (myViewModel.sign2.equals("")) {
                    if (myViewModel.sign1.equals("")) {
                        textView.setText(myViewModel.getMainNum().getValue());
                    } else {//如果输入第二个数时 如输入a+b中的b
                        textView.setText(myViewModel.num[0] + myViewModel.sign1 + myViewModel.getMainNum().getValue());
                    }
                } else {//如果式子为a+b*c的形式
                    textView.setText(myViewModel.num[0] + myViewModel.sign1 + myViewModel.num[1] + myViewModel.sign2 + myViewModel.getMainNum().getValue())
                    ;
                 }
            }
        });

        mBtn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("0");
            }
        });
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("1");
            }
        });
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("2");
            }
        });
        mBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("3");
            }
        });
        mBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("4");
            }
        });
        mBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("5");
            }
        });
        mBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("6");
            }
        });
        mBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("7");
            }
        });
        mBtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("8");
            }
        });
        mBtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.setMainNum("9");
            }
        });
        mBtnPo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!myViewModel.havePoint){
                    myViewModel.getMainNum().setValue(myViewModel.getMainNum().getValue() + ".");
                    myViewModel.havePoint = true;
                }
            }
        });
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1 = "+";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }else if(myViewModel.sign2.equals("")){
                    myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                    myViewModel.sign1 = "+";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }else{
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWith_Num_1_Tocal());
                    myViewModel.sign2 = "";
                    myViewModel.num[1] = "";
                    myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                    myViewModel.sign1 = "+";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
            }
        });
        mBtnDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1 = "-";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }else if(myViewModel.sign2.equals("")){
                    myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                    myViewModel.sign1 = "-";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }else{
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWith_Num_1_Tocal());
                    myViewModel.sign2 = "";
                    myViewModel.num[1] = "";
                    myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                    myViewModel.sign1 = "-";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
            }
        });
        mBtnMu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1 = "*";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }else if(myViewModel.sign2.equals("")){
                    if(myViewModel.sign1.equals("*") || myViewModel.sign1.equals("/")){
                        myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                        myViewModel.sign1 = "*";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint = false;
                    }else{
                        myViewModel.num[1] = myViewModel.getMainNum().getValue();
                        myViewModel.sign2 = "*";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint = false;
                    }
                }else{//如果式子为a+b*c的形式
                    myViewModel.num[1] = myViewModel.mainNumWith_Num_1_Tocal();
                    myViewModel.sign2 = "*";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
            }
        });
        mBtnDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign1.equals("")){
                    myViewModel.sign1 = "/";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }else if(myViewModel.sign2.equals("")){
                    if(myViewModel.sign1.equals("*") || myViewModel.sign1.equals("/")){
                        myViewModel.num[0] = myViewModel.mainNumWithNum_0_Tocal();
                        myViewModel.sign1 = "/";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint = false;
                    }else{
                        myViewModel.num[1] = myViewModel.getMainNum().getValue();
                        myViewModel.sign2 = "/";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint = false;
                    }
                }else{//如果式子为a+b*c的形式
                    myViewModel.num[1] = myViewModel.mainNumWith_Num_1_Tocal();
                    myViewModel.sign2 = "/";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
            }
        });
        mBtnCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.sign2 = "";
                myViewModel.num[1] = "";
                myViewModel.sign1 = "";
                myViewModel.num[0] = "";
                myViewModel.getMainNum().setValue("0");
                myViewModel.havePoint = false;
            }
        });
        mBtnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myViewModel.sign2.equals("")){
                    if(!myViewModel.sign1.equals("")){
                        myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_0_Tocal());
                        if(myViewModel.getMainNum().getValue().contains(".")){
                            myViewModel.havePoint = true;
                        }else {
                            myViewModel.havePoint = false;
                        }
                            myViewModel.num[0] = "";
                            myViewModel.sign1 = "";
                    }
                }else{
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWith_Num_1_Tocal());
                    myViewModel.num[1] = "";
                    myViewModel.sign2 = "";
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_0_Tocal());
                    if(myViewModel.getMainNum().getValue().contains(".")){
                        myViewModel.havePoint = true;
                    }else{
                        myViewModel.havePoint = false;
                    }
                        myViewModel.num[0] = "";
                        myViewModel.sign1 = "";
                }
                textView.setText(myViewModel.getMainNum().getValue());
            }

        });
        mBtnBa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!myViewModel.getMainNum().getValue().equals("0")){
                   myViewModel.getMainNum().setValue(myViewModel.getMainNum().getValue().substring(0, myViewModel.getMainNum().getValue().length() - 1));
                    if(myViewModel.getMainNum().getValue().equals("")){
                        myViewModel.getMainNum().setValue("0");
                    }
                }
            }
        });
    }
}
