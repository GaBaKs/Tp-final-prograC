package segundaEntrega.persistencia.DAOAsociadoYDTO;

import java.util.ArrayList;

public interface IDAOAsociado {
    void update(DTOAsociado dto) throws Exception;
    ArrayList<DTOAsociado> getListaAsociados() throws Exception;
    void bajaAsociado(DTOAsociado dto) throws Exception;
    void altaAsociado(DTOAsociado dto) throws Exception;
    void inicializarDB() throws Exception;
    void guardarTodo(ArrayList<DTOAsociado> lista) throws Exception;

}
