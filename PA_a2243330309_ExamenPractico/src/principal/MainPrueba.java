package principal;

import modelo.*;
import controlador.NewProductoControlador;
import persistencia.ReporteExcel; // Importamos la clase del reporte
import java.util.ArrayList;
import java.util.List;

public class MainPrueba {
    public static void main(String[] args) {
        // 1. Inicializamos los controladores y la lista para el reporte
        NewProductoControlador controlador = new NewProductoControlador();
        List<NewProductos> listaParaExcel = new ArrayList<>();
        ReporteExcel reporte = new ReporteExcel();

        // 2. Creamos los productos y los procesamos
        
        // --- Abarrotes ---
        Abarrotes p1 = new Abarrotes("AB-100", "Aceite de Oliva", 50, 120.50, "aceite.jpg");
        controlador.guardarProductoComoJSON(p1);
        listaParaExcel.add(p1);

        // --- Lácteos ---
        Lacteos p2 = new Lacteos("LAC-001", "Leche Entera", 30, 25.50, "leche.jpg");
        controlador.guardarProductoComoJSON(p2);
        listaParaExcel.add(p2);

        // --- Bebidas ---
        Bebidas p3 = new Bebidas("BEB-500", "Refresco Cola", 100, 35.00, "cola.jpg");
        controlador.guardarProductoComoJSON(p3);
        listaParaExcel.add(p3);

        // --- Frutas y Verduras ---
        FrutasVerduras p4 = new FrutasVerduras("FYV-020", "Manzana Roja", 200, 45.00, "manzana.jpg");
        controlador.guardarProductoComoJSON(p4);
        listaParaExcel.add(p4);

        // --- Carnes ---
        CarnesPescados p5 = new CarnesPescados("CAR-005", "Pechuga de Pollo", 40, 95.00, "pollo.jpg");
        controlador.guardarProductoComoJSON(p5);
        listaParaExcel.add(p5);

        // --- Limpieza ---
        LimpiezaHogar p6 = new LimpiezaHogar("LIM-001", "Detergente Líquido", 60, 55.00, "detergente.jpg");
        controlador.guardarProductoComoJSON(p6);
        listaParaExcel.add(p6);

        // 3. Generamos el reporte Excel con todos los productos de la lista
        reporte.generarReporte(listaParaExcel, "Reporte_Inventario_General");

        // 4. Mensajes de confirmación
        System.out.println("¡Todos los JSON han sido generados exitosamente!");
        System.out.println("Proceso completo: JSONs y Excel generados.");
    }
}