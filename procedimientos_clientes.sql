CREATE PACKAGE procedimientos_clientes
IS
     PROCEDURE insertar_cliente(cedula IN NUMBER, telefono IN NUMBER, direccion IN VARCHAR2,
        primer_nombre IN VARCHAR2, segundo_nombre IN VARCHAR2, primer_apellido IN VARCHAR2,
        segundo_apellido IN VARCHAR2, empresa IN VARCHAR2, estatus IN VARCHAR2);
    PROCEDURE eliminar_cliente(id_usuario IN NUMBER);
    PROCEDURE editar_cliente(id_usuario IN NUMBER, cedula_usuario IN NUMBER, telefono_usuario IN NUMBER, direccion_usuario IN VARCHAR2,
        primer_nombre_usuario IN VARCHAR2, segundo_nombre_usuario IN VARCHAR2, primer_apellido_usuario IN VARCHAR2,
        segundo_apellido_usuario IN VARCHAR2, empresa_usuario IN VARCHAR2, estatus_usuario IN VARCHAR2);
END;
/
CREATE PACKAGE BODY procedimientos_clientes
IS

    --insertar nueva empresa
    FUNCTION insertar_empresa (nombre_empresa VARCHAR2)
    RETURN VARCHAR2
    IS
        respuesta VARCHAR2(100);
    BEGIN
        INSERT INTO empresas (nombre) VALUES (nombre_empresa);
        respuesta := 'La empresa ' || nombre_empresa || ' se inserto con exito';
        RETURN respuesta;
    EXCEPTION
        --ORA-00001: restricción única (SYSTEM.SYS_C0011233) violada
        WHEN DUP_VAL_ON_INDEX THEN
            respuesta := 'La empresa ' || nombre_empresa || ' ya existe.';
            RETURN respuesta;
    END insertar_empresa;
    
    --eliminar empresa
    FUNCTION eliminar_empresa(id_emp NUMBER) RETURN VARCHAR2
    --eliminar empresa metodo 2(exceptios)
    IS
        integrity_err EXCEPTION;
        PRAGMA EXCEPTION_INIT (integrity_err, -2292);
        contar_e NUMBER;
        respuesta VARCHAR2(100);
    BEGIN
        --eliminar empresa
        SELECT COUNT(*) INTO contar_e FROM empresas WHERE id_empresa = id_emp;
        IF contar_e = 0 THEN
            respuesta := 'la empresa no existe';
            RETURN respuesta;
        ELSE
            DELETE FROM empresas WHERE id_empresa = id_emp;
            respuesta := 'La empresa se elimino de la tabla.';
            RETURN respuesta;
        END IF;
    EXCEPTION
        --ORA-02292: restricción de integridad (SYSTEM.SYS_C0011234) violada - registro secundario encontrado
        WHEN integrity_err THEN
            respuesta := 'no se pudo eliminar la empresa, hay usuario(s) asociados a esta.';
            RETURN respuesta;
    END eliminar_empresa;
    
    --insertar cliente
    FUNCTION insertar_cliente(cedula_usuario NUMBER, telefono NUMBER, direccion VARCHAR2,
        primer_nombre VARCHAR2, segundo_nombre VARCHAR2, primer_apellido VARCHAR2,
        segundo_apellido VARCHAR2, empresa VARCHAR2, estatus VARCHAR2) RETURN VARCHAR2
    IS
        null_err EXCEPTION;
        PRAGMA EXCEPTION_INIT (null_err, -1400);
        id_emp NUMBER;
        respuesta VARCHAR2(100);
        respuesta2 VARCHAR2(100);
    BEGIN
        --insertar empresa
        respuesta2 := insertar_empresa(empresa);
        --insertar dato en tabla clientes
        INSERT INTO clientes (cedula, telefono, direccion, primer_nombre, segundo_nombre,
            primer_apellido, segundo_apellido, empresa, estatus)
        VALUES (cedula_usuario, telefono, direccion, primer_nombre, segundo_nombre, primer_apellido,
            segundo_apellido, empresa, estatus);
        respuesta := respuesta2 || ', ' || 'El usuario se ingreso de forma exitosa';
        RETURN respuesta;
    EXCEPTION
        --ORA-00001: restricción única (SYSTEM.SYS_C0011233) violada
        WHEN DUP_VAL_ON_INDEX THEN
            respuesta := 'ya hay un usuario registrado con el numero de cedula ' || cedula_usuario;
            SELECT id_empresa INTO id_emp FROM empresas WHERE nombre = empresa;
            respuesta2 := eliminar_empresa(id_emp);
            RETURN respuesta2 || ', ' || respuesta;
        WHEN null_err THEN
            --ORA-01400: no se puede realizar una inserción NULL
            respuesta := 'debes completar todos los campos.';
            RETURN respuesta;
    END insertar_cliente;
    
    PROCEDURE insertar_cliente(cedula IN NUMBER, telefono IN NUMBER, direccion IN VARCHAR2,
        primer_nombre IN VARCHAR2, segundo_nombre IN VARCHAR2, primer_apellido IN VARCHAR2,
        segundo_apellido IN VARCHAR2, empresa IN VARCHAR2, estatus IN VARCHAR2)
    AS
        respuesta VARCHAR2(200);
    BEGIN
        respuesta := insertar_cliente(cedula, telefono, direccion,  primer_nombre,
            segundo_nombre, primer_apellido, segundo_apellido, empresa, estatus);
        DBMS_OUTPUT.PUT_LINE(respuesta);
    END insertar_cliente;
    
    --eliminar cliente
    FUNCTION eliminar_cliente(id_usuario NUMBER) RETURN VARCHAR2
    IS
        nombre_empresa clientes.empresa%TYPE;
        id_emp NUMBER;
        respuesta VARCHAR2(250);
        respuesta2 VARCHAR2(250);
    BEGIN
        --extraer nombre empresa
        SELECT empresa INTO nombre_empresa FROM clientes WHERE id_persona = id_usuario;
        --eliminar usuario
        DELETE FROM clientes WHERE id_persona = id_usuario;
        --eliminar empresa
        SELECT id_empresa INTO id_emp FROM empresas WHERE nombre = nombre_empresa;
        respuesta2 := eliminar_empresa(id_emp);
        respuesta := respuesta2 || ', ' || 'El usuario se elimino de forma exitosa';
        RETURN respuesta;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            respuesta := 'el usuario no existe.';
            RETURN respuesta;
    END eliminar_cliente;
    
    PROCEDURE eliminar_cliente(id_usuario IN NUMBER)
    AS
        respuesta VARCHAR2(200);
    BEGIN
        respuesta := eliminar_cliente(id_usuario);
        DBMS_OUTPUT.PUT_LINE(respuesta);
    END eliminar_cliente;
    
    --editar cliente
    FUNCTION editar_cliente(id_usuario NUMBER, cedula_usuario NUMBER, telefono_usuario NUMBER,
        direccion_usuario VARCHAR2, primer_nombre_usuario VARCHAR2, segundo_nombre_usuario VARCHAR2, 
        primer_apellido_usuario VARCHAR2, segundo_apellido_usuario VARCHAR2, empresa_usuario VARCHAR2, estatus_usuario VARCHAR2) 
        RETURN VARCHAR2
    IS
        null_err EXCEPTION;
        PRAGMA EXCEPTION_INIT (null_err, -1407);
        nombre_emp VARCHAR2(100);
        id_e NUMBER;
        respuesta VARCHAR2(250);
        respuesta2 VARCHAR2(100);
        respuesta3 VARCHAR2(100);
    BEGIN
        SELECT empresa INTO nombre_emp FROM clientes WHERE id_persona = id_usuario;
        respuesta2 := insertar_empresa (empresa_usuario);
        --actualizar usuario
        UPDATE clientes
        SET cedula = cedula_usuario, telefono = telefono_usuario, direccion = direccion_usuario,
            primer_nombre = primer_nombre_usuario, segundo_nombre = segundo_nombre_usuario,
            primer_apellido = primer_apellido_usuario, segundo_apellido = segundo_apellido_usuario, empresa = empresa_usuario,
            estatus = estatus_usuario
        WHERE id_persona = id_usuario;
        --eliminar empresa antigua
        SELECT id_empresa INTO id_e FROM empresas WHERE nombre = nombre_emp;
        respuesta3 := eliminar_empresa(id_e);
        respuesta := respuesta2 || ', ' || respuesta3 || ', el usuario se edito de forma exitosa';
        RETURN respuesta;
    EXCEPTION
        --ORA-00001: restricción única (SYSTEM.SYS_C0011233) violada
        WHEN DUP_VAL_ON_INDEX THEN
            respuesta := 'La cedula que estas tratando de ingresar ya la posee otro usuario.';
            RETURN respuesta;
        WHEN NO_DATA_FOUND THEN
            respuesta := 'el id  no tiene un usuario asociado.';
            RETURN respuesta;
        --ORA-01400: no se puede realizar una inserción NULL
        WHEN null_err THEN
            respuesta := 'debes completar todos los campos.';
            RETURN respuesta;
    END editar_cliente;
    
    PROCEDURE editar_cliente(id_usuario IN NUMBER, cedula_usuario IN NUMBER, telefono_usuario IN NUMBER, direccion_usuario IN VARCHAR2,
        primer_nombre_usuario IN VARCHAR2, segundo_nombre_usuario IN VARCHAR2, primer_apellido_usuario IN VARCHAR2,
        segundo_apellido_usuario IN VARCHAR2, empresa_usuario IN VARCHAR2, estatus_usuario IN VARCHAR2)
    AS
        respuesta VARCHAR2(200);
    BEGIN
        respuesta := editar_cliente(id_usuario, cedula_usuario, telefono_usuario, direccion_usuario,  primer_nombre_usuario,
            segundo_nombre_usuario, primer_apellido_usuario, segundo_apellido_usuario, empresa_usuario, estatus_usuario);
        DBMS_OUTPUT.PUT_LINE(respuesta);
    END editar_cliente;
    
END;