package org.kauthara.chamthrah;

import java.util.ArrayList;


public class ChamLatin {
    public String word = "";
    public class S
    {
        public String a;
        public int b;
        public String c;
        public S(String _a, int _b)
        {
            a = _a;
            b = _b;
        }
        public S(String _a, int _b, String _c)
        {
            a = _a;
            b = _b;
            c = _c;
        }
    }
    public class Z
    {
        public String a;
        public String b;
        public String c;
        public Z(String _a, String _b)
        {
            a = _a;
            b = _b;
        }
        public Z(String _a, String _b, String _c)
        {
            a = _a;
            b = _b;
            c = _c;
        }
    }
    public S head_na(String a) {
        if (a.length() >= 2) {
            switch (a.substring(0, 2)) {
                case "ai":
                    return new S("\uAA04", 2);
            }
        }
        if (a.length() >= 1) {
            switch (a.charAt(0)) {
                case 'e':
                case 'é':
                    return new S("\uAA03", 1);
                case 'a':
                    return new S("\uAA00", 1);
                case 'i':
                    return new S("\uAA01", 1);
                case 'u':
                    return new S("\uAA02", 1);
                case 'o':
                    return new S("\uAA05", 1);
            }
        }
        return null;
    }

    // phu am thuong
    public S head_pat(String a) {
        if (a.length() >= 3) {
            switch (a.substring(0, 3)) {
                case "paa":
                    return new S("\uAA1B\uAA29\uAA00", 2);
            }
        }
        if (a.length() >= 2) {
            switch (a.substring(0, 2)) {
                case "kh":
                    return new S("\uAA07", 2);
                case "gh":
                    return new S("\uAA09", 2);
                case "ng":
                    return new S("\uAA0A", 2, "Ng");
                case "Ng":
                    return new S("\uAA0B", 2);
                case "ch":
                    return new S("\uAA0D", 2);
                case "jh":
                    return new S("\uAA0F", 2);
                case "ny":
                    return new S("\uAA10", 2, "Ny");
                case "Ny":
                    return new S("\uAA11", 2);
                case "nj":
                    return new S("\uAA12", 2);
                case "th":
                    return new S("\uAA14", 2);
                case "dh":
                    return new S("\uAA16", 2);
                case "nd":
                    return new S("\uAA19", 2);
                case "ph":
                    return new S("\uAA1C", 2);
                case "bh":
                    return new S("\uAA1E", 2);
                case "mb":
                    return new S("\uAA21", 2);
                case "pa":
                    return new S("\uAA1A", 1, "Pa");
                case "Pa":
                    return new S("\uAA1B\uAA29", 1);
            }
        }
        if (a.length() >= 1) {
            switch (a.substring(0, 1)) {
                case "k":
                    return new S("\uAA06", 1);
                case "g":
                    return new S("\uAA08", 1);
                case "c":
                    return new S("\uAA0C", 1);
                case "j":
                    return new S("\uAA0E", 1);
                case "t":
                    return new S("\uAA13", 1);
                case "d":
                    return new S("\uAA15", 1);
                case "n":
                    return new S("\uAA17", 1, "N");
                case "N":
                    return new S("\uAA18", 1);
                case "p":
                    return new S("\uAA1A", 1, "P");
                case "P":
                    return new S("\uAA1B\uAA29", 1);
                case "b":
                    return new S("\uAA1D", 1);
                case "m":
                    return new S("\uAA1F", 1, "M");
                case "M":
                    return new S("\uAA20", 1);
                case "r":
                    return new S("\uAA23", 1);
                case "l":
                    return new S("\uAA24", 1);
                case "w":
                    return new S("\uAA25", 1);
                case "S":
                    return new S("\uAA26", 1);
                case "s":
                    return new S("\uAA27", 1, "S");
                case "h":
                    return new S("\uAA28", 1);
                case "y":
                    return new S("\uAA22", 1);
                case "f":
                    return new S("\uAA1C", 1);
            }
        }
        return null;
    }

    public S head_pa(String a) {
        if (head_pat(a) != null) {
            int length_pa = head_pat(a).b;
            if (a.length() > length_pa && a.charAt(length_pa) == 'r') {
                return new S(head_pat(a).a + "\uAA34", length_pa + 1);
            } else if (a.length() > length_pa && a.charAt(length_pa) == 'l') {
                return new S(head_pat(a).a + "\uAA35", length_pa + 1);
            } else {
                return head_pat(a);
            }
        }
        return null;
    }

    public String convertk(String a) {
        switch (a)
        {
            case "k":
                return "\uAA40";
            case "c":
                return "\uAA44";
            case "t":
                return "\uAA45";
            case "n":
                return "\uAA46";
            case "p":
                return "\uAA47";
            case "y":
                return "\uAA48";
            case "r":
                return "\uAA49";
            case "l":
                return "\uAA4A";
            case "w":
                //  return "\uAA4E";
                return "\uAA25";
            case "s":
                return "\uAA4B";
            case "h":
                return "\uAA4D";
            case "m":
                return "\uAA4C";
        }
        return null;
    }

    public Z convert_na(String a) {
        String na = head_na(a).a;
        int length_na = head_na(a).b;
        if (a.length() == length_na) {
            return new Z(na, "na");
        }

        if (a.equals("aia")) {
            return new Z("\uAA00\uAA33", "na:aia");
        }

        if (a.equals("aie")) {
            return new Z("\uAA00\uAA33\uAA2E", "na:aie");
        }

        if (a.equals("aiem")) {
            return new Z("\uAA00\uAA33\uAA2E\uAA4C", "na:aiem"); // change font
        }

        if (a.length() == 4 && a.substring(0, 3).equals("aia")
                && convertk(a.charAt(3) + "") != null) {
            return new Z("\uAA00\uAA33" + convertk(a.charAt(3) + ""), "na:aia+k");
        }
        if (a.length() == 4 && a.substring(0, 3).equals("aie")
                && convertk(a.charAt(3) + "") != null) {
            return new Z("\uAA00\uAA33\uAA2E" + convertk(a.charAt(3) + ""), "na:aie+k");
        }

        if (a.length() == 3 && a.substring(0, 2).equals("ae")
                && convertk(a.charAt(2) + "") != null) {
            return new Z("\uAA00\uAA2E" + convertk(a.charAt(2) + ""), "na:ae+k");
        }

        if (a.equals("ai")) {
                return new Z("\uAA00\uAA30", "ai");
        }

        if (a.length() == 3 && a.substring(0, 2).equals("ai")
                && convertk(a.charAt(2) + "") != null) {
            return new Z("\uAA00\uAA30" + convertk(a.charAt(2) + ""), "na:ai+k");
        }

        switch (a.substring(length_na)) {
            case "m":
                return new Z(na + "\uAA4C", "na+m");
            case "ng":
                return new Z(na + "\uAA43", "na+ng");
        }

        if (a.substring(length_na).length() == 1 && convertk(a.substring(length_na)) != null) {
            return new Z(na + convertk(a.substring(length_na)), "na+k");
        }

        if (a.equals("ao")) {
            return new Z("\uAA00\uAA31", "na:ao");
        }

        if (a.length() == 3 && a.substring(0, 2).equals("ao") && convertk(a.substring(2)) != null) {
            return new Z("\uAA2F\uAA31" + convertk(a.substring(2)), "na:ao+k");
        }

        if (a.equals("aue")) {
            return new Z("\uAA00\uAA36\uAA2E", "na:aue");
        }

        if (a.length() == 4 && a.substring(0, 3).equals("aue") && convertk(a.substring(3)) != null) {
            return new Z("\uAA00\uAA36\uAA2E" + convertk(a.substring(3)), "na:aue+k");
        }

        return null;
    }

    public Z convert_pa_na(String pa, String a) { // pa + somthing + ket
        switch (a) {
            case "a":
                return new Z(pa, "pa+a");
            case "ei":
                return new Z(pa + "\uAA2C", "pa+ei");
            case "im":
                return new Z(pa + "\uAA2A\uAA4C", "pa+im");
            case "ang":
                return new Z(pa + "\uAA43", "pa+ang");
            case "am":
                return new Z(pa + "\uAA4C", "pa+am");
            case "e":
                return new Z(pa + "\uAA2E", "pa+e");
            case "em":
                return new Z(pa + "\uAA2E\uAA4C", "pa+em");
            case "eng":
                return new Z(pa + "\uAA2E\uAA43", "pa+eng");
            case "u":
                return new Z(pa + "\uAA2D", "pa+u");
            case "â":
                return new Z(pa + "\uAA32", "pa+â");
            case "ua":
                return new Z(pa + "\uAA36", "pa+ua");
            case "uei":
                return new Z(pa + "\uAA36\uAA2C", "pa+uei");
            case "ui":
                return new Z(pa + "\uAA36\uAA2A", "pa+uei");   //bo sung
            case "ue":
                return new Z(pa + "\uAA36\uAA2E", "pa+ue");
            case "uo":
                return new Z(pa + "\uAA36" + "\uAA2F", "pa+uo");
            case "o":
                return new Z(pa + "\uAA2F", "pa+o");
            case "ai":
                return new Z(pa + "\uAA30", "pa+e");
            case "ia":
                return new Z(pa + "\uAA33", "pa+ia");
            case "ie":
                return new Z(pa + "\uAA33\uAA2E", "pa+ie");
            case "au":
                return new Z(pa + "\uAA2E\uAA2D", "pa+au");
            case "um":
                return new Z(pa + "\uAA4C\uAA2D", "pa+um"); //change font
            case "âm":
                return new Z(pa + "\uAA4C\uAA32", "pa+âm"); //change font
            case "ao":
                return new Z(pa + "\uAA2F" + "\uAA31", "pa+ao");
            case "aom":
                return new Z(pa + "\uAA2F" + "\uAA31\uAA4C", "pa+aom");
            case "aong":
                return new Z(pa + "\uAA2F" + "\uAA31\uAA43", "pa+aong");
            case "iem":
                return new Z(pa + "\uAA33\uAA2E\uAA4C", "pa+iem");
            case "é":
                return new Z(pa + "\uAA2F" + "\uAA2E", "pa+é");
            case "om":
                return new Z(pa + "\uAA2F" + "\uAA4C", "pa+om"); //change font
            case "âng":
                return new Z(pa + "\uAA32\uAA42", "pa+âng"); //bo sung
            case "ong":
                return new Z(pa + "\uAA2F" + "\uAA42", "pa+ong");
            case "ung":
                return new Z(pa + "\uAA2D\uAA42", "pa+ung");
            case "ing":
                return new Z(pa + "\uAA2B\uAA42", "pa+ing");
            case "ieng":
                return new Z(pa + "\uAA33\uAA2E\uAA42", "pa+ieng");
            case "uang":
                return new Z(pa + "\uAA36\uAA42", "pa+uang");
            case "ueng":
                return new Z(pa + "\uAA36\uAA2E\uAA42", "pa+ueng");
            case "aing":
                return new Z(pa + "\uAA30\uAA43", "pa+aing");
            case "aue":
                return new Z(pa + "\uAA00\uAA36\uAA2E", "pa+aue");
            case "uai":
                return new Z(pa + "\uAA36\uAA30", "pa+uai");
            case "iéng":
                return new Z(pa + "\uAA2F\uAA33\uAA2E\uAA43", "pa+iéng");
                // fix this
                // case "ieng":
                // return new Z(pa + "\uAA33\uAA2E\uAA43", "pa+ieng");
            case "ié":
                return new Z(pa + "\uAA2F\uAA33\uAA2E", "pa+ié");
            case "éng":
                return new Z(pa + "\uAA2F\uAA2E\uAA42", "pa+éng");
            case "iim":
                return new Z(pa + "\uAA33\uAA2A\uAA4C", "pa+iim");
            case "iip":
                return new Z(pa + "\uAA33\uAA2A\uAA47", "pa+iip");
            case "i":
                return new Z(pa + "\uAA2A", "pa+i");
            case "ii":
                return new Z(pa + "\uAA2B", "pa+ii");

        }
        return null;
    }

    public Z convert_pa(String a) {
        String pa = head_pa(a).a;
        int length_pa = head_pa(a).b;
        if (a.length()== length_pa) {
            return new Z(pa, "pa");
        }
        Z con_pa_na = convert_pa_na(pa, a.substring(length_pa));
        if (con_pa_na != null) {
            return con_pa_na;
        }

        Z s_toconvert = convert_pa_na(pa, a.substring(length_pa, a.length() - 1));
        if (s_toconvert != null && convertk(a.charAt(a.length() - 1) + "") != null) {
            return new Z(s_toconvert.a + convertk(a.charAt(a.length() - 1) + ""), "pa+somthing+k");
        }

        if (a.charAt(length_pa) == 'a' && (convertk(a.substring(length_pa + 1)) != null)) {
            return new Z(pa + convertk(a.charAt(length_pa + 1) + ""), "pa+a+k");
        }
        return null;
    }
    public Z convert_thuong(String a)
    {
        int length = a.length();
        if (length == 0) {
            return null;
        }
        else if (length >= 1) {
            S abc = head_na(a);
            if (head_na(a) != null && head_na(a).b >= 1) {
                return convert_na(a);
            }
            else if (head_pa(a) != null && head_pa(a).b >= 1) {
                return convert_pa(a);
            }
        }
        return null;
    }
    private ArrayList<ArrayList<Integer>> partition(int n, ArrayList<Integer> answer, ArrayList<ArrayList<Integer>> answer2)
    {
        int MAXLENTH = 16;
        if (n <= 1) {
            return new ArrayList<ArrayList<Integer>>();
        }
        if (n == 2 || n == 3) {
            answer.add(n);
            ArrayList<Integer> answer3 = new ArrayList<Integer>();
            for (int i = 0; i < answer.size(); i++) {
                answer3.add(answer.get(i));
            }
            answer2.add(answer3);
            answer.remove(answer.size() - 1);
            return answer2;
        }
        if (n >= 4 && n <= 5) {
            answer.add(n);
            ArrayList<Integer> answer3 = new ArrayList<Integer>();
            for (int i = 0; i < answer.size(); i++)
            {
                answer3.add(answer.get(i));
            }
            answer2.add(answer3);
            answer.remove(answer.size() - 1);
        }
        if (3 < n && n < MAXLENTH) {
            for (int i = 2; i <= n - 2; i++) {
                if (2 <= i && i <= 5) {
                    answer.add(i);
                    partition(n - i, answer, answer2);
                    answer.remove(answer.size() - 1);
                }
            }
            return new ArrayList<ArrayList<Integer>>(n);
        }
        return new ArrayList<ArrayList<Integer>>();
    }

    public ArrayList<ArrayList<Integer>> my_division(int n)
    {
        ArrayList<Integer> answer = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> answer2 = new ArrayList<ArrayList<Integer>>();

        partition(n, answer, answer2);
        return answer2;
    }

    public Z my_convert(String a) {
        Z con_thuong = convert_thuong(a);
        if (con_thuong != null) {
            return con_thuong;
        }
        ArrayList<ArrayList<Integer>> list = my_division(a.length());
        for (int i = 0; i < list.size(); i++) {
            String answer = "";
            int sum = 0;
            int index = 0;
            list.get(i).add(0, 0);
            for (int j = 0; j < list.get(i).size() - 1; j++) {
                index += list.get(i).get(j);
                Z conv = convert_thuong(a.substring(index, index + list.get(i).get(j + 1)));
                if (conv == null) {
                    break;
                } else if (conv.b.length() > 3 && conv.b.substring(0, 3).equals("pa+")) {
                    sum++;
                    answer += conv.a;
                }
            }
            if (sum == list.get(i).size() - 1 && sum != 0) {
                return new Z(answer, "pa+na+pa+na");
            }
        }
        return null;
    } //end function my_convert()

    public Z convert_my_na_head (String a, int i) {
        Z conv_na = convert_na(a.substring(0, i));
        if (conv_na != null) {
            Z other_con = my_convert(a.substring(i));
            if (other_con != null) {
                return new Z(conv_na.a + other_con.a, "na+pa+...");
            }
        }
        return null;
    }
    public Z convert_ghep(String a) {
        Z con = my_convert(a);
        if (con != null) {
            return con;
        }
        if (a.length() >= 3 && head_na(a) != null) {
            for (int i = 1; i <= a.length() - 2; i++) {
                Z convert_head = convert_my_na_head(a, i);
                if (convert_head != null) {
                    return convert_head;
                }
            }
        }
        return my_convert(a);
    }

    public String convert_upper(String a) {
        if (head_pat(a) != null && head_pat(a).c != null) {
            return (a.charAt(0) + "").toUpperCase() + a.substring(1);
        }
        return null;
    }
    public String convert_ghep_advance(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        String[] split_minus = input.split("-");
        String output_slip = "";
        for (int i = 0; i < split_minus.length; i++) {
            if (convert_ghep(split_minus[i]) != null) {
                output_slip += convert_ghep(split_minus[i]).a;
            } else if (convert_ghep(split_minus[i] + "a") != null) {
                output_slip += convert_ghep(split_minus[i] + "a").a;
            }
        }
        return output_slip;
    }
    public ArrayList<String> convert_total(String input) {
        String lowercase_w = convert_ghep_advance(input);
        String uppercase_w = convert_ghep_advance(convert_upper(input));

        ArrayList<String> a = new ArrayList<String>();
        a.add(lowercase_w);
        if (uppercase_w.isEmpty()) {
            a.add(lowercase_w);
        } else {
            a.add(uppercase_w);
        }
        return a;
    }

    public void delete() {
        if (this.word.length() > 0) {
            this.word = this.word.substring(0, this.word.length() - 1);
        }
    }

    public void addKey(String key) {
        key = key.toLowerCase();
        this.word += key;
    }

    public void reset() {
        this.word = "";
    }

    public String getWord() {
        return this.word;
    }

    public String convert(int indexInList) {
        String wordReplace = this.word.replace("aa", "â");
        wordReplace = wordReplace.replace("âa", "aa");
        wordReplace = wordReplace.replace("ee", "é");
        wordReplace = wordReplace.replace("ée", "ee");
        ArrayList<String> convert = this.convert_total(wordReplace);
        if (convert != null && convert.get(indexInList) != null && !convert.get(indexInList).isEmpty()) {
            return convert.get(indexInList);
        } else {
            this.reset();
        }
        return "";
    }
}
