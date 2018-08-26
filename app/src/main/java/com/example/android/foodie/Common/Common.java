package com.example.android.foodie.Common;

import com.example.android.foodie.Model.User;

public class Common {

    public static User currentuser ;

    public static String ConvertStatus(String code) {
        switch (code) {
            case "0":
                return ("பொருள்கள் பதிவு செய்யப்பட்டது ");

            case "1":
                return ("பொருள்கள் ஏற்றப்பட்டது");

            default:
                return ("பொருள்கள் சேர்க்கப்பட்டது ");

        }
    }
}
