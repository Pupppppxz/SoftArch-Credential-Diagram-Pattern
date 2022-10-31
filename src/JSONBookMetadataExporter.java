import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONBookMetadataExporter extends BookMetadataExporter {

    private JSONArray jsonArray;
    private JSONObject rootObject;

    public JSONBookMetadataExporter() {
        initial();
    }

    private void initial() {
        try {
            jsonArray = new JSONArray();
            rootObject = new JSONObject();
            rootObject.put("Books", jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Book b) {
        super.add(b);
        jsonArray.add(this.createChildNode(b));
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
    public String getDataString() {
        return JSONHelper.prettyPrintJSON(rootObject.toJSONString());
    }
}
