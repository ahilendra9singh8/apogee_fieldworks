package fieldworks.geospatial.services;

import fieldworks.geospatial.entity.GeoPointEntity;
import fieldworks.geospatial.repository.GeoPointRepository;

import lombok.RequiredArgsConstructor;

import org.geotools.api.data.DataStore;
import org.geotools.api.data.DataStoreFinder;
import org.geotools.api.data.FeatureSource;
import org.geotools.api.feature.simple.SimpleFeature;
//import org.geotools.data.DataStore;
//import org.geotools.data.DataStoreFinder;
//import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureIterator;
import org.geotools.geojson.geom.GeometryJSON;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

//import org.opengis.feature.simple.SimpleFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import fieldworks.geospatial.entity.GeoLineEntity;
import fieldworks.geospatial.entity.GeoPolygonEntity;

import fieldworks.geospatial.repository.GeoLineRepository;
import fieldworks.geospatial.repository.GeoPolygonRepository;

//import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiLineString;

import org.locationtech.jts.geom.MultiPolygon;

@Service
@RequiredArgsConstructor
public class GeoSpatialService {

	@Autowired
	GeoPointRepository pointRepository;

	@Autowired
	GeoLineRepository lineRepository;

	@Autowired
	GeoPolygonRepository polygonRepository;

	public String uploadPointShapeFile(MultipartFile file) throws Exception {

		File dir = Files.createTempDirectory("shape").toFile();

		unzip(file, dir);

		File shpFile = null;

		for (File f : dir.listFiles()) {
			if (f.getName().endsWith(".shp")) {
				shpFile = f;
				break;
			}
		}

		if (shpFile == null) {
			throw new RuntimeException("SHP file not found");
		}

		Map<String, Object> map = new HashMap<>();
		map.put("url", shpFile.toURI().toURL());

		DataStore dataStore = DataStoreFinder.getDataStore(map);

		String typeName = dataStore.getTypeNames()[0];

		FeatureSource<?, ?> source = dataStore.getFeatureSource(typeName);

		FeatureIterator<?> iterator = source.getFeatures().features();

		while (iterator.hasNext()) {

			SimpleFeature feature = (SimpleFeature) iterator.next();

			Geometry geometry = (Geometry) feature.getDefaultGeometry();

			GeoPointEntity entity = new GeoPointEntity();

			entity.setName(feature.getAttribute("name").toString());

			entity.setVillage(feature.getAttribute("village").toString());

			entity.setGeom((Point) geometry);

			pointRepository.save(entity);
		}

		iterator.close();

		return "Point ShapeFile Uploaded Successfully";
	}

	public String uploadLineShapeFile(MultipartFile file) throws Exception {

		File dir = Files.createTempDirectory("shape_line").toFile();

		unzip(file, dir);

		File shpFile = null;

		for (File f : dir.listFiles()) {

			if (f.getName().endsWith(".shp")) {
				shpFile = f;
				break;
			}
		}

		if (shpFile == null) {
			throw new RuntimeException("SHP file not found");
		}

		Map<String, Object> map = new HashMap<>();

		map.put("url", shpFile.toURI().toURL());

		DataStore dataStore = DataStoreFinder.getDataStore(map);

		String typeName = dataStore.getTypeNames()[0];

		FeatureSource<?, ?> source = dataStore.getFeatureSource(typeName);

		FeatureIterator<?> iterator = source.getFeatures().features();

		while (iterator.hasNext()) {

			SimpleFeature feature = (SimpleFeature) iterator.next();

//			Geometry geometry = (Geometry) feature.getDefaultGeometry();
//
//			GeoLineEntity entity = new GeoLineEntity();
//
//			entity.setName(feature.getAttribute("name").toString());
//
////			entity.setRoadType(feature.getAttribute("road_type").toString());
//			entity.setRoadType("NHAI");
//
////			entity.setGeom((LineString) geometry);
//			entity.setGeom((MultiLineString) geometry);
//
//			lineRepository.save(entity);

			Geometry geometry = (Geometry) feature.getDefaultGeometry();

			System.out.println(geometry.getGeometryType());

			GeoLineEntity entity = new GeoLineEntity();

			entity.setName(feature.getAttribute("name").toString());

			entity.setRoadType("NHAI");

			entity.setGeom((MultiLineString) geometry);

			lineRepository.save(entity);
		}

		iterator.close();

		return "Line ShapeFile Uploaded Successfully";
	}

	public String uploadPolygonShapeFile(MultipartFile file) throws Exception {

		File dir = Files.createTempDirectory("shape_polygon").toFile();

		unzip(file, dir);

		File shpFile = null;

		for (File f : dir.listFiles()) {

			if (f.getName().endsWith(".shp")) {
				shpFile = f;
				break;
			}
		}

		if (shpFile == null) {
			throw new RuntimeException("SHP file not found");
		}

		Map<String, Object> map = new HashMap<>();

		map.put("url", shpFile.toURI().toURL());

		DataStore dataStore = DataStoreFinder.getDataStore(map);

		String typeName = dataStore.getTypeNames()[0];

		FeatureSource<?, ?> source = dataStore.getFeatureSource(typeName);

		FeatureIterator<?> iterator = source.getFeatures().features();

		while (iterator.hasNext()) {

			SimpleFeature feature = (SimpleFeature) iterator.next();

			Geometry geometry = (Geometry) feature.getDefaultGeometry();

			GeoPolygonEntity entity = new GeoPolygonEntity();

			entity.setName(feature.getAttribute("name").toString());

			entity.setVillage(feature.getAttribute("village").toString());

			entity.setGeom((MultiPolygon) geometry);

			polygonRepository.save(entity);
		}

		iterator.close();

		return "Polygon ShapeFile Uploaded Successfully";
	}

	private void unzip(MultipartFile zipFile, File destDir) throws Exception {

		ZipInputStream zis = new ZipInputStream(zipFile.getInputStream());

		ZipEntry entry;

		while ((entry = zis.getNextEntry()) != null) {

			File newFile = new File(destDir, entry.getName());

			FileOutputStream fos = new FileOutputStream(newFile);

			byte[] buffer = new byte[1024];

			int len;

			while ((len = zis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}

			fos.close();
		}

		zis.closeEntry();
		zis.close();
	}

	public String uploadPointGeoJson(MultipartFile file) throws Exception {

		String geoJson = new String(file.getBytes());

		ObjectMapper mapper = new ObjectMapper();

		JsonNode root = mapper.readTree(geoJson);

		JsonNode features = root.get("features");

		GeometryJSON geometryJSON = new GeometryJSON();

		for (JsonNode feature : features) {

			JsonNode properties = feature.get("properties");

			JsonNode geometryNode = feature.get("geometry");

			String geometryString = geometryNode.toString();

			Geometry geometry = geometryJSON.read(geometryString);

			GeoPointEntity entity = new GeoPointEntity();

			entity.setName(properties.get("name").asText());

			entity.setVillage(properties.get("village").asText());

			entity.setGeom((Point) geometry);

			pointRepository.save(entity);
		}

		return "GeoJSON Uploaded Successfully";
	}
}