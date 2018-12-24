package com.datadrizzle.poc;

import java.lang.reflect.InvocationTargetException;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngine;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.REngineStdOutput;
import org.rosuda.REngine.Rserve.RConnection;

public class RLangPOC {
	
	/*static {
		System.loadLibrary("jri");
	}*/
	
	/*static {
	    try {
	    	System.load("C:/Users/Admin/Documents/R/win-library/3.5/rJava/jri/x64/jri.dll");
	    } catch (UnsatisfiedLinkError e) {
	      System.err.println("Native code library failed to load.\n" + e);
	      System.exit(1);
	    }
	  }
*/
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, REngineException, REXPMismatchException {
		helloRWorld(args);
	}

	public static void helloRWorld(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
			InvocationTargetException, REngineException, REXPMismatchException {
		String[] Args = {"--vanilla"};
		REngine rengine = REngine.engineForClass("org.rosuda.REngine.JRI.JRIEngine", Args, new REngineStdOutput (), false);
		// rengine.eval(String.format("greeting <- '%s'", "Hello R World"));
		// REXP result = rengine.eval("greeting");
		// System.out.println("Greeting from R: " + result.asString());

		REXP result = rengine.parseAndEval(String.format("greeting <- '%s'", "Hello R World"));
//		REXP result = rengine.eval("greeting");
		System.out.println("Greeting from R: " + result.asString());

	}

	public void REngExample() {

	}

	public void RServExample() {

		double[] myvalues = { 1.0, 1.5, 2.2, 0.5, 0.9, 1.12 };

		RConnection c = null;

		try {
			c = new RConnection();
			c.assign("myvalues", myvalues);
			REXP x = c.eval("mean(myvalues)");
			System.out.println(x.asDouble());
			x = c.eval("sd(myvalues)");
			System.out.println(x.asDouble());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (c != null) {
				try {
					c.close();
				} finally {
				}
			}
		}
	}

}
