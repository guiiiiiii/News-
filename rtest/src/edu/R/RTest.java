package edu.R;

import java.util.Enumeration;
import java.util.Vector;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.RVector;
import org.rosuda.JRI.Rengine;


public class RTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		System.out.println(System.getenv("R_HOME"));
		System.out.println(System.getProperty("java.library.path"));
		System.loadLibrary("jri");
		System.out.println("fkfkfakkdk");
		//System.out.println(REngine.);
		*/
		Rengine rEngine=new Rengine(null,false,null);
		/*System.out.println(Rengine.versionCheck());
		
		REXP exp=rEngine.eval("a<-c(1:10)");
		int[] result1 = exp.asIntArray();
		for(int i:result1) {
			System.out.println(i);
		}
		
		
		REXP exp2 = rEngine.eval("a");
		
		int[] result2 = exp2.asIntArray();
		for(int i:result2) {
			System.out.println(i);
		}
		REXP exp3 =rEngine.eval("b<-LETTERS[1:10]");
		String s[] = exp3.asStringArray();
		for(String i:s) {
			System.out.println(i);
		}
		System.out.println(rEngine.eval("getwd()"));
		*/
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
	         if(exp6.rtype==exp6.STRSXP) {
	        	 System.out.println(exp6.asStringArray()[0]);
	         }else {
	        	 System.out.println(exp6.asDoubleArray()[0]);
	         }
		
	}
	}
}
