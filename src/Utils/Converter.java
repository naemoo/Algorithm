package Utils;

public class Converter {
    static String convertBrackets(String str) {
	String newStr = str.replaceAll("\\]", "\\}");
	newStr = newStr.replaceAll("\\[", "{");
	return newStr;
    }

    public static void main(String[] args) {
	String str = "[[1,5],[2,5],[3,5],[4,5]]";
	System.out.println(convertBrackets(str));
    }
}