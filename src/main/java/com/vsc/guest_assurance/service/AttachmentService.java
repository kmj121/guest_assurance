package com.vsc.guest_assurance.service;

import com.vsc.guest_assurance.common.ApiException;
import com.vsc.guest_assurance.common.MessageCode;
import com.vsc.guest_assurance.config.Config;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @Description
 * @Author Roge
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/5/5
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class AttachmentService {

    //@Autowired
    //private AttachmentMapper attachmentMapper;

    /**
     * 上传临时附件
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    //public String txUploadTmp(MultipartFile multipartFile) throws IOException {
    //    String filename = "";
    //    //检查图片文件大小
    //    checkFileSize(multipartFile);
    //    //保存文件
    //    filename = saveTmpFile(multipartFile);
    //
    //    //插入附件表
    //    addAttachment(filename, multipartFile);
    //
    //    return filename;
    //}

    /**
     * 创建附件
     *
     * @param filename
     * @param multipartFile
     */
    //private void addAttachment(String filename, MultipartFile multipartFile) {
    //    Attachment attachment = new Attachment();
    //    attachment.setFilename(filename);
    //    attachment.setFilenameOriginal(multipartFile.getOriginalFilename());
    //    attachment.setMime(multipartFile.getContentType());
    //    attachment.setCreateAt(new Date());
    //    attachmentMapper.insert(attachment);
    //}

    /**
     * 清理过期文件
     */
    //public void clearTmpFiles() {
    //    File tmp = new File(Config.attachFolder + Config.tmpFolder);
    //    File[] files = tmp.listFiles();
    //    for (File file : files) {
    //        if (file.lastModified() < System.currentTimeMillis() - Constant.ONE_DAY_IN_SECOND) {
    //            file.delete();
    //        }
    //    }
    //}

    /**
     * 查询附件
     *
     * @param filename
     * @return
     */
    //public Attachment getAttachment(String filename) {
    //    return attachmentMapper.selectByFilename(filename);
    //}

    /**
     * 保存文件
     *
     * @param multipartFile
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    //private String saveTmpFile(MultipartFile multipartFile) throws IllegalStateException, IOException {
    //    String filenameOriginal = multipartFile.getOriginalFilename();
    //    String filename = Config.tmpFolder + genFileName(filenameOriginal);
    //
    //    File target = getFile(filename);
    //
    //    multipartFile.transferTo(target);
    //
    //    return filename;
    //}

    /**
     * 保存设备，学校，班级导入文件
     *
     * @param multipartFile
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    //public String saveImportFile(MultipartFile multipartFile, String type) throws IllegalStateException, IOException {
    //    String filenameOriginal = multipartFile.getOriginalFilename();
    //    String filename = null;
    //    if (Constant.TYPE_EQUIPMENT.equals(type)) {
    //        filename = Config.importEquipmentFolder + genFileName(filenameOriginal);
    //    }
    //    if (Constant.TYPE_SCHOOL.equals(type)) {
    //        filename = Config.importSchoolFolder + genFileName(filenameOriginal);
    //    }
    //    if (Constant.TYPE_CLASS.equals(type)) {
    //        filename = Config.importClassFolder + genFileName(filenameOriginal);
    //    }
    //
    //    File target = getFile(filename);
    //
    //    multipartFile.transferTo(target);
    //
    //    return filename;
    //}

    /**
     * 保存临时文件到指定目录
     *
     * @param filename
     * @param destFold
     * @return
     */
    //public String moveTmpFile(String filename, String destFold) {
    //    if (StringUtils.isEmpty(filename)
    //            || !filename.startsWith(Config.tmpFolder)) {
    //        return filename;
    //    }
    //
    //    // 创建目录
    //    new File(Config.attachFolder + destFold).mkdirs();
    //
    //    String regularFilename = destFold + filename.replace(Config.tmpFolder, "");
    //    new File(Config.attachFolder + filename).renameTo(new File(Config.attachFolder + regularFilename));
    //
    //    Attachment attachment = getAttachment(filename);
    //    if (attachment != null) {
    //        attachment.setFilename(regularFilename);
    //        attachmentMapper.update(attachment);
    //    }
    //
    //    return regularFilename;
    //}

    /**
     * 根据文件扩展名生成文件名
     *
     * @param extension
     * @return
     */
    public String genFileNameByExt(String extension) {
        return UUID.randomUUID().toString() + extension;
    }

    /**
     * 根据原文件名生成文件名
     *
     * @param filenameOriginal
     * @return
     */
    private String genFileName(String filenameOriginal) {
        String extension = filenameOriginal.substring(filenameOriginal.lastIndexOf("."));
        return genFileNameByExt(extension);
    }

    /**
     * 根据文件名和目录创建该文件
     *
     * @param filename
     * @return
     */
    public File getFile(String filename) {
        File file = new File(Config.attachFolder + filename);
        file.getParentFile().mkdir();
        return file;
    }

    /**
     * 保存附件
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    //private String saveFile(MultipartFile multipartFile) throws IOException {
    //    String filenameOriginal = multipartFile.getOriginalFilename();
    //    String filename = genFileName(filenameOriginal);
    //
    //    File target = getFile(filename);
    //
    //    //将上传的附件信息填充到目标文件中
    //    multipartFile.transferTo(target);
    //
    //    return filename;
    //}

    /**
     * 检查文件大小
     * @param multipartFile
     */
    //public void checkFileSize(MultipartFile multipartFile) throws IOException {
    //    //图片文件类型
    //    String suffixList = "jpg,png,ico,bmp,jpeg";
    //    // 获取文件后缀
    //    String fileName = multipartFile.getOriginalFilename();
    //    String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
    //
    //    if (suffixList.contains(suffix.trim().toLowerCase())) {
    //        long length = multipartFile.getSize();
    //        if (length > Constant.IMG_FILE_LIMIT_SIZE) {
    //            throw new ApiException(MessageCode.CODE_IMG_FILE_SIZE_ERROR);
    //        }
    //    }
    //}


}
