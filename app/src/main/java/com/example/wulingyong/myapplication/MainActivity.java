package com.example.wulingyong.myapplication;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private String text="不同风格的作文，体现出写作文的同学性格脾性各方面的差异性和个体性。有的人性情敦厚，平和稳定，有的同学则行事雷厉风行，俗称热血风火轮，情绪容易被煽动，写出来的文章让人热血沸腾，澎湃激昂。\n" +
            " \n" +
            "不管何人写作文，重要的是内容。一篇佳作，如果有更恰当的优美的语言修饰，观点会更明确中心思想更加突出，这篇作文就越接近成功。但是内容的充盈与空洞，差别在于写作者本人的阅读程度，视野，环境。重点是阅读程度。大量的阅读一些古典名著，中外名篇，对于中学生的价值观跟处事态度会发生一些改变，对于写作文，某些片段也会如鱼得水，信手拈来。在这里，青少年要利用一切机会，多阅读，多思考。切记不要只阅读自己感兴趣的的书籍，可以涉及各方各面的，以便可以培养成一个客观全面的人。";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        textView=findViewById(R.id.text);
        textView.setText(text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:  //加密
                  AssetManager assets = null;
                assets = getResources().getAssets();
                try {
                    InputStream is = assets.open("pub.key");
                    PublicKey publicKey=RSAUtils.loadPublicKey(is);
                    String message=RSAEncrypt.encrypt(text,publicKey);
                    textView.setText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button2://解密
                AssetManager assetss = null;
                assetss = getResources().getAssets();
                InputStream iss = null;
                try {
                    iss = assetss.open("pri.key");
                    PrivateKey privateKey=RSAUtils.loadPrivateKey(iss);
                    String message2=RSAEncrypt.decrypt(textView.getText().toString(),privateKey);
                    textView.setText(message2);

                } catch (Exception e) {
                    e.printStackTrace();
                }


                break;
        }
    }
}
