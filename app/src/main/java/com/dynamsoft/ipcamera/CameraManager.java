package com.dynamsoft.ipcamera;

import android.content.Context;
import android.hardware.Camera;
import android.widget.Toast;

public class CameraManager {
	private Camera mCamera;
	private Context mContext;

	
	public CameraManager(Context context) {
		mContext = context;
		// Create an instance of Camera
        mCamera = getCameraInstance();
	}

	public Camera getCamera() {
		return mCamera;
	}

	private void releaseCamera() {
		if (mCamera != null) {
			mCamera.release(); // release the camera for other applications
			mCamera = null;
		}
	}
	
	public void onPause() {
		releaseCamera();
	}
	
	public void onResume() {
		if (mCamera == null) {
			mCamera = getCameraInstance();
		}
		
		Toast.makeText(mContext, "preview size = " + mCamera.getParameters().getPreviewSize().width + 
				", " + mCamera.getParameters().getPreviewSize().height, Toast.LENGTH_LONG).show(); 
	}
	
	/** A safe way to get an instance of the Camera object. */
	private static Camera getCameraInstance(){
	    Camera c = null;
	    try {
	        c = Camera.open(); // attempt to get a Camera instance
	    }
	    catch (Exception e){
	        // Camera is not available (in use or does not exist)
	    }
	    return c; // returns null if camera is unavailable
	}
	
}
