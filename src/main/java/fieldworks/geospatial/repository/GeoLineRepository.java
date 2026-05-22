package fieldworks.geospatial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fieldworks.geospatial.entity.GeoLineEntity;

public interface GeoLineRepository extends JpaRepository<GeoLineEntity, Long> {
}