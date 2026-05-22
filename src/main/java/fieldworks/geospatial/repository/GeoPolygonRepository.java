package fieldworks.geospatial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fieldworks.geospatial.entity.GeoPolygonEntity;

public interface GeoPolygonRepository extends JpaRepository<GeoPolygonEntity, Long> {
}