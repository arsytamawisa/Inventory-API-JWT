package com.enigma.api.inventory.services;

import com.enigma.api.inventory.entities.AbstractEntity;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileService {

    @Value("${application.data.dir}")
    private String imagePath;


    public void upload(AbstractEntity entity, InputStream inputStream) throws IOException, MimeTypeException {
        File dir = new File(imagePath, entity.getClass().getSimpleName().toLowerCase());
        if (!dir.exists()) dir.mkdirs();

        TikaConfig config   = TikaConfig.getDefaultConfig();
        InputStream stream  = TikaInputStream.get(inputStream);
        MediaType mediaType = config.getMimeRepository().detect(stream, new Metadata());
        MimeType mimeType   = config.getMimeRepository().forName(mediaType.toString());

        File file = new File(dir, entity.getId().toString() + mimeType.getExtension());
        try (OutputStream outputStream = new FileOutputStream(file)) {
            int length;
            byte[] data = new byte[8192];
            while ((length = stream.read(data)) > -1)
                outputStream.write(data, 0, length);
        }
    }


    public void download(AbstractEntity entity, OutputStream outputStream) throws IOException {
        File dir = new File(imagePath, entity.getClass().getSimpleName().toLowerCase());
        File file = new File(dir, entity.getId().toString());

        try (InputStream inputStream = new FileInputStream(file)) {
            int length;
            byte[] data = new byte[8192];
            while ((length = inputStream.read(data)) > -1)
                outputStream.write(data, 0, length);
        }
    }

}
