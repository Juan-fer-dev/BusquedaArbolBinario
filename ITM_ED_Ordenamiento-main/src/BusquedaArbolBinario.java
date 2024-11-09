import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BusquedaArbolBinario {
    private ArbolBinario arbol;

    public BusquedaArbolBinario(ArbolBinario arbol) {
        this.arbol = arbol;
    }

    public void buscar(JTable tbl, String termino) {
        List<Documento> resultados = new ArrayList<>();
        buscarRecursivo(arbol.getRaiz(), termino, resultados);

        // Crear los datos para la tabla solo con los resultados
        String[][] datos = new String[resultados.size()][Documento.encabezados.length];
        for (int i = 0; i < resultados.size(); i++) {
            datos[i][0] = resultados.get(i).getApellido1();
            datos[i][1] = resultados.get(i).getApellido2();
            datos[i][2] = resultados.get(i).getNombre();
            datos[i][3] = resultados.get(i).getDocumento();
        }
        DefaultTableModel dtm = new DefaultTableModel(datos, Documento.encabezados);
        tbl.setModel(dtm);
    }

    private void buscarRecursivo(Nodo nodo, String termino, List<Documento> resultados) {
        if (nodo == null) {
            return;
        }

        Documento doc = nodo.getDocumento();
        // Verifica si el documento coincide con el término de búsqueda
        if (doc.getNombreCompleto().contains(termino) || doc.getDocumento().contains(termino)) {
            resultados.add(doc);
        }

        // Recorre los nodos izquierdo y derecho del árbol
        buscarRecursivo(nodo.izquierdo, termino, resultados);
        buscarRecursivo(nodo.derecho, termino, resultados);
    }
}
