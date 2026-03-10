package fieldworks.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fieldworks.exception.CustomException;

@Service
public class LandProcessor {

	@Autowired
	DelaunayTriangulator delaunayTriangulator;

	public File processFiles(MultipartFile clFile, MultipartFile frlFile) throws IOException {
		File xmlFile = null;
		Map<Integer, String[]> clDataDataMap = readCLFile(clFile);
		Map<Integer, String[]> frlDataDataMapDetails = readFRLFile(frlFile);
		if (clDataDataMap.size() != frlDataDataMapDetails.size()) {
			throw CustomException.invalidData(
					"CL and FRL data length mismatch: " + clDataDataMap.size() + " <> " + frlDataDataMapDetails.size());
		}
		Map<String, Object> result = createPointCloud(clDataDataMap, frlDataDataMapDetails);
		List<String> pointCloudList = (List<String>) result.get("pointCloudList");
		List<String> input_ASC_List = (List<String>) result.get("input_ASC_List");
		Map<Integer, String[]> dataMapBoundary = (Map<Integer, String[]>) result.get("dataMapBoundary");
		xmlFile = delaunayTriangulator.processData(pointCloudList, dataMapBoundary);
		return xmlFile;
	}

	private Map<String, Object> createPointCloud(Map<Integer, String[]> clDataDataMap,
			Map<Integer, String[]> frlDataDataMapDetails) {

		Map<String, Object> resultMap = new HashMap<>();
		Integer[] keyArray = clDataDataMap.keySet().toArray(new Integer[0]);
		String offset = "20";

		Map<Integer, String[]> dataMapBoundary = new LinkedHashMap<>();
		List<String> headingAngleList = new ArrayList<>();
		List<String> azimuthAngleList = new ArrayList<>();

		Map<Integer, String[]> dataMap1 = new LinkedHashMap<>();
		Map<Integer, String[]> dataMap2 = new LinkedHashMap<>();
		Map<Integer, String[]> dataMap3 = new LinkedHashMap<>();
		double headingAngleLast = 0.0;
		double azimuthAngleLast = 0.0;

		List<String> errors = new ArrayList<>();
		for (int i = 0; i < keyArray.length; i++) {
			try {
//			System.out.println("iiiiiiiiiiii : " + i);
				String[] str2 = new String[3];
				String[] str = (String[]) clDataDataMap.get(keyArray[i]);
				if (i == keyArray.length - 1) {
					str2 = (String[]) clDataDataMap.get(keyArray[0]);
				} else {
					str2 = (String[]) clDataDataMap.get(keyArray[i + 1]);
				}
				String[] strDetails = (String[]) frlDataDataMapDetails.get(keyArray[i]);

				double x1 = Double.valueOf(str[1]); // indexOfEasting
				double y1 = Double.valueOf(str[2]); // indexOfNorthing
				double x2 = Double.valueOf(str2[1]); // indexOfEasting
				double y2 = Double.valueOf(str2[2]); // indexOfNorthing

				double offsetPoint = Double.valueOf(strDetails[8]); // 8
				double offsetPointL = Double.valueOf(strDetails[1]); // 1
				double slopeLhs = Double.valueOf(strDetails[5].replace("%", "")) / 100; // 5
				double slopeLhs2 = Double.valueOf(strDetails[3].replace("%", "")) / 100; // 3

				double slopeRhs = Double.valueOf(strDetails[12].replace("%", "")) / 100; // 12
				double slopeRhs2 = Double.valueOf(strDetails[10].replace("%", "")) / 100; // 10

				double distanceLhs = Double.valueOf(offset);
				double distanceRhs = Double.valueOf(offset);

				double headingAngle = 0.0;
				double azimuthAngle = 0.0;
				if (i == keyArray.length - 1) {
					headingAngle = headingAngleLast;
					azimuthAngle = azimuthAngleLast;
				} else {
					headingAngle = CalculateAngleInRadians(x1, y1, x2, y2);
					azimuthAngle = CalculateAzimuthAngle(x1, y1, x2, y2);
				}

				headingAngleList.add(String.valueOf(headingAngle));
				azimuthAngleList.add(String.valueOf(azimuthAngle));

				double slopeAngleL1 = calculateAngle(slopeLhs);
				double slopeAngleL2 = calculateAngle(slopeLhs2);

				double slopeAngleR1 = calculateAngle(slopeRhs);
				double slopeAngleR2 = calculateAngle(slopeRhs2);

				double L1X = Math.round((x1 - (offsetPointL * Math.sin(headingAngle + 90 * Math.PI / 180))) * 1000.0)
						/ 1000.0;
				double L1Y = Math.round((y1 - (offsetPointL * Math.cos(headingAngle + 90 * Math.PI / 180))) * 1000.0)
						/ 1000.0;
				double L1Z = Math.round((Double.valueOf(strDetails[2])) * 1000.0) / 1000.0;

				double L2X = Math.round((L1X + (offsetPointL * Math.sin(headingAngle + 90 * Math.PI / 180))) * 1000.0)
						/ 1000.0;
				double L2Y = Math.round((L1Y + (offsetPointL * Math.cos(headingAngle + 90 * Math.PI / 180))) * 1000.0)
						/ 1000.0;
				double L2Z = Math.round((L1Z + offsetPoint * Math.tan(slopeAngleL1)) * 1000.0) / 1000.0;

				double L3X = Math.round((L1X - (distanceLhs * Math.sin(headingAngle + 90 * Math.PI / 180))) * 1000.0)
						/ 1000.0;
				double L3Y = Math.round((L1Y - (distanceLhs * Math.cos(headingAngle + 90 * Math.PI / 180))) * 1000.0)
						/ 1000.0;
				double L3Z = Math.round((L1Z - distanceLhs * Math.tan(slopeAngleL2)) * 1000.0) / 1000.0;

				double R1X = Math.round((x1 + (offsetPoint * Math.sin(headingAngle + 90 * Math.PI / 180))) * 1000.0)
						/ 1000.0;
				double R1Y = Math.round((y1 + (offsetPoint * Math.cos(headingAngle + 90 * Math.PI / 180))) * 1000.0)
						/ 1000.0;
				double R1Z = Math.round((Double.valueOf(strDetails[9])) * 1000.0) / 1000.0;

				double R2X = Math.round((R1X - (offsetPoint * Math.sin(headingAngle + 90 * Math.PI / 180))) * 1000.0)
						/ 1000.0;
				double R2Y = Math.round((R1Y - (offsetPoint * Math.cos(headingAngle + 90 * Math.PI / 180))) * 1000.0)
						/ 1000.0;
				double R2Z = Math.round(((R1Z + offsetPoint * Math.tan(slopeAngleR1))) * 1000.0) / 1000.0;

				double R3X = Math.round((R1X + (distanceRhs * Math.sin(headingAngle + 90 * Math.PI / 180))) * 1000.0)
						/ 1000.0;
				double R3Y = Math.round((R1Y + (distanceRhs * Math.cos(headingAngle + 90 * Math.PI / 180))) * 1000.0)
						/ 1000.0;
				double R3Z = Math.round((R1Z + distanceRhs * Math.tan(slopeAngleR2)) * 1000.0) / 1000.0;

				String[] str11 = new String[6];
				str11[0] = String.valueOf(R1Y);
				str11[1] = String.valueOf(R1X);
				str11[2] = String.valueOf(R1Z);
				str11[3] = String.valueOf(L1Y);
				str11[4] = String.valueOf(L1X);
				str11[5] = String.valueOf(L1Z);
				dataMap1.put(i, str11);

				String[] str12 = new String[6];
				str12[0] = String.valueOf(R2Y);
				str12[1] = String.valueOf(R2X);
				str12[2] = String.valueOf(R2Z);
				str12[3] = String.valueOf(L2Y);
				str12[4] = String.valueOf(L2X);
				str12[5] = String.valueOf(L2Z);
				dataMap2.put(i, str12);

				String[] str13 = new String[6];
				str13[0] = String.valueOf(R3Y);
				str13[1] = String.valueOf(R3X);
				str13[2] = String.valueOf(R3Z);
				str13[3] = String.valueOf(L3Y);
				str13[4] = String.valueOf(L3X);
				str13[5] = String.valueOf(L3Z);
				dataMap3.put(i, str13);

				String[] str4B = new String[6];
				str4B[0] = String.valueOf(R3Y);
				str4B[1] = String.valueOf(R3X);
				str4B[2] = String.valueOf(R3Z);
				str4B[3] = String.valueOf(L3Y);
				str4B[4] = String.valueOf(L3X);
				str4B[5] = String.valueOf(L3Z);
				dataMapBoundary.put(i, str4B);

				resultMap.put("dataMapBoundary", dataMapBoundary);

				if (i == keyArray.length - 2) {
					headingAngleLast = headingAngle;
					azimuthAngleLast = azimuthAngle;
				}

			} catch (Exception e) {
				// Catch any error in numeric conversion, array access, null pointer, etc.
				errors.add("Error at CL chainage " + keyArray[i] + ": " + e.getMessage());
			}

		}

		if (!errors.isEmpty()) {
			// Throw all errors at once
			throw CustomException.invalidData("Point cloud creation errors: " + String.join("; ", errors));
		}

		// Here actually created point clouds
		List<String> pointCloudList = new ArrayList<>();
		List<String> input_ASC_List = new ArrayList<>();

		int k = 0;

		for (Map.Entry<Integer, String[]> entry : dataMap1.entrySet()) {
			int id = entry.getKey();
			String[] values = entry.getValue();
			String[] values2 = dataMap2.get(id);
			String[] values3 = dataMap3.get(id);

			String xmlData25 = String.format("<P id=\"%d\">%s %s %s</P>%n", ++k, values3[1], values3[0], values3[2]);
			String xmlData26 = String.format("<P id=\"%d\">%s %s %s</P>%n", ++k, values3[4], values3[3], values3[5]);
			String xmlData21 = String.format("<P id=\"%d\">%s %s %s</P>%n", ++k, values[1], values[0], values[2]);
			String xmlData22 = String.format("<P id=\"%d\">%s %s %s</P>%n", ++k, values[4], values[3], values[5]);
			String xmlData23 = String.format("<P id=\"%d\">%s %s %s</P>%n", ++k, values2[1], values2[0], values2[2]);
			String xmlData24 = String.format("<P id=\"%d\">%s %s %s</P>%n", ++k, values2[4], values2[3], values2[5]);

			pointCloudList.add(xmlData25);
			pointCloudList.add(xmlData26);
			pointCloudList.add(xmlData21);
			pointCloudList.add(xmlData22);
			pointCloudList.add(xmlData23);
			pointCloudList.add(xmlData24);

			String xmlData5 = String.format("%s;%s;%s%n", values3[1], values3[0], values3[2]);
			String xmlData6 = String.format("%s;%s;%s%n", values3[4], values3[3], values3[5]);
			String xmlData1 = String.format("%s;%s;%s%n", values[1], values[0], values[2]);
			String xmlData2 = String.format("%s;%s;%s%n", values[4], values[3], values[5]);
			String xmlData3 = String.format("%s;%s;%s%n", values2[1], values2[0], values2[2]);
			String xmlData4 = String.format("%s;%s;%s%n", values2[4], values2[3], values2[5]);

			input_ASC_List.add(xmlData5);
			input_ASC_List.add(xmlData6);
			input_ASC_List.add(xmlData1);
			input_ASC_List.add(xmlData2);
			input_ASC_List.add(xmlData3);
			input_ASC_List.add(xmlData4);
		}
		resultMap.put("pointCloudList", pointCloudList);
		resultMap.put("input_ASC_List", input_ASC_List);

		return resultMap;
	}

	// ------------------ READ CL FILE ------------------
	private Map<Integer, String[]> readCLFile(MultipartFile clFile) throws IOException {
		Map<Integer, String[]> clDataMap = new LinkedHashMap<>();
		List<String> errors = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(clFile.getInputStream()))) {
			String line;
			int lineNumber = 0;

			// Skip header
			reader.readLine();
			lineNumber++;

			while ((line = reader.readLine()) != null) {
				lineNumber++;
				if (line.trim().isEmpty())
					continue;

				String[] parts = line.split(",", -1); // include empty fields

				if (parts.length < 3) {
					errors.add("Line " + lineNumber + " has insufficient columns: found " + parts.length
							+ ". Line content: " + line);
					continue;
				}

				int chainage = 0;
				boolean validLine = true;

				try {
					chainage = (int) Double.parseDouble(parts[0].trim());
					Double.parseDouble(parts[1].trim());
					Double.parseDouble(parts[2].trim());
				} catch (NumberFormatException e) {
					errors.add("Line " + lineNumber + " has invalid numeric values. Chainage: " + parts[0]
							+ ", Easting: " + parts[1] + ", Northing: " + parts[2]);
					validLine = false;
				}

				if (validLine) {
					clDataMap.put(chainage, new String[] { parts[0].trim(), parts[1].trim(), parts[2].trim() });
				}
			}
		}

		if (!errors.isEmpty()) {
			// Combine all errors and throw as CustomException
			throw CustomException.invalidData("CL file validation errors: " + String.join("; ", errors));
		}

		return clDataMap;
	}


	// ------------------ READ FRL FILE ------------------
	private Map<Integer, String[]> readFRLFile(MultipartFile frlFile) throws IOException {
		Map<Integer, String[]> frlDataMap = new LinkedHashMap<>();
		List<String> errors = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(frlFile.getInputStream()))) {
			String line;
			int lineNumber = 0;

			// Skip header
			reader.readLine();
			lineNumber++;

			while ((line = reader.readLine()) != null) {
				lineNumber++;
				if (line.trim().isEmpty())
					continue;

				String[] data = line.split(",", -1); // include empty fields
				boolean validLine = true;

				// Check minimum 13 columns
				if (data.length < 13) {
					errors.add("Line " + lineNumber + " has insufficient columns: found " + data.length
							+ ". Line content: " + line);
					continue;
				}

				int chainage = 0;
				try {
					chainage = (int) Double.parseDouble(data[0].trim());
					// Optionally check numeric for Median Offset, Elevation, Slop, Reverse Offset,
					// Reverse Slope
					for (int i = 1; i <= 5; i++) {
						if (!data[i].trim().isEmpty()) {
							Double.parseDouble(data[i].trim());
						}
					}
					for (int i = 7; i <= 12; i++) {
						if (!data[i].trim().isEmpty()) {
							Double.parseDouble(data[i].trim());
						}
					}
				} catch (NumberFormatException e) {
					errors.add("Invalid numeric value at line " + lineNumber + ". Line content: " + line);
					validLine = false;
				}

				if (validLine) {
					// Ensure exactly 13 elements (pad empty if less)
					String[] values = new String[13];
					for (int i = 0; i < 13; i++) {
						values[i] = i < data.length ? data[i].trim() : "";
					}
					frlDataMap.put(chainage, values);
				}
			}
		}

		if (!errors.isEmpty()) {
			// Combine all errors into one message and throw
			throw CustomException.invalidData("FRL file validation errors: " + String.join("; ", errors));
		}
		return frlDataMap;
	}

	// For Azimuth angle
	public static double CalculateAzimuthAngle(double x1, double y1, double x2, double y2) {
		try {
			double angle = Math.atan2(x2 - x1, y2 - y1);
			angle = angle * (180 / Math.PI);
			if (angle < 0) {
				angle = 360 + angle;
			}
			return angle;
		} catch (Exception e) {
			throw CustomException.invalidData("Invalid input for CalculateAzimuthAngle: x1=" + x1 + ", y1=" + y1
					+ ", x2=" + x2 + ", y2=" + y2 + ". " + e.getMessage());
		}
	}

	// Previous CalculateAngleInRadians
	public static double CalculateAngleInRadians(double x1, double y1, double x2, double y2) {
		try {
			double angle = Math.atan2(x2 - x1, y2 - y1);
			angle = angle * (180 / Math.PI);
			if (angle < 0) {
				angle = 360 + angle;
			}
			if (angle >= 360) {
				angle = angle - 360;
			}
			double radians = angle * (Math.PI / 180);
			return radians;
		} catch (Exception e) {
			throw CustomException.invalidData("Invalid input for CalculateAngleInRadians: x1=" + x1 + ", y1=" + y1
					+ ", x2=" + x2 + ", y2=" + y2 + ". " + e.getMessage());
		}
	}

	public static double calculateAngle(double slope) {
		try {
			double angle = Math.atan(slope);
			angle = angle * (180 / Math.PI);
			if (angle < 0) {
				angle = 360 + angle;
			}
			if (angle >= 360) {
				angle = angle - 360;
			}
			double radians = angle * (Math.PI / 180);
			return radians;
		} catch (Exception e) {
			throw CustomException
					.invalidData("Invalid slope value for calculateAngle: " + slope + ". " + e.getMessage());
		}
	}
	
	
	
	
//	delaunayTriangulator
}