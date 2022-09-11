package utilidades;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataDriven {
    public List<String> obtenerDatosPrueba(String tituloCp) throws IOException {

        ArrayList<String> datos = new ArrayList<String>();
        FileInputStream file;

        file = new FileInputStream("C:\\Users\\Jose.Valdez\\Documents\\TrabajoPOM_Valdez\\src\\test\\resources\\datos\\DataDriven.xlsx");

        //crear un objeto de tipo excel
        XSSFWorkbook excel = new XSSFWorkbook(file);

        int cantidadHojasExcel = excel.getNumberOfSheets();
        //System.out.println("Cantidad de hojas: " + cantidadHojasExcel);

        for (int i = 0; i < cantidadHojasExcel; i++) {
            if (excel.getSheetName(i).equalsIgnoreCase("DatosCP")) {
                XSSFSheet hojaExcel = excel.getSheetAt(i);
                Iterator<Row> filas = hojaExcel.iterator();
                Row primeraFila = filas.next();
                Iterator<Cell> celda = primeraFila.cellIterator();

                int k = 0;
                int columna = 0;

                //identificamos la columna que dice "CasosDePrueba"
                while (celda.hasNext()) {
                    Cell celdaSeleccionada = celda.next();
                    if (celdaSeleccionada.getStringCellValue().equalsIgnoreCase("CasosDePrueba")) {
                        //identificamos la columna
                        columna = k;
                    }
                    k++;
                }

                while (filas.hasNext()) {
                    Row r = filas.next();

                    if (r.getCell(columna).getStringCellValue().equalsIgnoreCase(tituloCp)) {
                        Iterator<Cell> cv = r.cellIterator();

                        while(cv.hasNext()){
                            Cell c = cv.next();
                            //System.out.println(c.getCellType());
                            if(c.getCellType() == CellType.STRING){
                                //System.out.println("string: " + c.getStringCellValue());
                                datos.add(c.getStringCellValue());
                            }else if (c.getCellType() == CellType.NUMERIC){
                                //System.out.println("numeric: "+NumberToTextConverter.toText(c.getNumericCellValue()));
                                datos.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }
                        }
                    }
                }
            }
        }
        return datos;
    }
}
