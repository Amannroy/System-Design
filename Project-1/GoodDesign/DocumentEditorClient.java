package Project-1.GoodDesign;


import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

// Interface for document elements
interface DocumentElement {
    String render();
}

// Text element that holds plain text
class TextElement implements DocumentElement {
    private String text;

    public TextElement(String text) {
        this.text = text;
    }

    @Override
    public String render() {
        return text;
    }
}

// Image element that stores image path
class ImageElement implements DocumentElement {
    private String imagePath;

    public ImageElement(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String render() {
        return "[Image: " + imagePath + "]";
    }
}

// Represents a new line
class NewLineElement implements DocumentElement {
    @Override
    public String render() {
        return "\n";
    }
}

// Represents a tab space
class TabSpaceElement implements DocumentElement {
    @Override
    public String render() {
        return "\t";
    }
}

// Document class holds all elements like text, images, newline, etc.
class Document {
    private List<DocumentElement> documentElements = new ArrayList<>();

    public void addElement(DocumentElement element) {
        documentElements.add(element);
    }

    public String render() {
        StringBuilder result = new StringBuilder();
        for (DocumentElement element : documentElements) {
            result.append(element.render());
        }
        return result.toString();
    }
}

// Interface for saving the document
interface Persistence {
    void save(String data);
}

// Save document to a file
class FileStorage implements Persistence {
    @Override
    public void save(String data) {
        try {
            FileWriter outFile = new FileWriter("document.txt");
            outFile.write(data);
            outFile.close();
            System.out.println("Document saved to document.txt");
        } catch (IOException e) {
            System.out.println("Error: Unable to write to file.");
        }
    }
}

// (Optional) Save document to a database
class DBStorage implements Persistence {
    @Override
    public void save(String data) {
        // Database saving logic would go here
    }
}

// Main document editor class
class DocumentEditor {
    private Document document;
    private Persistence storage;
    private String renderedDocument = "";

    public DocumentEditor(Document document, Persistence storage) {
        this.document = document;
        this.storage = storage;
    }

    public void addText(String text) {
        document.addElement(new TextElement(text));
    }

    public void addImage(String imagePath) {
        document.addElement(new ImageElement(imagePath));
    }

    public void addNewLine() {
        document.addElement(new NewLineElement());
    }

    public void addTabSpace() {
        document.addElement(new TabSpaceElement());
    }

    public String renderDocument() {
        if (renderedDocument.isEmpty()) {
            renderedDocument = document.render();
        }
        return renderedDocument;
    }

    public void saveDocument() {
        storage.save(renderDocument());
    }
}

// Main class that runs the program
public class DocumentEditorClient {
    public static void main(String[] args) {
        Document document = new Document();
        Persistence persistence = new FileStorage();
        DocumentEditor editor = new DocumentEditor(document, persistence);

        editor.addText("Hello, world!");
        editor.addNewLine();
        editor.addText("This is a real-world document editor example.");
        editor.addNewLine();
        editor.addTabSpace();
        editor.addText("Indented text after a tab space.");
        editor.addNewLine();
        editor.addImage("picture.jpg");

        System.out.println(editor.renderDocument());
        editor.saveDocument();
    }
}

