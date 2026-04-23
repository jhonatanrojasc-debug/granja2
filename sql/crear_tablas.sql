-- ==========================================
-- Script para crear base de datos y tablas
-- Granja Avicola - PostgreSQL
-- ==========================================

-- Crear base de datos (ejecutar como superusuario)
-- CREATE DATABASE granja_avicola;

-- Conectar a la base de datos
-- \c granja_avicola;

-- Tabla Granja
CREATE TABLE IF NOT EXISTS granja (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    ubicacion VARCHAR(200),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla Galpon
CREATE TABLE IF NOT EXISTS galpon (
    id SERIAL PRIMARY KEY,
    identificador VARCHAR(20) NOT NULL UNIQUE,
    capacidad INTEGER NOT NULL CHECK (capacidad > 0),
    granja_id INTEGER REFERENCES granja(id) ON DELETE CASCADE
);

-- Tabla Ave
CREATE TABLE IF NOT EXISTS ave (
    id SERIAL PRIMARY KEY,
    identificador VARCHAR(20) NOT NULL UNIQUE,
    raza VARCHAR(50) NOT NULL,
    edad INTEGER CHECK (edad >= 0),
    peso DECIMAL(5,2) CHECK (peso > 0),
    estado_salud VARCHAR(30) DEFAULT 'Saludable',
    galpon_id INTEGER REFERENCES galpon(id) ON DELETE SET NULL
);

-- Tabla Alimento
CREATE TABLE IF NOT EXISTS alimento (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    cantidad_disponible INTEGER NOT NULL CHECK (cantidad_disponible >= 0),
    fecha_caducidad DATE
);

-- Tabla Empleado
CREATE TABLE IF NOT EXISTS empleado (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    turno VARCHAR(20),
    granja_id INTEGER REFERENCES granja(id) ON DELETE CASCADE
);

-- Tabla Produccion
CREATE TABLE IF NOT EXISTS produccion (
    id SERIAL PRIMARY KEY,
    fecha DATE NOT NULL,
    cantidad_huevos INTEGER NOT NULL CHECK (cantidad_huevos >= 0),
    galpon_id INTEGER REFERENCES galpon(id) ON DELETE CASCADE
);

-- Tabla ConsumoAlimento
CREATE TABLE IF NOT EXISTS consumo_alimento (
    id SERIAL PRIMARY KEY,
    ave_id INTEGER REFERENCES ave(id) ON DELETE CASCADE,
    alimento_id INTEGER REFERENCES alimento(id) ON DELETE CASCADE,
    cantidad INTEGER NOT NULL CHECK (cantidad > 0),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ==========================================
-- Datos de prueba (opcional)
-- ==========================================

INSERT INTO granja (nombre, ubicacion) VALUES
    ('Granja Central', 'Cali, Valle del Cauca'),
    ('Granja Norte', 'Bogota, Cundinamarca');

INSERT INTO galpon (identificador, capacidad, granja_id) VALUES
    ('GAL-001', 100, 1),
    ('GAL-002', 150, 1),
    ('GAL-003', 200, 2);

INSERT INTO alimento (tipo, cantidad_disponible, fecha_caducidad) VALUES
    ('Concentrado Pollo', 500, '2026-12-31'),
    ('Maiz Molido', 1000, '2026-06-30');

INSERT INTO empleado (nombre, cargo, turno, granja_id) VALUES
    ('Juan Perez', 'Cuidador', 'Mañana', 1),
    ('Maria Lopez', 'Veterinario', 'Tarde', 1);

INSERT INTO ave (identificador, raza, edad, peso, estado_salud, galpon_id) VALUES
    ('AVE-001', 'Pollito Blanco', 4, 2.5, 'Saludable', 1),
    ('AVE-002', 'Ross', 3, 3.0, 'Saludable', 1);

INSERT INTO produccion (fecha, cantidad_huevos, galpon_id) VALUES
    ('2026-04-22', 80, 1),
    ('2026-04-21', 95, 1);

-- Verificar tablas creadas
SELECT table_name FROM information_schema.tables WHERE table_schema = 'public';