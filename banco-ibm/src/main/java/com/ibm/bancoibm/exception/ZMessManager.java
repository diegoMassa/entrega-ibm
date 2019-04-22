package com.ibm.bancoibm.exception;

public class ZMessManager extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public final static String ALL = "All ";
    public final static String ENTCHILD = "Tablas Relacioandas(Hijos)";
    public final static String FOREIGNDATA = "Data de Clases Foraneas: ";
    public static String ENTITY_SUCCESFULLYSAVED = "Entidad guardada exitosamente!";
    public static String ENTITY_SUCCESFULLYDELETED = "Entidad eliminada exitosamente!";
    public static String ENTITY_SUCCESFULLYMODIFIED = "Entidad modificada exitosamente!";
    public static String ENTITY_WITHSAMEKEY = "Otra entidad con el mismo id fue encontrada";
    public static String ENTITY_NOENTITYTOUPDATE = "No se encontro la entidad con el tipo de id ";

    public ZMessManager() {
    }

    public ZMessManager(String exception) {
        super(exception);
    }

    public class NotValidFieldException extends ZMessManager {
        private static final long serialVersionUID = 1L;

        public NotValidFieldException(String info) {
            super("El valor para el campo: \"" + info + "\" no es valido");
        }
    }

    public class NullEntityExcepcion extends ZMessManager {
        private static final long serialVersionUID = 1L;

        public NullEntityExcepcion(String info) {
            super("El " + info + " Entity no puede estar vacia o ser nula");
        }
    }

    public class EmptyFieldException extends ZMessManager {
        private static final long serialVersionUID = 1L;

        public EmptyFieldException(String info) {
            super("El valor para el campo: \"" + info + "\" no puede estar vacia o ser nula");
        }
    }

    public class NotValidFormatException extends ZMessManager {
        private static final long serialVersionUID = 1L;

        public NotValidFormatException(String info) {
            super("El formato o el tamano para el campo: \"" + info + "\" no es valido");
        }
    }

    public class DeletingException extends ZMessManager {
        private static final long serialVersionUID = 1L;

        public DeletingException(String info) {
            super("La entidad que esta intentando eliminar " +
                "tiene informacion relacionada, " +
                "por favor antes de intentar de nuevo, " +
                "verifique la data de la entidad, \"" + info + "\"");
        }
    }

    public class ForeignException extends ZMessManager {
        private static final long serialVersionUID = 1L;

        public ForeignException(String info) {
            super("No hay data relacionada con el id \"" + info + "\"");
        }
    }

    public class GettingException extends ZMessManager {
        private static final long serialVersionUID = 1L;

        public GettingException(String info) {
            super("Se encontro una excepcion en el getting " + info);
        }
    }

    public class FindingException extends ZMessManager {
        private static final long serialVersionUID = 1L;

        public FindingException(String info) {
            super("Se encontro una excepcion tratando de encontrar: " + info);
        }
    }
}