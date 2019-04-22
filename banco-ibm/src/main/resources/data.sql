-- CLIENTES
insert into clientes (nombre, direccion, ciudad, telefono) values('Diego Munoz', 'Cra 23 #33C 75', 'Cali', 4846227);
insert into clientes (nombre, direccion, ciudad, telefono) values('Andres Vargas', 'Clle 13B # 42B 38', 'Cali', 3015905876);
insert into clientes (nombre, direccion, ciudad, telefono) values('Katherine Cifuentes', 'Cra 23A #13B 42', 'Cali', 3217294771);
insert into clientes (nombre, direccion, ciudad, telefono) values('Diego Gomez', 'Vortexbird', 'Cali', 3164824629);
insert into clientes (nombre, direccion, ciudad, telefono) values('Luz Lopez', 'Clle 13B # 42B 38', 'Cali', 1023456789);

--  TARJETAS
insert into tarjetas (clie_id, ccv, numero_tarjeta, tipo_tarjeta) values(1, 499, 998388372261882, 'VISA');
insert into tarjetas (clie_id, ccv, numero_tarjeta, tipo_tarjeta) values(2, 188, 1188910018291288, 'MASTER CARD');
insert into tarjetas (clie_id, ccv, numero_tarjeta, tipo_tarjeta) values(3, 911, 1234432156788765, 'VISA');
insert into tarjetas (clie_id, ccv, numero_tarjeta, tipo_tarjeta) values(4, 100, 0987789065433456, 'MASTER CARD');
insert into tarjetas (clie_id, ccv, numero_tarjeta, tipo_tarjeta) values(4, 888, 8917648389123563, 'VISA');

--  ASESORES
insert into asesores (especialidad, nombre) values('VENTA DE VEHICULOS','CARLOS  OTALVARO');
insert into asesores (especialidad, nombre) values('VENTA DE CASAS','PEDRITO PEREZ');


--  CONSUMOS
insert into transacciones (tarje_id, descripcion, monto, fecha) values(1, 'Compra de casa', 90000000, CURRENT_DATE);
insert into transacciones (tarje_id, descripcion, monto, fecha) values(1, 'Compra de carro', 20000000, CURRENT_DATE);
insert into transacciones (tarje_id, descripcion, monto, fecha) values(2, 'Compra de Perrito', 500000, CURRENT_DATE);
insert into transacciones (tarje_id, descripcion, monto, fecha) values(2, 'Compra de Nevera', 1000000, CURRENT_DATE);
insert into transacciones (tarje_id, descripcion, monto, fecha) values(3, 'Compra de Lavadora', 800000, CURRENT_DATE);
insert into transacciones (tarje_id, descripcion, monto, fecha) values(3, 'Anticipio de Nomina', 9000000, CURRENT_DATE);
insert into transacciones (tarje_id, descripcion, monto, fecha) values(4, 'Compra de Zapatos', 120000, CURRENT_DATE);
insert into transacciones (tarje_id, descripcion, monto, fecha) values(4, 'Compra de Ropa', 500000, CURRENT_DATE);