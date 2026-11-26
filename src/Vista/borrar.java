/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.io.FileOutputStream;


/**
 *
 * @author crist
 */
public class borrar {
    // desde aqui copiar
    public void crearPDF(JTable tabla){
        
        try {
        
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("Cartola"));
            
            doc.open();
            
            doc.add(new Paragraph("Transacciones:"));
            
            TableModel modelo = tabla.getModel();
            
            PdfPTable pdfTable = new PdfPTable(modelo.getColumnCount());
            
            for (int i = 0; i < modelo.getColumnCount(); i++){
                pdfTable.addCell(modelo.getColumnName(i));
            }
            for (int i = 0; i < modelo.getRowCount(); i++){
                for (int j = 0; j < modelo.getColumnCount(); j++){
                    Object valor = modelo.getValueAt(i, j);
                    
                    if (valor != null) pdfTable.addCell(valor.toString());
                    else pdfTable.addCell("");
                }
            }
            
            doc.add(pdfTable);
            doc.close();
            
            
            
        } catch(Exception e){
            e.printStackTrace();
        }
       
        
    }
    // hasta aqui
}
