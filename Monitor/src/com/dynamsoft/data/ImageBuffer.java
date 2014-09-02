package com.dynamsoft.data;

import java.io.ByteArrayOutputStream;
import java.util.LinkedList;

public class ImageBuffer{

    private int mTotalLength = 0;
    private final int mFrameLength;
    private ByteArrayOutputStream mByteArrayOutputStream;
    
    public ImageBuffer(int frameLength, int width, int height) {
        mByteArrayOutputStream = new ByteArrayOutputStream();
        mFrameLength = frameLength;
    }
    
    public int fillBuffer(byte[] data, int off, int len, LinkedList<byte[]> YUVQueue) {
        mTotalLength += len;
        mByteArrayOutputStream.write(data, off, len);
        
        if (mTotalLength == mFrameLength) {
            
            synchronized (YUVQueue) {
            	YUVQueue.add(mByteArrayOutputStream.toByteArray());
            	mByteArrayOutputStream.reset();
            }
            
            mTotalLength = 0;         
            System.out.println("received file");
        }
        
        return 0;
    }
}
