package utilidad;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modelo.Evaluacion;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GestorArchivos {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<String[]> leerDatosDesdeExcel(String ruta) {
        List<String[]> listaDatos = new ArrayList<>();
        File archivo = new File(ruta);
        
        // Validar que el archivo existe y es un .xlsx antes de intentar abrirlo
        if (!archivo.exists() || !ruta.toLowerCase().endsWith(".xlsx")) {
            return listaDatos;
        }

        try (FileInputStream fis = new FileInputStream(archivo);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) { 
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String grupo = getCellValue(row.getCell(0)); // Col A
                String prof  = getCellValue(row.getCell(1)); // Col B
                String mat   = getCellValue(row.getCell(2)); // Col C
                String alum  = getCellValue(row.getCell(4)); // Col E

                if (!alum.isEmpty()) {
                    listaDatos.add(new String[]{alum, mat, prof, grupo});
                }
            }
        } catch (org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException e) {
            JOptionPane.showMessageDialog(null, "El archivo no es un Excel (.xlsx) válido.\nVerifica que no estés seleccionando un archivo JSON o de texto.");
        } catch (Exception e) {
            System.err.println("ERROR de lectura: " + e.getMessage());
        }
        return listaDatos;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue().trim();
            case NUMERIC: return String.valueOf((int)cell.getNumericCellValue());
            default: return "";
        }
    }

    public static void guardarJSON(Evaluacion eval, String rutaCompleta) {
        try (FileWriter writer = new FileWriter(rutaCompleta)) {
            gson.toJson(eval, writer);
        } catch (IOException e) {
            throw new RuntimeException("Error en JSON: " + e.getMessage());
        }
    }

    public static void generarExcel(Evaluacion eval, String rutaCompleta) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Evaluación Final");
            
            Row r0 = sheet.createRow(0); 
            r0.createCell(0).setCellValue("Asignatura:"); 
            r0.createCell(1).setCellValue(eval.asignatura);
            
            Row r1 = sheet.createRow(1); 
            r1.createCell(0).setCellValue("Profesor:"); 
            r1.createCell(1).setCellValue(eval.profesor);
            
            Row r2 = sheet.createRow(2); 
            r2.createCell(0).setCellValue("Promedio Grupal:"); 
            r2.createCell(1).setCellValue(String.format("%.2f", eval.promedio));
            
            Row rHeader = sheet.createRow(4);
            rHeader.createCell(0).setCellValue("Lista de Alumnos y Promedios:");
            
            int f = 5;
            for (String a : eval.alumnos) {
                sheet.createRow(f++).createCell(0).setCellValue(a);
            }
            
            sheet.autoSizeColumn(0);
            try (FileOutputStream out = new FileOutputStream(rutaCompleta)) {
                workbook.write(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}