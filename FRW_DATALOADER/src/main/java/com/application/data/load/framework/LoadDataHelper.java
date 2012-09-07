package com.application.data.load.framework;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.infomata.data.CSVFormat;
import com.infomata.data.DataFile;
import com.infomata.data.DataRow;

/**
 * Helper class that would load the data from the CSV file for customerDAO.
 * 
 * 
 */
public class LoadDataHelper {

	private List<String> attributeList = null;

	private boolean bAttribuesPopulated;

	/**
	 * @param filePath
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public Object[] getCSVData(String filePath, Class<?> clazz)
			throws Exception {
		String className = clazz.getName();
		List<Object> lstCSVData = new ArrayList<Object>();
		Object[] retObject = null;

		List<DataRow> lstDataRow = readCSV(filePath);
		try {

			for (Iterator<DataRow> iter = lstDataRow.iterator(); iter.hasNext();) {
				attributeList = new ArrayList<String>();
				bAttribuesPopulated = true;
				Object object = Class.forName(className).newInstance();
				DataRow element = (DataRow) iter.next();
				Object myObj = populateVO(object, "", "", element);
				lstCSVData.add(myObj);
			}
			retObject = (Object[]) lstCSVData.toArray(new Object[lstCSVData
					.size()]);
			return getTypeArray(clazz, retObject);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		// finally {
		// finalize();
		// }

	}

	/**
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	private List<DataRow> readCSV(String filePath) throws Exception {
		File objFile = null;
		List<DataRow> list = new ArrayList<DataRow>();
		try {
			DataFile objDataFile = DataFile.createReader("8859_1");
			objDataFile.setDataFormat(new CSVFormat());
			// first line is column header
			objDataFile.containsHeader(true);
			objFile = new File(filePath);
			objDataFile.open(objFile);
			for (DataRow row = objDataFile.next(); row != null; row = objDataFile
					.next()) {
				list.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	/**
	 * @param obj
	 * @param attributePrefix
	 * @param currentPrefix
	 * @param dataRow
	 * @return
	 * @throws Exception
	 */
	private Object populateVO(Object obj, String attributePrefix,
			String currentPrefix, DataRow dataRow) throws Exception {
		String objectMethodName = "";
		Class<?> claszz = obj.getClass();
		Method[] methods = claszz.getMethods();
		// Method method = null;
		int size = 0;
		size = methods.length;

		for (int i = 0; i < size; i++) {
			Method classMethod = (Method) methods[i];
			objectMethodName = methods[i].getName();
			if (objectMethodName.startsWith("get")
					|| objectMethodName.startsWith("is")) {
				String methodName = classMethod.getName();
				Class<?> returnType = classMethod.getReturnType();
				String returnTypeName = returnType.getName();
				String attributeName = getAttributeName(methodName);
				if ("class".equalsIgnoreCase(attributeName)) {
					continue;
				}
				if (!"".equals(currentPrefix)) {
					attributePrefix = currentPrefix;
				}
				if (!"".equals(attributePrefix)) {
					attributeName = attributePrefix + "." + attributeName;
				}
				if (!returnType.isArray()) {
					if (returnTypeName.endsWith("DAO")) {
						currentPrefix = attributeName;
						Object object = Class.forName(returnType.getName())
								.newInstance();
						Object VoObject = populateVO(object, attributePrefix,
								currentPrefix, dataRow);
						Class<?>[] objParamTypes = new Class<?>[1];
						Object[] objParams = new Object[1];
						objParamTypes[0] = object.getClass();
						objParams[0] = object;
						String calledMethod = getSettermethodName(methodName);
						invokeMethod(obj, calledMethod, objParamTypes,
								objParams);

						currentPrefix = "";
					} else {
						attributeList.add(attributeName);
						if (returnType.isPrimitive()) {
							populatePrimitiveValues(obj, returnType,
									methodName, attributeName, dataRow);
						} else {
							populateObject(obj, returnType, methodName,
									attributeName, dataRow);
						}

					}

				}// retrun type is an array
				else if (returnType.isArray()) {
					String strArrayTypeName = returnType.getComponentType()
							.getName();

					if (strArrayTypeName.endsWith("DAO")) {
						// Class<?> class1 = Class<?>.forName(strArrayTypeName);

						currentPrefix = attributeName;
						Object object = Class.forName(
								returnType.getComponentType().getName())
								.newInstance();
						Object VOforArray = populateVO(object, attributePrefix,
								currentPrefix, dataRow);

						currentPrefix = "";

						Class<?>[] objParamTypes = new Class<?>[] { returnType };

						Object objeParmVal = Array.newInstance(
								returnType.getComponentType(), 2);
						Object[] src = new Object[] { VOforArray, VOforArray };
						System.arraycopy(src, 0, objeParmVal, 0, src.length);
						Object[] objParams = new Object[1];
						objParams[0] = objeParmVal;
						String calledMethod = getSettermethodName(methodName);
						invokeMethod(obj, calledMethod, objParamTypes,
								objParams);
					} else {
						if (returnType.getComponentType().isPrimitive()) {
							populatePrimitiveArray(obj, returnType, methodName,
									attributeName, dataRow);

						} else if (returnType.getComponentType().newInstance() instanceof String) {
							// Class<?> class1 =
							// Class<?>.forName(strArrayTypeName);
							String strVal = dataRow.getString(attributeName);

							Class<?>[] objParamTypes = new Class<?>[] { returnType };

							Object objeParmVal = Array.newInstance(
									returnType.getComponentType(), 2);
							Object[] src = new Object[] { strVal, strVal };
							System.arraycopy(src, 0, objeParmVal, 0, src.length);
							Object[] objParams = new Object[1];
							objParams[0] = objeParmVal;
							String calledMethod = getSettermethodName(methodName);
							invokeMethod(obj, calledMethod, objParamTypes,
									objParams);
						}
						attributeList.add(attributeName);
					}

				}
			}
		}

		return obj;
	}

	/**
	 * @param object
	 * @param strMethodName
	 * @param objParamTypes
	 * @param objParams
	 * @return
	 * @throws Exception
	 */
	private Object invokeMethod(Object object, String strMethodName,
			Class<?>[] objParamTypes, Object[] objParams) throws Exception {

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

	/**
	 * @param type
	 * @param objectArray
	 * @return
	 */
	private Object[] getTypeArray(Class<?> type, Object[] objectArray) {
		Object target = Array.newInstance(type, 0);
		if (objectArray != null && objectArray.length > 0) {
			target = Array.newInstance(type, objectArray.length);
			System.arraycopy(objectArray, 0, target, 0, objectArray.length);
		}
		return (Object[]) target;
	}

	private void populateObject(Object sourceObject, Class<?> objectType,
			String methodName, String attributeName, DataRow currentDataRow)
			throws Exception {

		if (objectType.isAssignableFrom(String.class)) {
			populateString(sourceObject, methodName, attributeName,
					currentDataRow);
		}
		if (objectType.isAssignableFrom(Integer.class)) {
			populateInteger(sourceObject, methodName, attributeName,
					currentDataRow);
		} else if (objectType.isAssignableFrom(Date.class)) {
			populateDate(sourceObject, methodName, attributeName,
					currentDataRow);
		}
	}

	/**
	 * @param sourceObject
	 * @param methodName
	 * @param attributeName
	 * @param currentDataRow
	 * @throws Exception
	 */
	private void populateString(Object sourceObject, String methodName,
			String attributeName, DataRow currentDataRow) throws Exception {

		String strVal = currentDataRow.getString(attributeName);
		Class<?>[] objParamTypes = new Class<?>[1];
		Object[] objParams = new Object[1];
		objParamTypes[0] = String.class;
		objParams[0] = strVal;
		String calledMethod = getSettermethodName(methodName);
		invokeMethod(sourceObject, calledMethod, objParamTypes, objParams);
	}

	/**
	 * @param sourceObject
	 * @param methodName
	 * @param attributeName
	 * @param currentDataRow
	 * @throws Exception
	 */
	private void populateInteger(Object sourceObject, String methodName,
			String attributeName, DataRow currentDataRow) throws Exception {

		String strVal = currentDataRow.getString(attributeName);
		if (strVal == null || "".equals(strVal)) {
			strVal = "0";
		}
		Class<?>[] objParamTypes = new Class<?>[1];
		Object[] objParams = new Object[1];
		objParamTypes[0] = Integer.class;
		objParams[0] = new Integer(strVal);
		String calledMethod = getSettermethodName(methodName);

		invokeMethod(sourceObject, calledMethod, objParamTypes, objParams);
	}

	/**
	 * @param sourceObject
	 * @param methodName
	 * @param attributeName
	 * @param currentDataRow
	 * @throws Exception
	 */
	private void populateDate(Object sourceObject, String methodName,
			String attributeName, DataRow currentDataRow) throws Exception {

		Date date = currentDataRow.getDate(attributeName, "dd/MM/yyyy");
		Class<?>[] objParamTypes = new Class<?>[1];
		Object[] objParams = new Object[1];
		objParamTypes[0] = Date.class;
		objParams[0] = date;
		String calledMethod = getSettermethodName(methodName);
		invokeMethod(sourceObject, calledMethod, objParamTypes, objParams);
	}

	/**
	 * @param sourceObject
	 * @param inputType
	 * @param methodName
	 * @param attributeName
	 * @param currentDatarow
	 * @throws Exception
	 */
	private void populatePrimitiveArray(Object sourceObject,
			Class<?> inputType, String methodName, String attributeName,
			DataRow currentDatarow) throws Exception {

		String strArrayTypeName = inputType.getComponentType().getName();

		if ("int".equalsIgnoreCase(strArrayTypeName)) {
			int intVal = currentDatarow.getInt(attributeName, 0);
			Class<?>[] objParamTypes = new Class<?>[] { inputType };
			Object objeParmVal = Array.newInstance(
					inputType.getComponentType(), 2);

			int[] src = new int[] { intVal, intVal };

			System.arraycopy(src, 0, objeParmVal, 0, src.length);
			Object[] objParams = new Object[1];
			objParams[0] = objeParmVal;
			String calledMethod = getSettermethodName(methodName);
			invokeMethod(sourceObject, calledMethod, objParamTypes, objParams);

		} else if ("long".equalsIgnoreCase(strArrayTypeName)) {

			String strLong = currentDatarow.getString(attributeName, "").trim();
			if ("".equals(strLong)) {
				strLong = "0";
			}
			long longVal = Long.parseLong(strLong);

			Class<?>[] objParamTypes = new Class<?>[] { inputType };
			Object objeParmVal = Array.newInstance(
					inputType.getComponentType(), 2);

			long[] src = new long[] { longVal, longVal };

			System.arraycopy(src, 0, objeParmVal, 0, src.length);
			Object[] objParams = new Object[1];
			objParams[0] = objeParmVal;
			String calledMethod = getSettermethodName(methodName);
			invokeMethod(sourceObject, calledMethod, objParamTypes, objParams);

		} else if ("double".equalsIgnoreCase(strArrayTypeName)) {
			double doubleVal = currentDatarow.getDouble(attributeName, 0);
			// Class<?> class1 = double.class;
			Class<?>[] objParamTypes = new Class<?>[] { inputType };
			Object objeParmVal = Array.newInstance(
					inputType.getComponentType(), 2);
			double[] src = new double[] { doubleVal, doubleVal };
			System.arraycopy(src, 0, objeParmVal, 0, src.length);
			Object[] objParams = new Object[1];
			objParams[0] = objeParmVal;
			String calledMethod = getSettermethodName(methodName);
			invokeMethod(sourceObject, calledMethod, objParamTypes, objParams);

		} else if ("boolean".equalsIgnoreCase(strArrayTypeName)) {

			String str = currentDatarow.getString(attributeName, "");
			boolean boolVal = false;
			if (str.equalsIgnoreCase("TRUE")) {
				boolVal = true;
			}
			Class<?>[] objParamTypes = new Class<?>[] { inputType };
			Object objeParmVal = Array.newInstance(
					inputType.getComponentType(), 2);
			boolean[] src = new boolean[] { boolVal, boolVal };
			System.arraycopy(src, 0, objeParmVal, 0, src.length);
			Object[] objParams = new Object[1];
			objParams[0] = objeParmVal;
			String calledMethod = getSettermethodName(methodName);
			invokeMethod(sourceObject, calledMethod, objParamTypes, objParams);
		}
	}

	/**
	 * @param sourceObject
	 * @param inputType
	 * @param methodName
	 * @param attributeName
	 * @param currentDatarow
	 * @throws Exception
	 */
	private void populatePrimitiveValues(Object sourceObject,
			Class<?> inputType, String methodName, String attributeName,
			DataRow currentDatarow) throws Exception {

		if ("int".equalsIgnoreCase(inputType.getName())) {

			int intVal = currentDatarow.getInt(attributeName, 0);
			Class<?>[] objParamTypes = new Class<?>[1];
			Object[] objParams = new Object[1];
			objParamTypes[0] = int.class;
			objParams[0] = new Integer(intVal);
			String calledMethod = getSettermethodName(methodName);
			invokeMethod(sourceObject, calledMethod, objParamTypes, objParams);

		} else if ("long".equalsIgnoreCase(inputType.getName())) {

			String strLong = currentDatarow.getString(attributeName, "").trim();
			if ("".equals(strLong)) {
				strLong = "0";
			}
			long longVal = Long.parseLong(strLong);
			Class<?>[] objParamTypes = new Class<?>[1];
			Object[] objParams = new Object[1];
			objParamTypes[0] = long.class;
			objParams[0] = new Long(longVal);
			String calledMethod = getSettermethodName(methodName);
			invokeMethod(sourceObject, calledMethod, objParamTypes, objParams);

		} else if ("double".equalsIgnoreCase(inputType.getName())) {

			double doubleVal = currentDatarow.getDouble(attributeName, 0);
			Class<?>[] objParamTypes = new Class<?>[1];
			Object[] objParams = new Object[1];
			objParamTypes[0] = double.class;
			objParams[0] = new Double(doubleVal);
			String calledMethod = getSettermethodName(methodName);
			invokeMethod(sourceObject, calledMethod, objParamTypes, objParams);

		} else if ("boolean".equalsIgnoreCase(inputType.getName())) {

			boolean boolVal = false;
			if ("TRUE".equalsIgnoreCase(currentDatarow.getString(attributeName,
					""))) {
				boolVal = true;
			}
			Class<?>[] objParamTypes = new Class<?>[1];
			Object[] objParams = new Object[1];
			objParamTypes[0] = boolean.class;
			objParams[0] = new Boolean(boolVal);
			String calledMethod = (methodName.startsWith("is")) ? methodName
					.replaceFirst("is", "set")
					: getSettermethodName(methodName);
			invokeMethod(sourceObject, calledMethod, objParamTypes, objParams);

		}
	}

	/**
	 * @param methodName
	 * @return
	 */
	private String getAttributeName(String methodName) {
		StringBuffer sb = new StringBuffer();
		if (methodName.startsWith("is")) {
			sb.append(methodName);
		} else {
			sb.append(methodName.substring(3, 4).toLowerCase()).append(
					methodName.substring(4));
		}
		return sb.toString();

	}

	/**
	 * @param methodName
	 * @return
	 */
	private String getSettermethodName(String methodName) {
		StringBuffer sb = new StringBuffer("s");
		sb.append(methodName.substring(1));
		return sb.toString();

	}

	public void printAttributes(List<String> lstAttribute) {
		for (Iterator<String> iter = lstAttribute.iterator(); iter.hasNext();) {
			String attribute = (String) iter.next();
			System.out.println(attribute);
		}
	}

	/**
	 * @return Returns the attributeList.
	 */
	public List<String> getAttributeList() throws Exception {
		if (bAttribuesPopulated) {
			return attributeList;
		} else {
			throw new Exception("Attributes not populated yet");
		}
	}
	//
	// public static void main(String[] args) throws Exception {
	// LoadCSVDataHelper printObject = new LoadCSVDataHelper();
	//
	// String strFile = "C:\\basicAddress.csv";
	//
	// DetermineServiceLocationResultVO[] locationResultVOs
	// =(DetermineServiceLocationResultVO[]) printObject.getCSVData(strFile,
	// DetermineServiceLocationResultVO.class);
	// List lst = printObject.attributeList;
	// for (Iterator attribute = lst.iterator(); attribute.hasNext();) {
	// String element = (String) attribute.next();
	//
	// }
	// List lstAttribute = printObject.getAttributeList();
	// printObject.printAttributes(lstAttribute);
	// System.out.println();
	//
	// }

	/*
	 * protected void finalize() { if (this.objDataFile != null) { try {
	 * this.objDataFile.close(); } catch (Exception ex) { ex.printStackTrace();
	 * } } }
	 */

	// public static void main(String[] args)throws Exception
	// {
	//
	//
	// LoadCustomerDataHelper printObject = new LoadCustomerDataHelper();
	//
	// String strFile =
	// "C:\\Sech_Workspace\\SechSystemProperties\\csv\\CustomerInformation.csv";
	//
	// CustomerSummaryTestVO[] customerSummaryVOs = (CustomerSummaryTestVO[])
	// printObject
	// .getCSVData(strFile, CustomerSummaryTestVO.class);
	// List lst = printObject.attributeList;
	// for (Iterator attribute = lst.iterator(); attribute.hasNext();) {
	// String element = (String) attribute.next();
	//
	// }
	// List lstAttribute = printObject.getAttributeList();
	// printObject.printAttributes(lstAttribute);
	// System.out.println(lstAttribute);
	//
	//
	// }
	//
	// /**
	// * @return
	// */
	// private List getAttributeList() {
	// // TODO Auto-generated method stub
	// return attributeList;
	// }
	//

	/*
	 * private void populateArrayObject(Object sourceObject, Object objectType,
	 * String methodName, String attributeName, DataRow currentDataRow) throws
	 * Exception { }
	 * 
	 * List lst = printObject.attributeList; // for (Iterator attribute =
	 * lst.iterator(); attribute.hasNext();) { // String element = (String)
	 * attribute.next(); // // } // List lstAttribute =
	 * printObject.getAttributeList(); //
	 * printObject.printAttributes(lstAttribute); // System.out.println(); // //
	 * } private void printAttributes(List lstAttribute) { for (Iterator iter =
	 * lstAttribute.iterator(); iter.hasNext();) { String attribute = (String)
	 * iter.next(); } }
	 */
}