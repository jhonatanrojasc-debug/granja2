-- Schema Granja Avicola (PostgreSQL)

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