DROP TABLE IF EXISTS climas;
CREATE TABLE climas (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    fecha DATE,
    nombre_ciudad VARCHAR (100) NOT NULL,
    descripcion_general VARCHAR (400),
    descripcion_dia VARCHAR (400),
    descripcion_noche VARCHAR (400),
    temperatura_maxima  VARCHAR (20),
    temperatura_minima  VARCHAR (20)
);