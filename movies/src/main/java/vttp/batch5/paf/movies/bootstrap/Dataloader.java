package vttp.batch5.paf.movies.bootstrap;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.springframework.stereotype.Component;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
@Component("dataloader")
public class Dataloader {

  public List<JsonObject> unzipLoader(String filePath) throws IOException {
    ZipFile zipFile = new ZipFile(filePath);
    Enumeration<? extends ZipEntry> entries = zipFile.entries();
    List<JsonObject> jsonList = new ArrayList<>();
    while(entries.hasMoreElements()){
        ZipEntry entry = entries.nextElement();
        InputStream stream = zipFile.getInputStream(entry);
        JsonReader jReader = Json.createReader(stream);
        JsonObject data = jReader.readObject();
        jsonList.add(data);
    }
    zipFile.close();
    return jsonList;

}

}
