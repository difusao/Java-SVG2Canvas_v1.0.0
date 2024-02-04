import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.svg.SVGDocumentLoaderAdapter;
import org.apache.batik.swing.svg.SVGDocumentLoaderEvent;
import org.w3c.dom.svg.SVGDocument;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;

public class SVGViewer extends JFrame {

    private JSVGCanvas canvas;

    public SVGViewer() {
        // Configurações básicas da janela
        setTitle("Visualizador SVG");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Criação do JSVGCanvas
        canvas = new JSVGCanvas();

        // Adiciona um ouvinte para carregamento do documento SVG
        canvas.addSVGDocumentLoaderListener(new SVGDocumentLoaderAdapter() {
            @Override
            public void documentLoadingCompleted(SVGDocumentLoaderEvent e) {
                SVGDocument svgDocument = e.getSVGDocument();
                canvas.setSVGDocument(svgDocument);
            }
        });

        // Adiciona o JSVGCanvas à janela
        getContentPane().add(canvas, BorderLayout.CENTER);

        // Lógica adicional (se necessário)

        // Exibe a janela
        setLocationRelativeTo(null);
        setVisible(true);

        // Carrega o documento SVG
        loadSVGDocument("snoopy.svg");
    }

    private void loadSVGDocument(String filePath) {
        try {
            // Carrega o documento SVG no canvas
            canvas.loadSVGDocument(new File(filePath).toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SVGViewer());
    }
}
