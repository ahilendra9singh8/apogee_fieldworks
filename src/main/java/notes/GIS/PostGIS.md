# PostGIS Complete Structured Notes

#1. What is PostGIS?
PostGIS is a spatial extension of PostgreSQL.

It converts PostgreSQL into a GIS database.

## 1.1: What PostgreSQL Normally Stores

| id | name |
| -- | ---- |
| 1  | NH44 |

Only normal data.

## : What PostGIS Stores

| id | road_name | geometry        |
| -- | --------- | --------------- |
| 1  | NH44      | LINESTRING(...) |


Now database can store:
-> coordinates
-> roads
-> polygons
-> GIS objects

## 1.3: What PostGIS Supports

| Feature         | Example      |
| --------------- | ------------ |
| Point           | Survey point |
| Line            | Highway      |
| Polygon         | Village      |
| Spatial Queries | Distance     |
| Raster          | DEM          |
| GeoJSON         | Web GIS      |


# 2. Important Geospatial Concepts

## 2.1: Geometry
Spatial shape/object.

| Geometry Type | Example |
| ------------- | ------- |
| Point         | Pole    |
| LineString    | Road    |
| Polygon       | Village |


##2.2: SRID
Spatial Reference ID.

Defines coordinate system.

Example
4326

means:
WGS84 Latitude/Longitude

##2.3: Geometry Column
Special PostGIS column storing coordinates.

Example
geom geometry(LineString,4326)

###Meaning
| Part       | Meaning          |
| ---------- | ---------------- |
| geom       | Column name      |
| geometry   | Spatial datatype |
| LineString | Geometry type    |
| 4326       | SRID             |




#3. Creating Spatial Tables

##3.1: Create Survey Point Table

CREATE TABLE survey_points(
    id SERIAL PRIMARY KEY,
    point_name VARCHAR(100),
    geom geometry(Point,4326)
);

### Explanation

| Part                 | Meaning              |
| -------------------- | -------------------- |
| SERIAL               | Auto increment       |
| point_name           | Point name           |
| geometry(Point,4326) | Spatial point column |


##3.2: Table Structure

| id | point_name | geom       |
| -- | ---------- | ---------- |
| 1  | Point A    | POINT(...) |

##3.3: Create Road Table

CREATE TABLE road_network(
    id SERIAL PRIMARY KEY,
    road_name VARCHAR(100),
    geom geometry(LineString,4326)
);

### Road Table Structure

| id | road_name | geom            |
| -- | --------- | --------------- |
| 1  | NH44      | LINESTRING(...) |


##3.4: Create Village Polygon Table

CREATE TABLE village_boundary(
    id SERIAL PRIMARY KEY,
    village_name VARCHAR(100),
    geom geometry(Polygon,4326)
);

###Polygon Table Structure

| id | village_name | geom         |
| -- | ------------ | ------------ |
| 1  | Village A    | POLYGON(...) |



#4. Inserting Spatial Data

##4.1: Insert Point Data

INSERT INTO survey_points(point_name, geom)
VALUES(
    'Point A',
    ST_GeomFromText(
        'POINT(77.21 28.61)',
        4326
    )
);

###Query Explanation

| Function        | Meaning                  |
| --------------- | ------------------------ |
| ST_GeomFromText | Converts WKT to geometry |
| POINT           | Geometry type            |
| 4326            | SRID                     |


### Table After Insert

| id | point_name | geom               |
| -- | ---------- | ------------------ |
| 1  | Point A    | POINT(77.21 28.61) |


#4.2: Insert LineString

INSERT INTO road_network(road_name, geom)
VALUES(
    'NH44',
    ST_GeomFromText(
        'LINESTRING(
            77.1 28.5,
            77.2 28.6,
            77.3 28.7
        )',
        4326
    )
);


### Table After Insert

| id | road_name | geom            |
| -- | --------- | --------------- |
| 1  | NH44      | LINESTRING(...) |


##4.3: Insert Polygon

INSERT INTO village_boundary(village_name, geom)
VALUES(
    'Village A',
    ST_GeomFromText(
        'POLYGON((
            77 28,
            78 28,
            78 29,
            77 29,
            77 28
        ))',
        4326
    )
);

### Table After Insert

| id | village_name | geom         |
| -- | ------------ | ------------ |
| 1  | Village A    | POLYGON(...) |



#5. Important PostGIS Functions

##5.1: ST_AsText()
Purpose
Geometry ko readable text me convert karta hai.

SELECT
    id,
    ST_AsText(geom)
FROM survey_points;


###Output:
| id | st_astext          |
| -- | ------------------ |
| 1  | POINT(77.21 28.61) |


###Explanation

| Function  | Meaning                  |
| --------- | ------------------------ |
| ST_AsText | Geometry → readable text |


##5.2: ST_AsGeoJSON()
Purpose
Geometry ko GeoJSON me convert karta hai.

Used in:
-> React
-> OpenLayers
-> Leaflet

###Query

SELECT
    id,
    ST_AsGeoJSON(geom)
FROM survey_points;


###Output

{
 "type":"Point",
 "coordinates":[77.21,28.61]
}

###Explanation

| Function     | Meaning            |
| ------------ | ------------------ |
| ST_AsGeoJSON | Geometry → GeoJSON |


##5.3: ST_Distance()
Purpose
2 geometries ke beech distance calculate karta hai.

###Add More Data
Table

| id | point_name | geom               |
| -- | ---------- | ------------------ |
| 1  | Point A    | POINT(77.21 28.61) |
| 2  | Point B    | POINT(77.22 28.62) |


###Query

SELECT
    ST_Distance(a.geom,b.geom)
FROM survey_points a,
     survey_points b
WHERE a.id = 1
AND b.id = 2;

###Output
0.014

###Explanation

| Part        | Meaning           |
| ----------- | ----------------- |
| a.geom      | First geometry    |
| b.geom      | Second geometry   |
| ST_Distance | Distance function |



##Important Note
Geometry distance usually returns:
degree units

###Accurate Meter Distance

SELECT
ST_Distance(
    a.geom::geography,
    b.geom::geography
)
FROM survey_points a,
     survey_points b
WHERE a.id = 1
AND b.id = 2;

###Explanation

| Part        | Meaning               |
| ----------- | --------------------- |
| ::geography | Earth curvature based |
| ST_Distance | Meter distance        |


##5.4: ST_Buffer()
Purpose
Geometry ke around area create karta hai.

Query
SELECT
    ST_Buffer(
        geom::geography,
        100
    )
FROM road_network;

###Meaning
100 meter corridor around road

###Output Concept
Road
========

100m area around it


###Real Use Cases

| Use              | Example       |
| ---------------- | ------------- |
| Highway corridor | 100m          |
| Impact zone      | Pollution     |
| Land acquisition | Road widening |


#5.5: ST_Intersects()
Purpose
Checks if two geometries overlap/intersect.

###Query

SELECT
    r.road_name,
    v.village_name
FROM road_network r
JOIN village_boundary v
ON ST_Intersects(
    r.geom,
    v.geom
);

###Output

| road_name | village_name |
| --------- | ------------ |
| NH44      | Village A    |

###Explanation

| Part          | Meaning          |
| ------------- | ---------------- |
| ST_Intersects | Checks overlap   |
| r.geom        | Road geometry    |
| v.geom        | Village geometry |

###Real Use Cases

| Example                  |
| ------------------------ |
| Highway crossing village |
| River crossing road      |

##5.6 ST_Contains()
Purpose
Checks if polygon contains geometry.

###Query

SELECT
    p.point_name
FROM survey_points p
JOIN village_boundary v
ON ST_Contains(
    v.geom,
    p.geom
);

###Output

| point_name |
| ---------- |
| Point A    |

###Meaning
Village polygon contains point

##5.7 ST_Within()
Purpose
Opposite of ST_Contains.

###Query

SELECT
    p.point_name
FROM survey_points p
JOIN village_boundary v
ON ST_Within(
    p.geom,
    v.geom
);

###Meaning
Point is inside polygon

##5.8 ST_Length()
Purpose
Line length calculation.

###Query
SELECT
    road_name,
    ST_Length(
        geom::geography
    )/1000 AS length_km
FROM road_network;

###Output

| road_name | length_km |
| --------- | --------- |
| NH44      | 15.4      |

###Explanation
| Part      | Meaning           |
| --------- | ----------------- |
| ST_Length | Calculates length |
| /1000     | Meter → KM        |

##5.9 ST_Area()
Purpose
Polygon area calculation.

###Query
SELECT
    village_name,
    ST_Area(
        geom::geography
    )/1000000 AS area_sqkm
FROM village_boundary;

###Output
| village_name | area_sqkm |
| ------------ | --------- |
| Village A    | 12.5      |


##5.10 ST_Transform()
Purpose
Changes CRS.

###Query
SELECT
    ST_Transform(
        geom,
        3857
    )
FROM road_network;

###Meaning
4326 → 3857

###Real Use
| CRS  | Use         |
| ---- | ----------- |
| 4326 | GPS         |
| 3857 | Google Maps |


##5.11 ST_DWithin()
Purpose
Find nearby geometries within distance.

###Query
SELECT *
FROM survey_points
WHERE ST_DWithin(
    geom::geography,
    ST_SetSRID(
        ST_MakePoint(77.21,28.61),
        4326
    )::geography,
    100
);

###Meaning
Find points within 100 meters

###Explanation

| Function     | Meaning       |
| ------------ | ------------- |
| ST_MakePoint | Creates point |
| ST_SetSRID   | Assign CRS    |
| ST_DWithin   | Nearby search |


#6. Spatial Indexing

##6.1: Why Spatial Index Needed?

###Without index:

❌ Slow queries

###With index:

✅ Fast spatial search

##6.2 Create Spatial Index

CREATE INDEX idx_road_geom
ON road_network
USING GIST(geom);

###Explanation

| Part | Meaning            |
| ---- | ------------------ |
| GIST | Spatial index type |
| geom | Geometry column    |


#7. GeoJSON API Query

VERY IMPORTANT interview query.

###Query
SELECT json_build_object(
'type', 'FeatureCollection',
'features', json_agg(
    json_build_object(
        'type', 'Feature',
        'geometry',
        ST_AsGeoJSON(geom)::json,
        'properties',
        to_jsonb(t) - 'geom'
    )
))
FROM road_network t;

###Output
{
 "type":"FeatureCollection",
 "features":[]
}

###Explanation


#8. Important Interview Queries
##8.1 Find Nearest Road

SELECT
    road_name
FROM road_network
ORDER BY geom <->
ST_SetSRID(
    ST_MakePoint(77.21,28.61),
    4326
)
LIMIT 1;

###Meaning
Nearest road search


##8.2 Check Geometry Type
SELECT
    GeometryType(geom)
FROM road_network;

###Output
| geometrytype |
| ------------ |
| LINESTRING   |


##8.3 Check SRID
SELECT
    ST_SRID(geom)
FROM road_network;

###Output
| st_srid |
| ------- |
| 4326    |


##8.4 Invalid Geometry Check
SELECT *
FROM village_boundary
WHERE NOT ST_IsValid(geom);

###Meaning
Find corrupted geometry

##8.5 Fix Invalid Geometry
SELECT
    ST_MakeValid(geom)
FROM village_boundary;


#9. Geometry vs Geography

| Geometry   | Geography             |
| ---------- | --------------------- |
| Flat earth | Real earth            |
| Fast       | Accurate              |
| Most used  | Distance calculations |


#10. Complete PostGIS Workflow

CSV / SHP / GeoJSON
        ↓
      QGIS
        ↓
   PostgreSQL
        ↓
     PostGIS
        ↓
 Spatial Queries
        ↓
    GeoServer
        ↓
   WMS / WFS
        ↓
Frontend Map



#11. Most Important Interview Questions

##Q1. What is PostGIS?

Spatial extension of PostgreSQL.

##Q2. What is geometry column?

Spatial column storing coordinates.

##Q3. Difference between geometry and geography?

Geometry is fast and flat. Geography uses earth curvature.

##Q4. What is SRID?

Spatial Reference ID.

##Q5. What does ST_Buffer do?

Creates surrounding area around geometry.

##Q6. What does ST_Intersects do?

Checks overlap between geometries.

##Q7. What is GeoJSON used for?

Frontend web GIS rendering.

##Q8. What spatial index does PostGIS use?

GiST index.

##Q9. What does ST_Transform do?

Changes CRS system.

##Q10. Why use PostGIS?

For storing and querying spatial GIS data.