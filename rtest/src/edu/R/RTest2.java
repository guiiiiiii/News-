package edu.R;

import java.util.Enumeration;
import java.util.Vector;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.RVector;
import org.rosuda.JRI.Rengine;

public class RTest2 {

	public static void main(String[] args) {

		RTest2 obj = new RTest2();
		//obj.test1();
		obj.test3(args[0]);
	}
	//자바에서 입력데이터 전달해서 r실행
	public static String[] test3(String input) {
		System.loadLibrary("jri"); //이거없음 ㄴㄴㄴ
		Rengine rEngine = new Rengine(null, false, null);
		
		rEngine.eval("stu<-data.frame(name=c('ㅎ','ㄴ','ㅈ','ㅇ'),eng=c(100,90,80,80),mat=c(77,88,99,22),kor=c(56,98,100,99))");
		
		rEngine.eval("library(dplyr)");
		rEngine.eval("onestu<-stu %>% filter(name=='"+input+"')");
		
		rEngine.eval("total<-onestu$eng+onestu$mat+onestu$kor");
		rEngine.eval("avg<-total/3");
		
		REXP name=rEngine.eval("onestu$name<-as.character(onestu$name)");
		REXP eng=rEngine.eval("onestu$eng<-as.character(onestu$eng)");
		REXP kor=rEngine.eval("onestu$kor<-as.character(onestu$kor)");
		REXP mat=rEngine.eval("onestu$mat<-as.character(onestu$mat)");
		
		System.out.println(name.asString());
		System.out.println(eng.asString());
		System.out.println(mat.asString());
		System.out.println(kor.asString());
		
		REXP exp = rEngine.eval("total");
		Double result=exp.asDouble();
		System.out.println(result);
		
		REXP exp1 = rEngine.eval("avg");
		Double result1=exp1.asDouble();
		System.out.println(result1);
		
		String name1=name.asString();
		String eng1=eng.asString();
		String mat1=mat.asString();
		String kor1=kor.asString();
		String total=result.toString();
		String avg=result1.toString();
		
		String[] r = {name1,eng1,mat1,kor1,total,avg};
		
		rEngine.end();
		
		return r;
	}
	//이미지 파일
	public void test2() {
		System.loadLibrary("jri"); //이거없음 ㄴㄴㄴ
		Rengine rEngine = new Rengine(null, false, null);
		
		rEngine.eval("png('C:/java_class/workspace/rtest/WebContent/test.png')");
		rEngine.eval("plot(sample(c(1:5)),5");
		rEngine.eval("dev.off()");
		rEngine.end();
	}
	
	public void test1() {
		Rengine rEngine = new Rengine(null, false, null);
		rEngine.eval("library(ggplot2)");
		REXP exp5 = rEngine.eval("mpg");

		RVector rv5 = exp5.asVector();
		Vector v5 = rv5.getNames();
		/*
		 * Enumeration e = v5.elements(); while (e.hasMoreElements()) {
		 * System.out.println(e.nextElement()); }
		 */

		for (Object o : v5) {
			System.out.println(o);
			REXP exp6 = rEngine.eval("mpg$" + o);
			if (exp6.rtype == exp6.STRSXP) {
				System.out.println(exp6.asStringArray()[0]);
			} else {
				System.out.println(exp6.asDoubleArray()[0]);
			}

		}
	}
}