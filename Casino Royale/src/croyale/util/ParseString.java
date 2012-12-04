package croyale.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;


public class ParseString {

	// HashMap for storing objects created by user
	private static HashMap<String, Object> hmObjects = new HashMap<String, Object>();
	// Parses a command string for a new object instantiation (Option 3)
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void parseString(String s) throws ClassNotFoundException,
				InstantiationException, IllegalAccessException,
				NoSuchMethodException, SecurityException, IllegalArgumentException,
				InvocationTargetException {

			// Local variable <-- will be used as a counter for looping
			int i;
			// Split expression by assignment variable and the new object call
			String[] expression = s.trim().split("=");
			// If expression contains the keyword new right after the equals sign
			// then get rid of it
			if (expression[1].trim().toUpperCase().startsWith("NEW"))
				expression[1] = expression[1].substring(expression[1].toUpperCase().indexOf("NEW")+4);
			// Split the object call (after equals sign) into class name
			// (constructor) and arguments
			// If the length of newObject is only 1 then the constructor has no
			// arguments and a default one may
			// be called
			String[] newObject = expression[1].trim().split("[ ,)(;]+");
			Class clsObject = Class.forName(newObject[0]);

			// Checks to see if arguments exist in the call
			if (newObject.length > 1) {
				Class[] lstClassPars = new Class[newObject.length - 1];
				Object[] objArgList = new Object[newObject.length - 1];

				for (i = 1; i < newObject.length; i++) {
					// Checks if argument is an integer
					try {
						int intPar = Integer.parseInt(newObject[i]);
						objArgList[i - 1] = intPar;
						lstClassPars[i - 1] = int.class;
						continue;
					} catch (NumberFormatException nfe) {

					}
					// Check if argument is a double
					try {
						double d = Double.parseDouble(newObject[i]);
						objArgList[i - 1] = d;
						lstClassPars[i - 1] = double.class;
						continue;
					} catch (Exception ex) {

					}
					// Checks if argument is a string
					if (newObject[i].contains("\"")) {
						objArgList[i - 1] = newObject[i].toString();
						lstClassPars[i - 1] = String.class;
						continue;
					}
				}
				// Gets the appropriate constructor based on parameter types
				Constructor ct = clsObject.getConstructor(lstClassPars);
				// Creates object and stores it in the hash map with the appropriate
				// variable name as the key
				hmObjects.put(expression[0], ct.newInstance(objArgList));
				// If no parameters exist then just call the default constructor
			} else {
				hmObjects.put(expression[0], clsObject.newInstance());
			}
		}


}
