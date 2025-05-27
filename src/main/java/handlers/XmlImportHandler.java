package handlers;

import data.Monster;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlImportHandler extends FileImportHandler {
    @Override
    public boolean canHandle(File file) {
        return file.getName().toLowerCase().endsWith(".xml");
    }

    @Override
    public List<Monster> handle(File file) throws IOException {
        XmlMapper mapper = new XmlMapper();
        List<Monster> monsters = new ArrayList<>();
        if (canHandle(file)) {
            monsters = mapper.readValue(file,
                    mapper.getTypeFactory().constructCollectionType(List.class, Monster.class));
            monsters.forEach(m -> m.setSource("XML: " + file.getName()));
        } else {
            monsters = super.handle(file);
        }
        return monsters;
    }
}