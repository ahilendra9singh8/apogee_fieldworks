#1. What is Geospatial Data?
Definition
Geospatial data means:
Data that is connected to a location on Earth.

This data contains:
-> Coordinates
-> Geometry
-> Position on map

##Simple Example

###Normal Data

| id | road_name |
| -- | --------- |
| 1  | NH44      |

-> This is NOT geospatial because location is missing.

###Geospatial Data

| id | road_name | geometry        |
| -- | --------- | --------------- |
| 1  | NH44      | LINESTRING(...) |

-> Now it becomes geospatial because location exists.


## Real Examples of Geospatial Data

| Real World Object | Geospatial Type |
| ----------------- | --------------- |
| Toll Plaza        | Point           |
| Highway           | Line            |
| Village Boundary  | Polygon         |
| Satellite Image   | Raster          |
| DEM               | Raster          |


## Main Categories of Geospatial Data

Geospatial Data
   ├── Vector Data
   └── Raster Data
   

#2. Vector Data
What is Vector Data?
Vector data stores geographic objects(Road, School etc.) using coordinates.

It represents:
-> Points
-> Lines
-> Polygons


## Characteristics of Vector Data

| Property  | Description       |
| --------- | ----------------- |
| Storage   | Coordinates       |
| Best For  | Roads, boundaries |
| Accuracy  | High              |
| Editable  | Yes               |
| File Size | Small             |


##Types of Vector Data

| Geometry Type | Used For           |
| ------------- | ------------------ |
| Point         | Pole, Survey Point |
| LineString    | Road, River        |
| Polygon       | Village, Land      |


## 2.1: Point Geometry
Definition
Single coordinate location.

Example:

| Real Object   | Example |
| ------------- | ------- |
| Electric Pole | Point   |
| Survey Point  | Point   |
| Tree          | Point   |

###Coordinate Example
POINT(77.21 28.61)

### Table Example
| id | point_name | geometry           |
| -- | ---------- | ------------------ |
| 1  | Pole A     | POINT(77.21 28.61) |


##2.2: LineString Geometry
Connected coordinates forming a line.

###Example:

| Real Object | Example |
| ----------- | ------- |
| Highway     | Line    |
| Pipeline    | Line    |
| River       | Line    |


###Coordinate Example
LINESTRING(
77.1 28.5,
77.2 28.6,
77.3 28.7
)


###Table Example

| id | road_name | geometry        |
| -- | --------- | --------------- |
| 1  | NH44      | LINESTRING(...) |


## 2.3: Polygon Geometry
Closed area boundary.

### Example:

| Real Object      | Example |
| ---------------- | ------- |
| Village Boundary | Polygon |
| Forest Area      | Polygon |
| Land Parcel      | Polygon |


### Coordinate Example
POLYGON((
77 28,
78 28,
78 29,
77 29,
77 28
))


### Table Example
| id | village_name | geometry     |
| -- | ------------ | ------------ |
| 1  | Village A    | POLYGON(...) |


### Vector Data Internally
Vector data mainly contains:

Geometry + Attributes

### Example
| id | road_name | width | geometry   |
| -- | --------- | ----- | ---------- |
| 1  | NH44      | 45    | LINESTRING |


### Geometry stores:
-> shape
-> coordinates
-> location

### Attributes store:
-> name
-> type
-> width
-> metadata


#3. Vector File Formats

##3.1 Shapefile (SHP)
Traditional GIS vector format.

MOST IMPORTANT GIS format.

Important Point

### Shapefile is NOT a single file.
It is a group of files.

### SHP File Structure

roads/
 ├── road.shp
 ├── road.dbf
 ├── road.shx
 ├── road.prj
 └── road.cpg
 
### SHP Components

| File | Purpose            |
| ---- | ------------------ |
| .shp | Geometry           |
| .dbf | Attribute table    |
| .shx | Spatial index      |
| .prj | Projection/CRS     |
| .cpg | Character encoding |


## What data is inside .shp?
Geometry only.

Example:

LINESTRING(...)

## What data is inside .dbf?
Attribute table.

Example:

| id | road_name | width |
| -- | --------- | ----- |
| 1  | NH44      | 45    |


## What data is inside .prj?
Projection information.

Example:

EPSG:4326

## What happens if files are missing?

| Missing File | Result         |
| ------------ | -------------- |
| .dbf         | No attributes  |
| .prj         | Wrong location |
| .shx         | Slow loading   |
 
 
## SHP Advantages

| Advantage            | Reason            |
| -------------------- | ----------------- |
| Popular              | Industry standard |
| Supported everywhere | QGIS, GeoServer   |
| Easy to use          | Simple format     |


## SHP Limitations

| Limitation       | Reason           |
| ---------------- | ---------------- |
| Multiple files   | Hard to manage   |
| Old format       | Limited support  |
| Size limitations | Large data issue |



## 3.2:  GeoJSON
Modern JSON-based GIS format.

Mostly used in:
-> React
-> OpenLayers
-> Leaflet
-> Cesium


### GeoJSON Structure

{
 "type":"FeatureCollection",
 "features":[
   {
     "type":"Feature",
     "geometry":{
       "type":"Point",
       "coordinates":[77.21,28.61]
     },
     "properties":{
       "name":"Point A"
     }
   }
 ]
}


### GeoJSON Components

| Key               | Meaning              |
| ----------------- | -------------------- |
| FeatureCollection | Multiple GIS objects |
| geometry          | Coordinates          |
| properties        | Attributes           |


### GeoJSON Table Representation

| name    | geometry |
| ------- | -------- |
| Point A | POINT    |


### GeoJSON Advantages

| Advantage    | Reason            |
| ------------ | ----------------- |
| Web friendly | Easy frontend use |
| Lightweight  | JSON format       |
| API support  | REST APIs         |


## 3.3: KML
Full Form
Keyhole Markup Language

### Definition
XML-based GIS format.

Used in:
-> Google Earth
-> GPS systems

### KML Structure

<Placemark>
   <name>Point A</name>

   <Point>
      <coordinates>
         77.21,28.61
      </coordinates>
   </Point>
</Placemark>


### KML Components

| Tag         | Meaning    |
| ----------- | ---------- |
| Placemark   | GIS object |
| Point       | Geometry   |
| coordinates | Location   |


### KML Advantages

| Advantage            | Reason        |
| -------------------- | ------------- |
| Google Earth support | Visualization |
| Human readable       | XML           |


## 3.4: GML
Full Form
Geography Markup Language

### Definition
OGC standard XML GIS format.

### GML Example:

<gml:Point>
   <gml:coordinates>
      77.21,28.61
   </gml:coordinates>
</gml:Point>


### GML Use
-> GIS data exchange
-> OGC services
-> WFS responses


## 3.5 CSV
Simple table file.

Coordinates columns required for GIS.

### CSV Example

id,name,lat,lon
1,PointA,28.61,77.21

### CSV Table

| id | name   | lat   | lon   |
| -- | ------ | ----- | ----- |
| 1  | PointA | 28.61 | 77.21 |


### CSV in GIS
QGIS converts:

lat + lon → Point Geometry


## 3.6 DXF
CAD(Computer-Aided Design) drawing format.

Used in:
-> AutoCAD (CAD software)
-> Civil engineering


### DXF Contains

| Object Type | Example        |
| ----------- | -------------- |
| Line        | Road alignment |
| Polyline    | Drain          |
| Text        | Labels         |


## NHAI Use Cases
-> Road design
-> Utility mapping
-> Cross section drawings



#4. Raster Data

What is Raster Data?
Raster data stores information as pixels/grid cells.

Like images.

### Raster Structure
100 x 100 grid cells

Each cell contains value.

### Raster Example

| Pixel | Value         |
| ----- | ------------- |
| 1     | Elevation 120 |
| 2     | Elevation 121 |


### Raster Characteristics

| Property  | Description    |
| --------- | -------------- |
| Storage   | Pixels         |
| Best For  | Satellite, DEM |
| File Size | Large          |
| Editable  | Limited        |


### Raster File Formats

| File Type | Purpose            |
| --------- | ------------------ |
| TIFF      | Image              |
| GeoTIFF   | Geo image          |
| IMG       | ERDAS raster       |
| ECW       | Compressed imagery |


## 4.1 GeoTIFF
Raster image with coordinate information.

### GeoTIFF Contains

| Data        | Included |
| ----------- | -------- |
| Image       | Yes      |
| Coordinates | Yes      |
| CRS         | Yes      |


###GeoTIFF Use
-> Satellite imagery
-> DEM
-> Drone images

##4.2 DEM (Digital Elevation Model)
Raster containing elevation values.

### DEM Table Example

| X    | Y    | Elevation |
| ---- | ---- | --------- |
| 77.1 | 28.5 | 120m      |


### DEM Use Cases

| Use            | Example           |
| -------------- | ----------------- |
| Slope analysis | Highway           |
| Contours       | Terrain           |
| Cut/Fill       | Road construction |


## 4.3: TIN (Triangulated Irregular Network)
Terrain surface represented using triangles.

### TIN Structure

Point ---- Point
   \       /
     Triangle
     

     
### TIN Use Cases

| Use              | Example           |
| ---------------- | ----------------- |
| Terrain modeling | Civil engineering |
| Surface analysis | Highway design    |



# 5. Geometry Column
Special spatial column in PostGIS.

Stores geometry coordinates.

### Example Table

| id | road_name | geom            |
| -- | --------- | --------------- |
| 1  | NH44      | LINESTRING(...) |


### Geometry Column Example
geom geometry(LineString,4326)

### Meaning

| Part       | Meaning          |
| ---------- | ---------------- |
| geom       | Column name      |
| geometry   | Spatial datatype |
| LineString | Geometry type    |
| 4326       | SRID             |


##Geometry Examples
###Point
POINT(77.21 28.61) 

###Line
LINESTRING(...)

###Polygon
POLYGON(...)


##Geometry Column Use Cases

| Use                  | Example      |
| -------------------- | ------------ |
| Map rendering        | GeoServer    |
| Spatial queries      | PostGIS      |
| Distance calculation | GIS analysis |


#6. CRS and SRID
##6.1: CRS

Coordinate Reference System.

Defines earth coordinate system.

### Common CRS

| CRS       | Use         |
| --------- | ----------- |
| EPSG:4326 | GPS         |
| EPSG:3857 | Google Maps |
| UTM       | Survey      |


##6.2: SRID

Spatial Reference ID.

###Example:
4326

means WGS84 coordinate system.


#7. Complete GIS Workflow


Survey Data
   ↓
CSV / SHP / DXF / DEM
   ↓
QGIS Processing
   ↓
PostGIS Storage
   ↓
GeoServer Publishing
   ↓
WMS / WFS
   ↓
Frontend Map


# 9. Important Interview Questions

Q1. What is geospatial data?

Location-based data connected to earth coordinates.

Q2. Difference between vector and raster?
Vector	Raster
Coordinates	Pixels
Roads	Satellite
Q3. Is shapefile a single file?

No. Multiple related files.

Q4. What is stored in .dbf?

Attributes table.

Q5. What is GeoJSON used for?

Web GIS and frontend mapping.

Q6. What is DEM?

Digital Elevation Model.

Q7. What is geometry column?

Spatial coordinate column in PostGIS.

Q8. What is CRS?

Coordinate system for map positioning.

Q9. What is SRID?

Identifier of CRS system.

Q10. What is GeoTIFF?

Raster image with geographic coordinates.