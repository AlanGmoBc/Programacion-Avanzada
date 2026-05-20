package model;

import java.io.*;
import java.util.*;

public class ProductoDAO {

    String archivo = "productos.txt";

    public void add(Producto p) throws Exception {

        FileWriter writer = new FileWriter(archivo, true);
        writer.write(p.c + "|" + p.n + "|" + p.p + "\n");
        writer.close();
    }

    public List<String> all() throws Exception {

        List<String> lista = new ArrayList<>();

        File file = new File(archivo);
        if (!file.exists()) return lista;

        BufferedReader br = new BufferedReader(new FileReader(file));

        String linea;
        while ((linea = br.readLine()) != null) {
            lista.add(linea);
        }

        br.close();

        return lista;
    }
}
