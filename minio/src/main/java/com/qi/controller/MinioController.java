package com.qi.controller;

import com.qi.entriy.Result;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2022/3/14 14:49
 */
@RestController
public class MinioController {
    private static final String MINIO_BUCKET = "mybucketdemo";
    @Autowired
    public MinioClient minioClient;
    @Autowired
    Result result;

    /**
     * 测试
     *
     * @return
     */
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    /**
     * 获取桶中文件列表
     *
     * @return
     * @throws ServerException
     * @throws InsufficientDataException
     * @throws ErrorResponseException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws InvalidResponseException
     * @throws XmlParserException
     * @throws InternalException
     */
    @GetMapping("/list")
    public ArrayList<String> list() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Iterable<io.minio.Result<Item>> listObjects = minioClient.listObjects(ListObjectsArgs.builder().bucket(MINIO_BUCKET).build());
        Iterator<io.minio.Result<Item>> iterator = listObjects.iterator();
        ArrayList<String> itemList = new ArrayList<>();
        while (iterator.hasNext()) {
            Item item = iterator.next().get();
            itemList.add(item.objectName());
        }
        return itemList;
    }

    /**
     * 上传多文件至Minio
     *
     * @param multipartFiles
     * @return
     * @throws ServerException
     * @throws InsufficientDataException
     * @throws ErrorResponseException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws InvalidResponseException
     * @throws XmlParserException
     * @throws InternalException
     */
    @PostMapping("/upload")
    public String upload(@RequestParam(name = "file") MultipartFile[] multipartFiles) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        if (multipartFiles == null || multipartFiles.length == 0) {
            return "上传文件不能为空";
        }

        boolean found =
                minioClient.bucketExists(BucketExistsArgs.builder().bucket(MINIO_BUCKET).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(MINIO_BUCKET).build());
        }

        for (MultipartFile file : multipartFiles) {
            //遍历文件数组
            String originalFilename = file.getOriginalFilename();
            try (InputStream in = file.getInputStream()) {
                minioClient.putObject(PutObjectArgs.builder().bucket(MINIO_BUCKET).object(originalFilename).stream(in, in.available(), -1).build());
            }

//            minioClient.uploadObject(UploadObjectArgs.builder()
//                    .bucket(MINIO_BUCKET)
//                    .object(originalFilename)
//                    //Win 上传成功
////                    .filename("C:\\Users\\Administrator\\Desktop\\JVM.png")
//                    //mac 因为权限问题 无法访问磁盘
//                    .filename("/Users/jiaqi.zhang/Downloads/dahai.jpeg")
//                    .build());
        }
        return "上传成功";
    }

    /**
     * 从桶中下载文件
     *
     * @return
     */
    @PostMapping("/download")
    public String download(String fileName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        //TODO 有问题
        minioClient.downloadObject(DownloadObjectArgs.builder().bucket(MINIO_BUCKET).filename("/Users/jiaqi.zhang/Desktop/upload/" + fileName).object(fileName).build());
        return "下载成功";
    }

    /**
     * 删除单个文件
     *
     * @param fileName
     * @return
     * @throws ServerException
     * @throws InsufficientDataException
     * @throws ErrorResponseException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws InvalidResponseException
     * @throws XmlParserException
     * @throws InternalException
     */
    @RequestMapping("/delete")
    public String delete(String fileName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(MINIO_BUCKET).object(fileName).build());
        return "删除成功";
    }
}
