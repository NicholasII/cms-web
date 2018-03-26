package com.sun.cms.web.backup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class TestCmd {

	@Test
	public void testBackUp(){
		try {
			String cmd = "cmd /c mysqldump -uroot -p123 cms";
			Process process = Runtime.getRuntime().exec(cmd);
			InputStream is = process.getInputStream();
			FileOutputStream os = new FileOutputStream(new File("E:\\cmd.sql"));
			IOUtils.copy(is, os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
