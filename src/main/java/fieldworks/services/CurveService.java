//package fieldworks.services;
//
//import org.springframework.stereotype.Service;
//import fieldworks.dto.CurvePointDTO;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class CurveService {
//
//    public List<List<CurvePointDTO>> generateMultiCurves() {
//        List<CurvePointDTO> main = generateMainCurve();
//        double[] offsets = {-40, -20, 20, 40}; // meters: left & right offsets
//        List<List<CurvePointDTO>> allCurves = new ArrayList<>();
//
//        allCurves.add(main); // main line first
//        for (double offset : offsets) {
//            List<CurvePointDTO> curve = generateAlignedOffsetCurve(main, offset);
//            allCurves.add(curve);
//        }
//        return allCurves;
//    }
//
//    // --- MAIN CURVE ---
//    private List<CurvePointDTO> generateMainCurve() {
//        List<CurvePointDTO> list = new ArrayList<>();
//        double startLat = 28.6000;
//        double startLon = 77.1000;
//        double cumulative = 0.0;
//        double prevLat = startLat, prevLon = startLon;
//
//        list.add(new CurvePointDTO(prevLon, prevLat, 0.0));
//
//        for (int i = 1; i <= 200; i++) {
//            double lon = startLon + (i * 0.002);
//            double lat = startLat + Math.sin(i * 0.25) * 0.015;
//
//            double dx = lon - prevLon;
//            double dy = lat - prevLat;
//            double meterFactor = 111000.0;
//            double seg = Math.sqrt(dx * dx + dy * dy) * meterFactor;
//            cumulative += seg;
//
//            list.add(new CurvePointDTO(lon, lat, cumulative));
//            prevLat = lat;
//            prevLon = lon;
//        }
//        return list;
//    }
//
//    // --- OFFSET CURVE (direction-aligned) ---
//    private List<CurvePointDTO> generateAlignedOffsetCurve(List<CurvePointDTO> base, double offsetMeters) {
//        List<CurvePointDTO> offsetList = new ArrayList<>();
//        double meterPerDeg = 111000.0;
//
//        int n = base.size();
//        if (n < 2) return offsetList;
//
//        // first and last tangent direction from main curve
//        double startDx = base.get(1).getEasting() - base.get(0).getEasting();
//        double startDy = base.get(1).getNorthing() - base.get(0).getNorthing();
//        double endDx = base.get(n - 1).getEasting() - base.get(n - 2).getEasting();
//        double endDy = base.get(n - 1).getNorthing() - base.get(n - 2).getNorthing();
//
//        double startLen = Math.sqrt(startDx * startDx + startDy * startDy);
//        double endLen = Math.sqrt(endDx * endDx + endDy * endDy);
//        startDx /= startLen;
//        startDy /= startLen;
//        endDx /= endLen;
//        endDy /= endLen;
//
//        // calculate offset for each point (smooth)
//        for (int i = 0; i < n; i++) {
//            double lon = base.get(i).getEasting();
//            double lat = base.get(i).getNorthing();
//
//            double prevLon = (i == 0) ? base.get(i).getEasting() : base.get(i - 1).getEasting();
//            double prevLat = (i == 0) ? base.get(i).getNorthing() : base.get(i - 1).getNorthing();
//            double nextLon = (i == n - 1) ? base.get(i).getEasting() : base.get(i + 1).getEasting();
//            double nextLat = (i == n - 1) ? base.get(i).getNorthing() : base.get(i + 1).getNorthing();
//
//            double dx = nextLon - prevLon;
//            double dy = nextLat - prevLat;
//            double len = Math.sqrt(dx * dx + dy * dy);
//            if (len == 0) len = 1e-9;
//            dx /= len;
//            dy /= len;
//
//            // perpendicular (normal)
//            double nx = -dy;
//            double ny = dx;
//
//            double offsetLat = lat + (ny * offsetMeters / meterPerDeg);
//            double offsetLon = lon + (nx * offsetMeters / (meterPerDeg * Math.cos(Math.toRadians(lat))));
//
//            offsetList.add(new CurvePointDTO(offsetLon, offsetLat, base.get(i).getChainage()));
//        }
//
//        // align start & end exactly to main curve tangent
//        alignCurveEnds(offsetList, base, offsetMeters, startDx, startDy, endDx, endDy);
//        return offsetList;
//    }
//
//    // --- END ALIGNMENT ---
//    private void alignCurveEnds(List<CurvePointDTO> offsetList, List<CurvePointDTO> main, double offsetMeters,
//                                double startDx, double startDy, double endDx, double endDy) {
//        double meterPerDeg = 111000.0;
//
//        // main start & end points
//        CurvePointDTO mainStart = main.get(0);
//        CurvePointDTO mainEnd = main.get(main.size() - 1);
//
//        // offset start direction
//        double nx1 = -startDy;
//        double ny1 = startDx;
//        double startLat = mainStart.getNorthing() + (ny1 * offsetMeters / meterPerDeg);
//        double startLon = mainStart.getEasting() + (nx1 * offsetMeters / (meterPerDeg * Math.cos(Math.toRadians(mainStart.getNorthing()))));
//
//        // offset end direction
//        double nx2 = -endDy;
//        double ny2 = endDx;
//        double endLat = mainEnd.getNorthing() + (ny2 * offsetMeters / meterPerDeg);
//        double endLon = mainEnd.getEasting() + (nx2 * offsetMeters / (meterPerDeg * Math.cos(Math.toRadians(mainEnd.getNorthing()))));
//
//        // replace first & last point with aligned projection
//        offsetList.set(0, new CurvePointDTO(startLon, startLat, main.get(0).getChainage()));
//        offsetList.set(offsetList.size() - 1,
//                new CurvePointDTO(endLon, endLat, main.get(main.size() - 1).getChainage()));
//    }
//}

// with file



//Testing 2
package fieldworks.services;

import fieldworks.dto.CurvePointDTO;
import org.locationtech.proj4j.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

@Service
public class CurveService {

	private static final boolean IS_NORTH = true;

	private final CRSFactory crsFactory = new CRSFactory();
	private final CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();

	// ✅ Use direct PROJ parameters (no EPSG file lookup)
	private final CoordinateReferenceSystem wgs84 = crsFactory.createFromParameters("WGS84",
			"+proj=longlat +datum=WGS84 +no_defs");

	private final CoordinateReferenceSystem utm44n = crsFactory.createFromParameters("UTM44N",
			"+proj=utm +zone=44 +datum=WGS84 +units=m +no_defs");

	/**
	 * Reads UTM coordinates from file and generates main + offset curves.
	 */
	public List<List<CurvePointDTO>> generateMultiCurves() {
		String path = "C:\\Users\\user\\Downloads\\curveFile";
		List<double[]> rawUTM = readRawUTMFromFile(path);
		if (rawUTM.isEmpty()) {
			System.err.println("⚠️ No points found in file: " + path);
			return Collections.emptyList();
		}

		// Define transforms
		CoordinateTransform utmToWgs = ctFactory.createTransform(utm44n, wgs84);
		CoordinateTransform wgsToUtm = ctFactory.createTransform(wgs84, utm44n);

		// ✅ Convert UTM → LatLon (correct axis order)
		List<CurvePointDTO> main = new ArrayList<>();
		for (double[] u : rawUTM) {
			double easting = u[0];
			double northing = u[1];
			double chainage = u[2];

			ProjCoordinate utm = new ProjCoordinate(easting, northing);
			ProjCoordinate latlon = new ProjCoordinate();
			utmToWgs.transform(utm, latlon);

			// In Proj4J, latlon.x = longitude, latlon.y = latitude
			main.add(new CurvePointDTO(latlon.x, latlon.y, chainage));
		}

		// Debug output
		System.out.println("Main curve loaded points (first 5) [lat,lon,chainage]:");
		main.stream().limit(5).forEach(p -> System.out.printf("Lat: %.6f, Lon: %.6f, Chainage: %.2f%n", p.getNorthing(),
				p.getEasting(), p.getChainage()));

		// Generate offset curves
		List<Double> offsetList = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			offsetList.add(-i * 10.0); // Left side offsets: -10, -20, ... -100
			offsetList.add(i * 10.0); // Right side offsets: 10, 20, ... 100
		}
		offsetList.sort(Double::compare); // Optional: ensures left-to-right order

		List<List<CurvePointDTO>> allCurves = new ArrayList<>();
		allCurves.add(main); // main red line

		for (double offset : offsetList) {
			allCurves.add(generateOffsetCurveProj4(main, offset, wgsToUtm, utmToWgs));
		}

		System.out.println("✅ Total curves generated: " + allCurves.size());
		return allCurves;
	}

	/**
	 * Reads UTM coordinates (E, N, Chainage) from LINESTRING ZM file.
	 */
	private List<double[]> readRawUTMFromFile(String filePath) {
		List<double[]> result = new ArrayList<>();
		try {
			String content = Files.readString(Paths.get(filePath)).trim();
			Matcher m = Pattern.compile("LINESTRING ZM\\s*\\(([^)]+)\\)", Pattern.CASE_INSENSITIVE).matcher(content);
			if (m.find()) {
				String[] points = m.group(1).split(",");
				for (String pt : points) {
					String[] vals = pt.trim().split("\\s+");
					if (vals.length >= 4) {
						double easting = Double.parseDouble(vals[0]);
						double northing = Double.parseDouble(vals[1]);
						double chainage = Double.parseDouble(vals[3]);
						result.add(new double[] { easting, northing, chainage });
					}
				}
			} else {
				System.err.println("LINESTRING ZM not found in file: " + filePath);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * Generate offset curve using Proj4J for accurate UTM math.
	 */
	private List<CurvePointDTO> generateOffsetCurveProj4(List<CurvePointDTO> base, double offsetMeters,
			CoordinateTransform wgsToUtm, CoordinateTransform utmToWgs) {
		List<CurvePointDTO> out = new ArrayList<>();
		int n = base.size();
		if (n < 2)
			return out;

		ProjCoordinate c1 = new ProjCoordinate();
		ProjCoordinate c2 = new ProjCoordinate();
		ProjCoordinate utm1 = new ProjCoordinate();
		ProjCoordinate utm2 = new ProjCoordinate();

		for (int i = 0; i < n - 1; i++) {
			CurvePointDTO p1 = base.get(i);
			CurvePointDTO p2 = base.get(i + 1);

			// longitude → x, latitude → y
			c1.x = p1.getEasting();
			c1.y = p1.getNorthing();
			c2.x = p2.getEasting();
			c2.y = p2.getNorthing();

			wgsToUtm.transform(c1, utm1);
			wgsToUtm.transform(c2, utm2);

			double dx = utm2.x - utm1.x;
			double dy = utm2.y - utm1.y;
			double len = Math.hypot(dx, dy);
			if (len == 0)
				len = 1e-9;

			// Perpendicular unit vector
			double nx = -dy / len;
			double ny = dx / len;

			// Offset UTM point
			double offX = utm1.x + nx * offsetMeters;
			double offY = utm1.y + ny * offsetMeters;

			// Convert back to lat/lon
			ProjCoordinate offLatLon = new ProjCoordinate();
			utmToWgs.transform(new ProjCoordinate(offX, offY), offLatLon);

			out.add(new CurvePointDTO(offLatLon.x, offLatLon.y, p1.getChainage()));
		}

		return out;
	}
}
