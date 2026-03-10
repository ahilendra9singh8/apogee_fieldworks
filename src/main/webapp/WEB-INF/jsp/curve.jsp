<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>OpenStreetMap - Curve Plot</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="https://unpkg.com/leaflet/dist/leaflet.css" />
<script src="https://unpkg.com/leaflet/dist/leaflet.js"></script>

<style>
html, body {
  height: 100%;
  margin: 0;
  padding: 0;
}

#map {
  width: 100%;
  height: 95vh;
}

#controls {
  position: absolute;
  top: 10px;
  left: 20px;
  z-index: 1000;
  background: rgba(255, 255, 255, 0.95);
  padding: 10px 16px;
  border-radius: 6px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
  font-family: Arial, sans-serif;
  font-size: 14px;
  line-height: 1.6em;
}
#controls label {
  display: block;
  margin-left: 8px;
}
</style>
</head>
<body>

<div id="controls">
  <strong>Curve Layers</strong><br>
  <label><input type="checkbox" id="showMarkers"> Show chainage markers (every 100m)</label>
  <label><input type="checkbox" id="showVertices"> Show curve vertices</label>
</div>

<div id="map"></div>

<script>
const map = L.map('map', { renderer: L.canvas() }).setView([26.99, 80.95], 6);
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
  maxZoom: 19,
  attribution: '&copy; OpenStreetMap contributors'
}).addTo(map);

let curveLayerGroup = L.layerGroup().addTo(map);
let vertexLayerGroup = L.layerGroup().addTo(map);
let markerLayerGroup = L.layerGroup().addTo(map);
let allCurvesData = [];

function drawCurves() {
  curveLayerGroup.clearLayers();
  vertexLayerGroup.clearLayers();
  markerLayerGroup.clearLayers();

  const showVertices = document.getElementById('showVertices').checked;
  const showMarkers = document.getElementById('showMarkers').checked;

  const mainColor = '#ff0000';
  const offsetColor = '#0000FF';
  const layers = [];

  allCurvesData.forEach((curve, idx) => {
    const latlngs = curve.map(p => [p.northing, p.easting]);
    const color = idx === 0 ? mainColor : offsetColor;

    // main/offset lines
    const poly = L.polyline(latlngs, {
      color,
      weight: idx === 0 ? 4 : 2,
      opacity: 0.9,
      interactive: false
    }).addTo(curveLayerGroup);
    layers.push(poly);

    // show vertices efficiently
    if (showVertices) {
      const vertexGeoJson = {
        type: "FeatureCollection",
        features: curve.map(p => ({
          type: "Feature",
          geometry: { type: "Point", coordinates: [p.easting, p.northing] },
          properties: { chainage: p.chainage, idx }
        }))
      };

      L.geoJSON(vertexGeoJson, {
        pointToLayer: (feature, latlng) => L.circleMarker(latlng, {
          radius: feature.properties.idx === 0 ? 3 : 2,
          color: "#000",
          fillColor: feature.properties.idx === 0 ? mainColor : offsetColor,
          fillOpacity: 1.0,
          weight: 0.7
        }).bindTooltip(`Ch: ${feature.properties.chainage.toFixed(2)} m`)
      }).addTo(vertexLayerGroup);
    }

    // show chainage markers (every 100m)
    if (showMarkers && idx === 0) {
      for (let i = 0; i < curve.length; i += 10) {
        const p = curve[i];
        L.circleMarker([p.northing, p.easting], {
          radius: 3,
          color: '#000',
          fillColor: '#00ff00',
          fillOpacity: 0.9
        })
        .bindTooltip('Ch: ' + Math.round(p.chainage))
        .addTo(markerLayerGroup);
      }
    }
  });

  const group = L.featureGroup(layers);
  const bounds = group.getBounds();
  if (bounds && bounds.isValid()) {
    map.fitBounds(bounds, { padding: [20, 20] });
  }
}

function loadCurves() {
  fetch('/map/data')
    .then(r => {
      if (!r.ok) throw new Error('Network error ' + r.status);
      return r.json();
    })
    .then(data => {
      allCurvesData = data || [];
      if (allCurvesData.length === 0) {
        alert('No data');
        return;
      }
      drawCurves();
    })
    .catch(err => alert('Error: ' + err.message));
}

// Initial load
loadCurves();

// Toggle handlers
document.getElementById('showMarkers').addEventListener('change', drawCurves);
document.getElementById('showVertices').addEventListener('change', drawCurves);
</script>

</body>
</html>
