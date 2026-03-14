package Libreria;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import java.io.File;
import java.util.ArrayList;

public class ArchivoXML {
    private String nombreArchivo;

    public ArchivoXML(String nombre) {
        this.nombreArchivo = nombre + ".xml";
    }

    // Método para guardar una lista de datos en XML
    public void guardar(ArrayList<String[]> datos, String nodoRaiz, String nodoHijo, String[] etiquetas) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            // Elemento raíz (ej: <Categorias>)
            Element root = doc.createElement(nodoRaiz);
            doc.appendChild(root);

            for (String[] fila : datos) {
                // Elemento hijo (ej: <Categoria>)
                Element hijo = doc.createElement(nodoHijo);
                root.appendChild(hijo);

                for (int i = 0; i < etiquetas.length; i++) {
                    Element campo = doc.createElement(etiquetas[i]);
                    campo.appendChild(doc.createTextNode(fila[i]));
                    hijo.appendChild(campo);
                }
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(nombreArchivo));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para cargar datos desde XML
    public ArrayList<String[]> cargar(String nodoHijo, String[] etiquetas) {
        ArrayList<String[]> lista = new ArrayList<>();
        try {
            File f = new File(nombreArchivo);
            if (!f.exists()) return lista;

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(f);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(nodoHijo);

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    String[] fila = new String[etiquetas.length];
                    for (int j = 0; j < etiquetas.length; j++) {
                        fila[j] = e.getElementsByTagName(etiquetas[j]).item(0).getTextContent();
                    }
                    lista.add(fila);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}