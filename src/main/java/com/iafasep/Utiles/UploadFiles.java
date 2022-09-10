package com.iafasep.Utiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author MERCANTIL GROUP SAC
 */
public class UploadFiles {

    public void MesaPartes(String codigo, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes;
            bytes = file.getBytes();
            Path path = Paths.get(Utiles.getServerPath() + "MesaPartes\\" + codigo + "-" + file.getOriginalFilename());
            Files.write(path, bytes);
        }
    }

    public void CertificacionPresupuestal(String codigo, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes;
            bytes = file.getBytes();
            Path path = Paths.get(Utiles.getServerPath() + "Presupuesto\\CertificadoPresupuestal\\" + codigo + "-" + file.getOriginalFilename());
            Files.write(path, bytes);
        }
    }

}
