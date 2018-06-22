package com.example.catchman.studlife.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.catchman.studlife.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;


public class FindRoomFragment extends BaseFragment {

    @BindView(R.id.etRoom)
    EditText etRoom;
    @BindView(R.id.tvInfoText)
    TextView tvInfoText;
    private int flour;
    private int block;
    private boolean isNotBlock;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_room, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    
    
    @OnTextChanged(R.id.etRoom)
    public void onTextChanged(){
        int room = Integer.parseInt(etRoom.getText().toString());
        String info = getInfoRoom(room);
        info += "Для того щоб дібратися до аудиторії необхідно:\n";
        switch (block){
            case 0:
                info += "Увійти в новий блок та піднятися на " + flour + " поверх.";
                break;
            case 1:
                info += "Увійти в 1 та піднятися на " + flour + " поверх.";
                break;
            case 2:
            case 3:
            case 4:
                info += "Увійти у перший блок, перейти до " + block + " блоку та піднятися на " + flour + " поверх\nАБО\n ";
                info += "Увійти в новий блок, піднятися на 3 поверх, перейти до " + block + " блоку та піднятися на " + flour + " поверх.";
                break;
        }
        tvInfoText.setText(info);
    }

    private String getInfoRoom(int room) {
        String result = new String();
        if(room < 1000){
            flour = room/100;
            result += room/100 + " поверх\n";
            room = room - ((int)room/100)*100;
            if(room/10 >= 5){
                result += "новий блок\n";
                block = 0;
            } else {
                block = room/10;
                result += "між блоком " + room/10 + " та "  + (room/10 + 1) + "\n";
            }
        } else {
            result += room/1000 + " поверх\n";
            flour = room/1000;
            room = room - ((int)room/1000)*1000;
            block = room/100;
            result += room/100 + " блок\n";
        }
        return result;
    }


}
