package fieldworks.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import fieldworks.exception.CustomException;
import fieldworks.models.Edge;
import fieldworks.models.GeometryUtils;
import fieldworks.models.Point;
import fieldworks.models.Triangle;

@Service
public class DelaunayTriangulator {

	public File processData(List<String> pointCloudList, Map<Integer, String[]> dataMapBoundary) {
		try {
			String folderName = "C:\\opt\\Apogee\\javaProject\\output";
			// Step 1: Create Point List
			List<Point> pointList = createPointList(pointCloudList);
			// Step 2: Create Boundary List
			List<Integer> boundaryList = createBoundaryList(dataMapBoundary, pointList);
			// Step 3: Triangulation
			HashSet<Triangle> triangulation = getTriangulation(pointList);
			// Step 4: Create Faces List
			List<String> facesList = createFacesList(triangulation, boundaryList);
			// Step 5: Write LandXML File
			File xmlFile = createLandXMLFile(pointCloudList, facesList, folderName);
			return xmlFile;
		} catch (CustomException ce) {
			throw ce; // already a user-friendly exception
		} catch (Exception e) {
			throw CustomException.internalError("Error processing Delaunay Triangulation: " + e.getMessage());
		}
	}

	private List<Point> createPointList(List<String> pointCloudList) {

		List<Point> pointList = new ArrayList<>();
		try {
			for (String xmlLine : pointCloudList) {
				xmlLine = xmlLine.trim();
				if (xmlLine.isEmpty())
					continue;

				String idPart = xmlLine.substring(xmlLine.indexOf("id=\"") + 4, xmlLine.indexOf("\">"));
//				int id = Integer.parseInt(idPart);
				int id;
				try {
					id = Integer.parseInt(idPart);
				} catch (NumberFormatException nfe) {
					throw CustomException.invalidData("Invalid Point ID: '" + idPart + "' in line: " + xmlLine);
				}
				// Extract coordinates (between > and </P>)
				String coordPart = xmlLine.substring(xmlLine.indexOf("\">") + 2, xmlLine.indexOf("</P>")).trim();
				String[] coords = coordPart.split("\\s+");
				if (coords.length != 3) {
					throw CustomException
							.invalidData("Invalid coordinate format for point ID " + id + ": " + coordPart);
				}
				try {
					double x = Double.parseDouble(coords[0]);
					double y = Double.parseDouble(coords[1]);
					double z = Double.parseDouble(coords[2]);

					pointList.add(new Point(x, y, z, id));
				} catch (NumberFormatException nfe) {
					throw CustomException
							.invalidData("Non-numeric coordinate for point ID " + id + ": " + Arrays.toString(coords));
				}
			}

			// optional print check
//		System.out.println("Total pointList: " + pointList.size());
//		pointList.forEach(System.out::println);
		} catch (Exception e) {
			throw CustomException.internalError("Error creating point list: " + e.getMessage());
		}
		return pointList;
	}

	private List<Integer> createBoundaryList(Map<Integer, String[]> dataMapBoundary, List<Point> pointList) {
		List<Integer> boundaryList = new ArrayList<>();
		try {
			for (Map.Entry<Integer, String[]> entry : dataMapBoundary.entrySet()) {
				int id = entry.getKey();
				String[] values = entry.getValue();
				int index = 0;
				for (int k = 0; k < 2; k++) {
					for (int i = 0; i < pointList.size(); i++) {
						if (pointList.get(i).getY() == Double.valueOf(values[3])
								&& pointList.get(i).getX() == Double.valueOf(values[4])
								&& pointList.get(i).getZ() == Double.valueOf(values[5])) {
							index = i;
							boundaryList.add(index + 1);
							break;
						}
					}
					for (int i = 0; i < pointList.size(); i++) {
						if (pointList.get(i).getY() == Double.valueOf(values[0])
								&& pointList.get(i).getX() == Double.valueOf(values[1])
								&& pointList.get(i).getZ() == Double.valueOf(values[2])) {
							index = i;
							boundaryList.add(index + 1);
							break;
						}
					}
				}
			}

			// optional print check
//		System.out.println("Total boundaryList: " + boundaryList.size());
//		boundaryList.forEach(System.out::println);
		} catch (Exception e) {
			throw CustomException.internalError("Error creating boundary list: " + e.getMessage());
		}

		return boundaryList;
	}

	private static HashSet<Triangle> getTriangulation(List<Point> pointList) {
		try {
			int triangleID = 1;
			List<Triangle> triangulation = new LinkedList<>();
			double M = getMaximumAbsoluteCoordinate(pointList);
			Triangle superTriangle = new Triangle(new Point(3 * M, 0, 0, -1), // -1
					new Point(0, 3 * M, 0, -2), // -2
					new Point(-3 * M, -3 * M, 0, -3), -1);// -3
			triangulation.add(superTriangle);
			HashSet<Triangle> solution = new HashSet<>();
			for (Point point : pointList) {
//            System.out.println();
//            if (point.id % 10000 == 0)System.out.println("point: " + point);

				HashSet<Edge> edge1stAppearance = new HashSet<>();
				HashSet<Edge> polygon = new HashSet<>();

				Iterator<Triangle> i = triangulation.iterator();
				while (i.hasNext()) {
					Triangle triangle = i.next();
					if (GeometryUtils.inCircle(point, triangle.getP1(), triangle.getP2(), triangle.getP3())) {
						i.remove();
						solution.remove(triangle);
						if (edge1stAppearance.contains(triangle.getE1())) {
							polygon.remove(triangle.getE1());
						} else {// 1st appearance
							edge1stAppearance.add(triangle.getE1());
							polygon.add(triangle.getE1());
						}

						if (edge1stAppearance.contains(triangle.getE2())) {
							polygon.remove(triangle.getE2());
						} else {
							edge1stAppearance.add(triangle.getE2());
							polygon.add(triangle.getE2());
						}

						if (edge1stAppearance.contains(triangle.getE3())) {
							polygon.remove(triangle.getE3());
						} else {
							edge1stAppearance.add(triangle.getE3());
							polygon.add(triangle.getE3());
						}
					}
				}

				for (Edge edge : polygon) {
					Triangle newTriangle = new Triangle(point, edge.getP1(), edge.getP2(), triangleID++);
					triangulation.add(newTriangle);
					if (hasNoSuperTrianglePoint(newTriangle, superTriangle)) {
						solution.add(newTriangle);
					}
				}
			}
			return solution;
		} catch (Exception e) {
			throw CustomException.internalError("Error in Delaunay triangulation: " + e.getMessage());
		}
	}

	private static List<String> createFacesList(HashSet<Triangle> triangles, List<Integer> boundaryList) {
		try {
			ArrayList<String> faceList = new ArrayList<>();

			for (Triangle triangle : triangles) {
				boolean flag = true;
				double diff = 3000;
				boolean isDifferenceGreaterThan3000 = Math
						.abs(triangle.getP1().getId() - triangle.getP2().getId()) > diff
						|| Math.abs(triangle.getP1().getId() - triangle.getP3().getId()) > diff
						|| Math.abs(triangle.getP2().getId() - triangle.getP3().getId()) > diff;

				if (isDifferenceGreaterThan3000) {
					flag = false;
				}

				if (!triangleMatchesIds(triangle, boundaryList)) {
					String facesString = String.format("<F>%d %d %d</F>", triangle.getP1().getId(),
							triangle.getP2().getId(), triangle.getP3().getId());
					if (!faceList.contains(facesString)) {
						faceList.add(facesString);
					}
				}
			}
			return faceList;
		} catch (Exception e) {
			throw CustomException.internalError("Error creating faces list: " + e.getMessage());   
		}
	}

	private File createLandXMLFile(List<String> pointCloudList, List<String> facesList, String folderName) {
		String[] filePathArr = folderName.split(",");
		String mergedFilePath = filePathArr[0] + "landAPG.xml";
		File xmlFile = new File(mergedFilePath);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(xmlFile))) {

			// --- Static XML Header ---
			String staticText = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
					+ "<LandXML xmlns=\"http://www.landxml.org/schema/LandXML-1.2\" "
					+ "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
					+ "xsi:schemaLocation=\"http://www.landxml.org/schema/LandXML-1.2 "
					+ "http://www.landxml.org/schema/LandXML-1.2/LandXML-1.2.xsd\" "
					+ "version=\"1.2\" date=\"2023-10-26\" time=\"10:27:17\" "
					+ "readOnly=\"false\" language=\"English\">\n" + "  <Project name=\"GDS\" desc=\"\">\n"
					+ "    <Feature name=\"projectExt\">\n"
					+ "      <Property label=\"id\" value=\"588661834f68443b8d133a5eeca4457c\" />\n"
					+ "    </Feature>\n" + "  </Project>\n" + "  <Units>\n"
					+ "    <Metric linearUnit=\"meter\" widthUnit=\"meter\" heightUnit=\"meter\" "
					+ "diameterUnit=\"meter\" areaUnit=\"squareMeter\" volumeUnit=\"cubicMeter\" "
					+ "temperatureUnit=\"celsius\" pressureUnit=\"HPA\" angularUnit=\"decimal degrees\" "
					+ "directionUnit=\"decimal degrees\" />\n" + "  </Units>\n"
					+ "  <Application name=\"AMC\" manufacturer=\"Apogee GNSS Pvt Ltd\" "
					+ "version=\"38.0.8371.29903\" timeStamp=\"2023-10-26T10:27:17\">\n"
					+ "    <Author createdBy=\"Apogee\" timeStamp=\"2023-10-26T10:27:17\" />\n" + "  </Application>\n"
					+ "  <CoordinateSystem desc=\"Default\" ogcWktCode=\"PROJCS[&quot;Default&quot;,GEOGCS[&quot;WGS 1984&quot;,DATUM[&quot;WGS 1984&quot;,SPHEROID[&quot;World Geodetic System 1984&quot;,6378137,298.2572229329]],PRIMEM[&quot;Greenwich&quot;,0,AUTHORITY[&quot;EPSG&quot;,&quot;8901&quot;]],UNIT[&quot;Degree&quot;,0.01745329251994,AUTHORITY[&quot;EPSG&quot;,&quot;9102&quot;]],AXIS[&quot;Long&quot;,EAST],AXIS[&quot;Lat&quot;,NORTH]],PROJECTION[&quot;Transverse_Mercator&quot;],PARAMETER[&quot;False_Easting&quot;,620800.124],PARAMETER[&quot;False_Northing&quot;,2835284.345],PARAMETER[&quot;Latitude_Of_Origin&quot;,0],PARAMETER[&quot;Central_Meridian&quot;,0],PARAMETER[&quot;Scale_Factor&quot;,1],UNIT[&quot;Meter&quot;,1,AUTHORITY[&quot;EPSG&quot;,&quot;9001&quot;]],AXIS[&quot;East&quot;,EAST],AXIS[&quot;North&quot;,NORTH]]\" />\n"
					+ "  <Surfaces>\n" + "    <Surface name=\"MCW (Finish)\">\n"
					+ "      <Definition surfType=\"TIN\" elevMax=\"242.4127494\" elevMin=\"207.4260019\">\n"
					+ "        <Pnts>\n";

			writer.write(staticText);

			// --- Write all <P> points from pointCloudList ---
			for (String point : pointCloudList) {
				if (point != null && !point.trim().isEmpty()) {
					writer.write(point.trim());
					writer.newLine();
				}
			}

			// --- Write Faces from memory ---
			writer.write("</Pnts>\n        <Faces>\n");
			for (String face : facesList) {
				writer.write(face.trim());
				writer.newLine();
			}

			// --- Close XML ---
			writer.write("</Faces>\n" + "        <Feature code=\"ApogeeGNSS\">\n"
					+ "          <Property label=\"color\" value=\"128,128,128\" />\n" + "        </Feature>\n"
					+ "      </Definition>\n" + "    </Surface>\n" + "  </Surfaces>\n" + "</LandXML>");
			writer.flush();

			// 🟢 FILE DISK PAR SAVE HO RAHI HAI YAHA
			System.out.println("✅ LandXML file created successfully at: " + xmlFile.getAbsolutePath());

		} catch (IOException e) {
			e.printStackTrace();
		}

		// 🟢 FILE OBJECT RETURN HO RHA HAI YAHA
		return xmlFile;
	}

	// Boundary Method
	private static boolean triangleMatchesIds(Triangle triangle, List<Integer> pointIds) {
		return pointIds.contains(triangle.getP1().getId()) && pointIds.contains(triangle.getP2().getId())
				&& pointIds.contains(triangle.getP3().getId());
	}

	private static boolean hasNoSuperTrianglePoint(Triangle triangle, Triangle superTriangle) {
		return (!triangle.getP1().equals(superTriangle.getP1()) && !triangle.getP1().equals(superTriangle.getP2())
				&& !triangle.getP1().equals(superTriangle.getP3()) && !triangle.getP2().equals(superTriangle.getP1())
				&& !triangle.getP2().equals(superTriangle.getP2()) && !triangle.getP2().equals(superTriangle.getP3())
				&& !triangle.getP3().equals(superTriangle.getP1()) && !triangle.getP3().equals(superTriangle.getP2())
				&& !triangle.getP3().equals(superTriangle.getP3()));
	}

	private static double getMaximumAbsoluteCoordinate(List<Point> pointList) {
		double M = 0.0; // absolute maximum
		for (Point point : pointList) {
			if (Math.abs(point.getX()) > M) {
				M = Math.abs(point.getX());
			}
			if (Math.abs(point.getY()) > M) {
				M = Math.abs(point.getY());
			}
		}
		return M;
	}

}
