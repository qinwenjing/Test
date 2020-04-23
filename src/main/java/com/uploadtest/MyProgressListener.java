package com.uploadtest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

import com.uploadtest.vo.FileUploadStatus;

/**
 * 工作原理：
 * 实时显示上传进度的原理是服务器在处理上传文件的同时，将上传进度的信息例如文件总长度、以上传多少、传输速率等写入Session中。客户浏览器利用Ajax技术再新开一个独立的线程从Session
 * 中获取上传进度信息，并实时显示。Ajax技术能够不断刷新页面获取服务器数据。Session课看作是服务器内存，可用于存放少量的客户信息。需要使用commons-fileupload-1.2.1.jar与commons-io
 * -1.4.jar包。
 */

/**
 * 添加监听该监听器后，上传组件 在上传文件的时会不断回调该方法，回传这些数据。利用这些数字，就可以计算出文件上传的进度，用进度条实时显示出来。
 * 因此需要把这些数据保存起来。代码中把数据保存到了一个UploadStatus中。
 */

public class MyProgressListener implements ProgressListener {
    private HttpSession session;

    public MyProgressListener(HttpServletRequest req) {
        session = req.getSession();
        FileUploadStatus status = new FileUploadStatus();
        session.setAttribute("status", status);
    }

    /* pBytesRead  到目前为止读取文件的比特数
     * pContentLength 文件总大小
     * pItems 目前正在读取第几个文件
     */
    public void update(long pBytesRead, long pContentLength, int pItems) {
        // TODO Auto-generated method stub
        FileUploadStatus status = (FileUploadStatus) session.getAttribute("status");
        status.setPBytesRead(pBytesRead);
        status.setPContentLength(pContentLength);
        status.setPItems(pItems);
    }
}
