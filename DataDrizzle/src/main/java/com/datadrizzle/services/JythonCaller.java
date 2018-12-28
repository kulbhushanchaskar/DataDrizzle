/*package com.datadrizzle.services;

import org.python.core.PyClass;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class JythonCaller {

	private PythonInterpreter pythonInterpreter;

	public JythonCaller() {
		pythonInterpreter = new PythonInterpreter();
	}

	public void invokeClass() {
		pythonInterpreter.exec("from divider import Divider");
		PyClass dividerDef = (PyClass) pythonInterpreter.get("Divider");
		PyObject divider = dividerDef.__call__();
		PyObject pyObject = divider.invoke("divide", new PyInteger(20), new PyInteger(4));
		System.out.println(pyObject.toString());
	}

}
*/