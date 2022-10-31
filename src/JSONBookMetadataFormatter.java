import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.StringWriter;

public class JSONBookMetadataFormatter implements BookMetadataFormatter {

    private JSONArray jsonArray;
    private JSONObject rootObject;

    public JSONBookMetadataFormatter() throws IOException {
        reset();
    }

    @Override
    public BookMetadataFormatter reset() {
        // Please implement this method. You may create additional methods as you see fit.

        try {
            jsonArray = new JSONArray();
            rootObject = new JSONObject();
            rootObject.put("Books", jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this;
    }

    @Override
    public BookMetadataFormatter append(Book b) {
        // Please implement this method. You may create additional methods as you see fit.

        // add to array
        jsonArray.add(this.createChildNode(b));

        return this;
    }

    private JSONObject createChildNode(Book b) {
        JSONObject childObj = new JSONObject();

        // child json object
        childObj.put(Book.Metadata.AUTHORS.toString(), this.createAuthorsNode(b.getAuthors()));
        childObj.put(Book.Metadata.PUBLISHER, b.getPublisher());
        childObj.put(Book.Metadata.TITLE, b.getTitle());
        childObj.put(Book.Metadata.ISBN, b.getISBN());

        return childObj;
    }

    private JSONArray createAuthorsNode(String[] authors) {
        JSONArray jArr = new JSONArray();
        for (String author: authors) {
            jArr.add(author);
        }
        return jArr;
    }

    @Override
    public String getMetadataString() {
        // Please implement this method. You may create additional methods as you see fit.
        return JSONHelper.prettyPrintJSON(rootObject.toJSONString());
    }


}
