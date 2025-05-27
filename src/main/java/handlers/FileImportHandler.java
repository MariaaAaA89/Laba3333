package handlers;

import data.Monster;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class FileImportHandler implements Handler {
    protected Handler nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract boolean canHandle(File file);

    public List<Monster> handle(File file) throws IOException {
        List<Monster> monsters = new ArrayList<>();
        if (nextHandler != null) {
            monsters = nextHandler.handle(file);
        } else {
            throw new IOException("Подходящего обработчика нет");
        }
        return monsters;
    }
}