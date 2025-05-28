package handlers;

import data.Monster;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Handler {
    void setNextHandler(Handler handler);
    List<Monster> handle(File request) throws IOException;
}
