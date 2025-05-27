package handlers;

import data.Monster;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class YamlImportHandler extends FileImportHandler {
    @Override
    public boolean canHandle(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".yaml") || name.endsWith(".yml");
    }

    @Override
    public List<Monster> handle(File file) throws IOException {
        YAMLMapper mapper = new YAMLMapper();
        List<Monster> monsters = new ArrayList<>();
        if (canHandle(file)) {
            monsters = mapper.readValue(file,
                    mapper.getTypeFactory().constructCollectionType(List.class, Monster.class));
            monsters.forEach(m -> m.setSource("YAML: " + file.getName()));
        } else {
            monsters = super.handle(file);
        }
        return monsters;
    }
}