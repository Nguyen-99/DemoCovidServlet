package com.nguyenz.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyRegex {
    private final static String vnCharacter = "ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵýỷỹ";

    public static boolean validateName(String name) {

        if (!name.trim().equals("")) {
            return name.matches("[a-z" + vnCharacter + "A-Z\\s]+");
        } else {
            return false;
        }
    }

    public static boolean validateDob(String dob) {
        String regex = "(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dob);
        return matcher.matches();
    }

    public static boolean validateAddress(String address) {
        String regex = "[\\w\\s,/" + vnCharacter + "]+";
        if(!address.trim().equals("")){
            return address.matches(regex);
        }else {
            return false;
        }
    }

    public static boolean search(String search, String name) {
        String regex = ".*" + search + ".*";
        return name.matches(regex);
    }
}
