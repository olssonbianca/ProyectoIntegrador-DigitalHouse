package com.finalprojectc7t3.backend.service.impl;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;;
import com.finalprojectc7t3.backend.configurations.S3ConfigClient;
import com.finalprojectc7t3.backend.dto.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3ServiceImpl {

    private final S3ConfigClient s3Client;

    @Autowired
    public S3ServiceImpl(S3ConfigClient s3Client) {
        this.s3Client = s3Client;
    }

    public String saveImageInS3(MultipartFile imagen) throws IOException {
        String extensionImagen = StringUtils.getFilenameExtension(imagen.getOriginalFilename());
        String key = String.format("%s.%s", UUID.randomUUID(), extensionImagen);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(imagen.getContentType());

        PutObjectRequest guardarObjetoS3 =
                new PutObjectRequest(s3Client.getNombreBucketS3(), key, imagen.getInputStream(), metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead);

        s3Client.s3Cliente().putObject(guardarObjetoS3);
        return key;
    }

    public Asset obtenerImagen(String key) throws IOException {

        S3Object objetoS3 = s3Client.s3Cliente().getObject(s3Client.getNombreBucketS3(), key);
        ObjectMetadata metadata = objetoS3.getObjectMetadata();

        return Asset.builder()
                .contenido(IOUtils.toByteArray(objetoS3.getObjectContent()))
                .tipoContenido(metadata.getContentType()).
                build();
    }

    public void deleteImageS3(String key) {
        s3Client.s3Cliente().deleteObject(s3Client.getNombreBucketS3() ,key);
    }

    public String obtenerUrlS3(String key) {
        return String.format(s3Client.getUrlAws(), s3Client.getNombreBucketS3(), key);
    }

}
