DROP TABLE Facturas CASCADE CONSTRAINTS;
DROP TABLE Empresas CASCADE CONSTRAINTS;
DROP SEQUENCE Facturas_ID_Seq;


CREATE TABLE Empresas (
    cifEmpresa VARCHAR(255) PRIMARY KEY,
    nombreEmpresa VARCHAR(255),
    dueno VARCHAR(255)
);

CREATE TABLE Facturas (
    ID INT PRIMARY KEY,
    cifEmpresa VARCHAR(255),
    FechaFactura DATE,
    ProductoVendido VARCHAR(255),
    PrecioProducto NUMBER,
    CantidadVendida INT,
    PrecioTotal NUMBER,
    FOREIGN KEY (cifEmpresa) REFERENCES Empresas(cifEmpresa)
);

-- Insertar Empresa 1
INSERT INTO Empresas (cifEmpresa, nombreEmpresa, dueno)
VALUES ('12345678A', 'Empresa A', 'dueno A');

-- Insertar Empresa 2
INSERT INTO Empresas (cifEmpresa, nombreEmpresa, dueno)
VALUES ('12345678B', 'Empresa B', 'dueno B');


-- Insertar Facturas para Empresa 1
INSERT INTO Facturas (ID, cifEmpresa, FechaFactura, ProductoVendido, PrecioProducto, CantidadVendida, PrecioTotal)
VALUES
    (1, '12345678A', TO_DATE('15-01-2023', 'DD-MM-YYYY'), 'Producto 1', 10.5, 3, 31.5);

INSERT INTO Facturas (ID, cifEmpresa, FechaFactura, ProductoVendido, PrecioProducto, CantidadVendida, PrecioTotal)
VALUES
    (2, '12345678A', TO_DATE('20-02-2023', 'DD-MM-YYYY'), 'Producto 2', 15.0, 2, 30.0);

INSERT INTO Facturas (ID, cifEmpresa, FechaFactura, ProductoVendido, PrecioProducto, CantidadVendida, PrecioTotal)
VALUES
    (3, '12345678A', TO_DATE('10-03-2023', 'DD-MM-YYYY'), 'Producto 3', 8.0, 5, 40.0);


-- Insertar Facturas para Empresa 2
INSERT INTO Facturas (ID, cifEmpresa, FechaFactura, ProductoVendido, PrecioProducto, CantidadVendida, PrecioTotal)
VALUES
    (4, '12345678B', TO_DATE('05-01-2023', 'DD-MM-YYYY'), 'Producto 4', 12.0, 4, 48.0);

INSERT INTO Facturas (ID, cifEmpresa, FechaFactura, ProductoVendido, PrecioProducto, CantidadVendida, PrecioTotal)
VALUES
    (5, '12345678B', TO_DATE('15-02-2023', 'DD-MM-YYYY'), 'Producto 5', 7.5, 6, 45.0);

INSERT INTO Facturas (ID, cifEmpresa, FechaFactura, ProductoVendido, PrecioProducto, CantidadVendida, PrecioTotal)
VALUES
    (6, '12345678B', TO_DATE('25-03-2023', 'DD-MM-YYYY'), 'Producto 6', 9.0, 3, 27.0);





CREATE SEQUENCE Facturas_ID_Seq START WITH 7 INCREMENT BY 1;

set linesize 150
-- Define el formato de las columnas
column id format 999
column nombreempresa format a20
column cifempresa format a10
column fechafac format a10
column productovendido format a20
column precioproducto format 999.99
column cantidadvendida format 999
column preciototal format 999.99

-- Ejecuta la consulta
select * from facturas;

-- Define el formato de las columnas
column cifempresa format a10
column nombreempresa format a20
column dueno format a20

-- Ejecuta la consulta
select * from empresas;
