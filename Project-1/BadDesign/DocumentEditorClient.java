import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

// Think of this like a notebook class
class DocumentEditor {

    // A list to store text and image names
    private List<String> documentElements;

    // A final string to store the full book
    private String renderedDocument;

    // Constructor: Make a new empty notebook
    public DocumentEditor() {
        documentElements = new ArrayList<>();
        renderedDocument = "";
    }

    // Add some text to the book
    public void addText(String text) {
        documentElements.add(text);
    }

    // Add an image name to the book
    public void addImage(String imagePath) {
        documentElements.add(imagePath);
    }

    // Make the full book by joining everything
    public String renderDocument() {
        if (renderedDocument.isEmpty()) {
            StringBuilder result = new StringBuilder();
            for (String element : documentElements) {
                if (element.endsWith(".jpg") || element.endsWith(".png")) {
                    result.append("[Image: ").append(element).append("]\n");
                } else {
                    result.append(element).append("\n");
                }
            }
            renderedDocument = result.toString();
        }
        return renderedDocument;
    }

    // Save the book into a file
    public void saveToFile() {
        try {
            FileWriter writer = new FileWriter("document.txt");
            writer.write(renderDocument());
            writer.close();
            System.out.println("Document saved to document.txt");
        } catch (IOException e) {
            System.out.println("Error: Unable to open file for writing.");
        }
    }
}

// This is where the fun starts!
public class DocumentEditorClient {
    public static void main(String[] args) {
        DocumentEditor editor = new DocumentEditor();  // Make a new notebook
        editor.addText("Hello, world!");               // Add some text
        editor.addImage("picture.jpg");                // Add an image
        editor.addText("This is a document editor.");  // More text

        System.out.println(editor.renderDocument());   // Show the notebook
        editor.saveToFile();                           // Save it
    }
}
