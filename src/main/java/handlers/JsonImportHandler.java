package handlers;

import data.Monster;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonImportHandler extends FileImportHandler {
    @Override
    public boolean canHandle(File file) {
        return file.getName().toLowerCase().endsWith(".json");
    }

    @Override
    public List<Monster> handle(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Monster> monsters = new ArrayList<>();
        if (canHandle(file)) {
            monsters = mapper.readValue(file,
                    mapper.getTypeFactory().constructCollectionType(List.class, Monster.class));
            monsters.forEach(m -> m.setSource("JSON: " + file.getName()));
        } else {
            monsters = super.handle(file);
        }
        return monsters;
    }
}