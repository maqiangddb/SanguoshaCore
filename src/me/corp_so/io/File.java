package me.corp_so.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class File {

	private String name;

	public File(String fileName) {
		this.name = fileName;
	}

	/**
	 * 这个功能用 scanner 实现将会非常简单
	 * @return
	 */
	public String getContents() {
		// TODO Auto-generated method stub
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(new java.io.File(name));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = null;
		StringBuffer sb = new StringBuffer();
		try {
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

}
