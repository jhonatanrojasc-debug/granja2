-- Datos iniciales
INSERT INTO granja (nombre, ubicacion) VALUES ('Granja El Progreso', 'Colombia') ON CONFLICT DO NOTHING;
INSERT INTO galpon (identificador, capacidad, granja_id) VALUES ('G1', 50, 1) ON CONFLICT DO NOTHING;
INSERT INTO alimento (tipo, cantidad_disponible, fecha_caducidad) VALUES ('Concentrado', 1000, '2026-12-31') ON CONFLICT DO NOTHING;