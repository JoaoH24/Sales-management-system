IF NOT EXISTS(SELECT * FROM sys.databases WHERE name = 'Gestion_ventas')
CREATE DATABASE Gestion_ventas;
GO



USE Gestion_ventas;

CREATE TABLE Empleado (
    empleado_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    direccion VARCHAR(255),
    fecha_contratacion DATE,
    posicion VARCHAR(100),
    salario DECIMAL(10, 2),
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);
GO

CREATE TABLE Cliente (
    cliente_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    direccion VARCHAR(255),
    fecha_registro DATE NOT NULL
);
GO

CREATE TABLE CategoriaProducto (
    categoria_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT
);
GO

CREATE TABLE Producto (
    producto_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL,
    categoria_id INT,
    FOREIGN KEY (categoria_id) REFERENCES CategoriaProducto(categoria_id)
);
GO

CREATE TABLE Pedido (
    pedido_id INT IDENTITY(1,1) PRIMARY KEY,
    cliente_id INT,
    empleado_id INT,
    fecha_pedido DATE NOT NULL,
    estado VARCHAR(50) NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES Cliente(cliente_id),
    FOREIGN KEY (empleado_id) REFERENCES Empleado(empleado_id)
);
GO

CREATE TABLE DetallePedido (
    detalle_pedido_id INT IDENTITY(1,1) PRIMARY KEY,
    pedido_id INT,
    producto_id INT,
    cantidad INT NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES Pedido(pedido_id),
    FOREIGN KEY (producto_id) REFERENCES Producto(producto_id)
);
GO
-- empleado admin
INSERT INTO Empleado (nombre, apellido, correo, telefono, direccion, fecha_contratacion, posicion, salario,username, password) 
VALUES ('Joao', 'HF', 'joao@admin.com', '999 888 777', 'Residencial Los Bosque del Paraiso','1900-01-01', 'SysAdmin', 8000, 'admin','admin');

-- clientes random
INSERT INTO Cliente (nombre, apellido, correo, telefono, direccion, fecha_registro)
VALUES ('Juan', 'Pérez', 'juan.perez@example.com', '555-1234', 'Calle Falsa 123', GETDATE());

INSERT INTO Cliente (nombre, apellido, correo, telefono, direccion, fecha_registro)
VALUES ('María', 'González', 'maria.gonzalez@example.com', '555-5678', 'Avenida Siempre Viva 742', GETDATE());

-- categorias producto
INSERT INTO CategoriaProducto (nombre, descripcion)
VALUES ('Electrónica', 'Productos electrónicos como televisores, teléfonos, computadoras, etc.');

INSERT INTO CategoriaProducto (nombre, descripcion)
VALUES ('Ropa', 'Prendas de vestir para hombres, mujeres y niños.');

INSERT INTO CategoriaProducto (nombre, descripcion)
VALUES ('Alimentos', 'Productos alimenticios y bebidas.');

INSERT INTO CategoriaProducto (nombre, descripcion)
VALUES ('Muebles', 'Mobiliario para el hogar y la oficina.');