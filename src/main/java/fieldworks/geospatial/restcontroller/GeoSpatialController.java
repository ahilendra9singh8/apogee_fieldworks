package fieldworks.geospatial.restcontroller;

import fieldworks.geospatial.services.GeoSpatialService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/geospatial")
@RequiredArgsConstructor
public class GeoSpatialController {

	@Autowired
	GeoSpatialService service;

	@PostMapping("/upload/point")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?> uploadPoint(@RequestParam("file") MultipartFile file) throws Exception {

		return ResponseEntity.ok(service.uploadPointShapeFile(file));
	}

	@PostMapping("/upload/line")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?> uploadLine(@RequestParam("file") MultipartFile file) throws Exception {

		return ResponseEntity.ok(service.uploadLineShapeFile(file));
	}

	@PostMapping("/upload/polygon")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?> uploadPolygon(@RequestParam("file") MultipartFile file) throws Exception {

		return ResponseEntity.ok(service.uploadPolygonShapeFile(file));
	}

	@PostMapping("/upload/geojson/point")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?> uploadGeoJsonPoint(@RequestParam("file") MultipartFile file) throws Exception {

		return ResponseEntity.ok(service.uploadPointGeoJson(file));
	}
}
