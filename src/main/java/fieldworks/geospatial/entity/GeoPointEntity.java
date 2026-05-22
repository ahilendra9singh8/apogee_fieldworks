package fieldworks.geospatial.entity;

import org.locationtech.jts.geom.Point;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "geo_point")
//@Getter
//@Setter
public class GeoPointEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String village;

	@Column(columnDefinition = "geometry(Point,4326)")
	private Point geom;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public Point getGeom() {
		return geom;
	}

	public void setGeom(Point geom) {
		this.geom = geom;
	}

	public GeoPointEntity(Long id, String name, String village, Point geom) {
		super();
		this.id = id;
		this.name = name;
		this.village = village;
		this.geom = geom;
	}

	public GeoPointEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}

//Important

//Point geom;
//
//JTS geometry use ho raha hai.