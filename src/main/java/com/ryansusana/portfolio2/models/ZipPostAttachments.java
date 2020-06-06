package com.ryansusana.portfolio2.models;

import com.elepy.annotations.Inject;
import com.elepy.exceptions.ElepyException;
import com.elepy.handlers.ActionHandler;
import com.elepy.http.HttpContext;
import com.elepy.models.ModelContext;
import com.elepy.uploads.FileService;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipPostAttachments implements ActionHandler<Post> {

    @Inject
    private FileService files;

    @Override
    public void handle(HttpContext context, ModelContext<Post> modelContext) throws Exception {
        var zipFile = context.recordIds().stream().map(Objects::toString).collect(Collectors.joining("-")).concat(".zip");
        final var fileUploadsId = modelContext.getCrud().getByIds(context.recordIds())
                .stream()
                .map(Post::getAttachments)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        if (fileUploadsId.isEmpty()) {
            throw new ElepyException("No files found", 400);
        }
        ensureFilesOnServer(fileUploadsId);
        context.response().type("application/zip");
        context.response().result(IOUtils.toByteArray(zipFiles(zipFile, fileUploadsId)));
    }

    @SneakyThrows
    private void ensureFilesOnServer(Set<String> ids) {

        final var home = System.getProperty("user.home").concat("/portfolio-attachments/");
        if (!Files.exists(Paths.get(home)))
            Files.createDirectory(Paths.get(home));
        ids.stream()
                .filter(id -> !new File(home + id).exists())
                .map(files::readFile)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(fileUpload -> {
                    final var file = new File(home + "/portfolio-attachments/" + fileUpload.getName());
                    copyInputStreamToFile(fileUpload.getContent(), file);
                });
    }

    @SneakyThrows
    private InputStream zipFiles(String attachment, Set<String> filePaths) {

        final var file = new File(attachment);
        final var home = System.getProperty("user.home").concat("/portfolio-attachments/");
        if (!file.exists()) {
            try (FileOutputStream fos = new FileOutputStream(attachment);
                 ZipOutputStream zos = new ZipOutputStream(fos)) {
                for (String path : filePaths) {
                    String aFile = path.replaceAll("/elepy/uploads/", home);
                    zos.putNextEntry(new ZipEntry(new File(aFile).getName()));

                    byte[] bytes = Files.readAllBytes(Paths.get(aFile));
                    zos.write(bytes, 0, bytes.length);
                    zos.closeEntry();
                }
            }
        }
        return new FileInputStream(attachment);
    }

    @SneakyThrows
    private static void copyInputStreamToFile(InputStream inputStream, File file) {
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            IOUtils.copy(inputStream, outputStream);
        }
    }

}
