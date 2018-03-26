package com.sun.cms.web.backup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

/**
 * 网站备份打包和压缩的测试类
 * @author dongq
 * 一、备份
 *	1、现将upload目录下所有文件打tar包
 *	2、将tar包压缩成tar.gz	
 *	3、sumba上传
 * 二、恢复
 * 	1、将压缩文件gz解压缩tar打包文件
 * 	2、tar文件恢复为upload
 */
public class TestTarAndGzip {
	
	/**
	 * 单个文件的打包
	 * ###java i/o不能访问文件夹
	 */
	@Test
	public void testSimpleTar(){
		try {
			String path = "E:\\upload\\thumbnail";
			String target = "E:\\test.tar";
			TarArchiveOutputStream taros = new TarArchiveOutputStream(new FileOutputStream(target)); 		
			File file = new File(path);
			File[] files = file.listFiles();
			for (File f : files) {
				FileInputStream fileis = new FileInputStream(f);;
				String p = f.getParentFile().getParent();
				String pp = f.getParentFile().getAbsolutePath().substring(p.length()+1);
				TarArchiveEntry entry = new TarArchiveEntry(pp+File.separator+f.getName());
				entry.setSize(f.length());
				taros.putArchiveEntry(entry);
				IOUtils.copy(fileis, taros);
				taros.closeArchiveEntry();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 单个文件
	 */
	@Test
	public void testSimpleUnTar(){
		try {
			String source = "E:\\test.tar";
			String target = "F:\\upload";
			TarArchiveInputStream taris = new TarArchiveInputStream(new FileInputStream(source)); 	
			TarArchiveEntry entry=null;
			while ((entry = taris.getNextTarEntry()) != null) {
				String path = target + File.separator +entry.getName();
				File file = new File(path);
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				FileOutputStream fileos = new FileOutputStream(file);
				IOUtils.copy(taris, fileos);	
				fileos.close();			
			}
			taris.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testTarFile() {
		String path = "E:\\upload";
		String target = "E:\\test2.tar";
		tarFile(path, target);
	}
	/**
	 * 打包压缩
	 * @param source 源目录
	 * @param target 目标目录
	 */
	private void tarFile(String source,String target){
		try {
			TarArchiveOutputStream taros = new TarArchiveOutputStream(new FileOutputStream(target)); 		
			tarFile(new File(source), taros, source.length()); 
			gzipFile(new File(target));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 解压缩文件
	 */
	@Test
	public void testUnTarFile() {
		String target = "F:\\upload";
		String source = "E:\\test2.tar.gz";
		unTarFile(source, target);
	}
	
	private void unTarFile(String source, String target) {
		File source_file = new File(source);
		int length = source_file.getAbsolutePath().length();
		String resultPath = source_file.getAbsolutePath().substring(0, length - 3);
		unGzipFile(source_file, resultPath);
		unTarFile(source, target, resultPath);
	}
	
	private void unTarFile(String source,String target,String resultPath){
		TarArchiveInputStream taris = null;
		try {
			taris = new TarArchiveInputStream(new FileInputStream(resultPath)); 
			TarArchiveEntry entry = null;		
			while ((entry=taris.getNextTarEntry())!=null) {
				String path  = target + File.separator +entry.getName();
				File file = new File(path);
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				FileOutputStream fileos = new FileOutputStream(file);
				IOUtils.copy(taris, fileos);
				fileos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (taris!=null) {
				try {
					taris.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private void gzipFile(File file){
		FileInputStream fileis = null;
		GZIPOutputStream gzipos = null;
		try {
		    fileis = new FileInputStream(file);
			gzipos = new GZIPOutputStream(new FileOutputStream(file.getAbsolutePath()+".gz"));
			IOUtils.copy(fileis, gzipos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (gzipos!=null) {
				try {
					gzipos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fileis!=null) {
				try {
					fileis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private void unGzipFile(File file,String target){
		FileOutputStream fileos = null;
		GZIPInputStream gzipis = null;
		try {
		    fileos = new FileOutputStream(target);
			gzipis = new GZIPInputStream(new FileInputStream(file));
			IOUtils.copy(gzipis, fileos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (fileos!=null) {
				try {
					fileos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (gzipis!=null) {
				try {
					gzipis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	@Test
	public void testGzip(){
		FileInputStream fileis = null;
		GZIPOutputStream gzipos = null;
		try {
			String target = "E:\\test.tar.gz";
			String source = "E:\\test.tar";
			File file = new File(source);
		    fileis = new FileInputStream(file);
			gzipos = new GZIPOutputStream(new FileOutputStream(target));
			IOUtils.copy(fileis, gzipos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (gzipos!=null) {
				try {
					gzipos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fileis!=null) {
				try {
					fileis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	@Test
	public void testUnGzip(){
		FileOutputStream fileos = null;
		GZIPInputStream gzipis = null;
		try {
			String source = "E:\\test.tar.gz";
			String target = "E:\\test.tar";
		    fileos = new FileOutputStream(target);
			gzipis = new GZIPInputStream(new FileInputStream(source));
			IOUtils.copy(gzipis, fileos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (fileos!=null) {
				try {
					fileos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (gzipis!=null) {
				try {
					gzipis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private void tarFile(File f,TarArchiveOutputStream os,int pos){
		if (f.isDirectory()) {
			File[]files = f.listFiles();
			for (File file : files) {
				tarFile(file, os, pos);
			}			
		}else {
			FileInputStream fileis=null;
			try {
			    fileis = new FileInputStream(f);				
				TarArchiveEntry entry = new TarArchiveEntry(f.getParent().substring(pos)+File.separator+f.getName());
				entry.setSize(f.length());
				os.putArchiveEntry(entry);
				IOUtils.copy(fileis, os);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					fileis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					os.closeArchiveEntry();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
