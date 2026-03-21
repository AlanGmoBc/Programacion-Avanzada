package persistencia;

import modelo.NewProductos;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ReporteExcel {

    public void generarReporte(List<NewProductos> listaProductos, String nombreArchivo) {
        // 1. Creamos el libro de Excel (.xlsx)
        Workbook libro = new XSSFWorkbook();
        
        // 2. Creamos una hoja en el libro
        Sheet hoja = libro.createSheet("Inventario de Productos");

        // 3. Creamos el encabezado (Fila 0)
        String[] columnas = {"Folio", "Nombre", "Categoría", "Stock", "Precio", "Logística"};
        Row filaEncabezado = hoja.createRow(0);
        
        CellStyle estiloEncabezado = libro.createCellStyle();
        Font fuente = libro.createFont();
        fuente.setBold(true);
        estiloEncabezado.setFont(fuente);

        for (int i = 0; i < columnas.length; i++) {
            Cell celda = filaEncabezado.createCell(i);
            celda.setCellValue(columnas[i]);
            celda.setCellStyle(estiloEncabezado);
        }

        // 4. Llenamos el Excel con los datos de los productos
        int numeroFila = 1;
        for (NewProductos p : listaProductos) {
            Row fila = hoja.createRow(numeroFila++);
            fila.createCell(0).setCellValue(p.getFolio());
            fila.createCell(1).setCellValue(p.getNombre());
            fila.createCell(2).setCellValue(p.getCategoria());
            fila.createCell(3).setCellValue(p.getStock());
            fila.createCell(4).setCellValue(p.getPrecio());
            fila.createCell(5).setCellValue(p.getInformacionLogistica());
        }

        // Ajustar el ancho de las columnas automáticamente
        for (int i = 0; i < columnas.length; i++) {
            hoja.autoSizeColumn(i);
        }

        // 5. Guardamos el archivo
        try (FileOutputStream fileOut = new FileOutputStream(nombreArchivo + ".xlsx")) {
            libro.write(fileOut);
            libro.close();
            System.out.println("¡Reporte de Excel generado: " + nombreArchivo + ".xlsx!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}