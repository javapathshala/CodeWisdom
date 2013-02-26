/**
 * Copywrite @ Dimit Chadha
 */
package com.jp.koncept.dto.conversion;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author Dimit Chadha
 * 
 */
public class DTOConversion1 {

	public Object convertDTOData(Object sourceObj, Object destObj) {
		try {

			Class<?> sourceClass = sourceObj.getClass();
			Class<?> destinationClass = destObj.getClass();

			Method[] sourceMethods = sourceClass.getMethods();
			Method method = null;

			String sourceMethodName = "";
			int size = sourceMethods.length;
			Object getValue = null;
			for (int i = 0; i < size; i++) {
				method = (Method) sourceMethods[i];
				sourceMethodName = method.getName();
				if (sourceMethodName.startsWith("get") || sourceMethodName.startsWith("is")) {
					String attributeName = getAttributeName(sourceMethodName);
					if ("class".equalsIgnoreCase(attributeName)) {
						continue;
					} else {

						getValue = invokeGetOnSource(method, sourceObj);
//						/System.out.println(getValue);
						Class<?> returnType = method.getReturnType();

						 destObj = invokeSetOnDest(getValue, destObj, returnType, sourceMethodName);
					}
					// if (!"".equals(currentPrefix)) {
					// attributePrefix = currentPrefix;
					// }
					// if (!"".equals(attributePrefix)) {
					// attributeName = attributePrefix + "." + attributeName;
					// }

					// if return type is not array

				}

			}

			// System.out.println(((LocationDTO) locationDTO).getAddress());

			// /convertDTOData(sourceClass, destinationClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
return destObj;
	}

	private String getAttributeName(String methodName) {
		StringBuffer sb = new StringBuffer();
		if (methodName.startsWith("is")) {
			sb.append(methodName);
		} else {
			sb.append(methodName.substring(3, 4).toLowerCase()).append(methodName.substring(4));
		}
		return sb.toString();

	}

	private Object invokeGetOnSource(Method sourceMethodName, Object sourceObj) {
		Object obj = null;

		obj = invokeGetMethod(sourceMethodName, sourceObj);
		return obj;
	}

	private Object invokeGetMethod(Method sourceMethodName, Object sourceObj) {
		Object objReturn = null;
		try {
			// Method method = null;
			//			
			// //Object obnj = sourceClass.newInstance();
			// method = sourceClass.getMethod(sourceMethodName);
			objReturn = sourceMethodName.invoke(sourceObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objReturn;
	}

	public void convertDTOData(Class<?> sourceClass, Class<?> destinationClass) {
		try {
			Method[] sourceMethods = sourceClass.getMethods();
			int size = 0;
			size = sourceMethods.length;
			String sourceMethodName = "";
			Method method = null;
			Object getObjValue = null;
			String attributeName = "";
			Object destObj = destinationClass.newInstance();
			for (int i = 0; i < size; i++) {
				method = (Method) sourceMethods[i];
				sourceMethodName = method.getName();
				if (sourceMethodName.startsWith("get") || sourceMethodName.startsWith("is")) {
					if ("class".equalsIgnoreCase(attributeName)) {
						continue;
					} else {
						attributeName = getAttributeName1(sourceMethodName);
						// getObjValue = invokeGetOnSource(sourceClass,
						// sourceMethodName, attributeName);

						Class<?> returnType = method.getReturnType();

						destObj = invokeSetOnDest(getObjValue, destObj, returnType, sourceMethodName);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Object invokeSetOnDest(Object getValue, Object destObj, Class<?> returnType,String sourceMethodName)
			throws Exception {
		populateObject(getValue, destObj, returnType, sourceMethodName);
		return destObj;

	}

	private String getAttributeName1(String sourceMethodName) {
		return sourceMethodName.substring(3);
	}

	// #########################################################

	private Object invokeMethod(Object object, String strMethodName, Class<?>[] objParamTypes, Object[] objParams) throws Exception {
		Object objReturn = null;
		try {
			Method method = null;
			Class<?> class1 = object.getClass();
			method = class1.getMethod(strMethodName, objParamTypes);
			objReturn = method.invoke(object, objParams);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return objReturn;
	}

	private void populateObject(Object getValue, Object destObj, Class<?> returnType, String sourceMethodName)
			throws Exception {
		if (returnType.isAssignableFrom(String.class)) {
			populateString(destObj, sourceMethodName, getValue.toString());
		}
		if (returnType.isAssignableFrom(Integer.class)) {
			// populateInteger(sourceObject, methodName, attributeName);
		} else if (returnType.isAssignableFrom(Date.class)) {
			// populateDate(sourceObject, methodName, attributeName);
		}
	}

	private void populateString(Object destObj, String methodName, String getValue) throws Exception {
		Class<?>[] objParamTypes = new Class[1];
		Object[] objParams = new Object[1];
		objParamTypes[0] = String.class;
		objParams[0] = getValue;
		String calledMethod = getSettermethodName(methodName);
		invokeMethod(destObj, calledMethod, objParamTypes, objParams);
	}

	private String getSettermethodName(String methodName) {
		StringBuffer sb = new StringBuffer("s");
		sb.append(methodName.substring(1));
		return sb.toString();

	}

}
