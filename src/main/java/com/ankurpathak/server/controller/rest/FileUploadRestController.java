package com.ankurpathak.server.controller.rest;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.ankurpathak.server.controller.exception.ServiceException;
import com.ankurpathak.server.controller.exception.ServiceRuntimeException;
import com.ankurpathak.server.controller.exception.ValidationException;
import com.ankurpathak.server.dto.FileDto;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by ankur on 12-04-2017.
 */
@RestController
@RequestMapping("/api/v2")
public class FileUploadRestController {

    // public static final Path images = Paths.get(System.getProperty("user.dir") ,"images");

    @Autowired
    private AmazonS3 s3;

    static final List<String> docMimes = Arrays.asList(
            "application/pdf",
            "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "application/vnd.ms-powerpointtd",
            "application/vnd.openxmlformats-officedocument.presentationml.presentation",
            "application/vnd.ms-excel",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
    );


    static final List<String> imageMimes = Arrays.asList(
            "image/jpeg",
            "image/png"
    );

    @PostMapping("/upload")
    public List<String> uploadResources(@ModelAttribute("fileDto") @Valid FileDto fileDto, BindingResult result) {

        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        MultipartFile file = fileDto.getFile();


        String fileName = file.getOriginalFilename();
        String type = file.getContentType();
        List<String> urls = Collections.emptyList();


        if (type != null) {

            if (!docMimes.contains(type) && !imageMimes.contains(type)) {
                result.rejectValue("file", "NotSupported.fileDto.file", new Object[]{type}, "NotSupported.FileDto.file");
                throw new ValidationException(result);
            }


            try {
                // s3.headBucket(new HeadBucketRequest(filesDto.getBucket()));
                boolean flag = s3.doesObjectExist(fileDto.getBucket(), fileDto.getDirectory() + "/");
                if (!flag) {
                    throw new ServiceException("S3 Bucket or Directory does not exist.");
                }
            } catch (AmazonClientException ex) {
                throw new ServiceRuntimeException("Problem in uploading file to S3.");
            }


            try {

                PutObjectRequest putObjectRequest = new PutObjectRequest(fileDto.getBucket(), fileDto.getDirectory() + "/" + fileName, file.getInputStream(), new ObjectMetadata());
                putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
                PutObjectResult putObjectResult = s3.putObject(putObjectRequest);
                return Lists.newArrayList(((AmazonS3Client) s3).getResourceUrl(fileDto.getBucket(), fileDto.getDirectory() + "/" + fileName));
            } catch (AmazonClientException | IOException ex) {
                throw new ServiceRuntimeException("Problem in uploading file to S3.");
            }


        } else {
            result.rejectValue("file", "Fake.fileDto.file", new Object[]{}, "Fake.FileDto.file");
            throw new ValidationException(result);
        }


    }


}