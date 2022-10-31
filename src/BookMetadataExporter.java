import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.PrintStream;

public abstract class BookMetadataExporter extends BookCollection {

    @Override
    public void add(Book b) {
        super.add(b);
    }

    public void export(PrintStream stream) {
        // Please implement this method. You may create additional methods as you see fit.
        stream.println(this.getDataString());
    }

    public abstract String getDataString();
}
