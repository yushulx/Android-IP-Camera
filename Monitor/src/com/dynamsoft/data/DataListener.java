package com.dynamsoft.data;

import java.awt.image.BufferedImage;

public interface DataListener {
	public void onDirty(BufferedImage bufferedImage);
}
